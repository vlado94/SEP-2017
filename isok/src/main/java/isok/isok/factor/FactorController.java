package isok.isok.factor;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import model.dto.FactorDTO;

@RestController
@RequestMapping("/api/factor")
public class FactorController {

	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping
	private List<FactorDTO> findAll() {
		ResponseEntity<FactorDTO[]> responseEntity = restTemplate().getForEntity(
				dataccessPort+"/factor", FactorDTO[].class);
		FactorDTO[] objects = responseEntity.getBody();
		List<FactorDTO> list =  Arrays.asList(objects);
		return list;
		
	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@PostMapping
	private FactorDTO save(@RequestBody FactorDTO obj) {
		FactorDTO newFactorDTO = restTemplate().postForObject(
				dataccessPort+"/factor", obj, FactorDTO.class);
		
		return newFactorDTO;
	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping("/{id}")
	private FactorDTO findOne(@PathVariable Long id) {
		FactorDTO factorDTO = restTemplate().getForObject(
				dataccessPort+"/factor/"+id, FactorDTO.class);
		return factorDTO;
	}
	
	@PreAuthorize("hasAnyRole('seller','price_management')")
	@PutMapping
	private FactorDTO update(@RequestBody FactorDTO obj) {
		HttpEntity<?> requestEntity = new HttpEntity<Object>(obj);
		HttpEntity<FactorDTO> updateFactorEntity = restTemplate().exchange(
				dataccessPort+"/factor", HttpMethod.PUT, requestEntity, FactorDTO.class );
		FactorDTO updateFactorDTO  =  updateFactorEntity.getBody();
		return updateFactorDTO;
	}
	
	@PreAuthorize("hasAnyRole('seller','price_management')")
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) throws BadRequestException{
		try {
			ResponseEntity<Boolean> retVal = restTemplate().exchange(
					dataccessPort+"/factor/"+id, HttpMethod.DELETE, null, Boolean.class);
			return retVal.getBody();
		} catch(HttpServerErrorException e) {
			throw e;
		}
	}
	
	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping("/category/{id}")
	private List<FactorDTO> findFactorsByID(@PathVariable Long id) {
		ResponseEntity<FactorDTO[]> responseEntity = restTemplate().getForEntity(
				dataccessPort+"/factor/category/"+id, FactorDTO[].class);
		FactorDTO[] objects = responseEntity.getBody();
		return  Arrays.asList(objects);
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
