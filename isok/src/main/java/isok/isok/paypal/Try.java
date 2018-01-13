package isok.isok.paypal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("/paypal")
public class Try {
	
	//private String clientId = "AbInyjrhe52aSJsPdXENWMgtwKrgwSPieS1D10U9AQrZUDqSY-AO2NM9me1ZQoZ5o6o2emgdNlI3wqWN";
	//private String clientSecret = "EP--OXh41OB4sj1FtSPicy_9-REMtPfl8sc3unhM4GAIbjN_6OXNwrVijBCun4nfBeUeGtTb99LXf3Jz";
	
	@Autowired
	PaypalService paypalService;
	
	@GetMapping
	public String ggg(HttpServletRequest request) {
		
		String successUrl = URLUtils.getBaseURl(request) + "/paypal/g" ;
		//System.out.println("Success link je: " + successUrl);
		//String cancelUrl = URLUtils.getBaseURl(request) + "/api" + PaypalController.PAYPAL_CANCEL_URL;
		//System.out.println("Cancel link je: " + cancelUrl);
		Payment payment;
		try {
			payment = paypalService.createPayment("3.00", "USD","paypal", "sale", "Opis paymenta", 
					successUrl, "https://www.b92.net/", 1l);
			//System.out.println(payment.getId());
			//System.out.println(payment.getClientID()); null
			//System.out.println(payment.getOAuthTokenCredential().getAccessToken()); null
			//System.out.println(payment.getPayer().getPayerInfo().getPayerId()); null
			for(Links links : payment.getLinks()){
			    if(links.getRel().equals("approval_url")){
			        //responsTransactionEntity.setUrl(links.getHref());
			        //return new ResponseEntity<ResponsTransactionEntity>(responsTransactionEntity, HttpStatus.OK);
			    	return links.getHref();
			    }
			}
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "all zero";
	}

	
	@GetMapping("/g")
	public String metoda(HttpServletRequest request) {
		String token=(String) request.getParameter("token");
		String paymentId=(String) request.getParameter("paymentId");
		String payerId=(String) request.getParameter("PayerID");
		try {
			paypalService.executePayment(paymentId, payerId);
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "eto me";
	}
}
