package isok.isok.insurance.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Autowired
	RestTemplate restTemplate;

	@Value("${dataccessPort}")
	private String dataccessPort;

	@PostMapping
	private InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest obj) {
		InsurancePolicyRequest newInsuranceReq = restTemplate
				.postForObject(dataccessPort + "/insurancePolicy", obj, InsurancePolicyRequest.class);
		return newInsuranceReq;
	}

	@PostMapping("/calculateSuggestedPrice")
	private Double calculateSuggestedPrice(@RequestBody InsurancePolicyCalculatePriceRequest obj) {
		obj.setAmount(15l); // izbrisati nakon ispravljenog fronta
		Double price = restTemplate.postForObject(
				dataccessPort + "/insurancePolicy/calculateSuggestedPrice", obj, Double.class);
		return price;
	}
}
