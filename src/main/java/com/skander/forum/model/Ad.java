package com.skander.forum.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ad implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAd;
	@Enumerated(EnumType.STRING)
	private canal canal;
	private Date dateDebut;
	private Date dateFin;
	private int nbrInitVues;
	private int nbrCibleVues;
	private float cout;
	private String type;
	private String file;
	private String link;
	@JsonIgnore
	@OneToOne
	private PopulationCible cible;
	@ManyToOne
	private User user;
		
}
