package model.request;

public class PinRequest {

	private Long cardHolder;

	private double totalPrice;

	private int pin;

	public PinRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(Long cardHolder) {
		this.cardHolder = cardHolder;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

}
