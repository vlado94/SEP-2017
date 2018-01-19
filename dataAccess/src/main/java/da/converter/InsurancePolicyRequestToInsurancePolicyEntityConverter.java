package da.converter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import da.insurancePolicy.InsurancePolicy;
import da.person.Person;
import da.person.PersonService;
import da.priceList.PriceList;
import da.priceList.PriceListService;
import da.priceListItem.PriceListItem;
import da.priceListItem.PriceListItemService;
import model.request.InsurancePolicyRequest;
import model.request.PersonRequest;

@Component
public class InsurancePolicyRequestToInsurancePolicyEntityConverter
		implements Converter<InsurancePolicyRequest, InsurancePolicy> {

	@Autowired
	private PriceListService priceListService;

	@Autowired
	private PriceListItemService priceListItemService;

	@Autowired
	private PersonService personService;

	public InsurancePolicyRequestToInsurancePolicyEntityConverter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public InsurancePolicy convert(InsurancePolicyRequest source) {
		InsurancePolicy insurancePolicy = new InsurancePolicy();
		insurancePolicy.setDuration(source.getDuration());
		insurancePolicy.setStartDate(source.getStartDate());

		PriceList currentPriceList = priceListService.findCurrent();
		PriceListItem currentRegionPrice = priceListItemService.findByFactorIdAndPriceListId(source.getRegion(),
				currentPriceList.getId());
		insurancePolicy.getPriceListItems().add(currentRegionPrice);
		PriceListItem currentSportPrice = priceListItemService.findByFactorIdAndPriceListId(source.getSport(),
				currentPriceList.getId());
		insurancePolicy.getPriceListItems().add(currentSportPrice);

		for (PersonRequest p : source.getPersons()) {
			Person person = personService.findByJmbg(p.getPersonNo());
			if (person == null) {
				person = new Person();
				person.setAddress(p.getAddress());
				person.setContractor(p.isContractor());
				person.setEmail(p.getEmail());
				person.setFirstName(p.getFirstName());
				person.setJmbg(p.getPersonNo());
				person.setLastName(p.getLastName());
				person.setPassportNumber(p.getPassportNo());
				person.setPhone(p.getPhone());

				PriceListItem currentAgePrice = priceListItemService.findByFactorIdAndPriceListId((long) getAgeFromJMBG(p.getPersonNo()),
						currentPriceList.getId());
				person.setAge(currentAgePrice);
			}
			insurancePolicy.getPersons().add(person);
		}
		return insurancePolicy;
	}

	private int getAgeFromJMBG(String jmbg) {	
		
		int year = Integer.parseInt(jmbg.substring(4, 7));
		LocalDateTime date = LocalDateTime.now();
		int yearNow = date.getYear();
		
		int age = yearNow - year; 
		return age;
	}
}
