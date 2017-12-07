package da.priceListItem;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PriceListItemRepository extends PagingAndSortingRepository<PriceListItem, Long>{

	public PriceListItem findByFactorIdAndPriceListId(Long factorId,Long priceListId);
}
