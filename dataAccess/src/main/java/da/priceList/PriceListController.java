package da.priceList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import da.factor.FactorService;
import da.priceListItem.PriceListItem;
import da.priceListItem.PriceListItemService;
import model.dto.FactorDTO;
import model.dto.PriceListItemDTO;

@RestController
@RequestMapping("/priceList")
public class PriceListController {

	@Autowired
	private PriceListService service;

	@Autowired
	private FactorService factorService;

	@Autowired
	private PriceListItemService itemService;
	
	@GetMapping("/findLast")
	private List<PriceListItemDTO> findLast() {
		//ovo treba na repo da se spusti sa upitom
		List<PriceList> list = service.findAll();
		List<PriceListItem> itemList = itemService.findAll();
		PriceList temp = list.get(list.size()-1);
		for (PriceList priceList : list) {
			if(temp.getFromDate().compareTo(priceList.getFromDate()) < 0)
				temp = priceList;
		}

		List<PriceListItemDTO> retVal = new ArrayList<PriceListItemDTO>();
		for (PriceListItem priceListItem : itemList) {
			if(priceListItem.getPriceList().getId() == temp.getId()) {
				PriceListItemDTO dto = new PriceListItemDTO();
				dto.setId(priceListItem.getId());
				dto.setFactorName(priceListItem.getFactor().getName());
				dto.setPercent(priceListItem.getPercent());
				dto.setFactor(priceListItem.getFactor().getId());
				retVal.add(dto);
			}
		}		
		return retVal;
	}
	
	@PostMapping
	private boolean save(@RequestBody List<Double> obj) {
		PriceList pl = new PriceList();
		pl.setFromDate(new Date(Calendar.getInstance().getTimeInMillis()));
		List<PriceList> list = service.findAll();
		PriceList plOld = list.get(list.size()-1);
		plOld.setToDate(pl.getFromDate());
		List<PriceListItemDTO> items = findLast();
		
		service.save(plOld);
		pl = service.save(pl);
		for(int i =0;i<obj.size();i++) {
			PriceListItem item = new PriceListItem();
			if(obj.get(i) == null)
				item.setPercent(0.0);
			else 
				item.setPercent(obj.get(i));
				
			item.setPriceList(pl);
			item.setFactor(factorService.findOne(items.get(i).getFactor()));
			itemService.save(item);
		}
		return true;
	}

}
