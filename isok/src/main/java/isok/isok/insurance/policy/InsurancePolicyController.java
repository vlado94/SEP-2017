package isok.isok.insurance.policy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyRequest;

@RestController
@RequestMapping("/api/insurancePolicy")
public class InsurancePolicyController {

	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@PostMapping
	private InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest obj) {
		InsurancePolicyRequest newInsuranceReq = restTemplate().postForObject(
				dataccessPort.toString()+"/insurancePolicy", obj, InsurancePolicyRequest.class);
		return newInsuranceReq;
	}
	
	@PostMapping("/calculatePrice")
	private Integer calculatePrice(@RequestBody InsurancePolicyCalculatePriceRequest obj) {
		Integer insurancePrice = restTemplate().getForObject(
				dataccessPort.toString()+"/insurancePolicy/calculatePrice", /*obj*/Integer.class);
		return obj.getDuration();
	}
}
