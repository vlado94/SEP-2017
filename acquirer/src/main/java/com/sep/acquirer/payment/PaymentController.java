package com.sep.acquirer.payment;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sep.acquirer.bank.Bank;
import com.sep.acquirer.bank.BankService;
import com.sep.acquirer.paymentRequest.PaymentRequest;
import com.sep.acquirer.transaction.TransactionService;


@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4600")
public class PaymentController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private BankService bankService;

	@Value("${server.port}")
	private String port;
	
	
	@GetMapping("/test")
	private String findAll() {
		System.out.println("Success");
		return "success";
	}
	
	@PostMapping("/pay")
	private void Pay(HttpServletResponse httpServletResponse, @RequestBody PaymentRequest paymentRequest) {
		System.out.println(port);
		Bank bank = bankService.findByAccountNumber(paymentRequest.getCardNum());
		if(bank != null) {
			if(bank.getPort().equals(port))
				transactionService.submitPayment(paymentRequest);
			else
				System.out.println("HTTP CLIENT TO ANOTHER BANK");
		}
	}
	
	
}
