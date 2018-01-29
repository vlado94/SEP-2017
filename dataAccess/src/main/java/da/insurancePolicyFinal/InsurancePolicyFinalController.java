package da.insurancePolicyFinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.dto.InsurancePolicyFinalDTO;
import model.response.InsurancePolicyCheckoutResponse;

@RestController
@RequestMapping("/insurancePolicyFinal")
public class InsurancePolicyFinalController {
	
	@Autowired
    InsurancePolicyFinalService insurancePolicyFinalService;
	
    @Autowired
    ConversionService conversionService;
	
	@PostMapping
	public InsurancePolicyFinalDTO getCheckout(@RequestBody InsurancePolicyCheckoutResponse response) {

		//InsurancePolicyCheckoutResponse response = insurancePolicyService.getCheckout(request);
		InsurancePolicyFinal insurancePolicyFinal = conversionService.convert(response, InsurancePolicyFinal.class);
		
		insurancePolicyFinal = insurancePolicyFinalService.save(insurancePolicyFinal);
		InsurancePolicyFinalDTO insurancePolicyFinalDTO = new InsurancePolicyFinalDTO(insurancePolicyFinal.getId(), insurancePolicyFinal.getTotalPrice());
		//insurancePolicyFinalModel = new Ins
		return insurancePolicyFinalDTO;
		
	}
	
	/*@PostMapping("/paypal")
	public InsurancePolicyCheckoutResponse getCheckoutPayPal(@RequestBody InsurancePolicyCheckoutResponse response) {

		InsurancePolicyFinal insurancePolicyFinal = conversionService.convert(response, InsurancePolicyFinal.class);
		
		insurancePolicyFinal = insurancePolicyFinalService.save(insurancePolicyFinal);
		response.setId(insurancePolicyFinal.getId());
		return response;
		
	}*/
	
	@PostMapping("/paying")
	public boolean paying(@RequestBody InsurancePolicyFinalDTO response) {
		InsurancePolicyFinal insurancePolicyFinal = insurancePolicyFinalService.findById(response.getId());
		insurancePolicyFinal.setPaid(true);
		try {
			insurancePolicyFinalService.save(insurancePolicyFinal);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	

}
