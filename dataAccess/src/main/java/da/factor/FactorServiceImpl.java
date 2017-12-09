package da.factor;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.insurancePolicy.InsurancePolicy;
import da.insurancePolicy.InsurancePolicyRepository;
import da.priceListItem.PriceListItem;

@Service
@Transactional
public class FactorServiceImpl implements FactorService {

	@Autowired
	private FactorRepository repository;

	@Autowired
	private InsurancePolicyRepository incusranceRepository;

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
		/* treba spustiti na repo*/
		boolean temp = false;
		List<InsurancePolicy> list = (List<InsurancePolicy>)incusranceRepository.findAll();
		for (InsurancePolicy insurancePolicy : list) {
			Set<PriceListItem> priceListItems= insurancePolicy.getPriceListItems();
			for (PriceListItem priceListItem : priceListItems) {
				if(priceListItem.getFactor().getId()==id)
					temp = true;
			}
		}
		if(temp == false)
			repository.deleteById(id);
		else 
			throw new BadRequestException();
	}

	@Override
	public List<Factor> findByCategory(Long categoryId) {

		return repository.findByCategoryId(categoryId);
	}

}
