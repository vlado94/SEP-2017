package da.rules;

import java.util.ArrayList;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.dto.Popust;
import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyRequest;

@Service
public class RuleService {

	private final KieContainer kieContainer;
	   
    @Autowired
    public RuleService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
    
    public ArrayList<Popust> getClassifiedItem(InsurancePolicyRequest i) {
    	
    	ArrayList<Popust> lista=new ArrayList();
  
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(i);
        kieSession.insert(lista);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(lista.size());
        return lista;
    }
    
    public ArrayList<Popust> getClassifiedItem(InsurancePolicyCalculatePriceRequest request) {
    	
    	ArrayList<Popust> lista=new ArrayList();
  
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(request);
        kieSession.insert(lista);
        kieSession.fireAllRules();
        kieSession.dispose();
        
        return lista;
    }
	
    
  public ArrayList<Popust> getClassifiedItem(InsurancePolicyCarCalculatePriceRequest request) {
    	
    	ArrayList<Popust> lista=new ArrayList();
  
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(request);
        kieSession.insert(lista);
        kieSession.fireAllRules();
        kieSession.dispose();
        
        return lista;
    }
	
}
