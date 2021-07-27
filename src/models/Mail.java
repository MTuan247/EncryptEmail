package models;

import javax.mail.Address;

public class Mail {
	private String subject;
	private Address from;
	private String content;
	private String noticeSecurity;
	
	public Mail() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Mail(String subject, Address from, String content) {
		super();
		this.subject = subject;
		this.from = from;
		this.content = content;
	}
	
	public Mail(String subject, Address from, String content, String noticeSecurity) {
		super();
		this.subject = subject;
		this.from = from;
		this.content = content;
		this.noticeSecurity = noticeSecurity;
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


	public String getNoticeSecurity() {
		return noticeSecurity;
	}


	public void setNoticeSecurity(String noticeSecurity) {
		this.noticeSecurity = noticeSecurity;
	}
	
	
	
}
