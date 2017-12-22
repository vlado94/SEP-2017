package da.insurancePolicy;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import da.person.PersonService;
import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
import model.request.PersonRequest;

@RestController
@RequestMapping("/insurancePolicy")
public class InsurancePolicyController {

	@Autowired
	private InsurancePolicyService insurancePolicyService;

	@Autowired
	private PersonService personService;

    @Autowired
    ConversionService conversionService;
    
	@PostMapping
	public InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest request) {
		InsurancePolicy entity = conversionService.convert(request, InsurancePolicy.class);
		
		return request;
	}

	/*Metoda za racunanje preporucene cijene polise*/
	@PostMapping("/calculateSuggestedPrice")
	public Double calculatePrice(@RequestBody InsurancePolicyCalculatePriceRequest request) {
	
		return insurancePolicyService.calculateSuggestedPrice(request);
		
	}
	
	/*Metoda za racunanje ukupne cijene polise*/
	@PostMapping("/calculatePrice")
	public Double calculatePriceWithPersons(@RequestBody InsurancePolicyRequest request) {
		InsurancePolicyRequest policy = new InsurancePolicyRequest();
		policy.setDuration(5);
		policy.setPersons(new ArrayList<>());
		PersonRequest person1 = new PersonRequest("Jovan","Jovanovic","1212994156225","12563","adress","06451145",28l,false, "fjass@sdha");
		PersonRequest person2 = new PersonRequest("Milan","Milanovic","1906994156225","85952","adress","06451145",62l,false, "fjass@sdha");
		policy.getPersons().add(person1);
		policy.getPersons().add(person2);
		
		policy.setRegion(9l);
		policy.setSport(1l);
		policy.setAmount(15l);
		
		double amount = insurancePolicyService.calculatePolice(policy);
	
		return 1.1;
	}
}
