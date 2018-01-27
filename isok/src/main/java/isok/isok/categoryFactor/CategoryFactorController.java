package isok.isok.categoryFactor;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.keycloak.representations.AccessToken;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import isok.isok.factor.FactorController;
import model.dto.CategoryFactor;

@RestController
@RequestMapping("/api/categoryFactor")
public class CategoryFactorController {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@Autowired
	AccessToken accessToken;
	
	private static Logger logger = LoggerFactory.getLogger(FactorController.class);
	
	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping
	private List<CategoryFactor> findAll() {
		logger.info("Expect category factors");
		ResponseEntity<CategoryFactor[]> responseEntity = restTemplate.getForEntity(
				dataccessPort+"/categoryFactor", CategoryFactor[].class);
		CategoryFactor[] objects = responseEntity.getBody();
		logger.info("Category factors are listed");
		return  Arrays.asList(objects);
	}
	
	@PreAuthorize("hasAnyRole('seller','price_management')")
	@GetMapping("/{id}")
	private CategoryFactor findOne(@PathVariable Long id) {
		logger.info("Expect category factor id " + id);
        CategoryFactor quote = restTemplate.getForObject(
        		dataccessPort+"/categoryFactor/"+id, CategoryFactor.class);
        logger.info("Category factor with " + id + " is found");
		return quote;
	}
	
	@PreAuthorize("hasAnyRole('seller','price_management')")
	@PutMapping
	private CategoryFactor update(@RequestBody CategoryFactor categoryFactor) {	 
		logger.info("Update cateogry factor with id " + categoryFactor.getId());
		HttpEntity<?> requestEntity = new HttpEntity<Object>(categoryFactor);
		HttpEntity<CategoryFactor> updateCategoryEntity = restTemplate.exchange(
				dataccessPort+"/categoryFactor", HttpMethod.PUT, requestEntity, CategoryFactor.class );
		CategoryFactor updateCategory  =  updateCategoryEntity.getBody();
		System.out.println("User " + accessToken.getName() + " updated category factor " + updateCategory.getName() + " to " + updateCategory.getBasePrice());
		logger.info("Category factor with id " + updateCategory.getId() + " is updated");
		return updateCategory;
	}
		
	@PreAuthorize("hasAnyRole('seller','price_management')")
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) throws BadRequestException{
		logger.info("Delete category factor with id " + id);
		try {
			ResponseEntity<Boolean> retVal = restTemplate.exchange(
					dataccessPort+"/categoryFactor/"+id, HttpMethod.DELETE, null, Boolean.class);
			logger.info("Cateogry factor with id " + id + " is deleted");
			return retVal.getBody();
		} catch(HttpServerErrorException e) {
			logger.info("Category factor isn't deleted successfully");
			throw e;
		}
	}
}