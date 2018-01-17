package model.dto;

public class Discount {

	private Integer percent;
	private String discountName;
	private double amount;
	
	public Discount() {
		
	}
	
	public Discount(int percent, String discountName, double amount) {
		super();
		this.percent = percent;
		this.discountName = discountName;
		this.amount = amount;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
}
