package com.javacard.javacardActivate;

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
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activateJC")
@CrossOrigin(origins = "http://localhost:4500")
public class JCActivateController {

	@GetMapping("/checkPin/{pin}")
	private Boolean doCheck(@PathVariable int pin) throws IOException {
		//dodati i parametar korisnika ciju cemo karticu pokrenuti
		
		int id=1;
		String finalDestination="";
		
		if(id==1)
		{
			finalDestination="Wallet";
		}
		if(id==2)
		{
			finalDestination="Wallet1";
		}
		if(id==3)
		{
			finalDestination="Wallet2";
		}
		if(id==4)
		{
			finalDestination="Wallet3";
		}
		if(id==5)
		{
			finalDestination="Wallet4";
		}
		
		
		System.out.println(finalDestination);	
		
		boolean cardBlocked;
		int pinCounter=0;
		String wrongPinResponse="SW1: 63";
		String correctPinResponse="SW1: 90";
		String validationComand="INS: 20";
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
			
		String APDU_PIN="0x80 0x20 0x00 0x00 0x04";
		for(int i=0;i<4;i++)
		{
			APDU_PIN+=" 0x0"+ pinStr.get(i);
		}
		
		APDU_PIN+=" 0x7F;";
			
		System.out.println(APDU_PIN);
			
		try(FileWriter fw = new FileWriter("C:\\JavaCard33\\samples\\"
				+ "classic_applets\\"+finalDestination+"\\"
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
	            "cmd.exe", "/c", "cd \"C:\\JavaCard33\\samples\\"
	            		+ "classic_applets\\"+finalDestination+"\\applet\" && ant all");
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
	   
	    /////////////////////////////////////////////
	    /////////////////////////////////////////////
	    //read output, provera da li ima 3 neuspela pokusaja za pin, ako ima, kartica je blokirana
	    BufferedReader reader;
	    try {
	    	reader = new BufferedReader(new FileReader(
			"C:\\JavaCard33\\samples\\"
			+ "classic_applets\\"+finalDestination+"\\applet\\default.output"));
			String line1 = reader.readLine();
			while (line1 != null)
			{
				System.out.println(line1);
				
				if(line1.toLowerCase().contains(validationComand.toLowerCase()))
				{
					if(line1.toLowerCase().contains(wrongPinResponse.toLowerCase()))
					{
						++pinCounter;
					}
					else
					{
						pinCounter=0;
					} 
					
					if(pinCounter==3)
					{
						//////
						// ukoliko je 3 puta za redom pogresen pin
						// ubaciti deo d se u bazi promeni da je kartica boliranaa 
						//////
						cardBlocked = true;
						System.out.println("3 neuspesna pokusaja,kartica je blokirana");
					}
					else
					{
						cardBlocked=false;
					}
					
				}
				
				// read next line
				line1 = reader.readLine();
			}
				reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
	    
	    
	    ///////////
	    //citanje poslednje komande
	    ///////////
	    FileInputStream in = new FileInputStream("C:\\JavaCard33\\samples"
	    		+ "\\classic_applets\\"+finalDestination+"\\applet\\default.output");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		 
		  String strLine = null, tmp;
		 
		  while ((tmp = br.readLine()) != null)
		  {
		     strLine = tmp;
		  }
		 
		  String lastLine = strLine;
		  System.out.println("LAST APDU");
		  System.out.println(lastLine);
		 
		 
		  
		if(lastLine.toLowerCase().contains(wrongPinResponse.toLowerCase()))
		{
		    System.out.println("WRONG PIN");  
		    System.out.println(pinCounter);  
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
