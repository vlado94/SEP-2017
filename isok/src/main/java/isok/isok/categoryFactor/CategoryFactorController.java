package isok.isok.categoryFactor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoryFactor")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryFactorController {

	@Autowired
	private CategoryFactorService service;

	@GetMapping
	private List<CategoryFactor> findAll() {
		return service.findAll();
	}

	@PostMapping
	private CategoryFactor saveCategoryFactor(@RequestBody CategoryFactor categoryFactor) {
		CategoryFactor newCategoryFactor = service.save(categoryFactor);
		return newCategoryFactor;
	}

	@GetMapping("/{id}")
	private CategoryFactor findOne(@PathVariable Long id) {
		return service.findOne(id);
	}
	
	@PutMapping
	private CategoryFactor updateCategoryFactor(@RequestBody CategoryFactor categoryFactor) {
		CategoryFactor updateCategory = service.save(categoryFactor);
		return updateCategory;
	}
	
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) {
		try {
			service.delete(id);
			System.out.println("obrisan sa id-em : " + id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
