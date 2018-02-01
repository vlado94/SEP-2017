package external.external.acquirer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.dto.InsurancePolicyFinalDTO;
import model.response.InsurancePolicyCheckoutResponse;

@RestController
@RequestMapping("/external/acquirer")
@CrossOrigin(origins = "http://localhost:4300")
public class AcquirerController {
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping
	private InsurancePolicyFinalDTO saveInsurancePolicy(@RequestBody InsurancePolicyCheckoutResponse insurancePolicyCheckoutResponse) {
		ResponseEntity<InsurancePolicyFinalDTO> responseEntity = restTemplate.postForEntity(
				dataccessPort+"/insurancePolicyFinal", insurancePolicyCheckoutResponse, InsurancePolicyFinalDTO.class);
		InsurancePolicyFinalDTO insurancePolicyFinal = responseEntity.getBody();
		System.out.println(insurancePolicyFinal.toString());
		return insurancePolicyFinal;
	}
	
	@PostMapping("/cardPayment")
	private boolean cardPayment(@RequestBody Long policyId) {
		
		ResponseEntity<InsurancePolicyCheckoutResponse> responseEntity = restTemplate.postForEntity(
				dataccessPort+"/insurancePolicyFinal/cardPayment", policyId, InsurancePolicyCheckoutResponse.class);
		System.out.println("Usao u card payment");
		InsurancePolicyCheckoutResponse response = responseEntity.getBody();
		ResponseEntity<Boolean> responseEntityPdf = restTemplate.postForEntity(
				dataccessPort+"/insurancePolicy/getPDF", response, Boolean.class);
		System.out.println("proslo");
		return true;
	}
}
