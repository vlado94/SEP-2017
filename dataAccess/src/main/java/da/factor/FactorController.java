package da.factor;

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

import da.categoryFactor.CategoryFactorService;
import da.dto.FactorDTO;



@RestController
@RequestMapping("/factor")
public class FactorController {

	@Autowired
	private FactorService service;
	
	@Autowired
	private CategoryFactorService categoryService;

	@GetMapping
	private List<FactorDTO> findAll() {
		List<Factor> list = service.findAll();
		
		List<FactorDTO> retVal = new ArrayList<FactorDTO>();
		for (Factor f : list) {
			FactorDTO temp = f.getDTO();
			temp.setCategoryName(f.getCategory().getName());
			retVal.add(temp);
		}
		
		return retVal;
	}

	@PostMapping
	private FactorDTO save(@RequestBody FactorDTO obj) {
		Factor f = new Factor();
		f.setName(obj.getName());
		f.setCategory(categoryService.findOne(obj.getCategory()));
		FactorDTO newFactor = service.save(f).getDTO();
		return newFactor;
	}

	@GetMapping("/{id}")
	private FactorDTO findOne(@PathVariable Long id) {
		Factor factor =  service.findOne(id);
		return factor.getDTO();
	}
	
	@PutMapping
	private FactorDTO update(@RequestBody FactorDTO obj) {
		Factor f = new Factor();
		f.setId(obj.getId());
		f.setName(obj.getName());
		f.setCategory(categoryService.findOne(obj.getCategory()));
		FactorDTO updateFactor = service.save(f).getDTO();
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
