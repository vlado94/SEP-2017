package isok.isok.factor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FactorServiceImpl implements FactorService{
	
	@Autowired
	private FactorRepository repository;

	@Override
	public List<Factor> findAll() {
		return (List<Factor>) repository.findAll();
	}

	@Override
	public Factor findOne(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Factor save(Factor factor) {
		return repository.save(factor);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
