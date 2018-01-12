package isok.isok.categoryFactor;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import model.dto.CategoryFactor;

@RestController
@RequestMapping("/api/categoryFactor")
public class CategoryFactorController {

	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@GetMapping
	private List<CategoryFactor> findAll() {
		ResponseEntity<CategoryFactor[]> responseEntity = restTemplate().getForEntity(
				getDataccessPortHttps()+"/categoryFactor", CategoryFactor[].class);
		CategoryFactor[] objects = responseEntity.getBody();
		return  Arrays.asList(objects);
	}
	
	@GetMapping("/{id}")
	private CategoryFactor findOne(@PathVariable Long id) {
        CategoryFactor quote = restTemplate().getForObject(
        		getDataccessPortHttps()+"/categoryFactor/"+id, CategoryFactor.class);
		return quote;
	}
	
	@PutMapping
	private CategoryFactor update(@RequestBody CategoryFactor categoryFactor) {	 
		HttpEntity<?> requestEntity = new HttpEntity<Object>(categoryFactor);
		HttpEntity<CategoryFactor> updateCategoryEntity = restTemplate().exchange(
				getDataccessPortHttps()+"/categoryFactor", HttpMethod.PUT, requestEntity, CategoryFactor.class );
		CategoryFactor updateCategory  =  updateCategoryEntity.getBody();
		return updateCategory;
	}
		
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) throws BadRequestException{
		try {
			ResponseEntity<Boolean> retVal = restTemplate().exchange(
					getDataccessPortHttps()+"/categoryFactor/"+id, HttpMethod.DELETE, null, Boolean.class);
			return retVal.getBody();
		} catch(HttpServerErrorException e) {
			throw e;
		}
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