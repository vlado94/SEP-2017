package da.priceList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PriceListServiceImpl implements PriceListService{
	
	@Autowired
	private PriceListRepository repository;

	@Override
	public List<PriceList> findAll() {
		return (List<PriceList>) repository.findAll();
	}

	@Override
	public PriceList findOne(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public PriceList save(PriceList factor) {
		return repository.save(factor);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public PriceList findCurrent() {
		// TODO Auto-generated method stub
		return repository.findFirstByOrderByIdDesc();
	}
}
