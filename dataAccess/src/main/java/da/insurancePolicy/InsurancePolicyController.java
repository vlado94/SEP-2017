package da.insurancePolicy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import da.person.PersonService;
import model.request.InsurancePolicyRequest;

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
}