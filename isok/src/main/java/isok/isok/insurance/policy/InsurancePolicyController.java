package isok.isok.insurance.policy;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyRequest;

@RestController
@RequestMapping("/api/insurancePolicy")
public class InsurancePolicyController {

	@Value("${dataccessPort}")
	private String dataccessPort;

	@PostMapping
	private InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest obj) {
		InsurancePolicyRequest newInsuranceReq = restTemplate()
				.postForObject(getDataccessPortHttps() + "/insurancePolicy", obj, InsurancePolicyRequest.class);
		return newInsuranceReq;
	}

	@PostMapping("/calculateSuggestedPrice")
	private Double calculateSuggestedPrice(@RequestBody InsurancePolicyCalculatePriceRequest obj) {
		obj.setAmount(15l); // izbrisati nakon ispravljenog fronta
		Double price = restTemplate().postForObject(
				getDataccessPortHttps() + "/insurancePolicy/calculateSuggestedPrice", obj, Double.class);
		return price;
	}
	

	public String getDataccessPortHttps() {
		return dataccessPort.replace("http", "https").toString();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
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
