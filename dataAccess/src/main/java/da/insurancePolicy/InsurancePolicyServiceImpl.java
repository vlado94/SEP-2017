package da.insurancePolicy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import da.person.Person;
import da.priceList.PriceList;
import da.priceList.PriceListService;
import da.priceListItem.PriceListItem;
import da.priceListItem.PriceListItemRepository;
import da.rules.Popust;
import da.rules.RuleService;
import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
import model.request.InsurancePolicyResponce;
import model.request.PersonRequest;
import model.request.PersonResponse;

@Service
@Transactional
public class InsurancePolicyServiceImpl implements InsurancePolicyService{

	@Autowired
	private InsurancePolicyRepository repository;

	@Autowired
	private PriceListItemRepository priceListItemRepo;

	@Autowired
	private RuleService ruleService;

	@Autowired
	private PriceListService priceListService;
	
	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;
	
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
		double coverPer = 0;
		double regPer = 0;
		double sportBasePrice = 0;
		double regBasePrice = 0;
		double coverBasePrice = 0;
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
			if(item.getFactor().getId() == insurencePolicy.getAmount()) {
				coverPer = item.getPercent();
				coverBasePrice = item.getFactor().getCategory().getBasePrice();
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
		double coverPrice = coverBasePrice + coverBasePrice * coverPer/100;
		pricePerDayForSportAndRegion = sportPrice + regionPrice;
		
		InsurancePolicy policy  = insurancePolicyRepository.save(generatePolicyFromInsurencePolicyRequest(insurencePolicy, usableList));
		InsurancePolicyResponce responce = generateInsurenceResponceFromPolicyRequest(insurencePolicy, policy);
	
		
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
				PersonResponse personResponce = generatePersonResponseFromRequest(person);
				for (PriceListItem priceListItem : ageItems) { //tri kategorije
				String agePeriod = priceListItem.getFactor().getName();
				String[] ageList = agePeriod.split("-");
				String firstAge = ageList[0];
				String secondAge = ageList[1];
				
				personResponce.setSportPrice(sportPrice);
				personResponce.setRegionPrice(regionPrice);
				personResponce.setCoverPrice(coverPrice);
				if( person.getAge()>=Integer.parseInt(firstAge)  && person.getAge() <= Integer.parseInt(secondAge) ) {
					double baseForAge = priceListItem.getFactor().getCategory().getBasePrice();
					double percentForAge = priceListItem.getPercent();
					agePrice = baseForAge +   baseForAge * percentForAge/100; 
					double amountForPerson = sportPrice + regionPrice + agePrice + coverPrice; 
					personResponce.setAgePrice(agePrice);
					personResponce.setTotalPrice(amountForPerson);
					responce.getPersons().add(personResponce);
				}
			} 
		}
		
		for(int i = 0; i<responce.getPersons().size(); i++) {
			retVal += insurencePolicy.getDuration() * responce.getPersons().get(i).getTotalPrice();
		}
		policy.setAmount(retVal);
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
			if(item.getFactor().getId() == insurencePolicy.getRegion() || item.getFactor().getId() == insurencePolicy.getSport() || item.getFactor().getId() == insurencePolicy.getAmount()) {
				itemList.add(item);
			}
		
		}
		policy.setPriceListItems(itemList);
		return policy;
	}

	private InsurancePolicyResponce generateInsurenceResponceFromPolicyRequest(InsurancePolicyRequest insurencePolicyRequest, InsurancePolicy policy) {
		InsurancePolicyResponce responce = new InsurancePolicyResponce();
		responce.setPolicyID(policy.getId());
		responce.setStartDate(policy.getStartDate());
		responce.setDuration(policy.getDuration());
		responce.setRegion(insurencePolicyRequest.getRegion());
		responce.setSport(insurencePolicyRequest.getSport());
		responce.setCoverAmount(insurencePolicyRequest.getAmount());
		responce.setAmount(policy.getAmount());
		responce.setPersons(new ArrayList<PersonResponse>());
	
		return responce;
	}

	private PersonResponse generatePersonResponseFromRequest(PersonRequest person) {
		PersonResponse response = new PersonResponse();
		response.setFirstName(person.getFirstName());
		response.setLastName(person.getLastName());
		response.setJmbg(person.getJmbg());
		
		return response;
	}

	@Override
	public Double calculateSuggestedPrice(InsurancePolicyCalculatePriceRequest policy) {
		
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
		double pricePerDayForSportAndRegionAndCover;
		double sportPer = 0;
		double coverPer = 0;
		double regPer = 0;
		double sportBasePrice = 0;
		double regBasePrice = 0;
		double coverBasePrice = 0;
		double agePer = 0; /*fali za godine*/
		
		for (PriceListItem item : usableList) {
			if(item.getFactor().getId() == policy.getRegion()) {
				regPer = item.getPercent(); 
				regBasePrice = item.getFactor().getCategory().getBasePrice();
			}
			if(item.getFactor().getId() == policy.getSport()) {
				sportPer = item.getPercent();
				sportBasePrice = item.getFactor().getCategory().getBasePrice();
			}
			if(item.getFactor().getId() == policy.getAmount()) {
				coverPer = item.getPercent();
				coverBasePrice = item.getFactor().getCategory().getBasePrice();
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
		double coverPrice = coverBasePrice + coverBasePrice * coverPer/100;
		pricePerDayForSportAndRegionAndCover = sportPrice + regionPrice + coverPrice;
		
		/*upit na repo*/
		List<PriceListItem> ageItems = new ArrayList<PriceListItem>();
		
		for (PriceListItem item : usableList) {
			if(item.getFactor().getCategory().getId() == 2) {
				ageItems.add(item);
			}
		}
		List<Double> agePrices = new ArrayList<>(); 
		for (PriceListItem priceListItem : ageItems) {
			double baseForAge = priceListItem.getFactor().getCategory().getBasePrice();
			double percentForAge = priceListItem.getPercent();
			double agePrice = baseForAge +   baseForAge * percentForAge/100; 
			
			if(priceListItem.getFactor().getName().equals("0-16")) {
				if(policy.getFirstAgeCategory() != 0)
					agePrice = agePrice * policy.getFirstAgeCategory();
			
			}else if(priceListItem.getFactor().getName().equals("16-60")) {
				if(policy.getSecondAgeCategory() != 0)
					agePrice = agePrice * policy.getSecondAgeCategory();
			}else {
				if(policy.getThirdAgeCategory() != 0)
					agePrice = agePrice * policy.getThirdAgeCategory();
			}
			agePrices.add(agePrice);
		}
		int numOfPersons = policy.getFirstAgeCategory() + policy.getSecondAgeCategory() + policy.getThirdAgeCategory();
		retVal = numOfPersons * pricePerDayForSportAndRegionAndCover; 
		
		for(int i = 0; i< agePrices.size(); i++) {
			retVal += agePrices.get(i);
		}
		
		ArrayList<Popust> discounts = ruleService.getClassifiedItem(policy);
		Double price = retVal * policy.getDuration();
		System.out.println(discounts.size());
		
		
		return price;
	}
	
	
	
	@Override
	public Double calculateSuggestedPriceHome(InsurancePolicyHomeCalculatePriceRequest policy) {
		double retVal = 0;
		PriceList last = priceListService.findCurrent();
		//ide na repo
		List<PriceListItem> list = (ArrayList<PriceListItem>)priceListItemRepo.findAll();
		List<PriceListItem> usableList = new ArrayList<>();
		for (PriceListItem item : list) {
			if(item.getPriceList().getId() == last.getId())
				usableList.add(item);		
		}
		//ovim sam dobila aktuelne stavke cenovnika
		
		double sizePercent = 0;
		double sizeBasePrice = 0;
		double agePercent = 0;
		double ageBasePrice = 0;
		double valuePercent = 0;
		double valueBasePrice = 0;
		double riskPercent = 0;
		double riskBasePrice = 0;
		double duration = policy.getDuration();
		double pricePerDay = 0;
		
		for (PriceListItem item : usableList) {
			if(item.getFactor().getId() == policy.getSize()) {
				sizePercent = item.getPercent(); 
				sizeBasePrice = item.getFactor().getCategory().getBasePrice();
			}
			if(item.getFactor().getId() == policy.getAge()) {
				agePercent = item.getPercent();
				ageBasePrice = item.getFactor().getCategory().getBasePrice();
			}
			if(item.getFactor().getId() == policy.getValue()) {
				valuePercent = item.getPercent();
				valueBasePrice = item.getFactor().getCategory().getBasePrice();
			}if(item.getFactor().getId() == policy.getRisk()) {
				riskPercent = item.getPercent();
				riskBasePrice = item.getFactor().getCategory().getBasePrice();
			}
		}
		double sizePrice = sizeBasePrice + sizeBasePrice * sizePercent/100;
		double agePrice = ageBasePrice + ageBasePrice * agePercent/100;
		double valuePrice = valueBasePrice + valueBasePrice * valuePercent/100;
		double riskPrice = riskBasePrice + riskBasePrice * riskPercent/100;
		pricePerDay = sizePrice + agePrice + valuePrice +  riskPrice;
		
		return duration * pricePerDay;
	}

	@Override
	public Double calculateSuggestedPriceCar(InsurancePolicyCarCalculatePriceRequest request) {
		double retVal = 0;
		double duration = request.getDuration();
		
		PriceList last = priceListService.findCurrent();
		//ide na repo
		List<PriceListItem> list = (ArrayList<PriceListItem>)priceListItemRepo.findAll();
		List<PriceListItem> usableList = new ArrayList<>();
		for (PriceListItem item : list) {
			if(item.getPriceList().getId() == last.getId())
				usableList.add(item);		
		}
		double popravkaPercent = 0;
		double popravkaBasePrice = 0;
		double prevozPercent = 0;
		double prevozBasePrice = 0;
		double slepovanjePercent = 0;
		double slepovanjeBasePrice = 0;
		double smjestajPercent = 0;
		double smjestajBasePrice = 0;
		double pricePerDay = 0;
		
		for (PriceListItem item : usableList) {
			if(request.getPopravka() != null) {
				if(item.getFactor().getId() == request.getPopravka()) {
					popravkaPercent = item.getPercent(); 
					popravkaBasePrice = item.getFactor().getCategory().getBasePrice();
				}
			}
			if(request.getPrevoz() != null) {
				if(item.getFactor().getId() == request.getPrevoz()) {
					prevozPercent = item.getPercent();
					prevozBasePrice = item.getFactor().getCategory().getBasePrice();
				}
			}
			if(request.getSlepovanje() != null) {
				if(item.getFactor().getId() == request.getSlepovanje()) {
					slepovanjePercent = item.getPercent();
					slepovanjeBasePrice = item.getFactor().getCategory().getBasePrice();
				}
			}
			if(request.getSmestaj() != null) {
				if(item.getFactor().getId() == request.getSmestaj()) {
					smjestajPercent = item.getPercent();
					smjestajBasePrice = item.getFactor().getCategory().getBasePrice();
				}
			}
		}
		
		double popravkaPrice = popravkaBasePrice + popravkaBasePrice * popravkaPercent/100;
		double prevozPrice = prevozBasePrice + prevozBasePrice * prevozPercent/100;
		double slepovanjePrice = slepovanjeBasePrice + slepovanjeBasePrice * slepovanjePercent/100;
		double smjestajPrice = smjestajBasePrice + smjestajBasePrice * smjestajPercent/100;
		pricePerDay = popravkaPrice + prevozPrice + slepovanjePrice +  smjestajPrice;

		return duration * pricePerDay;
		
	}


	
}