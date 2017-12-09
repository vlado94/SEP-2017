package da.factor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import da.categoryFactor.CategoryFactor;
import model.dto.FactorDTO;

@Entity
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
	
	public static Factor getObj(FactorDTO dto) {
		Factor retVal = new Factor();
		retVal.setId(dto.getId());
		retVal.setName(dto.getName());
		return retVal;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryFactor getCategory() {
		return category;
	}

	public void setCategory(CategoryFactor category) {
		this.category = category;
	}
}
