package com.sep.acquirer.paymentRequest;

public class PaymentRequestCard {
	
	private String holderName; 	
	private String billNum;	// required, or card ID
	private String expDate;
	private double policyPrice;	// required
	private Long policyId;	// required
	
	
	
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
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
