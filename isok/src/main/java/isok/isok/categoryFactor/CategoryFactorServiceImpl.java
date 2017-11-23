package isok.isok.categoryFactor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryFactorServiceImpl implements CategoryFactorService{
	
	@Autowired
	private CategoryFactorRepository repository;

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
		// TODO Auto-generated method stub
		return repository.save(categoryFactor);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
