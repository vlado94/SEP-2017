package da.insurancePolicy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import da.person.PersonService;
import model.dto.Discount;
import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyCheckoutRequest;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
import model.request.MailRequest;
import model.request.PersonRequest;
import model.response.InsurancePolicyCalculatePriceResponse;
import model.response.InsurancePolicyCheckoutResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/insurancePolicy")
public class InsurancePolicyController {

	@Autowired
	private InsurancePolicyService insurancePolicyService;

	@Autowired
	private PersonService personService;
	
	@Value("${dataccessPort}")
	private String dataccessPort;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	@Autowired
	private JavaMailSender mailSender;
    @Autowired
    ConversionService conversionService;
    
    private static Logger logger = LoggerFactory.getLogger(InsurancePolicyController.class);
    
	@PostMapping
	public InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest request) {
		InsurancePolicy entity = conversionService.convert(request, InsurancePolicy.class);
		
		return request;
	}

	/*Metoda za racunanje preporucene cijene polise*/
	@PostMapping("/calculateSuggestedPrice")
	public InsurancePolicyCalculatePriceResponse calculatePrice(@RequestBody InsurancePolicyCalculatePriceRequest request) throws FileNotFoundException {
		request.setNumberOfPersons(request.getFirstAgeCategory()+request.getSecondAgeCategory()+request.getThirdAgeCategory());
		InsurancePolicyCalculatePriceResponse response = insurancePolicyService.calculateSuggestedPrice(request);
		
		return response;
		
	}
	
	/*Metoda za racunanje ukupne cijene polise*/
	@PostMapping("/calculatePrice")
	public InsurancePolicyCalculatePriceResponse calculatePriceWithPersons(@RequestBody InsurancePolicyRequest request) {
		InsurancePolicyRequest policy = new InsurancePolicyRequest();
		policy.setDuration(5);
		policy.setPersons(new ArrayList<>());
		PersonRequest person1 = new PersonRequest("Jovan","Jovanovic","1212994156225","12563","adress","06451145",false, "fjass@sdha");
		PersonRequest person2 = new PersonRequest("Milan","Milanovic","1906994156225","85952","adress","06451145",false, "fjass@sdha");
		policy.getPersons().add(person1);
		policy.getPersons().add(person2);
		
		policy.setRegion(9l);
		policy.setSport(1l);
		policy.setAmount(15l);
		
		InsurancePolicyCalculatePriceResponse response = insurancePolicyService.calculatePolice(policy);
	
		return response;
	}
	
	
	@PostMapping("/calculateSuggestedPriceHome")
	public Double calculateSuggestedPriceHome(@RequestBody InsurancePolicyHomeCalculatePriceRequest request) {
	
		return insurancePolicyService.calculateSuggestedPriceHome(request);
	
	}
	
	@PostMapping("/calculateSuggestedPriceCar")
	public InsurancePolicyCalculatePriceResponse calculateSuggestedPriceCar(@RequestBody InsurancePolicyCarCalculatePriceRequest request) {
		
		return insurancePolicyService.calculateSuggestedPriceCar(request);
		
	}
	
	
	@PostMapping("/getCheckout")
	public InsurancePolicyCheckoutResponse getCheckout(@RequestBody InsurancePolicyCheckoutRequest request) {
		
		return insurancePolicyService.getCheckout(request);
		
	}
	
	
	
	@PostMapping("/getPDF")
	public Boolean getPDF(@RequestBody InsurancePolicyCheckoutResponse response) throws FileNotFoundException {
		
			//InsurancePolicyCheckoutResponse response = generate();
			//response.setEmailEmployee("olja.miljatovic@sep.com");
			JasperPrint jasperPrint;
			
			String name = null ;
			String lastname = null;
			
			 Map<String, Object> parameters = new HashMap<String, Object>();
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(response.getPersons());
	   
	    String mailOfContractor = null;
	    for (PersonRequest personRequest : response.getPersons()) {
			if(personRequest.isContractor()) {
				parameters.put("OsiguravacIme", personRequest.getFirstName());
				parameters.put("OsiguravacPrezime", personRequest.getLastName());
				parameters.put("OsiguravacJMBG", personRequest.getPersonNo());
				parameters.put("OsiguravacPasos", personRequest.getPassportNo());
				mailOfContractor = personRequest.getEmail();
				name = personRequest.getFirstName();
				lastname = personRequest.getLastName();
				
			}
		}
	   parameters.put("Datum1", response.getStartDate());
	   parameters.put("Datum2", response.getStartDate());
	   parameters.put("Sport", response.getSport());
	   parameters.put("VelicinaPokrica", response.getAmount());
	   parameters.put("Region", response.getRegion());
	   parameters.put("CijenaOsiguranja", response.getPriceAndDiscountsForTravel().getBasePrice());
	   
	   Double suma = Double.valueOf(0);
	   for (Discount discount : response.getPriceAndDiscountsForTravel().getDiscounts()) {
		   suma += discount.getAmount();
	   }
	   
	   parameters.put("Popust",suma);
	   parameters.put("Naplata",  Double.valueOf(response.getPriceAndDiscountsForTravel().getFinalPrice()));
	   parameters.put("ItemDataSource", itemsJRBean);
	  
		String outputFile;
		
		if(response.getDurationForHome() == null && response.getDurationForCar() == null) {
			outputFile ="TravelPolicy.pdf";
			  parameters.put("UkupnoZaNaplatu", response.getPriceAndDiscountsForTravel().getFinalPrice());
		}else if(response.getDurationForHome() == null && response.getDurationForCar() != null) {
			outputFile ="CarPolicy.pdf";
			parameters.put("TipAutomobila", response.getTypeOfVehicle());
		    parameters.put("RegistracioniBroj", response.getRegistrationNumber());
		   parameters.put("GodisteAutomobila", response.getYear());
		   parameters.put("BrojSasije", response.getChassisNumber());
		   
		   parameters.put("VlasnikAutoIme", response.getFirstNameOwnerCar());
			parameters.put("VlasnikAutoPrezime", response.getLastNameOwnerCar());
			
			parameters.put("TrajanjeAuto", response.getDurationForCar());
			parameters.put("Slepanje", response.getSlepovanje());
			parameters.put("Prevoz", response.getPrevoz());
			parameters.put("Popravka", response.getPopravka());	
			parameters.put("Smestaj", response.getSmestaj());
			parameters.put("CijenaZaAuto", response.getPriceAndDiscountsForCar().getBasePrice());
			Double suma1 = Double.valueOf(0);
			   for (Discount discount : response.getPriceAndDiscountsForCar().getDiscounts()) {
				   suma1 += discount.getAmount();
			   }
			  parameters.put("PopustAuto", suma1);
			  
			  parameters.put("NaplataAuto", response.getPriceAndDiscountsForTravel().getFinalPrice());
			  parameters.put("UkupnoZaNaplatu", response.getPriceAndDiscountsForCar().getFinalPrice()+ response.getPriceAndDiscountsForTravel().getFinalPrice());
		}else if(response.getDurationForHome() != null && response.getDurationForCar() == null) {
			outputFile ="HomePolicy.pdf";
			
			parameters.put("VlasnikIme", response.getFirstNameOwnerHome());
			parameters.put("VlasnikPrezime", response.getLastNameOwnerHome());
			parameters.put("Adresa", response.getAddress());
			parameters.put("TrajanjeOsiguranja",response.getDurationForHome());
			parameters.put("Rizik",response.getRisk());
			parameters.put("Vrijednost", response.getValue());
			parameters.put("Godiste", response.getAge());
			parameters.put("CijenaOsiguranjaKuca", response.getPriceForHome());
			parameters.put("Velicina", response.getSize());
			parameters.put("UkupnoZaNaplatu", response.getPriceForHome()+ response.getPriceAndDiscountsForTravel().getFinalPrice());
		}else {
				outputFile ="HomeCarPolicy.pdf";
				parameters.put("TipAutomobila", response.getTypeOfVehicle());
			    parameters.put("RegistracioniBroj", response.getRegistrationNumber());
			   parameters.put("GodisteAutomobila", response.getYear());
			   parameters.put("BrojSasije", response.getChassisNumber());
			   
			   parameters.put("VlasnikAutoIme", response.getFirstNameOwnerCar());
				parameters.put("VlasnikAutoPrezime", response.getLastNameOwnerCar());
				
				parameters.put("TrajanjeAuto", response.getDurationForCar());
				parameters.put("Slepanje", response.getSlepovanje());
				parameters.put("Prevoz", response.getPrevoz());
				parameters.put("Popravka", response.getPopravka());	
				parameters.put("Smestaj", response.getSmestaj());
				parameters.put("CijenaZaAuto", response.getPriceAndDiscountsForCar().getBasePrice());
				Double suma1 = Double.valueOf(0);
				   for (Discount discount : response.getPriceAndDiscountsForCar().getDiscounts()) {
					   suma1 += discount.getAmount();
				   }
				  parameters.put("PopustAuto", suma1);
				  
				  parameters.put("NaplataAuto", response.getPriceAndDiscountsForTravel().getFinalPrice());
					parameters.put("VlasnikIme", response.getFirstNameOwnerHome());
					parameters.put("VlasnikPrezime", response.getLastNameOwnerHome());
					parameters.put("Adresa", response.getAddress());
					parameters.put("TrajanjeOsiguranja",response.getDurationForHome());
					parameters.put("Rizik",response.getRisk());
					parameters.put("Vrijednost", response.getValue());
					parameters.put("Godiste", response.getAge());
					parameters.put("CijenaOsiguranjaKuca", response.getPriceForHome());
					parameters.put("Velicina", response.getSize());
					parameters.put("UkupnoZaNaplatu", response.getPriceForHome()+ response.getPriceAndDiscountsForTravel().getFinalPrice() + response.getPriceAndDiscountsForCar().getFinalPrice());
		}

		
	     
			try {
				if(response.getDurationForHome() == null && response.getDurationForCar() == null) {
					jasperPrint = JasperFillManager.fillReport("TravelPolicy.jasper", parameters, new JREmptyDataSource());
				}else if(response.getDurationForHome() == null && response.getDurationForCar() != null) {
					jasperPrint = JasperFillManager.fillReport("CarPolicy.jasper", parameters, new JREmptyDataSource());
				}else if(response.getDurationForHome() != null && response.getDurationForCar() == null) {
					jasperPrint = JasperFillManager.fillReport("HomePolicy.jasper", parameters, new JREmptyDataSource());
				}else {
					jasperPrint = JasperFillManager.fillReport("HomeCarPolicy.jasper", parameters, new JREmptyDataSource());
				}
			
				
				 File file = new File(outputFile);
			        OutputStream outputStream= new FileOutputStream(file);
			        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			        logger.info("kreiran PDF");

			        if(response.getEmailEmployee() != null) {
			        	if(!response.getEmailEmployee().equals("")) {
			        	MailRequest mailRequest = new MailRequest("olja.miljatovic@sep.com", response.getEmailEmployee(), "Uplacena polisa osiguranja", "U prilogu se nalazi uplacena polisa osiguranja",name, lastname, response.getTotalPrice());
			        	Boolean result = restTemplate().postForObject(
			        		dataccessPort.toString()+"/mailController", mailRequest, Boolean.class);
			        	}
			        }
			       
			        
			        sendMail("sepftn20172@gmail.com", "sepftn20172@gmail.com", "Polisa","Uplacena polisa osiguranja",file);
			        sendMail("sepftn20172@gmail.com", mailOfContractor, "Polisa osiguranja","U prilogu se nalazi Vasa uplacena polisa osigranja.\n\n\nSrdacan pozdrav, \n Vas DDOR.",file);
			        
			       
			
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
		return true;

		

		
	}
/*	@GetMapping("/x")
	public String eo() {
		InsurancePolicyCalculatePriceRequest i=new InsurancePolicyCalculatePriceRequest();
		i.setStartDate(LocalDate.of(2018, 2, 5));
		
	}*/

	/*pomocna metoda NE BRISATI!!!*/
	private InsurancePolicyCheckoutResponse generate() {
		InsurancePolicyCheckoutResponse response = new InsurancePolicyCheckoutResponse();
		LocalDate date1 = LocalDate.parse("2017-02-03");
		response.setStartDate(date1);
		response.setDurationForTravel(12);
		response.setRegion("Evropa");
		response.setSport("Skijanje");
		response.setAmount("do 10000");
		response.setTypeOfPolicy("indiv");
		

		InsurancePolicyCalculatePriceResponse r = new InsurancePolicyCalculatePriceResponse();
		r.setBasePrice(24.2);
		Discount d1 = new Discount(2, "Popust na kolicinu", 12.4);
		Discount d2 = new Discount(3, "Popust na datum", 340);
		ArrayList<Discount> discounts = new ArrayList<Discount>();
		discounts.add(d1);
		discounts.add(d2);
		r.setFinalPrice(325.23);
		r.setDiscounts(discounts);
		response.setPriceAndDiscountsForTravel(r);
		
		PersonRequest p1 = new PersonRequest("Olja", "Miljatovic", "1432994189229","5325325","adresa1",
				"0640148217", true, "olja.miljatovic@yahoo.com");
		PersonRequest p2 = new PersonRequest("Sasa", "Miljatovic", "1812994185454","5325325","adresa1",
				"0640148217", false, "fefw@iehef.com");
		PersonRequest p3 = new PersonRequest("Sanja", "Miljatovic", "1812444489229","5325325","adresa1",
				"0640148217", false, "fefw@iehef.com");
		List<PersonRequest> persons = new ArrayList<PersonRequest>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		response.setPersons(persons);
	
		response.setTotalPrice(34234.26);
		
		
		response.setDurationForHome(24);
		response.setRisk("Rizik 1");
		response.setValue("234 metara");
		response.setAge("2015");
		response.setSize("32442");
		response.setAddress("adresa 1");
		response.setFirstNameOwnerHome("Olja");
		response.setLastNameOwnerHome("Miljatovic");
		response.setPriceForHome(324.32);
		
		/*car  */		
		
		//response.setDurationForCar(12);
		response.setSlepovanje("safni");
		response.setPopravka("fafa");
		response.setPrevoz("fafa");
		response.setSmestaj("fasfa");
		response.setFirstNameOwnerCar("Vladimir");
		response.setLastNameOwnerCar("Stanojevic");
		response.setTypeOfVehicle("fsafa");
		response.setYear(2016);
		response.setRegistrationNumber("341414");
		response.setChassisNumber("e34234");
		Discount dis1 = new Discount(2, "popust 1", 2.2);
		Discount dis2 = new Discount(2, "popust 2", 2.2);
		ArrayList<Discount> list = new ArrayList<Discount>();
		list.add(dis1);
		list.add(dis2);
		InsurancePolicyCalculatePriceResponse dis = new InsurancePolicyCalculatePriceResponse();
		dis.setBasePrice(23.4);
		dis.setDiscounts(list);
		dis.setFinalPrice(234.4);
		response.setPriceAndDiscountsForCar(dis);
		return response;
	}
	
	private void sendMail(String from, String to, String subject, String text, File file) {
		 try{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			helper.addAttachment(file.getName(), file);
			 mailSender.send(message);
		 }catch (MessagingException e) {
			   throw new MailParseException(e);
		 }
	}
}
