package isok.isok.paypal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalModel;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("/paypal")
public class Try {
	
	private String clientId = "AbInyjrhe52aSJsPdXENWMgtwKrgwSPieS1D10U9AQrZUDqSY-AO2NM9me1ZQoZ5o6o2emgdNlI3wqWN";
	private String clientSecret = "EP--OXh41OB4sj1FtSPicy_9-REMtPfl8sc3unhM4GAIbjN_6OXNwrVijBCun4nfBeUeGtTb99LXf3Jz";
	
	@Autowired
	PaypalService paypalService;
	
	@GetMapping
	public String ggg() {
		
		//String successUrl = "http://www.alo.rs") + "/api" + PaypalController.PAYPAL_SUCCESS_URL;
		//System.out.println("Success link je: " + successUrl);
		//String cancelUrl = URLUtils.getBaseURl(request) + "/api" + PaypalController.PAYPAL_CANCEL_URL;
		//System.out.println("Cancel link je: " + cancelUrl);
		Payment payment;
		try {
			payment = paypalService.createPayment(3d, "USD","paypal", "sale", "Opis paymenta", 
					"http://www.alo.rs", "http://www.alo.rs", 1l);
			System.out.println(payment);
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

}
