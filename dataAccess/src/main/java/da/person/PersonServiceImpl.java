package da.person;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository repository;

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return (List<Person>) repository.findAll();
	}

	@Override
	public Person findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public Person save(Person person) {
		// TODO Auto-generated method stub
		return repository.save(person);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Person findByJmbg(String jmbg) {
		// TODO Auto-generated method stub
		return repository.findByJmbg(jmbg);
	}
}
