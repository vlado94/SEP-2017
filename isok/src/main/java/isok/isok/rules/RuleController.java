package isok.isok.rules;

import java.sql.Date;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.request.InsurancePolicyRequest;

@RestController
@RequestMapping("/rule")
public class RuleController {

	
	
	private final RuleService ruleService;

	@Autowired
	public RuleController(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	
	
	@GetMapping
	private InsurancePolicyRequest getRule() {
		
		
		InsurancePolicyRequest newItem = new InsurancePolicyRequest();
		newItem.setDuration(6);

		

		InsurancePolicyRequest i2 = ruleService.getClassifiedItem(newItem);

		
		return i2;
		
	}
	
}
