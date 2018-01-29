package com.insurance.internal.bankMember;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.insurance.internal.factor.FactorController;

import model.dto.BankMemberDTO;

@RestController
@RequestMapping("/internal/insurancePolicy/bankMember")
@CrossOrigin(origins = "http://localhost:4500")
public class BankMemberController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${acquirerPort}")
	private String acquirerPort;
	
	private static Logger logger = LoggerFactory.getLogger(FactorController.class);
	
	@PreAuthorize("hasRole('seller')")
	@GetMapping
	public List<BankMemberDTO> findAll(){
		logger.info("Expect bank members");
		ResponseEntity<BankMemberDTO[]> responseEntity = restTemplate
				.getForEntity(acquirerPort + "/bankMember", BankMemberDTO[].class);
		BankMemberDTO[] objects = responseEntity.getBody();
		logger.info("Bank members are listed");
		return Arrays.asList(objects);		
	}
}
