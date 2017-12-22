package da.insurancePolicy;

import java.util.List;

import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyRequest;

public interface InsurancePolicyService {

	public List<InsurancePolicy> findAll();

	public InsurancePolicy findOne(Long id);

	public InsurancePolicy save(InsurancePolicy insurancePolicy);

	public void delete(Long id);

	public Double calculatePolice(InsurancePolicyRequest ipr);
	
	public Double calculateSuggestedPrice(InsurancePolicyCalculatePriceRequest policy);

}
