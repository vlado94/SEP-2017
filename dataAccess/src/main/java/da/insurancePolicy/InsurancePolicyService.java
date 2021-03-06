package da.insurancePolicy;

import java.util.List;

import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyCheckoutRequest;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
import model.response.InsurancePolicyCalculatePriceResponse;
import model.response.InsurancePolicyCheckoutResponse;

public interface InsurancePolicyService {

	public List<InsurancePolicy> findAll();

	public InsurancePolicy findOne(Long id);

	public InsurancePolicy save(InsurancePolicy insurancePolicy);

	public void delete(Long id);

	public InsurancePolicyCalculatePriceResponse calculatePolice(InsurancePolicyRequest ipr);
	
	public InsurancePolicyCalculatePriceResponse calculateSuggestedPrice(InsurancePolicyCalculatePriceRequest policy);

	public Double calculateSuggestedPriceHome(InsurancePolicyHomeCalculatePriceRequest policy);

	public InsurancePolicyCalculatePriceResponse calculateSuggestedPriceCar(InsurancePolicyCarCalculatePriceRequest request);

	public InsurancePolicyCheckoutResponse getCheckout(InsurancePolicyCheckoutRequest request);

}
