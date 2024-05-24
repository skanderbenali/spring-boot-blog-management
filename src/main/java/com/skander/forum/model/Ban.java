package com.skander.forum.model;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Ban {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idBan;
	@JsonIgnore
	@OneToOne
	private User user;
	private boolean status;
	private Date debutBan;
	private Date endBan;
	private int penalites;
	public Ban() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ban(User user, boolean status, Date debutBan, Date endBan, int penalites) {
		super();
		this.user = user;
		this.status = status;
		this.debutBan = debutBan;
		this.endBan = endBan;
		this.penalites = penalites;
	}

	
	public Ban(Long idBan) {
		super();
		this.idBan = idBan;
	}
	public Long getIdBan() {
		return idBan;
	}
	public void setIdBan(Long idBan) {
		this.idBan = idBan;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getDebutBan() {
		return debutBan;
	}
	public void setDebutBan(Date debutBan) {
		this.debutBan = debutBan;
	}
	public Date getEndBan() {
		return endBan;
	}
	public void setEndBan(Date endBan) {
		this.endBan = endBan;
	}
	public int getPenalites() {
		return penalites;
	}
	public void setPenalites(int penalites) {
		this.penalites = penalites;
	}
	@Override
	public String toString() {
		return "Ban [idBan=" + idBan + ", user=" + user + ", status=" + status + ", debutBan=" + debutBan + ", endBan="
				+ endBan + ", penalites=" + penalites + "]";
	}
	
	
	
	
}
