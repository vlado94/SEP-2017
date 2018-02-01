package model.request;

import java.io.File;

public class MailRequest {

	private String from;
	private String to;
	private String subject;
	private String text;
	private String name;
	private String lastName;
	private double amount;
	
	public String getFrom() {
		return from;
	}
	
	
	

	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public MailRequest() {
		
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public MailRequest(String from, String to, String subject, String text, String name, String lastname,Double amount) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.name = name;
		this.lastName = lastname;
		this.amount = amount;
	}
	
}
