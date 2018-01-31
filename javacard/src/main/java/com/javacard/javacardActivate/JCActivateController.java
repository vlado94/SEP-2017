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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javacard.PaymentRequestCard.PaymentRequestCard;

import model.dto.BankMemberDTO;
import model.request.PinRequest;

@RestController
@RequestMapping("/activateJC")
@CrossOrigin(origins = "http://localhost:4500")
public class JCActivateController {



	@Value("${acquirerPort}")
	private String acquirerPort;

	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/checkPin")
	public String doCheck(@RequestBody PinRequest obj) throws IOException {
		
		boolean cardBlocked=false;
		boolean correctPin=true;
		boolean payment=false;
		
		int pinCounter=0;
		String wrongPinResponse="SW1: 63";
		String correctPinResponse="SW1: 90";
		String validationComand="INS: 20";
		String response="";//Knit
		
		String cardNum =restTemplate.postForObject(acquirerPort+"/bankMember/getCardNumber", obj, String.class);
	   
		PaymentRequestCard request=new PaymentRequestCard();
		request.setCardNum(cardNum);
		request.setPolicyID(obj.getPolicyId());
		request.setPolicyPrice(obj.getTotalPrice());
		//////////////////////////////////////////  
		String finalDestination=selectCard(cardNum);
		
		generateAPDU(obj.getPin(),finalDestination);
		
		cref();

		antAll(finalDestination);
	    /////////////////////////////////
		
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
						restTemplate.postForObject(acquirerPort+"/bankMember/blockCard", obj, String.class);
						cardBlocked = true;
						System.out.println("3 neuspesna pokusaja,kartica je blokirana");
					}
					else
					{
						cardBlocked=false;
					}
					
				}
				line1 = reader.readLine();
			}
				reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
	    
	    BufferedReader reader2;
	    try {
	    	reader2 = new BufferedReader(new FileReader(
			"C:\\JavaCard33\\samples\\"
			+ "classic_applets\\"+finalDestination+"\\wallet.scr"));
			String line1 = reader2.readLine();
			String[] temp;
			while (line1 != null)
			{
				if(line1.contains("PAN"))
				{
					temp=line1.split(" ");
					System.out.println("PAN PAN PAN PAN PAN");
					System.out.println(temp[1]);
					request.setBillNum(temp[1]);
				}
				
				line1 = reader2.readLine();
			}
				reader2.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
	    
	   
	    FileInputStream in = new FileInputStream("C:\\JavaCard33\\samples"
	    		+ "\\classic_applets\\"+finalDestination+"\\applet\\default.output");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		 
		  String strLine = null, tmp;
		 
		  while ((tmp = br.readLine()) != null)
		  {
		     strLine = tmp;
		  }
		 
		  String lastLine = strLine;
		  System.out.println("LAST APDU: " +lastLine);
		  
		 
		 
		  
		if(lastLine.toLowerCase().contains(wrongPinResponse.toLowerCase()))
		{
		    System.out.println("WRONG PIN");  
		    System.out.println(pinCounter);  
		    correctPin=false;
			
		}
		if(lastLine.toLowerCase().contains(correctPinResponse.toLowerCase()))
		{
			System.out.println("CORRECT PIN");
			correctPin=true;
			payment=restTemplate.postForObject(acquirerPort+"/payment/payfromcard", request, Boolean.class);
			
		}
		in.close();
		
		if(correctPin==false){
			if(cardBlocked==false){
				response="Wrong pin";
			}
			else{
				response="Wrong pin,card is blocked";
			}
		}
		else{
			
			if(payment==true){
				response="Done";
			}
			else{
				response="Error";
			}
		}
		return response;
		
	}
	
	@GetMapping("/getResponse")
	private String getResponse() {
		return "Done";
		
	}
	
	private void generateAPDU(int pin,String finalDestination){
		ArrayList<String> pinStr=new ArrayList<>();
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
	}
	private String selectCard(String cardNum)

	{
		String folder="";

		if(cardNum.equals("132134"))
		{
			//111234 PAN
			folder="Card1";
		}
		if(cardNum.equals("132135"))
		{
			//111235
			folder="Card2";
		}
		if(cardNum.equals("132136"))
		{
			//111236
			folder="Card3";
		}
		if(cardNum.equals("132137"))
		{
			//222234
			folder="Card4";
		}
		
		if(cardNum.equals("132138"))
		{
			//222235
			folder="Card5";
		}
		if(cardNum.equals("132139"))
		{
			//222234
			folder="Card6";
		}
		
		return folder;
	}

	private void cref() throws IOException{
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
		
		
	   
	}
	private void antAll(String finalDestination) throws IOException{
	
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
	   
	}
}
