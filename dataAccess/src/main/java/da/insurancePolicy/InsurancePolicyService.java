package da.insurancePolicy;

import java.util.List;

public interface InsurancePolicyService {

	public List<InsurancePolicy> findAll();

	public InsurancePolicy findOne(Long id);

	public InsurancePolicy save(InsurancePolicy insurancePolicy);

	public void delete(Long id);

}
