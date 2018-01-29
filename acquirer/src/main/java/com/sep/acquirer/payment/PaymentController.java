package com.sep.acquirer.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
import com.sep.acquirer.transaction.TransactionService;


@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4600")
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
	
	
	@GetMapping("/test")
	private String findAll() {
		System.out.println("Success");
		return "success";
	}
	
	@PostMapping("/pay")
	private String Pay(@RequestBody PaymentRequest paymentRequest) {
		System.out.println(port);
		BankMember bankMember = bankMemberService.findByCardNumber(paymentRequest.getCardNum());
		Bank bank = bankMember.getBank();
		if(bank != null) {
			if(bank.getPort().equals(port)) {
				if(transactionService.submitPayment(paymentRequest)) {
					System.out.println("SUCCESFUL PAYMENT");
					return "True";
				}
			}
			else {
				ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + ":" + bank.getPort()+"/payment/pay", paymentRequest , String.class );
				return response.getBody();
			}
		}
		return "False";
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	
}
