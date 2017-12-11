package isok.isok.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

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

import model.dto.PriceListItemDTO;
import model.request.InsurancePolicyRequest;

@RestController
@RequestMapping("/rules")
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
	
	@GetMapping("/getRules")
	private String getRules() {
		String p="";
		try {
			String s=new File(".").getCanonicalPath();
			s=s.substring(0, s.indexOf("\\isok"));
			s=(s+"\\drools-spring-v2-kjar\\src\\main\\resources\\drools\\spring\\rules\\rules2.drl");
			System.out.println(s);
			File f= new File(s);
			FileReader fileReader = 
	                new FileReader(s);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            
	            String x;
	            
	            while((x = bufferedReader.readLine()) != null) {
	                System.out.println(x);
	                p+=(x);
	            }   

	            // Always close files.
	            bufferedReader.close();
	           
	            return p;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "greska";
	}
	
}
