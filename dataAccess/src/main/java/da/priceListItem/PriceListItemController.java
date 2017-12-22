package da.priceListItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/priceListItem")
public class PriceListItemController {

	@Autowired
	private PriceListItemService service;


}
