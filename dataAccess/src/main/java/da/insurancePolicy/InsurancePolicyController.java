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

	@GetMapping("/calculatePrice")
	public Integer calculatePrice(/*@RequestBody InsurancePolicyRequest request*/) {
		InsurancePolicyRequest policy = new InsurancePolicyRequest();
		policy.setDuration(5);
		policy.setPersons(new ArrayList<>());
		policy.getPersons().add(new PersonRequest());
		policy.getPersons().add(new PersonRequest());
		policy.getPersons().add(new PersonRequest());
		policy.getPersons().add(new PersonRequest());
		policy.getPersons().add(new PersonRequest());
		policy.setRegion(2l);
		policy.setSport(1l);
		
		
		insurancePolicyService.calculatePolice(policy);
		
		return 1;
	}
}
