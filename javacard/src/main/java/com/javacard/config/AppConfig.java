package com.javacard.config;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {

	
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
