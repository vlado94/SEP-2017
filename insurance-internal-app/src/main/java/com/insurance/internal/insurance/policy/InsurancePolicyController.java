package com.insurance.internal.insurance.policy;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCalculatePriceResponse;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyCheckoutRequest;
import model.request.InsurancePolicyCheckoutResponse;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
@RestController
@RequestMapping("/internal/insurancePolicy")
@CrossOrigin(origins = "http://localhost:4500")
public class InsurancePolicyController {
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PreAuthorize("hasRole('seller')")
	@PostMapping
	private InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest obj) {
		InsurancePolicyRequest newInsuranceReq = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy", obj, InsurancePolicyRequest.class);
		return newInsuranceReq;
	}
	
	@PreAuthorize("hasRole('seller')")
	@PostMapping("/calculateSuggestedPrice")
	private InsurancePolicyCalculatePriceResponse calculateSuggestedPrice(@RequestBody InsurancePolicyCalculatePriceRequest obj) {
		obj.setAmount(15l); //izbrisati nakon ispravljenog fronta
		InsurancePolicyCalculatePriceResponse response = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPrice", obj, InsurancePolicyCalculatePriceResponse.class);
		return response;
	}
	
	@PreAuthorize("hasRole('seller')")
	@PostMapping("/car/calculateSuggestedPrice")
	private InsurancePolicyCalculatePriceResponse calculateSuggestedPriceCar(@RequestBody InsurancePolicyCarCalculatePriceRequest obj) {
		obj.setSlepovanje(30l);
		obj.setPopravka(34l);
		obj.setSmestaj(37l);
		obj.setPrevoz(41l);
		InsurancePolicyCalculatePriceResponse price = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPriceCar", obj, InsurancePolicyCalculatePriceResponse.class);
		return price;
	}
	
	@PreAuthorize("hasRole('seller')")
	@PostMapping("/home/calculateSuggestedPrice")
	private Double calculateSuggestedPriceHome(@RequestBody InsurancePolicyHomeCalculatePriceRequest obj) {
		obj.setSize(18l);
		obj.setAge(22l);
		obj.setValue(26l);
		obj.setRisk(27l);
		
		Double price = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy/calculateSuggestedPriceHome", obj, Double.class);
		return price;
	}
	
	@PostMapping("/checkout")
	private InsurancePolicyCheckoutResponse getCheckout(@RequestBody InsurancePolicyCheckoutRequest obj) {
		InsurancePolicyCheckoutResponse response = restTemplate.postForObject(
				getDataccessPortHttps()+"/insurancePolicy/getCheckout", obj, InsurancePolicyCheckoutResponse.class);
		return response;
	}	

	public String getDataccessPortHttps() {
		return dataccessPort.replace("http", "https").toString();
	}
	
	/*@Bean
	public RestTemplate restTemplate() {
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());//TODO: hide password
		    keyStore.load(new FileInputStream(new File("sep.p12")), "sep12345".toCharArray());
	
		    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
		            new SSLContextBuilder()
		                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
		                    .loadKeyMaterial(keyStore, "sep12345".toCharArray())
		                    .build(),
		            NoopHostnameVerifier.INSTANCE);
	
		    HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
	
		    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		    RestTemplate restTemplate = new RestTemplate(requestFactory);
			
		    return restTemplate;
		}
		catch(Exception exc) {
			return null;
		}
	}*/
	

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
			
		try(FileWriter fw = new FileWriter("D:\\JavaCard33\\samples\\classic_applets\\Wallet\\"
			+ "wallet.scr", true);
				
		BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw))
		    {
				out.println(APDU_PIN);
			} catch (IOException e) {
		     //exception handling left as an exercise for the reader
			}
       
		
		ProcessBuilder builder1 = new ProcessBuilder(
	            "cmd.exe", "/c", "cd \"D:\\JavaCard33\\bin\" && cref");
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
	            "cmd.exe", "/c", "cd \"D:\\JavaCard33\\samples\\classic_applets\\Wallet\\applet\" && ant all");
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
			"D:\\JavaCard33\\samples\\classic_applets\\Wallet\\applet\\default.output"));
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
	    FileInputStream in = new FileInputStream("D:\\JavaCard33\\samples\\classic_applets\\Wallet\\applet\\default.output");
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
