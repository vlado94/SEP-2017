package com.javacard.PaymentRequestCard;

public class PaymentRequestCard {

	private String billNum;
	private String cardNum;
	private Long policyId;
	private double policyPrice;	
	
	
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public double getPolicyPrice() {
		return policyPrice;
	}
	public void setPolicyPrice(double policyPrice) {
		this.policyPrice = policyPrice;
	}
	public Long getPolicyID() {
		return policyId;
	}
	public void setPolicyID(Long policyID) {
		this.policyId = policyID;
	}	
	
	
}
