package isok.isok.factor;

import isok.isok.categoryFactor.CategoryFactor;
import isok.isok.dto.FactorDTO;
import lombok.Data;


@Data
public class Factor {

	private Long id;

	private String name;
		
	private CategoryFactor category;
	
	public FactorDTO getDTO () {
		FactorDTO retVal = new FactorDTO();
		retVal.setId(id);
		retVal.setName(name);
		retVal.setCategoryName(category.getName());
		retVal.setCategory(category.getId());
		return retVal;
	}
}
