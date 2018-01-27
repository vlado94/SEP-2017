package isok.isok.factor;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	RestTemplate restTemplate;

	@Value("${dataccessPort}")
	private String dataccessPort;

	private static Logger logger = LoggerFactory.getLogger(FactorController.class);

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping
	private List<FactorDTO> findAll() {
		logger.info("Expect factors");
		ResponseEntity<FactorDTO[]> responseEntity = restTemplate.getForEntity(getDataccessPortHttps() + "/factor",
				FactorDTO[].class);
		FactorDTO[] objects = responseEntity.getBody();
		List<FactorDTO> list = Arrays.asList(objects);
		logger.info("Factors are listed");
		return list;

	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@PostMapping
	private FactorDTO save(@RequestBody FactorDTO obj) {
		logger.info("Add factor with name " + obj.getName());
		FactorDTO newFactorDTO = restTemplate.postForObject(getDataccessPortHttps() + "/factor", obj,
				FactorDTO.class);
		logger.info("Factor is added, name of factor is " + newFactorDTO.getName());
		return newFactorDTO;
	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping("/{id}")
	private FactorDTO findOne(@PathVariable Long id) {
		logger.info("Expect factor id " + id);
		FactorDTO factorDTO = restTemplate.getForObject(getDataccessPortHttps() + "/factor/" + id, FactorDTO.class);
		logger.info("Factor with id " + factorDTO.getId() + " is found");
		return factorDTO;
	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@PutMapping
	private FactorDTO update(@RequestBody FactorDTO obj) {
		logger.info("Update factor with id " + obj.getId());
		HttpEntity<?> requestEntity = new HttpEntity<Object>(obj);
		System.out.println("update");
		HttpEntity<FactorDTO> updateFactorEntity = restTemplate.exchange(getDataccessPortHttps() + "/factor",
				HttpMethod.PUT, requestEntity, FactorDTO.class);
		FactorDTO updateFactorDTO = updateFactorEntity.getBody();
		logger.info("Factory with id " + obj.getId() + " is updated");
		return updateFactorDTO;
	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) throws BadRequestException {
		logger.info("Delete factor with id " + id);
		try {
			ResponseEntity<Boolean> retVal = restTemplate.exchange(getDataccessPortHttps() + "/factor/" + id,
					HttpMethod.DELETE, null, Boolean.class);
			logger.info("Factor with id " + id + " is deleted");
			return retVal.getBody();

		} catch (HttpServerErrorException e) {
			logger.info("Factor isn't deleted successfully");
			throw e;
		}
	}

	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping("/category/{id}")
	private List<FactorDTO> findFactorsByID(@PathVariable Long id) {
		logger.info("Find category by factor id " + id);
		ResponseEntity<FactorDTO[]> responseEntity = restTemplate
				.getForEntity(getDataccessPortHttps() + "/factor/category/" + id, FactorDTO[].class);
		FactorDTO[] objects = responseEntity.getBody();
		logger.info("Category is found");
		return Arrays.asList(objects);
	}

	public String getDataccessPortHttps() {
		return dataccessPort.replace("http", "https").toString();
	}


}
