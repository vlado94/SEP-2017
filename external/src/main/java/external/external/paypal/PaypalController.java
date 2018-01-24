package external.external.paypal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import external.external.price.PriceController;
import model.request.InsurancePolicyRequest;

@RestController
@RequestMapping("/external/paypal")
@CrossOrigin(origins = "http://localhost:4300")
public class PaypalController {
	
	@Autowired
	PaypalService paypalService;
	
	@Autowired
	HttpServletRequest request;
	
	private static Logger logger = LoggerFactory.getLogger(PaypalController.class);
	
	@PostMapping
	private String paypal(@RequestBody InsurancePolicyRequest insurancePolicyRequest) {
		String successUrl = URLUtils.getBaseURl(request) + "/external/paypal/execute";
		System.out.println("successurl " + successUrl);
		Payment payment;
		try {
			float price = insurancePolicyRequest.getPriceSum() / 100;
			System.out.println("price " + price);
			payment = paypalService.createPayment(Float.toString(price), "USD","paypal", "sale", "Opis paymenta", 
					successUrl, "https://www.b92.net/", 1l, insurancePolicyRequest);
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
	public String executeMethod() {
		String token=(String) request.getParameter("token");
		String paymentId=(String) request.getParameter("paymentId");
		String payerId=(String) request.getParameter("PayerID");
		InsurancePolicyRequest insurancePolicyRequest = paypalService.getInsuranceMap(paymentId);
		System.out.println(insurancePolicyRequest.toString());
		logger.info("Izvrsen payment " + paymentId);
		try {
			paypalService.executePayment(paymentId, payerId);
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "eto me";
	}

}
