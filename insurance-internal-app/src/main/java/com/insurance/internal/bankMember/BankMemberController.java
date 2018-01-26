package com.insurance.internal.bankMember;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.dto.BankMemberDTO;

@RestController
@RequestMapping("/internal/insurancePolicy/bankMember")
@CrossOrigin(origins = "http://localhost:4500")
public class BankMemberController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@PreAuthorize("hasRole('seller')")
	@GetMapping
	public List<BankMemberDTO> findAll(){
		
		ResponseEntity<BankMemberDTO[]> responseEntity = restTemplate
				.getForEntity(getDataccessPortHttps() + "/bankMember", BankMemberDTO[].class);
		BankMemberDTO[] objects = responseEntity.getBody();
		return Arrays.asList(objects);
		
	}

	public String getDataccessPortHttps() {
		return dataccessPort.replace("http", "https").toString();
	}
}
