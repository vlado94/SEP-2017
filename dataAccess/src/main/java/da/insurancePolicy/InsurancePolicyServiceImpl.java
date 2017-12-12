package da.insurancePolicy;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.insurancePolicyWithPrice.InsurancePolicyWithPrice;
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
	public Double calculatePolice(InsurancePolicyRequest policy) {
		double retVal = 0;
		
		/*insurencyPolicyWithPrice sluzi za generisanje pdf-a polise i sadrzi sve cijene za pojedinacne korisnike*/
		
		//InsurancePolicyWithPrice policyWithPrice = new InsurancePolicyWithPrice(policy);
		
		
		PriceList last = priceListService.findCurrent();
		/*ide na repo*/
		List<PriceListItem> list = (ArrayList<PriceListItem>)priceListItemRepo.findAll();
		List<PriceListItem> usableList = new ArrayList<>();
		for (PriceListItem item : list) {
			if(item.getPriceList().getId() == last.getId())
				usableList.add(item);		
		}
		/*ovim sam dobio aktuelne stavke cenovnika*/

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
			if(item.getFactor().getId() == policy.getRegion()) {
				regPer = item.getPercent(); //trenutni procenat za region
				regBasePrice = item.getFactor().getCategory().getBasePrice();
			}
			if(item.getFactor().getId() == policy.getSport()) {
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
		
		
		/*upit na repo*/
		List<PriceListItem> ageItems = new ArrayList<PriceListItem>();
		
		for (PriceListItem item : usableList) {
			if(item.getFactor().getId() == 2) {
				ageItems.add(item);
			}
		}
		
		
		for (PersonRequest person : policy.getPersons()) {
			for (PriceListItem priceListItem : ageItems) {
				String agePeriod = priceListItem.getFactor().getName();
				String[] ageList = agePeriod.split("-");
				
				String firstAge = ageList[0];
				String secondAge = ageList[1];
				
				if(Integer.parseInt(firstAge) >= person.getAge() && person.getAge() <= Integer.parseInt(secondAge) ) {
					double baseForAge = priceListItem.getFactor().getCategory().getBasePrice();
					double percentForAge = priceListItem.getPercent();
					double agePrice = baseForAge +   baseForAge * percentForAge/100; //konkretna cijena za tog covjeka za godine
				}
				
			} 
			
		}
		
		retVal = policy.getDuration() * pricePerDayForSportAndRegion;
		return retVal;
	}
}