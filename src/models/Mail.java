package models;

import javax.mail.Address;

public class Mail {
	private String subject;
	private Address from;
	private String content;
	
	public Mail(String subject, Address from, String content) {
		super();
		this.subject = subject;
		this.from = from;
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Address getFrom() {
		return from;
	}

	public void setFrom(Address from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
