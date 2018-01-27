package da.bankMember;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import da.bank.Bank;

@Entity
public class BankMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private double amount;

	private double cardNumber;

	private double billNumber;

	private boolean valid;

	public BankMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToOne
	private Bank bank;

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
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
