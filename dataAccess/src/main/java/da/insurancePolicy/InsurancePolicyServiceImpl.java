package da.insurancePolicy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.aspectj.weaver.PoliceExtensionUse;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import da.insurancePolicyWithPrice.InsurancePolicyWithPrice;
import da.person.Person;
import da.priceList.PriceList;
import da.priceList.PriceListService;
import da.priceListItem.PriceListItem;
import da.priceListItem.PriceListItemRepository;
import model.request.InsurancePolicyRequest;
import model.request.PersonRequest;

@Service
@Transactional
public class InsurancePolicyServiceImpl implements InsurancePolicyService{

	@Autowired
	private InsurancePolicyRepository repository;

	@Autowired
	private PriceListItemRepository priceListItemRepo;

	@Autowired
	private PriceListService priceListService;

	@Override
	public List<InsurancePolicy> findAll() {
		// TODO Auto-generated method stub
		return (List<InsurancePolicy>) repository.findAll();
	}

	@Override
	public InsurancePolicy findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public InsurancePolicy save(InsurancePolicy insurancePolicy) {
		// TODO Auto-generated method stub
		return repository.save(insurancePolicy);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Double calculatePolice(InsurancePolicyRequest insurencePolicy) {
		double retVal = 0;
		
		PriceList last = priceListService.findCurrent();
		/*ide na repo*/
		List<PriceListItem> list = (ArrayList<PriceListItem>)priceListItemRepo.findAll();
		List<PriceListItem> usableList = new ArrayList<>();
		for (PriceListItem item : list) {
			if(item.getPriceList().getId() == last.getId())
				usableList.add(item);		
		}
		/*ovim sam dobila aktuelne stavke cenovnika*/
		
		/*hardodirano da je prvo za sport*/
		/*cena dana polise bilo gde*/
		int day = 100;
		double pricePerDayForSportAndRegion;
		double sportPer = 0;
		double regPer = 0;
		double sportBasePrice = 0;
		double regBasePrice = 0;
		double agePer = 0; /*fali za godine*/
		
		for (PriceListItem item : usableList) {
			if(item.getFactor().getId() == insurencePolicy.getRegion()) {
				regPer = item.getPercent(); //trenutni procenat za region
				regBasePrice = item.getFactor().getCategory().getBasePrice();
			}
			if(item.getFactor().getId() == insurencePolicy.getSport()) {
				sportPer = item.getPercent();
				sportBasePrice = item.getFactor().getCategory().getBasePrice();
			}
		}
		
		//ispraviti cjenovnike
		if(regPer == 0)
			regPer = 1;
		if(sportPer == 0)
			sportPer = 1;
		if(agePer == 0)
			agePer = 1;
		
		double sportPrice = sportBasePrice + sportBasePrice * sportPer/100;
		double regionPrice = regBasePrice + regBasePrice * regPer/100;
		pricePerDayForSportAndRegion = sportPrice + regionPrice;
		
		/*insurencyPolicyWithPrice sluzi za generisanje pdf-a polise i sadrzi sve cijene za pojedinacne korisnike*/
		InsurancePolicy policy  = generatePolicyFromInsurencePolicyRequest(insurencePolicy, usableList);
		InsurancePolicyWithPrice policyWithPrice = new InsurancePolicyWithPrice(policy);
		
		/*upit na repo*/
		List<PriceListItem> ageItems = new ArrayList<PriceListItem>();
		
		for (PriceListItem item : usableList) {
			if(item.getFactor().getCategory().getId() == 2) {
				ageItems.add(item);
			}
		}
		
		double agePrice = 0;
			for(int i =0; i<insurencePolicy.getPersons().size(); i++) {
				PersonRequest person = insurencePolicy.getPersons().get(i);
				for (PriceListItem priceListItem : ageItems) { //tri kategorije
				String agePeriod = priceListItem.getFactor().getName();
				String[] ageList = agePeriod.split("-");
				String firstAge = ageList[0];
				String secondAge = ageList[1];
				
				if( person.getAge()>=Integer.parseInt(firstAge)  && person.getAge() <= Integer.parseInt(secondAge) ) {
					double baseForAge = priceListItem.getFactor().getCategory().getBasePrice();
					double percentForAge = priceListItem.getPercent();
					agePrice = baseForAge +   baseForAge * percentForAge/100; 
					double amountForPerson = sportPrice + regionPrice + agePrice; 
					policyWithPrice.getPricePerPerson().add(i, amountForPerson);;
				}
			} 
		}
		
		for(int i = 0; i<policyWithPrice.getPricePerPerson().size(); i++) {
			retVal += insurencePolicy.getDuration() * policyWithPrice.getPricePerPerson().get(i);
		}
		policyWithPrice.setAmount(retVal);
		return retVal;
	}

	private InsurancePolicy generatePolicyFromInsurencePolicyRequest(InsurancePolicyRequest insurencePolicy, List<PriceListItem> items) {
		InsurancePolicy policy = new InsurancePolicy();
		policy.setStartDate(insurencePolicy.getStartDate());
		policy.setEndDate(policy.getEndDate());
		policy.setDuration(insurencePolicy.getDuration());
		Set<Person> personList = new HashSet<Person>();
		Set<PriceListItem> itemList =  new HashSet<PriceListItem>();
		
		for (PersonRequest personRequest : insurencePolicy.getPersons()) {
			Person person = new Person(personRequest.getFirstName(), personRequest.getLastName(), personRequest.getJmbg(),personRequest.getPassportNumber(),
					personRequest.getAddress(), personRequest.getPhone(), personRequest.isContractor(), personRequest.getEmail());
			personList.add(person);
		}
		policy.setPersons(personList);
		
		for (PriceListItem item : items) {
			if(item.getFactor().getId() == insurencePolicy.getRegion() || item.getFactor().getId() == insurencePolicy.getSport()) {
				itemList.add(item);
			}
		
		}
		policy.setPriceListItems(itemList);
		return policy;
	}
	
	
}