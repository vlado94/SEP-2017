package isok.isok.rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.maven.cli.MavenCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.request.InsurancePolicyRequest;
import model.request.PersonRequest;

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
		InsurancePolicyRequest policy = new InsurancePolicyRequest();
		policy.setDuration(6);
		policy.setPersons(new ArrayList<>());
		PersonRequest person1 = new PersonRequest("Jovan","Jovanovic","1212994156225","12563","adress","06451145",28l,false, "fjass@sdha");
		PersonRequest person2 = new PersonRequest("Milan","Milanovic","1906994156225","85952","adress","06451145",62l,false, "fjass@sdha");
		policy.getPersons().add(person1);
		policy.getPersons().add(person2);
		policy.setStartDate(LocalDate.of(2018, 1, 1));
		policy.setRegion(9l);
		policy.setSport(1l);
		//policy.setAmount(15l);
		InsurancePolicyRequest i2 = ruleService.getClassifiedItem(policy);
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
	            bufferedReader.close();
	           */
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

	@PostMapping(value="/changeRules")
	private boolean save(@RequestBody RuleHelper ruleFileAsText) {
		String s;
		try {
			s = new File(".").getCanonicalPath();
			s=s.substring(0, s.indexOf("\\isok"));
			s=(s+"\\drools-spring-v2-kjar\\src\\main\\resources\\drools\\spring\\rules\\rules2.drl");
				Files.write(Paths.get(s), ruleFileAsText.getS().getBytes(), StandardOpenOption.CREATE);
				s = new File(".").getCanonicalPath();
				s=s.substring(0, s.indexOf("\\isok"));
				s+="\\drools-spring-v2-kjar";
				/*
				String[] cmd = { s + "\\mvn install" };
		        Process p = Runtime.getRuntime().exec(cmd);
		        p.waitFor();*/
				/*InvocationRequest request = new DefaultInvocationRequest();
				request.setPomFile( new File( "/path/to/pom.xml" ) );
				request.setGoals( Arrays.asList( "clean", "install" ) );

				Invoker invoker = new DefaultInvoker();
				invoker.execute( request );
				Process p=Runtime.getRuntime().exec("cmd \\c mvn -v",null,new File(s));
				System.out.println("check1");
		        p.waitFor();
		        System.out.println("check2");*/
				MavenCli cli = new MavenCli();
				System.out.println("before");
				cli.doMain(new String[]{"install"}, s, System.out, null);
				System.out.println("after");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return true;
	}
}


