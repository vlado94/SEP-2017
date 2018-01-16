package model.request;

import java.util.ArrayList;

import model.dto.Discount;

public class InsurancePolicyCalculatePriceResponce {

	private double basePrice;
	private ArrayList<Discount> discounts;
	private double finalPrice;
	
	public InsurancePolicyCalculatePriceResponce(double basePrice, ArrayList<Discount> discounts, double finalPrice) {
		super();
		this.basePrice = basePrice;
		this.discounts = discounts;
		this.finalPrice = finalPrice;
	}
	
	public InsurancePolicyCalculatePriceResponce() {
		
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public ArrayList<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(ArrayList<Discount> discounts) {
		this.discounts = discounts;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	
}
