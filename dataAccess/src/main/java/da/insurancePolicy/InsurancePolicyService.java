package da.insurancePolicy;

import java.util.List;

import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCalculatePriceResponce;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;

public interface InsurancePolicyService {

	public List<InsurancePolicy> findAll();

	public InsurancePolicy findOne(Long id);

	public InsurancePolicy save(InsurancePolicy insurancePolicy);

	public void delete(Long id);

	public Double calculatePolice(InsurancePolicyRequest ipr);
	
	public InsurancePolicyCalculatePriceResponce calculateSuggestedPrice(InsurancePolicyCalculatePriceRequest policy);

	public Double calculateSuggestedPriceHome(InsurancePolicyHomeCalculatePriceRequest policy);

	public Double calculateSuggestedPriceCar(InsurancePolicyCarCalculatePriceRequest request);

}
