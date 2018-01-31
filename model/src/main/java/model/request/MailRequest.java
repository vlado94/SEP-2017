package model.request;

import java.io.File;

public class MailRequest {

	private String from;
	private String to;
	private String subject;
	private String text;
	private File file;
	public String getFrom() {
		return from;
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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public MailRequest(String from, String to, String subject, String text, File file) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.file = file;
	}
	
}
