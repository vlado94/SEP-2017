package com.sep.acquirer.paymentRequest;


public class PaymentRequest {
	
	private String holderName; 
	private String cardNum;
	private String expDate;
	private String cvv2;
	private String policyPrice;
	private String policyID;

	public PaymentRequest() {
		
	}
	
	
	public PaymentRequest(String holderName, String cardNum, String expDate, String cvv2, String policyPrice,
			String policyID) {
		super();
		this.holderName = holderName;
		this.cardNum = cardNum;
		this.expDate = expDate;
		this.cvv2 = cvv2;
		this.policyPrice = policyPrice;
		this.policyID = policyID;
	}


	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}


	public String getPolicyPrice() {
		return policyPrice;
	}


	public void setPolicyPrice(String policyPrice) {
		this.policyPrice = policyPrice;
	}


	public String getPolicyID() {
		return policyID;
	}


	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}
	
	
	
	
}
