package external.external.paypal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import model.request.InsurancePolicyRequest;

@Service
public class PaypalService {

    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    

    private HashMap<String, String> map = new HashMap<String, String>();
    
    private HashMap<String, InsurancePolicyRequest> insuranceMap = new HashMap<String, InsurancePolicyRequest>();

    public Payment createPayment(String total, String currency, 
    		String method, String intent, String description, String successUrl, String cancelUrl, 
    		Long requestTransactionEntityID, InsurancePolicyRequest insurancePolicyRequest) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        //amount.setTotal(String.format("%.2f", 3d));
        amount.setTotal(total);
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);
        

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", mode);

        OAuthTokenCredential oAuthTokenCredential = new OAuthTokenCredential(clientId, clientSecret, sdkConfig);
        APIContext apiContext = new APIContext(oAuthTokenCredential.getAccessToken());

        String token = oAuthTokenCredential.getAccessToken();
        //System.out.println(token);;

        apiContext.setConfigurationMap(sdkConfig);
        payment = payment.create(apiContext);
        map.put(payment.getId(), token);
        insuranceMap.put(payment.getId(), insurancePolicyRequest);
/*
        PaypalCreatePaymentEntity paypalCreatePaymentEntity = new PaypalCreatePaymentEntity();
        paypalCreatePaymentEntity.setAccessToken(token);
        paypalCreatePaymentEntity.setPaymentID(payment.getId());
        paypalCreatePaymentEntity.setRequestTransactionID(requestTransactionEntityID);

        paypalCreatePaymentService.save(paypalCreatePaymentEntity);
*/
        return payment;
    }
    
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", mode);

        APIContext apiContext = new APIContext(map.get(paymentId));//map.get(paymentId)
        apiContext.setConfigurationMap(sdkConfig);

        return payment.execute(apiContext, paymentExecute);
    }

	public InsurancePolicyRequest getInsuranceMap(String paymentId) {
		return insuranceMap.get(paymentId);
	}

	public void setInsuranceMap(HashMap<String, InsurancePolicyRequest> insuranceMap) {
		this.insuranceMap = insuranceMap;
	}
    
    
}
