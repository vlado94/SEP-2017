package da.person;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long>{

	Person findByJmbg(String jmbg);

}
