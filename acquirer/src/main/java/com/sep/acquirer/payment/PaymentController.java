package com.sep.acquirer.payment;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sep.acquirer.bank.Bank;
import com.sep.acquirer.bankMember.BankMember;
import com.sep.acquirer.bankMember.BankMemberService;
import com.sep.acquirer.paymentRequest.PaymentRequest;
import com.sep.acquirer.paymentRequest.PaymentRequestCard;
import com.sep.acquirer.security.AcquirerExternalDto;
import com.sep.acquirer.transaction.TransactionService;


@RestController
@RequestMapping("/payment")

public class PaymentController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BankMemberService bankMemberService;

	@Value("${server.port}")
	private String port;
	
	@Value("${baseUrl}")
	private String baseUrl;
	
	@Value("${externalUrl}")
	private String externalUrl;
	
	@Value("${secretKey}")
	private String secretKey;
	
	@GetMapping("/test")
	private String findAll() {
		System.out.println("Success");
		return "success";
	}
	
	@CrossOrigin(origins = "http://localhost:4600")
	@PostMapping("/pay")
	private String PayFromWebApp(@RequestBody PaymentRequest paymentRequest) {
		System.out.println(port);
		BankMember bankMember = bankMemberService.findByCardNumber(paymentRequest.getCardNum());
		if(bankMember != null) {
			Bank bank = bankMember.getBank();
			if(bank != null) {
				if(bank.getPort().equals(port)) {
					if(transactionService.submitPayment(paymentRequest)) {
						System.out.println("SUCCESFUL PAYMENT");
						
						String md5Hash = DigestUtils.md5Hex(paymentRequest.getPolicyID() + secretKey);
						AcquirerExternalDto aed = new AcquirerExternalDto(Long.parseLong(paymentRequest.getPolicyID()), md5Hash);
						
						restTemplate.postForEntity(externalUrl+":8083/external/acquirer/cardPayment", aed, Boolean.class);
						
						return "True";
					}
				}
				else {
					ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + ":" + bank.getPort()+"/payment/pay", paymentRequest , String.class );
					return response.getBody();
				}
			}
		}
		return "False";
	}
	
	@PostMapping("/payfromcard")
	private boolean PayFromCard(@RequestBody PaymentRequestCard paymentRequest) {
		System.out.println(port);
		BankMember bankMember = bankMemberService.findByBillNumber(paymentRequest.getBillNum());
		if(bankMember != null) {
			Bank bank = bankMember.getBank();
			if(bank != null) {
				if(bank.getPort().equals(port)) {
					if(transactionService.submitPayment(paymentRequest)) {
						System.out.println("SUCCESFUL PAYMENT");
						return true;
					}
				}
				else {
					ResponseEntity<Boolean> response = restTemplate.postForEntity(baseUrl + ":" + bank.getPort()+"/payment/payfromcard", paymentRequest , Boolean.class );
					return response.getBody();
				}
			}
		}
		return false;
	}
	
	
	
	
	
}
