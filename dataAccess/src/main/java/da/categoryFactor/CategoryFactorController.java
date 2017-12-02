package da.categoryFactor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import da.dto.FactorDTO;
import da.factor.Factor;
import da.factor.FactorService;

@RestController
@RequestMapping("/categoryFactor")
public class CategoryFactorController {

	@Autowired
	private CategoryFactorService service;
	

	@Autowired
	private FactorService factorService;

	@GetMapping
	private List<CategoryFactor> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/findFactorsByID/{id}")
	private List<FactorDTO> findFactorsByID(@PathVariable Long id) {
		List<FactorDTO> retVal = new ArrayList<FactorDTO>();
		List<Factor> factors = factorService.findAll();
		if(id != 0) {
			for (Factor factor : factors) {
				if(factor.getCategory().getId() == id)
					retVal.add(factor.getDTO());
			}		
		} else {
			for (Factor factor : factors) {
				retVal.add(factor.getDTO());
			}
		}
		return retVal;
	}

	@PostMapping
	private CategoryFactor save(@RequestBody CategoryFactor categoryFactor) {
		CategoryFactor newCategoryFactor = service.save(categoryFactor);
		return newCategoryFactor;
	}

	@GetMapping("/{id}")
	private CategoryFactor findOne(@PathVariable Long id) {
		return service.findOne(id);
	}
	
	@PutMapping
	private CategoryFactor update(@RequestBody CategoryFactor categoryFactor) {
		CategoryFactor updateCategory = service.save(categoryFactor);
		return updateCategory;
	}
	
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}