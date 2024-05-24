package com.skander.forum.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	private String nom;
	
	@JsonIgnore
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "user")
	private List<Post> posts;
	
	@JsonIgnore
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy = "user")
	private List<Historique> searchs;
	
	@JsonIgnore
	@OneToMany (cascade=CascadeType.ALL,mappedBy = "user")
	private List<Ad> ads;
	
	@JsonIgnore
	@OneToMany (mappedBy = "sender")
	private List<Message> messages;
	
	@JsonIgnore
	@OneToMany (mappedBy = "receiver")
	private List<Message> messagess;
	

	@OneToOne(cascade=CascadeType.ALL,mappedBy = "user")
	private Ban ban;
	
	public User() {
		super();
	}
	
	public User(String nom) {
		super();
		this.nom = nom;
	}

	public User(Long id) {
		super();
		this.idUser = id;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Historique> getSearchs() {
		return searchs;
	}

	public void setSearchs(List<Historique> searchs) {
		this.searchs = searchs;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Message> getMessagess() {
		return messagess;
	}

	public void setMessagess(List<Message> messagess) {
		this.messagess = messagess;
	}

	public Ban getBan() {
		return ban;
	}

	public void setBan(Ban ban) {
		this.ban = ban;
	}
	
	
	


	
	
	
	
}
