package isok.isok.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.request.InsurancePolicyRequest;

@RestController
@RequestMapping("/rules")
@CrossOrigin(origins = "http://localhost:4200")
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
			File f= new File(s);
			/*FileReader fileReader = 
	                new FileReader(s);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            
	            
	            
	            String x;
	            while((x = bufferedReader.readLine()) != null) {
	                System.out.println(x);
	                p+=(x);
	                //p+="\\n";
	            }   

	            // Always close files.
	            bufferedReader.close();*/
	           
				FileInputStream fis = new FileInputStream(f);
	            byte[] data = new byte[(int) f.length()];
	            fis.read(data);
	            fis.close();
	
	            String str = new String(data, "UTF-8");
			
	            return str;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "greska";
	}	

	@PostMapping("/changeRules")
	private boolean save(@RequestBody String ruleFileAsText) {
		System.out.println("jdasjja");
		String s;
		try {
			s = new File(".").getCanonicalPath();
			s=s.substring(0, s.indexOf("\\isok"));
			s=(s+"\\drools-spring-v2-kjar\\src\\main\\resources\\drools\\spring\\rules\\rules2.drl");
				Files.write(Paths.get(s), ruleFileAsText.getBytes(), StandardOpenOption.CREATE);
				System.out.println("check1");
				s = new File(".").getCanonicalPath();
				s=s.substring(0, s.indexOf("\\isok"));
				s+="\\drools-spring-v2-kjar";
				
				String[] cmd = { s + "mvn install" };
		        Process p = Runtime.getRuntime().exec(cmd);
		        p.waitFor();
		        System.out.println("check2");
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return true;
	}
	
}
