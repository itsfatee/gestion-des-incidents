package com.sgi.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int id;
	private String nom;
	private String prenom;
	private String telephone;
	private String login;
	private String password;
	private String type;

	private List<Incident> incidents;
	
	private static Integer connectedUserId = null;
	
	public User(String nom, String prenom, String telephone, String login, String password, String type) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.login = login;
		this.password = password;
		this.type = type;
				
		incidents = new ArrayList<Incident>();
	}

	public User(int id, String nom, String prenom, String telephone, String login, String password, String type) {
		this(nom,prenom, telephone, login, password, type);
		
		connectedUserId = id;
		this.setId(id);
	}


	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
	
	public List<Incident> getIncidents() {
		return incidents;
	}

	public UserType getUserType() {
		
		if (type.equals(UserType.ADMINISTRATEUR.name()))
			return UserType.ADMINISTRATEUR;
		
		else if (type.equals(UserType.RESPONSABLE.name()))
			return UserType.RESPONSABLE;
		
		else if (type.equals(UserType.RAPPORTEUR.name()))
			return UserType.RAPPORTEUR;
		
		else if (type.equals(UserType.DEVELOPPEUR.name()))
			return UserType.DEVELOPPEUR;
		
		return null;
	}
	
	public static Integer getConnectedUserId () {
		return connectedUserId;
	}
	
	
}
