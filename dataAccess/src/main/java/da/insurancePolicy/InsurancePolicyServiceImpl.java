package da.insurancePolicy;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.priceList.PriceList;
import da.priceList.PriceListService;
import da.priceListItem.PriceListItem;
import da.priceListItem.PriceListItemRepository;
import model.request.InsurancePolicyRequest;

@Service
@Transactional
public class InsurancePolicyServiceImpl implements InsurancePolicyService{

	@Autowired
	private InsurancePolicyRepository repository;

	@Autowired
	private PriceListItemRepository priceListItemRepo;

	@Autowired
	private PriceListService priceListService;

	@Override
	public List<InsurancePolicy> findAll() {
		// TODO Auto-generated method stub
		return (List<InsurancePolicy>) repository.findAll();
	}

	@Override
	public InsurancePolicy findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public InsurancePolicy save(InsurancePolicy insurancePolicy) {
		// TODO Auto-generated method stub
		return repository.save(insurancePolicy);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Double calculatePolice(InsurancePolicyRequest policy) {
		double retVal = 0;
		return retVal;
	}
}