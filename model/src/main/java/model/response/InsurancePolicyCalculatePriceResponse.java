package model.response;

import java.util.ArrayList;

import model.dto.Discount;

public class InsurancePolicyCalculatePriceResponse {

	private double basePrice;
	private ArrayList<Discount> discounts;
	private double finalPrice;
	
	public InsurancePolicyCalculatePriceResponse(double basePrice, ArrayList<Discount> discounts, double finalPrice) {
		super();
		this.basePrice = basePrice;
		this.discounts = discounts;
		this.finalPrice = finalPrice;
	}
	
	public InsurancePolicyCalculatePriceResponse() {
		
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
