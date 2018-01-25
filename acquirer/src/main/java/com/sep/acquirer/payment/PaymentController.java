package com.sep.acquirer.payment;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sep.acquirer.paymentRequest.PaymentRequest;


@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4600")
public class PaymentController {

	@GetMapping("/test")
	private String findAll() {
		System.out.println("Success");
		return "success";
	}
	
	
	@PostMapping("/pay")
	private void Pay(@RequestBody PaymentRequest paymentRequest) {
		PaymentRequest tst = paymentRequest;
		String str = tst.toString();
	}
	
	
}
