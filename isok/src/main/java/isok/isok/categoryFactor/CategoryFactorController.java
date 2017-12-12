package isok.isok.categoryFactor;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@GetMapping
	private List<CategoryFactor> findAll() {
		ResponseEntity<CategoryFactor[]> responseEntity = restTemplate().getForEntity(
				dataccessPort.toString()+"/categoryFactor", CategoryFactor[].class);
		CategoryFactor[] objects = responseEntity.getBody();
		return  Arrays.asList(objects);
	}
	
	@GetMapping("/{id}")
	private CategoryFactor findOne(@PathVariable Long id) {
        CategoryFactor quote = restTemplate().getForObject(
        		dataccessPort.toString()+"/categoryFactor/"+id, CategoryFactor.class);
		return quote;
	}
	
	@PutMapping
	private CategoryFactor update(@RequestBody CategoryFactor categoryFactor) {	 
		HttpEntity<?> requestEntity = new HttpEntity<Object>(categoryFactor);
		HttpEntity<CategoryFactor> updateCategoryEntity = restTemplate().exchange(
				dataccessPort.toString()+"/categoryFactor", HttpMethod.PUT, requestEntity, CategoryFactor.class );
		CategoryFactor updateCategory  =  updateCategoryEntity.getBody();
		return updateCategory;
	}
		
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) throws BadRequestException{
		try {
			ResponseEntity<Boolean> retVal = restTemplate().exchange(
					dataccessPort.toString()+"/categoryFactor/"+id, HttpMethod.DELETE, null, Boolean.class);
			return retVal.getBody();
		} catch(HttpServerErrorException e) {
			throw e;
		}
	}
}