package com.skander.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMessage;
	private String message;
	private String file;
	@JsonIgnore
	@ManyToOne
	private User sender;
	@JsonIgnore
	@ManyToOne
	private User receiver;
	
	
	public Message() {
		super();
	}
	
	public Message(String message, String file, User sender, User receiver) {
		super();
		this.message = message;
		this.file = file;
		this.sender = sender;
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", message=" + message + ", file=" + file + ", sender=" + sender
				+ ", receiver=" + receiver + "]";
	}	
	
}
