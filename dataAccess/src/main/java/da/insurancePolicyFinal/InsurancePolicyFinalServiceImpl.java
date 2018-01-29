package da.insurancePolicyFinal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InsurancePolicyFinalServiceImpl implements InsurancePolicyFinalService{

	@Autowired
	private InsurancePolicyFinalRepository repo;
	
	@Override
	public InsurancePolicyFinal save(InsurancePolicyFinal insurancePolicyFinal) {
		// TODO Auto-generated method stub
		return repo.save(insurancePolicyFinal);
	}

	@Override
	public InsurancePolicyFinal findById(Long id) {
		return repo.findById(id).get();
	}

	
}
