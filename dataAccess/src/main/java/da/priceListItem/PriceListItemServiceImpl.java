package da.priceListItem;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PriceListItemServiceImpl implements PriceListItemService{
	
	@Autowired
	private PriceListItemRepository repository;

	@Override
	public List<PriceListItem> findAll() {
		return (List<PriceListItem>) repository.findAll();
	}

	@Override
	public PriceListItem findOne(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public PriceListItem save(PriceListItem item) {
		return repository.save(item);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public PriceListItem findByFactorIdAndPriceListId(Long factorId, Long priceListId) {
		// TODO Auto-generated method stub
		return repository.findByFactorIdAndPriceListId(factorId, priceListId);
	}
}