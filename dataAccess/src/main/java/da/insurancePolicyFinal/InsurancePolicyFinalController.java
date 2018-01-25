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

}
