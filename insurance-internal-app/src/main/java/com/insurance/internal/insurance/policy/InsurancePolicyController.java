package com.insurance.internal.insurance.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.dto.InsurancePolicyFinalDTO;
import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyCheckoutRequest;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
import model.response.InsurancePolicyCalculatePriceResponse;
import model.response.InsurancePolicyCheckoutResponse;

@RestController
@RequestMapping("/internal/insurancePolicy")
@CrossOrigin(origins = "http://localhost:4500")
public class InsurancePolicyController {

	@Value("${dataccessPort}")
	private String dataccessPort;

	@Autowired
	RestTemplate restTemplate;

	private static Logger logger = LoggerFactory.getLogger(InsurancePolicyController.class);

	@PreAuthorize("hasRole('seller')")
	@PostMapping
	private InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest obj) {
		logger.info("Create insurance policy");
		InsurancePolicyRequest newInsuranceReq = restTemplate
				.postForObject(dataccessPort + "/insurancePolicy", obj, InsurancePolicyRequest.class);
		logger.info("Insurance policy is created");
		return newInsuranceReq;
	}

	@PreAuthorize("hasRole('seller')")
	@PostMapping("/calculateSuggestedPrice")
	private InsurancePolicyCalculatePriceResponse calculateSuggestedPrice(
			@RequestBody InsurancePolicyCalculatePriceRequest obj) {

		logger.info("Calculate price for insurance policy");
		/*InsurancePolicyCalculatePriceResponse response = restTemplate.postForObject(
				dataccessPort+"/insurancePolicy/calculateSuggestedPrice", obj, InsurancePolicyCalculatePriceResponse.class);
		logger.info("Price is calculated and price is " + response.getFinalPrice());*/
		
		
		InsurancePolicyCalculatePriceResponse response  = new InsurancePolicyCalculatePriceResponse();
				 double d = restTemplate.postForObject(
						 dataccessPort+"/insurancePolicy/getPDF", 1, Double.class);
		return response;
	}

	@PreAuthorize("hasRole('seller')")
	@PostMapping("/car/calculateSuggestedPrice")
	private InsurancePolicyCalculatePriceResponse calculateSuggestedPriceCar(
			@RequestBody InsurancePolicyCarCalculatePriceRequest obj) {

		logger.info("Calculate price for insurance policy car");
		InsurancePolicyCalculatePriceResponse price = restTemplate.postForObject(
				dataccessPort+"/insurancePolicy/calculateSuggestedPriceCar", obj, InsurancePolicyCalculatePriceResponse.class);
		logger.info("Price is calculated and price is " + price.getFinalPrice());
		return price;
	}

	@PreAuthorize("hasRole('seller')")
	@PostMapping("/home/calculateSuggestedPrice")
	private Double calculateSuggestedPriceHome(@RequestBody InsurancePolicyHomeCalculatePriceRequest obj) {

		logger.info("Calculate price for insurance policy home");
		Double price = restTemplate.postForObject(
				dataccessPort+"/insurancePolicy/calculateSuggestedPriceHome", obj, Double.class);
		return price;
	}

	@PostMapping("/checkout")
	private InsurancePolicyCheckoutResponse getCheckout(@RequestBody InsurancePolicyCheckoutRequest obj) {
		InsurancePolicyCheckoutResponse response = restTemplate.postForObject(
				dataccessPort+"/insurancePolicy/getCheckout", obj, InsurancePolicyCheckoutResponse.class);
		return response;
	}
	
	@PostMapping("/save")
	private InsurancePolicyFinalDTO saveInsurancePolicy(@RequestBody InsurancePolicyCheckoutResponse insurancePolicyCheckoutResponse) {
		ResponseEntity<InsurancePolicyFinalDTO> responseEntity = restTemplate.postForEntity(
				dataccessPort+"/insurancePolicyFinal", insurancePolicyCheckoutResponse, InsurancePolicyFinalDTO.class);
		InsurancePolicyFinalDTO insurancePolicyFinal = responseEntity.getBody();
		System.out.println(insurancePolicyFinal.toString());
		return insurancePolicyFinal;
	}
	
}
