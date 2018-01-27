package com.insurance.internal.factor;

import java.util.Arrays;
import java.util.List;

import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@Value("${dataccessPort}")
	private String dataccessPort;

	@Autowired
	AccessToken accessToken;
	
	@Autowired
	RestTemplate restTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(FactorController.class);

	@PreAuthorize("hasRole('seller')")
	@GetMapping("/category/{id}")
	private List<FactorDTO> findFactorsByID(@PathVariable Long id) {
		logger.info("Find category by factor id " + id);
		ResponseEntity<FactorDTO[]> responseEntity = restTemplate
				.getForEntity(dataccessPort + "/factor/category/" + id, FactorDTO[].class);
		FactorDTO[] objects = responseEntity.getBody();
		logger.info("Category with id " + id + " is found");
		return Arrays.asList(objects);
	}
}
