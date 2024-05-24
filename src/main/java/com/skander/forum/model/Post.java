package com.skander.forum.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



/**
 * @author seyko
 *
 */


@Entity
public class Post {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPost;
	private String description;
	private String file;
	private Date dateDeCreation;
	private float totalStars;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	@JsonIgnore
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "post")
	private List<Comment> comments;
	
	@JsonIgnore
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "post")
	private List<Rate> rates;
	

	public Post(String description, String file, Date dateDeCreation, User user, List<Comment> comments,
			List<Rate> rates) {
		super();
		this.description = description;
		this.file = file;
		this.dateDeCreation = dateDeCreation;
		this.user = user;
		this.comments = comments;
		this.rates = rates;
	}

	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Post(Long id)
	{
		this.idPost= id ;
	}

	public Long getIdPost() {
		return idPost;
	}



	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}



	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Date getDateDeCreation() {
		return dateDeCreation;
	}
	public void setDateDeCreation(Date dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}




	public List<Rate> getRates() {
		return rates;
	}




	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}



	public float getTotalStars() {
		return totalStars;
	}


	public void setTotalStars(float totalStars) {
		this.totalStars = totalStars;
	}


	@Override
	public String toString() {
		return "Post [idPost=" + idPost + ", description=" + description + ", file=" + file + ", dateDeCreation="
				+ dateDeCreation + ", user=" + user + ", comments=" + comments + ", rates=" + rates + "]";
	}

	

	

	
	
	
}
