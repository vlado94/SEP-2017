package da.person;

import java.util.List;

public interface PersonService {

	public List<Person> findAll();

	public Person findById(Long id);

	public Person save(Person person);
	
	public void delete(Long id);

	public Person findByJmbg(String jmbg);
}
