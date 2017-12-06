package isok.isok.priceList;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.dto.FactorDTO;
import model.dto.PriceListItemDTO;

@RestController
@RequestMapping("/priceList")
@CrossOrigin(origins = "http://localhost:4200")
public class PriceListController {

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@GetMapping("/findLast")
	private List<PriceListItemDTO> findLast() {
		List<PriceListItemDTO> list = Arrays.asList(restTemplate().getForEntity(
				dataccessPort.toString()+"/priceList/findLast", PriceListItemDTO[].class)
				.getBody());
		return list;
	}
	
	@PostMapping
	private boolean save(@RequestBody List<Double> obj) {
		boolean result = restTemplate().postForObject(
				dataccessPort.toString()+"/priceList", obj, boolean.class);
		
		return result;
	}
}
