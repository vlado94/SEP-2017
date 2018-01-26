package model.dto;

public class InsurancePolicyFinalDTO {
	
	Long id;
	
	Double price;

	public InsurancePolicyFinalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsurancePolicyFinalDTO(Long id, Double price) {
		super();
		this.id = id;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "InsurancePolicyFinalDTO [id=" + id + ", price=" + price + "]";
	}
	
	

}
