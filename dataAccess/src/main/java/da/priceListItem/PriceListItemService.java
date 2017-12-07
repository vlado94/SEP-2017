package da.priceListItem;

import java.util.List;

public interface PriceListItemService {
	
	public List<PriceListItem> findAll();
	
	public PriceListItem findOne(Long id);
	
	public PriceListItem save(PriceListItem item);
	
	public void delete(Long id);

	public PriceListItem findByFactorIdAndPriceListId(Long factorId,Long priceListId);
}
