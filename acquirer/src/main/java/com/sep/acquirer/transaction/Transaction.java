package com.sep.acquirer.transaction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sep.acquirer.bank.Bank;
import com.sep.acquirer.bankMember.BankMember;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade= CascadeType.ALL)
	private Bank bank;
	
	@ManyToOne(cascade= CascadeType.ALL)
	private BankMember bankMember;
	
	private double amount;

	
	public Transaction() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public BankMember getBankMember() {
		return bankMember;
	}

	public void setBankMember(BankMember bankMember) {
		this.bankMember = bankMember;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
