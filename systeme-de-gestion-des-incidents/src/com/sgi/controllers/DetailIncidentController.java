package com.sgi.controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.sgi.entities.Incident;
import com.sgi.entities.Note;
import com.sgi.entities.Statut;
import com.sgi.entities.User;
import com.sgi.service.Service;
import com.sgi.ui.UIDetailIncident;
import com.sgi.ui.UIVisualiserIncident;
import com.sgi.utils.Utilitaire;

public class DetailIncidentController {
	
	private UIDetailIncident uiDetailIncident;
	private UIVisualiserIncident uiVisualiserIncident;
	//private UIAuthentification uiAuthentification;

	/**
	 * @throws Exception 
	 * @wbp.parser.entryPoint
	 */
	public DetailIncidentController(UIDetailIncident uiDetailIncident,UIVisualiserIncident uiVisualiserIncident) throws Exception {
		this.uiDetailIncident = uiDetailIncident;
		this.uiVisualiserIncident = uiVisualiserIncident;
		
		List<User> listDeveloppeur; 
		
		int idIncident = uiDetailIncident.getIdIncident();
		List<Note> notes;
		try {
			listDeveloppeur = Service.listDeveloppeur();
			notes = Service.listerNotes (idIncident);
			uiDetailIncident.afficherLesNotes (notes);
			uiDetailIncident.fillComboBox(listDeveloppeur);
			
		} catch (Exception e) {
			Utilitaire.displayErrorMessage("Erreur au chargement des notes : " + e.getMessage());
		}				
		
		addListeners ();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void addListeners () {
		
		uiDetailIncident.addAjouterNouvelleNoteListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = uiDetailIncident.getNouvelleNote();								
				
				if (message.equals("")) return;
				
				try {
					
					int idIncident = uiDetailIncident.getIdIncident();
					List<Note> notes;
					Note note = new Note (uiDetailIncident.getIdIncident(), 
								User.getConnectedUserId(),
								message, 
								Utilitaire.getCurrentTime());
					
					Service.creerNote (note);
					uiDetailIncident.effacerLeChampNouvelleNote();
					Utilitaire.displayNotification("Note ajout?e avec succ?s !");
					notes = Service.listerNotes (idIncident);
					uiDetailIncident.afficherLesNotes (notes);
					
				} catch (Exception e1) {
					Utilitaire.displayErrorMessage("Error " + e1.getMessage());
				}
				
			}
		});
		
		uiDetailIncident.addAssignerListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String developpeur = uiDetailIncident.getDeveloppeurs();
				int idIncident = uiDetailIncident.getIdIncident();
				/*System.out.println("---GET DEVELOPPEUR---");
				System.out.println(developpeur);
				System.out.println("---AFTER DEVELOPPEUR---");*/
				
				try {
					User user = Service.getUserByNom(developpeur);
					/*System.out.println("---USER---");
					System.out.println(user);
					System.out.println("----AFTER USER-----");
					System.out.println("----USER NOM-----");
					System.out.println(user.getNom());
					System.out.println("----AFTER USER NOM-----");*/
					int idDeveloppeur = user.getId(); //FIX ME!
					Incident incident = Service.getIncident(idIncident);
					uiDetailIncident.getField(incident);
					
					int confirmation = Utilitaire.showConfirmationMessage(
							"Assignation de l'incident\n"+
							 "\t ---- D?tails ---- \n"+
							 "\t ID : "+incident.getId()+"\n"+
							 "\t Application : "+incident.getApplication()+"\n"+
							 "\t Descrpition : "+incident.getDescription()+"\n"+
							 "\t Gravit? : "+incident.getGravite()+"\n"+
							 "\t------------------------- \n"+
							 "Assigner ? : "+user.getNom());
					if (confirmation == 0 )
					{
						
						incident.setStatut(Statut.ASSIGNED);						
						Service.updateIncident(incident,idDeveloppeur);
						Utilitaire.displayNotification("Incident assign? ? < "+user.getNom()+" "+user.getPrenom()+" > avec succ?s !");
						uiDetailIncident.assignerFalse();
						uiDetailIncident.toHide();
						uiDetailIncident.getField(incident);
						
						List<Incident> incidents;
						incidents = Service.listerIncidents();
						uiVisualiserIncident.loadIncident(incidents);
					}
					else if (confirmation == 1)
					{
						;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		uiDetailIncident.addCloturerListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idIncident = uiDetailIncident.getIdIncident();
				try {
					Incident incident = Service.getIncident(idIncident);
					if(incident.getStatut().compareTo("CLOSED") == 0)
					{
						Utilitaire.displayNotification("L'incident est d?ja clotur?");
					}else
					{
						incident.setStatut(Statut.CLOSED);
						Service.updateStatus(incident);	
						
						incident.setCloseDate(Utilitaire.getCurrentTime());
						Service.updateDateCloture(incident);
						
						Utilitaire.displayNotification("Incident clotur? avec succ?s !");
						uiDetailIncident.getField(incident);
						
						uiDetailIncident.fermerBoutonOuvrir();
						
						List<Incident> incidents;
						incidents = Service.listerIncidents();
						uiVisualiserIncident.loadIncident(incidents);
					}
				} catch (Exception e1) {
					
					Utilitaire.displayErrorMessage("Error :" + e1.getMessage());
				}
				
			}
		});
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void run() {
		uiDetailIncident.setVisible(true);
	}
}
