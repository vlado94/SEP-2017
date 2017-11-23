package isok.isok.categoryFactor;

import java.util.List;

public interface CategoryFactorService {
	
	public List<CategoryFactor> findAll();
	
	public CategoryFactor findOne(Long id);
	
	public CategoryFactor save(CategoryFactor categoryFactor);
	
	public void delete(Long id);

}
