package com.sgi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.sgi.entities.User;
import com.sgi.service.Service;
import com.sgi.ui.UIAdmin;
import com.sgi.ui.UIAuthentification;
//import com.sgi.ui.UIVisualiserUser;
import com.sgi.utils.Utilitaire;


public class AdminController {
	
	private UIAdmin uiAdmin;
	private UIAuthentification uIAuthentification;

	public AdminController(UIAdmin uiAdmin,
			UIAuthentification uIAuthentification) {
		this.uiAdmin = uiAdmin;
		this.uIAuthentification = uIAuthentification;
		
		List<User> users;
		try {
			users = Service.listerUsers();
			DefaultTableModel tableModel = (DefaultTableModel)uiAdmin.getTable();
			Object [] row = new Object[7];
			for(int i=0; i<users.size(); i++) {
				row[0] = users.get(i).getId();
				row[1] = users.get(i).getNom();
				row[2] = users.get(i).getPrenom();
				row[3] = users.get(i).getTelephone();
				row[4] = users.get(i).getLogin();
				row[5] = users.get(i).getPassword();
				row[6] = users.get(i).getType();
				
				tableModel.addRow(row);
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		addListeners();
	}

	private void addListeners () {
		
		uiAdmin.addAjouterlistener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nom = uiAdmin.getFirstName();
				String prenom = uiAdmin.getLastName();
				String tel = uiAdmin.getTel();
				String login = uiAdmin.getTLogin();
				String password = uiAdmin.getTPassword();
				String type = uiAdmin.getCbType();
				
				User user = new User (nom, prenom, tel, login, password, type);
				
				try {
					Service.creerUser(user); 
					uiAdmin.clear();
					Utilitaire.displayNotification("Utilisateur créé avec succès !");
					List<User> users = Service.listerUsers();
					//System.out.println(users);
					
				} catch (Exception e1) {
					Utilitaire.displayErrorMessage("Error : " + e1.getMessage());
				}								
			}
		});
		
		uiAdmin.addQuitterlistener ( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uiAdmin.dispose();
				uIAuthentification.clear();
				uIAuthentification.montrer();
				
			}
		});
		
		uiAdmin.addRechercherlistener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel tableUser = (DefaultTableModel)uiAdmin.getTable();
				String recherche = uiAdmin.getRecherche().toLowerCase();
				TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(tableUser);
				uiAdmin.tableUser().setRowSorter(tableRowSorter);
				tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+recherche));
				
				if(uiAdmin.getRecherche() == "")
				{
					AdminController adminController = new AdminController(uiAdmin, uIAuthentification);
					adminController.run();
				}
				

				} 
		}); 
		
		
		/*uiAdmin.addNextlistener ( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UIVisualiserUser uiVisualiser = new UIVisualiserUser();
				VisualiserUserController creerUserController = new VisualiserUserController (uiVisualiser);

				creerUserController.run ();
				
			}
		}); */
	}
	
	public void run() {
		uiAdmin.setVisible(true);
	}
}
