package da.factor;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FactorRepository extends PagingAndSortingRepository<Factor, Long>{

	//@Query("SELECT f FROM Factor f WHERE f.category.id=:categoryId")
	List<Factor> findByCategoryId(Long categoryId);

}
