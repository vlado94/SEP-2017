package isok.isok.rules;

import java.util.ArrayList;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.request.InsurancePolicyRequest;

@Service
public class RuleService {

	private final KieContainer kieContainer;
	   
    @Autowired
    public RuleService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
    
    public InsurancePolicyRequest getClassifiedItem(InsurancePolicyRequest i) {
    	
    	ArrayList<Integer> lista=new ArrayList<>();
    	
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(i);
        kieSession.insert(lista);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(lista.size());
        return i;
    }
	
}
