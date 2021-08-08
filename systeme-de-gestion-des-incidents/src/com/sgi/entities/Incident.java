package com.sgi.entities;

import java.util.List;

public class Incident {
	
	private int id;
	private int idRapporteur;
	private int idDeveloppeur;
	
	private String description;
	private String application;
	private String gravite;
	private String openDate;
	private String closeDate;
	
	private Statut statut;
	
	List<Note> notes;
	
	public Incident(String description, String application, String gravite) {
		this.description = description;
		this.application = application;
		this.gravite = gravite;
		
		this.statut = Statut.NOUVEAU;
	}
	
	public Incident(int id, String description, String application, String gravite) {
		this(description, application, gravite);
		this.id = id;
		
	}
	
	public Incident(int id2, String open_date, String description2, String application2, String gravite2,
			int idRapporteur2, int idDeveloppeur2, String statut2, String close_date) {
		this.id = id2;
		this.idRapporteur = idRapporteur2;
		this.idDeveloppeur = idDeveloppeur2;
		this.description = description2;
		this.application = application2;
		this.gravite = gravite2;
		this.openDate = open_date;
		this.closeDate = close_date;
		this.statut = Statut.valueOf(statut2);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getApplication() {
		return application;
	}
	
	public String getGravite() {
		return gravite;
	}
	
	public void setIdDeveloppeur(int idDeveloppeur) {
		this.idDeveloppeur = idDeveloppeur;
	}
	
	public void setIdRapporteur(int idRapporteur) {
		this.idRapporteur = idRapporteur;
	}
	
	public int getIdDeveloppeur() {
		return idDeveloppeur;
	}
	
	public int getIdRapporteur() {
		return idRapporteur;
	}

	public String getStatut() {
		return statut.name();
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	
	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
}
