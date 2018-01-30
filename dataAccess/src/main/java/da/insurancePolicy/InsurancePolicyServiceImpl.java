package da.insurancePolicy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.factor.Factor;
import da.factor.FactorService;
import da.person.Person;
import da.priceList.PriceList;
import da.priceList.PriceListService;
import da.priceListItem.PriceListItem;
import da.priceListItem.PriceListItemRepository;
import da.rules.RuleService;
import model.dto.Discount;
import model.dto.Popust;
import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyCarRequest;
import model.request.InsurancePolicyCheckoutRequest;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyHomeRequest;
import model.request.InsurancePolicyRequest;
import model.request.PersonRequest;
import model.response.InsurancePolicyCalculatePriceResponse;
import model.response.InsurancePolicyCheckoutResponse;
import model.response.InsurancePolicyResponse;
import model.response.PersonResponse;

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
	private FactorService factorService;
	
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

	/*servisi*/
	
	@Override
	public InsurancePolicyCalculatePriceResponse calculatePolice(InsurancePolicyRequest insurencePolicy) {
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
		pricePerDayForSportAndRegion = sportPrice + regionPrice+coverPrice;
		
		InsurancePolicy policy  = insurancePolicyRepository.save(generatePolicyFromInsurencePolicyRequest(insurencePolicy, usableList));
		InsurancePolicyResponse responce = generateInsurenceResponceFromPolicyRequest(insurencePolicy, policy);
	
		
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
				
				int age = getAgeFromJMBG(person.getPersonNo());
				
				
				if( age>=Integer.parseInt(firstAge)  && age<= Integer.parseInt(secondAge) ) {
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
		
		ArrayList<Popust> discounts = ruleService.getClassifiedItem(insurencePolicy);
		InsurancePolicyCalculatePriceResponse calculatedPriceResponce =  calculatePriceWithDiscounts(discounts, retVal);//return
		
		return calculatedPriceResponce;
	}

	@Override
	public InsurancePolicyCalculatePriceResponse calculateSuggestedPrice(InsurancePolicyCalculatePriceRequest policy) {
		
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
		InsurancePolicyCalculatePriceResponse responce =  calculatePriceWithDiscounts(discounts, price);//return
	
		return responce;
	}
	
	private InsurancePolicyCalculatePriceResponse calculatePriceWithDiscounts(ArrayList<Popust> discounts, Double basePrice ) {
		InsurancePolicyCalculatePriceResponse responce = new InsurancePolicyCalculatePriceResponse();
		responce.setBasePrice(basePrice);
		ArrayList<Discount> discountsToDisplay = new ArrayList<>();
		double totalReduction = 0;
		for (Popust popust : discounts) {
			Discount discount = new Discount();
			discount.setDiscountName(popust.getNazivPopusta());
			discount.setPercent( (int) popust.getIznosPopusta());
			discount.setAmount(basePrice* popust.getIznosPopusta()/100);
			totalReduction += basePrice* popust.getIznosPopusta()/100;
			discountsToDisplay.add(discount);
		}
		responce.setDiscounts(discountsToDisplay);
		responce.setFinalPrice(basePrice - totalReduction);
		return responce;
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
		
		double price = duration * pricePerDay;
		ArrayList<Popust> discounts = ruleService.getClassifiedItem(policy);
		InsurancePolicyCalculatePriceResponse responce =  calculatePriceWithDiscounts(discounts, price);//return
		return responce.getFinalPrice();
	}

	@Override
	public InsurancePolicyCalculatePriceResponse calculateSuggestedPriceCar(InsurancePolicyCarCalculatePriceRequest request) {
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

		Double price = duration * pricePerDay;
		ArrayList<Popust> discounts = ruleService.getClassifiedItem(request);
		InsurancePolicyCalculatePriceResponse responce =  calculatePriceWithDiscounts(discounts, price);//return
		return responce;
		
	}

	@Override
	public InsurancePolicyCheckoutResponse getCheckout(InsurancePolicyCheckoutRequest request) {
		/*travel*/
		InsurancePolicyRequest travelRequest = request.getInsurancePolicyRequest();
		InsurancePolicyCalculatePriceResponse responseForTravel = calculatePolice(travelRequest);
		/*car*/
		
		InsurancePolicyCheckoutResponse response = new InsurancePolicyCheckoutResponse();
		fillResponseWithTravelDetails(response, travelRequest ,responseForTravel);
		double priceForCar = 0;
		double priceForHome = 0;
		if(request.getInsurancePolicyCarRequest() != null) {
			InsurancePolicyCarCalculatePriceRequest carRequest = createIPCarCalculatePriceRequest(request.getInsurancePolicyCarRequest());
			InsurancePolicyCalculatePriceResponse responseForCar = calculateSuggestedPriceCar(carRequest);
			fillResponseWithCarDetails(response , request.getInsurancePolicyCarRequest(),responseForCar );
			priceForCar =  responseForCar.getFinalPrice();
		}
		/*home*/
		if(request.getInsurancePolicyHomeRequest() != null) {
			InsurancePolicyHomeCalculatePriceRequest homeRequest = createIPHomeCalculatePriceRequest(request.getInsurancePolicyHomeRequest());
			priceForHome = calculateSuggestedPriceHome(homeRequest);
			fillResponseWithHomeDetails(response, request.getInsurancePolicyHomeRequest(), priceForHome);
		}
		response.setTotalPrice(responseForTravel.getFinalPrice() + priceForCar + priceForHome);
		/*Poziv Sickove metode za popuste*/
		ArrayList<Popust> discounts = ruleService.getClassifiedItem(response);
		double basePrice=response.getTotalPrice();
		double totalReduction = 0;
		for (Popust popust : discounts) {
			Discount discount = new Discount();
			discount.setDiscountName(popust.getNazivPopusta());
			discount.setPercent( (int) popust.getIznosPopusta());
			discount.setAmount(basePrice* popust.getIznosPopusta()/100);
			totalReduction += basePrice* popust.getIznosPopusta()/100;
			//discountsToDisplay.add(discount);
		}
		//responce.setDiscounts(discountsToDisplay);
		response.setTotalPrice(basePrice - totalReduction);
		
		return response;
	}
	
	/*Pomocne metode*/
	
	private void fillResponseWithTravelDetails(InsurancePolicyCheckoutResponse response, InsurancePolicyRequest request,InsurancePolicyCalculatePriceResponse priceAndDiscounts ) {
		response.setStartDate(request.getStartDate());
		response.setDurationForTravel(request.getDuration());
		response.setRegion(getNameFromId(request.getRegion()));
		response.setSport(getNameFromId(request.getSport()));
		response.setAmount(getNameFromId(request.getAmount()));
		response.setTypeOfPolicy(getNameFromId(request.getTypeOfPolicy()));
		response.setPersons(request.getPersons());
		response.setPriceAndDiscountsForTravel(priceAndDiscounts);
	}
	
	private void fillResponseWithHomeDetails(InsurancePolicyCheckoutResponse response, InsurancePolicyHomeRequest request, double price) {
		response.setDurationForHome(request.getDuration());
		response.setRisk(getNameFromId(request.getRisk()));
		response.setValue(getNameFromId(request.getValue()));
		response.setAge(getNameFromId(request.getAge()));
		response.setSize(getNameFromId(request.getSize()));
		response.setAddress(request.getAddress());
		response.setFirstNameOwnerHome(request.getFirstName());
		response.setLastNameOwnerHome(request.getLastName());
		response.setJmbgOwnerHome(request.getPersonNo());
		response.setPriceForHome(price);
		
	}
	
	private void fillResponseWithCarDetails(InsurancePolicyCheckoutResponse response, InsurancePolicyCarRequest request, InsurancePolicyCalculatePriceResponse priceAndDiscounts) {
		response.setDurationForCar(request.getDuration());
		if(request.getPopravka() != null)
			response.setPopravka(getNameFromId(request.getPopravka()));
		else
			response.setPopravka("nije odabrano");
		if(request.getSlepovanje() != null)
			response.setSlepovanje(getNameFromId(request.getSlepovanje()));
		else
			response.setSlepovanje("nije odabrano");
		if(request.getPrevoz() != null)
			response.setPrevoz(getNameFromId(request.getPrevoz()));
		else
			response.setPrevoz("nije odabrano");
		if(request.getSmestaj() != null)
			response.setSmestaj(getNameFromId(request.getSmestaj()));
		else
			response.setSmestaj("nije odabrano");
		response.setTypeOfVehicle(request.getTypeOfVehicle());
		response.setYear(request.getYear());
		response.setRegistrationNumber(request.getRegistrationNumber());
		response.setChassisNumber(request.getChassisNumber());
		response.setFirstNameOwnerCar(request.getFirstName());
		response.setLastNameOwnerCar(request.getLastName());
		response.setJmbgOwnerCar(request.getPersonNo());
		response.setCarBrand(request.getVehicle());
		response.setPriceAndDiscountsForCar(priceAndDiscounts);
		
		
	}
	
	private String getNameFromId(Long id) {
		Factor categoryFactor = factorService.findOne(id);
		return categoryFactor.getName();
	}
	
	private InsurancePolicyCarCalculatePriceRequest createIPCarCalculatePriceRequest(InsurancePolicyCarRequest request) {
		InsurancePolicyCarCalculatePriceRequest response = new InsurancePolicyCarCalculatePriceRequest();
		response.setDuration(request.getDuration());
		response.setPopravka(request.getPopravka());
		response.setPrevoz(request.getPrevoz());
		response.setSlepovanje(request.getSlepovanje());
		response.setSmestaj(request.getSmestaj());
		return response;
		
	}
	
	private InsurancePolicyHomeCalculatePriceRequest createIPHomeCalculatePriceRequest(InsurancePolicyHomeRequest request) {
		InsurancePolicyHomeCalculatePriceRequest response = new InsurancePolicyHomeCalculatePriceRequest();
		response.setDuration(request.getDuration());
		response.setAge(request.getAge());
		response.setRisk(request.getRisk());
		response.setSize(request.getSize());
		response.setValue(request.getValue());
		return response;
		
	}

	private InsurancePolicy generatePolicyFromInsurencePolicyRequest(InsurancePolicyRequest insurencePolicy, List<PriceListItem> items) {
		InsurancePolicy policy = new InsurancePolicy();
		policy.setStartDate(insurencePolicy.getStartDate());
		policy.setEndDate(policy.getEndDate());
		policy.setDuration(insurencePolicy.getDuration());
		Set<Person> personList = new HashSet<Person>();
		Set<PriceListItem> itemList =  new HashSet<PriceListItem>();
		
		for (PersonRequest personRequest : insurencePolicy.getPersons()) {
			Person person = new Person(personRequest.getFirstName(), personRequest.getLastName(), personRequest.getPersonNo(),personRequest.getPassportNo(),
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
	
	private InsurancePolicyResponse generateInsurenceResponceFromPolicyRequest(InsurancePolicyRequest insurencePolicyRequest, InsurancePolicy policy) {
		InsurancePolicyResponse responce = new InsurancePolicyResponse();
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
		response.setJmbg(person.getPersonNo());
		
		return response;
	}
	
	private int getAgeFromJMBG(String jmbg) {
		String agePersonNo = jmbg.substring(4, 7);
		int year = 0;
		if(agePersonNo.startsWith("0")) {
			year = Integer.parseInt("2" + agePersonNo);
		}else {
			year = Integer.parseInt("1" + agePersonNo);
		}
		LocalDateTime date = LocalDateTime.now();
		int yearNow = date.getYear();
		
		int age = yearNow - year; 
		return age;
	}
}