package da.categoryFactor;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.factor.Factor;
import da.factor.FactorRepository;

@Service
@Transactional
public class CategoryFactorServiceImpl implements CategoryFactorService{
	
	@Autowired
	private CategoryFactorRepository repository;

	@Autowired
	private FactorRepository factorRepository;

	@Override
	public List<CategoryFactor> findAll() {
		return (List<CategoryFactor>) repository.findAll();
	}

	@Override
	public CategoryFactor findOne(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public CategoryFactor save(CategoryFactor categoryFactor) {
		return repository.save(categoryFactor);
	}

	@Override
	public void delete(Long id) {
		/* treba spustiti na repo*/
		boolean temp = false;
		List<Factor> list = (List<Factor>)factorRepository.findAll();
		for (Factor factor : list) 
			if(factor.getCategory().getId() == id)
					temp = true;
		if(temp == false)
			repository.deleteById(id);
		else
			throw new BadRequestException();
	}
}
