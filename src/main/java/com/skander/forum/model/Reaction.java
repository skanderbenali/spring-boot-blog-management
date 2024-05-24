package com.skander.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReaction;
	private String react;
	@ManyToOne
	private Comment comment;
	@ManyToOne
	private User user;
	
	public Reaction() {
		super();
	}
	

	public Reaction(String react, Comment comment, User user) {
		super();
		this.react = react;
		this.comment = comment;
		this.user = user;
	}


	public Long getIdReaction() {
		return idReaction;
	}


	public void setIdReaction(Long idReaction) {
		this.idReaction = idReaction;
	}


	public String getReact() {
		return react;
	}
	public void setReact(String react) {
		this.react = react;
	}
	
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Reaction [idReaction=" + idReaction + ", react=" + react + ", comment=" + comment + ", user=" + user
				+ "]";
	}



}
