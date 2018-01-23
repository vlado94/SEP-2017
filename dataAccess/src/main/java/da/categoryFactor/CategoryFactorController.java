package da.categoryFactor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import da.insurancePolicy.InsurancePolicyController;

@RestController
@RequestMapping("/categoryFactor")
public class CategoryFactorController {

	@Autowired
	private CategoryFactorService service;

	private static Logger logger = LoggerFactory.getLogger(InsurancePolicyController.class);
	
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
		logger.info("Dodana kategorija "+categoryFactor.getName());
		return updateCategory;
	}

	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) {
		try {
			service.delete(id);
			logger.info("Obrisana kategorija sa id-jem "+id);
			return true;
		} catch(Exception e) {
			throw new BadRequestException();
		}
	}
	
	
}