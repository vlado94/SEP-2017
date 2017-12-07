package da.factor;

import java.util.List;

public interface FactorService {
	
	public List<Factor> findAll();
	
	public Factor findOne(Long id);
	
	public Factor save(Factor factor);
	
	public void delete(Long id);

	public List<Factor> findByCategory(Long categoryId);

}
