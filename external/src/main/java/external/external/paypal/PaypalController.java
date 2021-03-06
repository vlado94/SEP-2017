package external.external.paypal;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import model.dto.InsurancePolicyFinalDTO;
import model.response.InsurancePolicyCheckoutResponse;

@RestController
@RequestMapping("/external/paypal")
@CrossOrigin(origins = "http://localhost:4300")
public class PaypalController {
	
	@Autowired
	PaypalService paypalService;
	
	@Autowired
	HttpServletRequest request;
	
	private static Logger logger = LoggerFactory.getLogger(PaypalController.class);
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping 
	private String paypal(@RequestBody InsurancePolicyCheckoutResponse insurancePolicyCheckoutResponse) {
		//String successUrl = URLUtils.getBaseURl(request) + "/external/paypal/execute";
		String successUrl = "http://localhost:4300/paypal";
		
		System.out.println("successurl " + successUrl);
		ResponseEntity<InsurancePolicyFinalDTO> responseEntity = restTemplate.postForEntity(
				dataccessPort+"/insurancePolicyFinal", insurancePolicyCheckoutResponse, InsurancePolicyFinalDTO.class);
		InsurancePolicyFinalDTO insurancePolicyFinalDTO = responseEntity.getBody();
		//insurancePolicyCheckoutResponse = 
		Payment payment;
		try {
			double price = insurancePolicyFinalDTO.getPrice() / 100;
			System.out.println("price " + price);
			price = Math.round(price*100.0)/100.0;
			
			payment = paypalService.createPayment(Double.toString(price), "USD","paypal", "sale", "Opis paymenta", 
					successUrl, "https://www.b92.net/", 1l, insurancePolicyFinalDTO);
			logger.info("Kreiran payment " + payment.getId());
			for(Links links : payment.getLinks()){
			    if(links.getRel().equals("approval_url")){
			        //responsTransactionEntity.setUrl(links.getHref());
			        //return new ResponseEntity<ResponsTransactionEntity>(responsTransactionEntity, HttpStatus.OK);
			    	System.out.println("linkovi " + links.getHref());
			    	return links.getHref();
			    }
			}
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "all zero";
	}
	
	@GetMapping("/execute")
	public boolean executeMethod(@RequestParam(value = "paymentId",required=true) String paymentId,
			@RequestParam(value = "PayerID",required=true) String PayerID) {
		
		InsurancePolicyFinalDTO insurancePolicyFinalDTO = paypalService.getInsuranceMap(paymentId);
		
		try {
			paypalService.executePayment(paymentId, PayerID);
			ResponseEntity<InsurancePolicyCheckoutResponse> responseEntity = restTemplate.postForEntity(
					dataccessPort+"/insurancePolicyFinal/paying", insurancePolicyFinalDTO, InsurancePolicyCheckoutResponse.class);
			InsurancePolicyCheckoutResponse response = responseEntity.getBody();
			//response mail
			ResponseEntity<Boolean> responseEntityPdf = restTemplate.postForEntity(
					dataccessPort+"/insurancePolicy/getPDF", response, Boolean.class);
			System.out.println("proslo paypal pdf " + responseEntityPdf.getBody());
			logger.info("Paypal is executed with payment id " + paymentId);
			
			return true;
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}
