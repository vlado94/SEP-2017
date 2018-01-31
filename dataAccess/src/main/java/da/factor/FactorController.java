package da.factor;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import da.categoryFactor.CategoryFactorService;
import da.priceList.PriceList;
import da.priceList.PriceListService;
import da.priceListItem.PriceListItem;
import da.priceListItem.PriceListItemService;
import model.dto.FactorDTO;

@RestController
@RequestMapping("/factor")
public class FactorController {

	@Autowired
	private FactorService service;
	
	@Autowired
	private CategoryFactorService categoryService;

	@Autowired
	private PriceListService priceListService;

	@Autowired
	private PriceListItemService priceListItemService;

	@Autowired
	private JavaMailSender mailSender;
	
	private static Logger logger = LoggerFactory.getLogger(FactorController.class);
	
	
	/*privremeno ovde, bice prebaceno*/
	public void sendMail() {
		 try{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("sepftn20172@gmail.com");
			helper.setTo("sepftn20172@gmail.com");
			helper.setSubject("Osiguranje");
			helper.setText("Uplacena vam je polisa osigranja. Srdacan pozdrav.");
			//FileSystemResource file = new FileSystemResource("D:\\todoSEP.txt");
			//helper.addAttachment(file.getFilename(), file);
			 mailSender.send(message);
		 }catch (MessagingException e) {
			   throw new MailParseException(e);
		 }
	}
	
	@GetMapping
	private List<FactorDTO> findAll() {
		//sendMail();
		List<Factor> list = service.findAll();
		
		List<FactorDTO> retVal = new ArrayList<FactorDTO>();
		for (Factor f : list) {
			FactorDTO temp = f.getDTO();
			temp.setCategoryName(f.getCategory().getName());
			retVal.add(temp);
		}
		
		return retVal;
	}

	@PostMapping
	private FactorDTO save(@RequestBody FactorDTO obj, @RequestHeader("Authorization") String token) {
		Factor f = new Factor();
		f.setName(obj.getName());
		f.setCategory(categoryService.findOne(obj.getCategory()));
		FactorDTO newFactor = service.save(f).getDTO();
		PriceListItem pli = new PriceListItem();
		pli.setPercent(0.0);
		pli.setFactor(service.findOne(newFactor.getId()));

		/* isto mora na repo -nadji aktivan cenovnik*/
		List<PriceList> priceLists = priceListService.findAll();
		PriceList active = priceLists.get(priceLists.size()-1);
		pli.setPriceList(active);
		priceListItemService.save(pli);		
		return newFactor;
	}

	@GetMapping("/{id}")
	private FactorDTO findOne(@PathVariable Long id) {
		Factor factor =  service.findOne(id);
		return factor.getDTO();
	}
	
	@PutMapping
	private FactorDTO update(@RequestBody FactorDTO obj, @RequestHeader("Authorization") String token) {
		Factor f = Factor.getObj(obj);
		f.setCategory(categoryService.findOne(obj.getCategory()));
		FactorDTO updateFactor = service.save(f).getDTO();
		System.out.println("usao u updat faktora");
		logger.info("Update faktora " + updateFactor.getName());
		return updateFactor;
	}
	
	@DeleteMapping("/{id}")
	private boolean delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return true;
		} catch(Exception e) {
			throw new BadRequestException();
		}
	}
	
	@GetMapping("/category/{categoryId}")
	private List<FactorDTO> findByCategory(@PathVariable Long categoryId) {
		List<FactorDTO> retVal = new ArrayList<>();
		List<Factor> ret = service.findByCategory(categoryId);
		for(Factor r : ret) {
			FactorDTO dto = new FactorDTO();
			dto.setCategory(r.getCategory().getId());
			dto.setCategoryName(r.getCategory().getName());
			dto.setId(r.getId());
			dto.setName(r.getName());
			retVal.add(dto);
		}
		return retVal;
	}

}
