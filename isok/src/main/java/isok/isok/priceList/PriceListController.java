package isok.isok.priceList;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	RestTemplate restTemplate;

	@Value("${dataccessPort}")
	private String dataccessPort;
	
	private static Logger logger = LoggerFactory.getLogger(PriceListController.class);

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping("/findLast")
	private List<PriceListItemDTO> findLast() {
		logger.info("Expect last items price");
		List<PriceListItemDTO> list = Arrays.asList(restTemplate
				.getForEntity(getDataccessPortHttps() + "/priceList/findLast", PriceListItemDTO[].class).getBody());
		logger.info("Last items price are listed");
		return list;
	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@PostMapping
	private boolean save(@RequestBody List<Double> obj) {
		logger.info("Change item price list");
		boolean result = restTemplate.postForObject(getDataccessPortHttps() + "/priceList", obj, boolean.class);
		logger.info("Item price list is changed");
		return result;
	}

	public String getDataccessPortHttps() {
		return dataccessPort.replace("http", "https").toString();
	}

}
