package com.sgi.ui;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import com.sgi.utils.Utilitaire;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class UIAdmin extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnQuitter;
	private JButton btnAjouter;
	private JTextField tFNom;
	private JTextField tFPrenom;
	private JTextField tFTel;
	private JTextField tFlogin;
	private JTextField tFPassword;
	private JComboBox<String> cBType;
	private JTextField tFRecherche;
	private JTextField tFId;
	private JLabel lblNewLabel_1;
	private JButton btnSupprimer;
	private JButton btnModifier;
	@SuppressWarnings("unused")
	private JTable table;
	private JButton btnRecherche;
	private JTable tableUser;
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public UIAdmin() {
		setTitle("Gestion des utilisateurs");
		setSize(new Dimension(842, 465));
		setPreferredSize(new Dimension(714, 465));
		setResizable(false);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		tFRecherche = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, tFRecherche, 21, SpringLayout.NORTH, panel);
		tFRecherche.setForeground(Color.BLACK);
		tFRecherche.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tFRecherche.setColumns(20);
		panel.add(tFRecherche);
		
		JLabel lbID = new JLabel("ID :");
		sl_panel.putConstraint(SpringLayout.NORTH, lbID, 69, SpringLayout.NORTH, panel);
		lbID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lbID);
		
		tFId = new JTextField();
		sl_panel.putConstraint(SpringLayout.EAST, lbID, -6, SpringLayout.WEST, tFId);
		sl_panel.putConstraint(SpringLayout.EAST, tFId, -10, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, tFId, 679, SpringLayout.WEST, panel);
		tFId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sl_panel.putConstraint(SpringLayout.NORTH, tFId, 66, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, tFId, -350, SpringLayout.SOUTH, panel);
		tFId.setEditable(false);
		panel.add(tFId);
		tFId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom : ");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 16, SpringLayout.SOUTH, lbID);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		tFNom = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, tFNom, 12, SpringLayout.SOUTH, tFId);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel, -15, SpringLayout.WEST, tFNom);
		sl_panel.putConstraint(SpringLayout.WEST, tFNom, 0, SpringLayout.WEST, tFId);
		sl_panel.putConstraint(SpringLayout.EAST, tFNom, 0, SpringLayout.EAST, tFId);
		panel.add(tFNom);
		tFNom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tFNom.setColumns(20);
		
		JLabel lblNewLabel_2 = new JLabel("Pr\u00E9nom :  ");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 13, SpringLayout.SOUTH, lblNewLabel);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tFPrenom = new JTextField();
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_2, -6, SpringLayout.WEST, tFPrenom);
		sl_panel.putConstraint(SpringLayout.SOUTH, tFNom, -10, SpringLayout.NORTH, tFPrenom);
		sl_panel.putConstraint(SpringLayout.NORTH, tFPrenom, 123, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, tFPrenom, 146, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, tFPrenom, 0, SpringLayout.WEST, tFId);
		sl_panel.putConstraint(SpringLayout.EAST, tFPrenom, 0, SpringLayout.EAST, tFId);
		panel.add(tFPrenom);
		tFPrenom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tFPrenom.setColumns(20);
		
		JLabel lblNewLabel_3 = new JLabel("T\u00E9l\u00E9phone : ");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 155, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_3, 0, SpringLayout.EAST, lbID);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		tFTel = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, tFTel, 6, SpringLayout.SOUTH, tFPrenom);
		sl_panel.putConstraint(SpringLayout.WEST, tFTel, 679, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, tFTel, 0, SpringLayout.EAST, tFId);
		panel.add(tFTel);
		tFTel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tFTel.setForeground(new Color(0, 0, 0));
		tFTel.setColumns(20);
		
		cBType = new JComboBox<>();
		sl_panel.putConstraint(SpringLayout.WEST, cBType, 0, SpringLayout.WEST, tFId);
		sl_panel.putConstraint(SpringLayout.EAST, cBType, 0, SpringLayout.EAST, tFId);
		panel.add(cBType);
		cBType.setMaximumRowCount(20);
		cBType.addItem("RAPPORTEUR");
		cBType.addItem("ADMINISTRATEUR");
		cBType.addItem("RESPONSABLE");
		cBType.addItem("DEVELOPPEUR");
		
		//cBType.setModel(new DefaultComboBoxModel(new String[] {"RAPPORTEUR", "ADMINISTRATEUR", "RESPONSABLE", "DEVELOPPEUR"}));
		
		cBType.setMaximumSize(new Dimension(32835, 32767));
		cBType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnAjouter = new JButton("Ajouter");
		sl_panel.putConstraint(SpringLayout.SOUTH, btnAjouter, -97, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnAjouter, 0, SpringLayout.WEST, lblNewLabel);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnAjouter);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_6 = new JLabel("Type : ");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_6, 4, SpringLayout.NORTH, cBType);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_6, 9, SpringLayout.WEST, lblNewLabel_2);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_6, 0, SpringLayout.EAST, lbID);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		tFPassword = new JTextField();
		sl_panel.putConstraint(SpringLayout.SOUTH, tFPassword, -195, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, cBType, 16, SpringLayout.SOUTH, tFPassword);
		sl_panel.putConstraint(SpringLayout.EAST, tFPassword, 0, SpringLayout.EAST, tFId);
		panel.add(tFPassword);
		tFPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tFPassword.setColumns(20);
		
		btnQuitter = new JButton("Quitter");
		sl_panel.putConstraint(SpringLayout.NORTH, btnAjouter, 0, SpringLayout.NORTH, btnQuitter);
		sl_panel.putConstraint(SpringLayout.NORTH, btnQuitter, 26, SpringLayout.SOUTH, cBType);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnQuitter, -97, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnAjouter, -34, SpringLayout.WEST, btnQuitter);
		sl_panel.putConstraint(SpringLayout.WEST, btnQuitter, 43, SpringLayout.WEST, tFId);
		sl_panel.putConstraint(SpringLayout.EAST, btnQuitter, -6, SpringLayout.EAST, panel);
		panel.add(btnQuitter);
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		tFlogin = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, tFPassword, 6, SpringLayout.SOUTH, tFlogin);
		sl_panel.putConstraint(SpringLayout.SOUTH, tFTel, -8, SpringLayout.NORTH, tFlogin);
		sl_panel.putConstraint(SpringLayout.SOUTH, tFlogin, -222, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, tFlogin, 181, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, tFlogin, 0, SpringLayout.WEST, tFId);
		sl_panel.putConstraint(SpringLayout.EAST, tFlogin, 0, SpringLayout.EAST, tFId);
		panel.add(tFlogin);
		tFlogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tFlogin.setColumns(20);
		
		JLabel lblNewLabel_5 = new JLabel("Password : ");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 221, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, tFPassword, 6, SpringLayout.EAST, lblNewLabel_5);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_5, 610, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_5, 0, SpringLayout.EAST, lbID);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Login : ");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 184, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_4, 9, SpringLayout.WEST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_4, 0, SpringLayout.EAST, lbID);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblNewLabel_1 = new JLabel("Selon le login :");
		sl_panel.putConstraint(SpringLayout.WEST, tFRecherche, 1, SpringLayout.EAST, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 87, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 24, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, -654, SpringLayout.EAST, panel);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		
		btnSupprimer = new JButton("Supprimer");
		sl_panel.putConstraint(SpringLayout.SOUTH, btnSupprimer, -23, SpringLayout.SOUTH, panel);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnSupprimer);
		
		btnModifier = new JButton("Modifier");
		sl_panel.putConstraint(SpringLayout.EAST, btnSupprimer, -40, SpringLayout.WEST, btnModifier);
		sl_panel.putConstraint(SpringLayout.EAST, btnModifier, -403, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnModifier, 338, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnModifier, 0, SpringLayout.SOUTH, btnSupprimer);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnModifier);
		
		
		btnRecherche = new JButton("Rechercher");
		sl_panel.putConstraint(SpringLayout.EAST, tFRecherche, -6, SpringLayout.WEST, btnRecherche);
		sl_panel.putConstraint(SpringLayout.WEST, btnRecherche, 394, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnRecherche, -1, SpringLayout.NORTH, tFRecherche);
		btnRecherche.setHorizontalAlignment(SwingConstants.LEADING);
		btnRecherche.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnRecherche);
		
		tableUser = new JTable();
		tableUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_3, 15, SpringLayout.EAST, tableUser);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_2, 15, SpringLayout.EAST, tableUser);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 6, SpringLayout.EAST, tableUser);
		sl_panel.putConstraint(SpringLayout.EAST, tableUser, -249, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, tableUser, 24, SpringLayout.SOUTH, tFRecherche);
		sl_panel.putConstraint(SpringLayout.WEST, tableUser, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, tableUser, -97, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnSupprimer, 30, SpringLayout.SOUTH, tableUser);
		sl_panel.putConstraint(SpringLayout.NORTH, btnModifier, 30, SpringLayout.SOUTH, tableUser);
		sl_panel.putConstraint(SpringLayout.WEST, lbID, 53, SpringLayout.EAST, tableUser);
		tableUser.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		tableUser.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "nom", "prenom", "telephone", "login", "password", "type"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		}); 
		panel.add(tableUser);
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}
	
	public void addAjouterlistener(ActionListener actionListener) {
		btnAjouter.addActionListener(actionListener);		
	}
	
	
	public void addQuitterlistener(ActionListener actionListener) {
		btnQuitter.addActionListener(actionListener);		
	}
	
	public void addRechercherlistener(ActionListener actionListener) {
		btnRecherche.addActionListener(actionListener);
	}
	
	
	
	public String getFirstName() {
		return tFNom.getText();
	}
	
	
	
	public String getLastName() {
		return tFPrenom.getText();
	}
	public String getTel() {
		return tFTel.getText();
	}
	
	public String getTLogin() {
		return tFlogin.getText();
	}
	
	public String getTPassword() {
		return tFPassword.getText();
	}
	
	public String getCbType() {
		return (String) cBType.getSelectedItem();
	}
	
	public String getRecherche() {
		return tFRecherche.getText();
	}
	
	public void clear() {
		this.tFNom.setText(null);
		this.tFPrenom.setText(null);
		this.tFTel.setText(null);
		this.tFlogin.setText(null);
		this.tFPassword.setText(null);
	}
	
	
	public DefaultTableModel getTable() {
		return (DefaultTableModel)tableUser.getModel();
	}
	
	public JTable tableUser() {
		return tableUser;
	}
}
