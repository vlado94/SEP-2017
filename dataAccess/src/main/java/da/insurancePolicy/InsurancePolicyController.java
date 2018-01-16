package da.insurancePolicy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import da.person.PersonService;
import model.request.InsurancePolicyCalculatePriceRequest;
import model.request.InsurancePolicyCalculatePriceResponce;
import model.request.InsurancePolicyCarCalculatePriceRequest;
import model.request.InsurancePolicyHomeCalculatePriceRequest;
import model.request.InsurancePolicyRequest;
import model.request.PersonRequest;
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

    @Autowired
    ConversionService conversionService;
    
	@PostMapping
	public InsurancePolicyRequest create(@RequestBody InsurancePolicyRequest request) {
		InsurancePolicy entity = conversionService.convert(request, InsurancePolicy.class);
		
		return request;
	}

	/*Metoda za racunanje preporucene cijene polise*/
	@PostMapping("/calculateSuggestedPrice")
	public Double calculatePrice(@RequestBody InsurancePolicyCalculatePriceRequest request) throws FileNotFoundException {
	
		request.setNumberOfPersons(request.getFirstAgeCategory()+request.getSecondAgeCategory()+request.getThirdAgeCategory());
		
		/*Banker banker = (Banker)httpSession.getAttribute("user");
		long BankID = banker.getBank().getId();
		Bank bank = bankService.findOne(BankID);
		ExcerptForBills ex = new ExcerptForBills(bank);
	    String outputFile ="D:\\ExcerptForBank.pdf";
	    JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(ex.setBills());
		
 
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ItemDataSource", itemsJRBean);
        parameters.put("BankName", bank.getName());
        parameters.put("CurrencyCode", bank.getCurrencyCode());
        parameters.put("bankerName", banker.getFirstname() + " " + banker.getLastname());
    
       JasperPrint jasperPrint = JasperFillManager.fillReport("D:\\excerptBank.jasper", parameters, new JREmptyDataSource());
        File file = new File(outputFile);
        OutputStream outputStream = new FileOutputStream(file);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        response.setContentType("application/pdf");
		InputStream inputStream = new FileInputStream(file);
		IOUtils.copy(inputStream, response.getOutputStream());*/
		
		InsurancePolicyCalculatePriceResponce response = insurancePolicyService.calculateSuggestedPrice(request);
		
	    String outputFile ="D:\\ExcerptForBank.pdf";
	    JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(response.getDiscounts());
	    Map<String, Object> parameters = new HashMap<String, Object>();
	    
	    //parameters.put("ItemDataSource", itemsJRBean);
	    parameters.put("BankName", "Ime banke");
        parameters.put("CurrencyCode", "kod");
        parameters.put("bankerName", "Ime i prezime");
        
        JasperPrint jasperPrint;
		try {
			jasperPrint = JasperFillManager.fillReport("D:\\excerptBank.jasper", parameters, new JREmptyDataSource());
			 File file = new File(outputFile);
		        OutputStream outputStream= new FileOutputStream(file);
		        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
        //response.setContentType("application/pdf");
		//InputStream inputStream = new FileInputStream(file);
		//IOUtils.copy(inputStream, response.getOutputStream());
	    
		return 2.3;
		
	}
	
	/*Metoda za racunanje ukupne cijene polise*/
	@PostMapping("/calculatePrice")
	public Double calculatePriceWithPersons(@RequestBody InsurancePolicyRequest request) {
		InsurancePolicyRequest policy = new InsurancePolicyRequest();
		policy.setDuration(5);
		policy.setPersons(new ArrayList<>());
		PersonRequest person1 = new PersonRequest("Jovan","Jovanovic","1212994156225","12563","adress","06451145",28l,false, "fjass@sdha");
		PersonRequest person2 = new PersonRequest("Milan","Milanovic","1906994156225","85952","adress","06451145",62l,false, "fjass@sdha");
		policy.getPersons().add(person1);
		policy.getPersons().add(person2);
		
		policy.setRegion(9l);
		policy.setSport(1l);
		policy.setAmount(15l);
		
		double amount = insurancePolicyService.calculatePolice(policy);
	
		return 1.1;
	}
	
	
	@PostMapping("/calculateSuggestedPriceHome")
	public Double calculateSuggestedPriceHome(@RequestBody InsurancePolicyHomeCalculatePriceRequest request) {
	
		
		
		return insurancePolicyService.calculateSuggestedPriceHome(request);
		
	}
	
	@PostMapping("/calculateSuggestedPriceCar")
	public Double calculateSuggestedPriceCar(@RequestBody InsurancePolicyCarCalculatePriceRequest request) {
		
		return insurancePolicyService.calculateSuggestedPriceCar(request);
		
	}
	
/*	@GetMapping("/x")
	public String eo() {
		InsurancePolicyCalculatePriceRequest i=new InsurancePolicyCalculatePriceRequest();
		i.setStartDate(LocalDate.of(2018, 2, 5));
		
	}*/
}
