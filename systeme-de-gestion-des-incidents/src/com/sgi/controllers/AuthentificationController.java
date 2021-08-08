package com.sgi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sgi.entities.User;
import com.sgi.entities.UserType;
import com.sgi.service.Service;
import com.sgi.ui.UIAdmin;
import com.sgi.ui.UIAuthentification;
import com.sgi.ui.UISelectionOperation;
import com.sgi.ui.UIVisualiserIncident;
import com.sgi.utils.Utilitaire;

public class AuthentificationController {

	private UIAuthentification uiAuthentification;

	public AuthentificationController(UIAuthentification uiAuthentification) {
		this.uiAuthentification = uiAuthentification;
		
		addListeners();
	}
	
	private void addListeners () {
		
		uiAuthentification.addValiderListener ( new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String login = uiAuthentification.getLogin();
				String password = uiAuthentification.getPassword();
				
				if (login.equals("") || password.equals("")) {
					
					Utilitaire.displayErrorMessage("Login or password is empty.");
					return;
				}
				
				try {
					User user = Service.authentifier(login, password);
					if (user == null) {
						
						Utilitaire.displayErrorMessage("Authentication error ! ");
					} else {												
						
						UserType userType = user.getUserType();
						switch (userType)
						{
							case RAPPORTEUR:
								UISelectionOperation uiSelectionOperation = new UISelectionOperation();
								SelectionOperationController selectionController = new SelectionOperationController (uiSelectionOperation, uiAuthentification);
								
								selectionController.run ();
								
								break;
								
							case DEVELOPPEUR:
								break;
								
							case RESPONSABLE:
								UIVisualiserIncident uiVisualiser = new UIVisualiserIncident();
								VisualiserIncidentController visualiserController = new VisualiserIncidentController(uiVisualiser);
								
								visualiserController.run();
								
								break;
								
							case ADMINISTRATEUR:
								UIAdmin uiAdmin = new UIAdmin();
								AdminController adminController = new AdminController(uiAdmin, uiAuthentification);
								
								adminController.run();
								break;
						}
						
						uiAuthentification.cacher();
					}
				} catch (Exception exception) {
					Utilitaire.displayErrorMessage("Error : " + exception.getMessage());
				}
			}
		});
		
		
		uiAuthentification.addQuitterListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uiAuthentification.dispose();				
			}
		});
	}

	public void run() {
		uiAuthentification.montrer();
	}
}
