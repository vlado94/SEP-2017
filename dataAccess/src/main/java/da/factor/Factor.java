package da.factor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import da.categoryFactor.CategoryFactor;
import da.dto.FactorDTO;
import lombok.Data;

@Entity
@Data
public class Factor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
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
