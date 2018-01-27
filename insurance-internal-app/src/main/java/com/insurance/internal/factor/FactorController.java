package com.insurance.internal.factor;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

	@PreAuthorize("hasRole('seller')")
	@GetMapping("/category/{id}")
	private List<FactorDTO> findFactorsByID(@PathVariable Long id) {
		// KeycloakAuthenticationProvider keycloakAuthenticationProvider =
		// keycloakAuthenticationProvider();
		/*
		 * KeycloakAuthenticationProvider auth = new KeycloakAuthenticationProvider();
		 * AccessToken.Access access = accessToken.getRealmAccess(); Set<String> roles =
		 * access.getRoles(); System.out.println(roles.size()); String s =
		 * session.getId();
		 */
		// System.out.println("first " + accessToken.getName());

		ResponseEntity<FactorDTO[]> responseEntity = restTemplate
				.getForEntity(dataccessPort + "/factor/category/" + id, FactorDTO[].class);
		FactorDTO[] objects = responseEntity.getBody();
		return Arrays.asList(objects);
	}

	/*@Bean
	public RestTemplate restTemplate() {
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());// TODO: hide password
			keyStore.load(new FileInputStream(new File("sep.p12")), "sep12345".toCharArray());

			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
					new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy())
							.loadKeyMaterial(keyStore, "sep12345".toCharArray()).build(),
					NoopHostnameVerifier.INSTANCE);

			HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

			ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
			RestTemplate restTemplate = new RestTemplate(requestFactory);

			return restTemplate;
		} catch (Exception exc) {
			return null;
		}
	}*/

}
