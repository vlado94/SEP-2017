package isok.isok.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.request.InsurancePolicyRequest;
/*
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
*/