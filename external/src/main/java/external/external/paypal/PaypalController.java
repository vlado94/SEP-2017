package external.external.paypal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.request.InsurancePolicyRequest;

@RestController
@RequestMapping("/external/paypal")
@CrossOrigin(origins = "http://localhost:4300")
public class PaypalController {
	
	@PostMapping
	private String paypal(@RequestBody InsurancePolicyRequest insurancePolicyRequest) {
		System.out.println("paypal prosao");
		return "aaaaaaaaaaa";
	}

}
