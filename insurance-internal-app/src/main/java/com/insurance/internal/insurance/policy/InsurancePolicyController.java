package com.insurance.internal.insurance.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCalculatePriceResponse;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyCheckoutRequest;
import model.request.InsurancePolicyCheckoutResponse;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
@RestController
@RequestMapping("/internal/insurancePolicy")
@CrossOrigin(origins = "http://localhost:4500")
public class InsurancePolicyController {
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping
	private InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest obj) {
		InsurancePolicyRequest newInsuranceReq = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy", obj, InsurancePolicyRequest.class);
		return newInsuranceReq;
	}
	
	@PostMapping("/calculateSuggestedPrice")
	private InsurancePolicyCalculatePriceResponse calculateSuggestedPrice(@RequestBody InsurancePolicyCalculatePriceRequest obj) {
		obj.setAmount(15l); //izbrisati nakon ispravljenog fronta
		InsurancePolicyCalculatePriceResponse response = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPrice", obj, InsurancePolicyCalculatePriceResponse.class);
		return response;
	}
	
	@PostMapping("/car/calculateSuggestedPrice")
	private InsurancePolicyCalculatePriceResponse calculateSuggestedPriceCar(@RequestBody InsurancePolicyCarCalculatePriceRequest obj) {
		obj.setSlepovanje(30l);
		obj.setPopravka(34l);
		obj.setSmestaj(37l);
		obj.setPrevoz(41l);
		InsurancePolicyCalculatePriceResponse price = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPriceCar", obj, InsurancePolicyCalculatePriceResponse.class);
		return price;
	}
	
	@PostMapping("/home/calculateSuggestedPrice")
	private Double calculateSuggestedPriceHome(@RequestBody InsurancePolicyHomeCalculatePriceRequest obj) {
		obj.setSize(18l);
		obj.setAge(22l);
		obj.setValue(26l);
		obj.setRisk(27l);
		
		Double price = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPriceHome", obj, Double.class);
		return price;
	}
	
	@PostMapping("/checkout")
	private InsurancePolicyCheckoutResponse getCheckout(@RequestBody InsurancePolicyCheckoutRequest obj) {
		InsurancePolicyCheckoutResponse response = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy/getCheckout", obj, InsurancePolicyCheckoutResponse.class);
		return response;
	}	

	public String getDataccessPortHttps() {
		return dataccessPort.replace("http", "https").toString();
	}
	
	/*@Bean
	public RestTemplate restTemplate() {
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());//TODO: hide password
		    keyStore.load(new FileInputStream(new File("sep.p12")), "sep12345".toCharArray());
	
		    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
		            new SSLContextBuilder()
		                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
		                    .loadKeyMaterial(keyStore, "sep12345".toCharArray())
		                    .build(),
		            NoopHostnameVerifier.INSTANCE);
	
		    HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
	
		    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		    RestTemplate restTemplate = new RestTemplate(requestFactory);
			
		    return restTemplate;
		}
		catch(Exception exc) {
			return null;
		}
	}*/
}
