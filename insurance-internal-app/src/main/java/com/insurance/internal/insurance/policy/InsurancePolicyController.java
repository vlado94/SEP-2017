package com.insurance.internal.insurance.policy;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
@RestController
@RequestMapping("/internal/insurancePolicy")
@CrossOrigin(origins = "http://localhost:4500")
public class InsurancePolicyController {
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@PostMapping
	private InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest obj) {
		InsurancePolicyRequest newInsuranceReq = restTemplate().postForObject(
				getDataccessPortHttps()+"/insurancePolicy", obj, InsurancePolicyRequest.class);
		return newInsuranceReq;
	}
	
	@PostMapping("/calculateSuggestedPrice")
	private Double calculateSuggestedPrice(@RequestBody InsurancePolicyCalculatePriceRequest obj) {
		obj.setAmount(15l); //izbrisati nakon ispravljenog fronta
		Double price = restTemplate().postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPrice", obj, Double.class);
		return price;
	}
	
	@PostMapping("/car/calculateSuggestedPrice")
	private Double calculateSuggestedPriceCar(@RequestBody InsurancePolicyCarCalculatePriceRequest obj) {
		obj.setSlepovanje(30l);
		obj.setPopravka(34l);
		obj.setSmestaj(37l);
		obj.setPrevoz(41l);
		Double price = restTemplate().postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPriceCar", obj, Double.class);
		return price;
	}
	
	@PostMapping("/home/calculateSuggestedPrice")
	private Double calculateSuggestedPriceHome(@RequestBody InsurancePolicyHomeCalculatePriceRequest obj) {
		obj.setSize(18l);
		obj.setAge(22l);
		obj.setValue(26l);
		obj.setRisk(27l);
		
		Double price = restTemplate().postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPriceHome", obj, Double.class);
		return price;
	}
	
	

	public String getDataccessPortHttps() {
		return dataccessPort.replace("http", "https").toString();
	}
	
	@Bean
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
	}
}
