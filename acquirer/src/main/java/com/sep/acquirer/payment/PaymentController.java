package com.sep.acquirer.payment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@GetMapping("/test")
	private String findAll() {
		System.out.println("Success");
		return "success";
	}
	
	
}
