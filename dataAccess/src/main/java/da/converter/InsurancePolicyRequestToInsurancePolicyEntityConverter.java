package da.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import da.insurancePolicy.InsurancePolicy;
import da.priceList.PriceList;
import da.priceList.PriceListService;
import da.priceListItem.PriceListItem;
import da.priceListItem.PriceListItemService;
import model.request.InsurancePolicyRequest;

public class InsurancePolicyRequestToInsurancePolicyEntityConverter implements Converter<InsurancePolicyRequest,InsurancePolicy>{

	@Autowired
	private PriceListService priceListService;

	@Autowired
	private PriceListItemService priceListItemService;

	@Override
	public InsurancePolicy convert(InsurancePolicyRequest source) {
		InsurancePolicy insurancePolicy = new InsurancePolicy();
		insurancePolicy.setDuration(source.getDuration());
		insurancePolicy.setEndDate(source.getEndDate());
		insurancePolicy.setStartDate(source.getStartDate());
		
		PriceList currentPriceList = priceListService.findCurrent();
		PriceListItem currentRegionPrice = priceListItemService.findByFactorIdAndPriceListId(source.getRegion(), currentPriceList.getId());
		insurancePolicy.getPriceListItems().add(currentRegionPrice);
		PriceListItem currentSportPrice = priceListItemService.findByFactorIdAndPriceListId(source.getSport(), currentPriceList.getId());
		insurancePolicy.getPriceListItems().add(currentSportPrice);
		
		return insurancePolicy;
	}

}
