package da.categoryFactor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import da.insurancePolicy.InsurancePolicyController;

@RestController
@RequestMapping("/categoryFactor")
public class CategoryFactorController {

	@Autowired
	private CategoryFactorService service;

	private static Logger logger = LoggerFactory.getLogger(InsurancePolicyController.class);
	
	@GetMapping
	private List<CategoryFactor> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	private CategoryFactor findOne(@PathVariable Long id) {
		return service.findOne(id);
	}

	@PutMapping
	private CategoryFactor update(@RequestBody CategoryFactor categoryFactor) {
		CategoryFactor cForUpdate = service.findOne(categoryFactor.getId());
		categoryFactor.setName(cForUpdate.getName());
		CategoryFactor updateCategory = service.save(categoryFactor);
		logger.info("Dodana kategorija "+categoryFactor.getName());
		return updateCategory;
	}

	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) {
		try {
			service.delete(id);
			logger.info("Obrisana kategorija sa id-jem "+id);
			return true;
		} catch(Exception e) {
			throw new BadRequestException();
		}
	}
	
	@GetMapping("/checkPin/{pin}")
	private Boolean doCheck(@PathVariable int pin) throws IOException {
		//dodati i parametar korisnika ciju cemo karticu pokrenuti
			
		ArrayList<String> pinStr=new ArrayList<>();
		boolean response=true;//init
		LinkedList<Integer> stack = new LinkedList<Integer>();
			
		while (pin > 0)
		{
		    stack.push( pin % 10 );
		    pin = pin / 10;
		}
		
		while (!stack.isEmpty())
		{
			pinStr.add(Integer.toString(stack.pop()));		
		}
			
		String APDU_PIN="0x80 0x20 0x00 0x00 0x05";
		for(int i=0;i<5;i++)
		{
			APDU_PIN+=" 0x0"+ pinStr.get(i);
		}
		
		APDU_PIN+=" 0x7F;";
			
		System.out.println(APDU_PIN);
			
		try(FileWriter fw = new FileWriter("C:\\JavaCard33\\samples\\classic_applets\\Wallet\\"
			+ "wallet.scr", true);
				
		BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw))
		    {
				out.println(APDU_PIN);
			} catch (IOException e) {
		     //exception handling left as an exercise for the reader
			}
       
		
		ProcessBuilder builder1 = new ProcessBuilder(
	            "cmd.exe", "/c", "cd \"C:\\JavaCard33\\bin\" && cref");
	    builder1.redirectErrorStream(true);
	    Process p1;
		p1 = builder1.start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd \"C:\\JavaCard33\\samples\\classic_applets\\Wallet\\applet\" && ant all");
	    builder.redirectErrorStream(true);
	    Process p;
			
		p = builder.start();
			
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    String line;
	    while (true)
	    {
	       line = r.readLine();
	       if (line == null) { break; }
	       System.out.println(line);
	    }
	        
	    //read output
	    BufferedReader reader;
	    try {
	    	reader = new BufferedReader(new FileReader(
			"C:\\JavaCard33\\samples\\classic_applets\\Wallet\\applet\\default.output"));
			String line1 = reader.readLine();
			while (line1 != null)
			{
				System.out.println(line1);
				// read next line
				line1 = reader.readLine();
			}
				reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
	    FileInputStream in = new FileInputStream("C:\\JavaCard33\\samples\\classic_applets\\Wallet\\applet\\default.output");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		 
		  String strLine = null, tmp;
		 
		  while ((tmp = br.readLine()) != null)
		  {
		     strLine = tmp;
		  }
		 
		  String lastLine = strLine;
		  System.out.println("LAST APDU");
		  System.out.println(lastLine);
		  String wrongPinResponse="SW1: 63";
		  String correctPinResponse="SW1: 90";
		 
		  
		if(lastLine.toLowerCase().contains(wrongPinResponse.toLowerCase()))
		{
		    System.out.println("WRONG PIN");  
			response=false;
		}
		if(lastLine.toLowerCase().contains(correctPinResponse.toLowerCase()))
		{
			System.out.println("CORRECT PIN");
			response=true;
		}
		in.close();
		
		return response;
		
	}
}