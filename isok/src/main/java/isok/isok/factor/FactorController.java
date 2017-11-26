package isok.isok.factor;

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
@RequestMapping("/factor")
@CrossOrigin(origins = "http://localhost:4200")
public class FactorController {

	@Autowired
	private FactorService service;

	@GetMapping
	private List<Factor> findAll() {
		return service.findAll();
	}

	@PostMapping
	private Factor save(@RequestBody Factor factor) {
		Factor newFactor = service.save(factor);
		return newFactor;
	}

	@GetMapping("/{id}")
	private Factor findOne(@PathVariable Long id) {
		return service.findOne(id);
	}
	
	@PutMapping
	private Factor update(@RequestBody Factor factor) {
		Factor updateFactor = service.save(factor);
		return updateFactor;
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
