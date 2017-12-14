package da.categoryFactor;

import java.util.List;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoryFactor")
public class CategoryFactorController {

	@Autowired
	private CategoryFactorService service;

	@GetMapping
	private List<CategoryFactor> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	private CategoryFactor findOne(@PathVariable Long id) {
		return service.findOne(id);
	}

	@PutMapping
	private CategoryFactor update(@RequestBody CategoryFactor categoryFactor) {
		CategoryFactor cForUpdate = service.findOne(categoryFactor.getId());
		categoryFactor.setName(cForUpdate.getName());
		CategoryFactor updateCategory = service.save(categoryFactor);
		return updateCategory;
	}

	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return true;
		} catch(Exception e) {
			throw new BadRequestException();
		}
	}
}