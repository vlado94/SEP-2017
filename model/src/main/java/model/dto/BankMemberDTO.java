package model.dto;

public class BankMemberDTO {

	private Long id;

	private String name;

	private double amount;

	private double cardNumber;

	private double billNumber;

	private boolean valid;

	public BankMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(double cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(double billNumber) {
		this.billNumber = billNumber;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
