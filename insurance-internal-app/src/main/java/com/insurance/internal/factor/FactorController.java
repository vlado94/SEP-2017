package com.insurance.internal.factor;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.dto.FactorDTO;

@RestController
@RequestMapping("/internal/factor")
@CrossOrigin(origins = "http://localhost:4500")
public class FactorController {

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Value("${dataccessPort}")
	private String dataccessPort;

	@GetMapping("/category/{id}")
	private List<FactorDTO> findFactorsByID(@PathVariable Long id) {
		ResponseEntity<FactorDTO[]> responseEntity = restTemplate().getForEntity(
				dataccessPort.toString()+"/factor/category/"+id, FactorDTO[].class);
		FactorDTO[] objects = responseEntity.getBody();
		return  Arrays.asList(objects);
	}

}
