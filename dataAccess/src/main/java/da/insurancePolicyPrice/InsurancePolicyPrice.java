package da.insurancePolicyPrice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class InsurancePolicyPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double basePrice;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Discount> discounts = new ArrayList<>();
	private double finalPrice;

	public InsurancePolicyPrice(double basePrice, ArrayList<Discount> discounts, double finalPrice) {
		super();
		this.basePrice = basePrice;
		this.discounts = discounts;
		this.finalPrice = finalPrice;
	}

	public InsurancePolicyPrice() {
		super();
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
