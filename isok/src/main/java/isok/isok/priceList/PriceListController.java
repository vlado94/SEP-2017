package isok.isok.priceList;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.dto.PriceListItemDTO;

@RestController
@RequestMapping("/priceList")
@CrossOrigin(origins = "http://localhost:4200")
public class PriceListController {

	@Value("${dataccessPort}")
	private String dataccessPort;

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping("/findLast")
	private List<PriceListItemDTO> findLast() {
		List<PriceListItemDTO> list = Arrays.asList(restTemplate()
				.getForEntity(getDataccessPortHttps() + "/priceList/findLast", PriceListItemDTO[].class).getBody());
		return list;
	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@PostMapping
	private boolean save(@RequestBody List<Double> obj) {
		boolean result = restTemplate().postForObject(getDataccessPortHttps() + "/priceList", obj, boolean.class);

		return result;
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
	}
}
