package com.skander.forum.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Comment {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idComment;
	private String description;
	private Date creationDate;
	private String file;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Post post;
	
	@JsonIgnore
	@OneToMany (mappedBy = "comment")
	private List<Reaction> reactions;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	public Comment() {
		super();
	}
		


	public Comment(String description, Date creationDate, String file, Post post, List<Reaction> reactions, User user) {
		super();
		this.description = description;
		this.creationDate = creationDate;
		this.file = file;
		this.post = post;
		this.reactions = reactions;
		this.user = user;
	}



	public Long getIdComment() {
		return idComment;
	}



	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}



	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}



	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", description=" + description + ", creationDate=" + creationDate
				+ ", file=" + file + ", post=" + post + ", reactions=" + reactions + ", user=" + user + "]";
	}



	

	
		
}
