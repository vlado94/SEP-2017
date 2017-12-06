package da.priceList;

import java.util.List;

public interface PriceListService {
	
	public List<PriceList> findAll();
	
	public PriceList findOne(Long id);
	
	public PriceList save(PriceList factor);
	
	public void delete(Long id);

}
