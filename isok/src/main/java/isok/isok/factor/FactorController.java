package isok.isok.factor;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import isok.isok.categoryFactor.CategoryFactor;
import isok.isok.dto.FactorDTO;

@RestController
@RequestMapping("/factor")
@CrossOrigin(origins = "http://localhost:4200")
public class FactorController {


	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@GetMapping
	private List<FactorDTO> findAll() {
		ResponseEntity<FactorDTO[]> responseEntity = restTemplate().getForEntity(
				dataccessPort.toString()+"/factor", FactorDTO[].class);
		FactorDTO[] objects = responseEntity.getBody();
		List<FactorDTO> list =  Arrays.asList(objects);
		return list;
	}

	@PostMapping
	private FactorDTO save(@RequestBody FactorDTO obj) {
		FactorDTO newFactorDTO = restTemplate().postForObject(
				dataccessPort.toString()+"/factor/save", obj, FactorDTO.class);
		
		return newFactorDTO;
	}

	@GetMapping("/{id}")
	private FactorDTO findOne(@PathVariable Long id) {
		FactorDTO factorDTO = restTemplate().getForObject(
	        		dataccessPort.toString()+"/factor/"+id, FactorDTO.class);
		return factorDTO;
	}
	
	@PutMapping
	private FactorDTO update(@RequestBody FactorDTO obj) {
		HttpEntity<?> requestEntity = new HttpEntity<Object>(obj);
		HttpEntity<FactorDTO> updateFactorEntity = restTemplate().exchange(
				dataccessPort.toString()+"/factor/update", HttpMethod.PUT, requestEntity, FactorDTO.class );
		FactorDTO updateFactorDTO  =  updateFactorEntity.getBody();
		return updateFactorDTO;
		
	}
	
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) {
		try {
			HttpEntity<Boolean> updateCategoryEntity = restTemplate().exchange(
					dataccessPort.toString()+"/factor/delete/"+id, HttpMethod.DELETE, null, Boolean.class);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
