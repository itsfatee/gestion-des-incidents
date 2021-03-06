package com.sgi.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;


import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sgi.dao.UserDaoImpl;
import com.sgi.entities.Incident;
import com.sgi.entities.Note;
import com.sgi.entities.User;
import com.sgi.jdbc.DBManager;
import com.sgi.utils.Utilitaire;

public class UIDetailIncident extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JButton buttonAjouter;
	
	private JComboBox<String> cBDeveloppeurs;
	private JButton buttonAssigner;
	private JButton buttonCloturer;
	private JButton buttonOuvrir;
	private JButton buttonAttente;
	private JButton buttonResolu;
	private JLabel labelDevelopper;
	private JPanel panelChangementEtat ;
	
	private JTextArea tANotes;
	private JTextArea tADescription;
	private JTextArea tANouvelleNote;
	
	private JTextField tFIdentifiant;
	private JTextField tFApplication;
	private JTextField tFStatut;
	private JTextField tFGravite;
	

	private int idIncident;
	
		
	public UIDetailIncident(int idIncident) throws Exception {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(700, 600));
		setModal(true);
		setResizable(false);
		
		
		this.idIncident = idIncident;
		
		setTitle("D\u00E9tails de l'incident <" + idIncident + ">");				
				
		JPanel panelNotes = new JPanel();
		getContentPane().add(panelNotes, BorderLayout.SOUTH);
		panelNotes.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAjouterNote = new JPanel();
		panelNotes.add(panelAjouterNote, BorderLayout.SOUTH);
		panelAjouterNote.setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtonAjouter = new JPanel();
		panelButtonAjouter.setBackground(new Color(0, 191, 255));
		panelAjouterNote.add(panelButtonAjouter, BorderLayout.EAST);
		
		buttonAjouter = new JButton("Ajouter");
		buttonAjouter.setBackground(new Color(0, 0, 0));
		panelButtonAjouter.add(buttonAjouter);
		
		JPanel paneltFNote = new JPanel();
		paneltFNote.setBackground(new Color(135, 206, 235));
		paneltFNote.setBorder(new TitledBorder(null, "Ajouter une note ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAjouterNote.add(paneltFNote, BorderLayout.CENTER);
		paneltFNote.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneNouvelleNote = new JScrollPane();
		scrollPaneNouvelleNote.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneNouvelleNote.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		paneltFNote.add(scrollPaneNouvelleNote, BorderLayout.CENTER);
		
		tANouvelleNote = new JTextArea();
		tANouvelleNote.setFont(new Font("Arial", Font.PLAIN, 12));
		tANouvelleNote.setRows(3);
		scrollPaneNouvelleNote.setViewportView(tANouvelleNote);
		
		JPanel panelFluxEchanges = new JPanel();
		panelFluxEchanges.setBackground(new Color(135, 206, 235));
		panelFluxEchanges.setBorder(new TitledBorder(null, "Flux d'\u00E9changes ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNotes.add(panelFluxEchanges);
		panelFluxEchanges.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneFluxEchanges = new JScrollPane();
		scrollPaneFluxEchanges.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneFluxEchanges.setPreferredSize(new Dimension(2, 190));
		panelFluxEchanges.add(scrollPaneFluxEchanges, BorderLayout.CENTER);
		
		tANotes = new JTextArea();
		tANotes.setEditable(false);
		tANotes.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPaneFluxEchanges.setViewportView(tANotes);
		
		JPanel panelDetailIncident = new JPanel();
		panelDetailIncident.setBackground(new Color(135, 206, 235));
		panelDetailIncident.setBorder(new TitledBorder(null, "D\u00E9tails de l'incident ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		panelDetailIncident.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.PREF_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel labelId = new JLabel("Id :");
		panelDetailIncident.add(labelId, "2, 2, right, default");
		
		tFIdentifiant = new JTextField();
		tFIdentifiant.setEnabled(false);
		tFIdentifiant.setEditable(false);
		panelDetailIncident.add(tFIdentifiant, "4, 2, fill, default");
		tFIdentifiant.setColumns(20);
		tFIdentifiant.setText(String.valueOf(idIncident));
		
		JLabel labelApplication = new JLabel("Application :");
		panelDetailIncident.add(labelApplication, "2, 4, right, default");
		
		tFApplication = new JTextField();
		tFApplication.setEnabled(false);
		tFApplication.setEditable(false);
		panelDetailIncident.add(tFApplication, "4, 4, fill, default");
		tFApplication.setColumns(10);
		tFApplication.setText(getApplication());
		
		JLabel labelDescription = new JLabel("Description :");
		labelDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDetailIncident.add(labelDescription, "2, 6");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(2, 80));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDetailIncident.add(scrollPane, "4, 6, fill, top");
		
		tADescription = new JTextArea();
		tADescription.setFont(new Font("Arial", Font.PLAIN, 11));
		tADescription.setRows(3);
		tADescription.setEditable(false);
		tADescription.setEnabled(false);
		scrollPane.setViewportView(tADescription);
		tADescription.setText(getDescription());
		
		JLabel labelStatut = new JLabel("Statut :");
		panelDetailIncident.add(labelStatut, "2, 8, right, default");
		
		tFStatut = new JTextField();
		tFStatut.setEnabled(false);
		tFStatut.setEditable(false);
		panelDetailIncident.add(tFStatut, "4, 8, fill, default");
		tFStatut.setColumns(10);
		tFStatut.setText(getStatut());
		
		JLabel labelGravite = new JLabel("Niveau de gravit\u00E9 :");
		panelDetailIncident.add(labelGravite, "2, 10, right, default");
		
		tFGravite = new JTextField();
		tFGravite.setEnabled(false);
		tFGravite.setEditable(false);
		panelDetailIncident.add(tFGravite, "4, 10, fill, default");
		tFGravite.setColumns(10);
		tFGravite.setText(getGravite());
		
		getContentPane().add(panelDetailIncident, BorderLayout.WEST);
		
		JButton buttonAttente = new JButton("En attente");
		buttonAttente.setBackground(new Color(0, 0, 0));
		buttonAttente.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(buttonAttente, "2, 14");
		
		JButton buttonResolu = new JButton("R\u00E9solu");
		buttonResolu.setBackground(new Color(0, 0, 0));
		panelDetailIncident.add(buttonResolu, "4, 14");
		
		JPanel panelChangementEtat = new JPanel();
		panelChangementEtat.setBackground(new Color(135, 206, 235));
		panelChangementEtat.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Autres actions ...", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelChangementEtat, BorderLayout.CENTER);
		panelChangementEtat.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAssigner = new JPanel();
		panelAssigner.setBackground(new Color(0, 191, 255));
		panelChangementEtat.add(panelAssigner, BorderLayout.NORTH);
		
		JLabel labelAssigner = new JLabel("Assign\u00E9 \u00E0 :");
		panelAssigner.add(labelAssigner);
		
		cBDeveloppeurs = new JComboBox<>();
		cBDeveloppeurs.setPreferredSize(new Dimension(150, 22));
		panelAssigner.add(cBDeveloppeurs);
		
		buttonAssigner = new JButton("Assigner");
		buttonAssigner.setBackground(new Color(0, 0, 0));
		panelAssigner.add(buttonAssigner);
		
		JPanel panelCloturer = new JPanel();
		panelCloturer.setBackground(new Color(0, 191, 255));
		FlowLayout fl_panelCloturer = (FlowLayout) panelCloturer.getLayout();
		fl_panelCloturer.setAlignment(FlowLayout.RIGHT);
		panelChangementEtat.add(panelCloturer, BorderLayout.SOUTH);
		
		buttonCloturer = new JButton("Cl\u00F4turer");
		buttonCloturer.setBackground(new Color(0, 0, 0));
		buttonCloturer.setToolTipText("Cl\u00F4turer le ticket incident ...");
		buttonCloturer.setPreferredSize(new Dimension(80, 25));
		panelCloturer.add(buttonCloturer);
		
		JButton buttonOuvrir = new JButton("Ouvrir");
		buttonOuvrir.setBackground(new Color(0, 0, 0));
		buttonOuvrir.setToolTipText("Ouvrir \u00E0 nouveau le ticket incident ...");
		buttonOuvrir.setPreferredSize(new Dimension(80, 25));
		buttonOuvrir.setSize(new Dimension(80, 25));
		panelCloturer.add(buttonOuvrir);
		
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}
	
	public int getIdIncident() {
		return idIncident;
	}
	
	public String getApplication() throws Exception {
		String application = null;
		Connection connection = DBManager.getConnection() ;
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT application from t_incidents where id="+idIncident+"");
		while(rs.next())
		{
			application = rs.getString("application") ;
			
		}
		return application;

	}
	
	public String getDescription() throws Exception {
		String description = null;
		Connection connection = DBManager.getConnection() ;
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT description from t_incidents where id="+idIncident+"");
		while(rs.next())
		{
			description = rs.getString("description") ;
			
		}
		return description;

	}
	
	
	public String getGravite() throws Exception {
		String gravite = null;
		Connection connection = DBManager.getConnection() ;
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT gravite from t_incidents where id="+idIncident+"");
		while(rs.next())
		{
			gravite = rs.getString("gravite") ;
			
		}
		return gravite;

	}
	
	public String getStatut() throws Exception {
		String statut = null;
		Connection connection = DBManager.getConnection() ;
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT statut from t_incidents where id="+idIncident+"");
		while(rs.next())
		{
			statut = rs.getString("statut") ;
			
		}
		return statut;

	}

	public void addAjouterNouvelleNoteListener(ActionListener actionListener) {
		buttonAjouter.addActionListener(actionListener);
	}
	
	public void addCloturerListener(ActionListener actionListener) {
		buttonCloturer.addActionListener(actionListener);
	}
	
	public void addAssignerListener(ActionListener actionListener) {
		buttonAssigner.addActionListener(actionListener);
	}

	public void afficherLesNotes(List<Note> notes) throws Exception {
		
		String fluxEchanges = "";
		
		for (Note note : notes) {
			
			int idUser = note.getIdCreateur();
			User user = new UserDaoImpl().read(idUser);
			String nCreateur = user.getNom();
			String pCreateur = user.getPrenom();
			String tCreateur = user.getType();
			//fluxEchanges += note.getDateCreation() + ":" + note.getIdCreateur() + "\n";
			fluxEchanges += " [ Auteur ] : " + pCreateur+ " "+nCreateur + "  [ Fonction ] : " + tCreateur + " [ Date - Heure] : "+ note.getDateCreation() + "\n";
			fluxEchanges += note.getMessage() + "\n\n";
		}
		
		this.tANotes.setText(fluxEchanges);
	}

	public String getNouvelleNote() {
		return this.tANouvelleNote.getText();
	}

	public void effacerLeChampNouvelleNote() {
		this.tANouvelleNote.setText(null);
	}	
	
	public String getDeveloppeurs() {
		
		return (String) this.cBDeveloppeurs.getSelectedItem();
	}
	
	public void getField(Incident incident) {	
		
		tFIdentifiant.setText(String.valueOf(incident.getId()));
		tFApplication.setText(incident.getApplication());
		tADescription.setText(incident.getDescription());
		tFGravite.setText(incident.getGravite());
		tFStatut.setText(incident.getStatut());
		
	}
	
	public void assignerFalse() {
		this.cBDeveloppeurs.setVisible(false);
		this.buttonAssigner.setVisible(false);
		
	}
	
	public void fermerBoutonOuvrir(){
		this.buttonOuvrir.setVisible(false);
	}

	
	/*public int fillComboBox() throws Exception {
		
		Connection connection = DBManager.getConnection() ;
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT id, nom, prenom from t_users where type='DEVELOPPEUR' ");
		int id=0;		
		  while(rs.next()){
			    id = rs.getInt("id");
		        String nom=rs.getString("nom");
		        String prenom=rs.getString("prenom");
		        cBDeveloppeurs.addItem(nom+" "+prenom);
		    }
		  return id;
	}*/
	
	public void fillComboBox(List<User> listDeveloppeur) {
		for (User user: listDeveloppeur) {
			//this.cBDeveloppeurs.addItem(user.getNom()+" "+user.getPrenom());
			this.cBDeveloppeurs.addItem(user.getNom());
		}
	}
	
	/*public void hideButton() 
	{
		buttonAttente.setVisible(false);
		buttonResolu.setVisible(false);
	}*/
	
	public void toHide() 
	{
		buttonAssigner.setVisible(false);
		cBDeveloppeurs.setVisible(false);
	}
	
	public void hidePanel()
	{
		panelChangementEtat.setVisible(false);
		
	}
	
	public void toAssign(String nom) {
		this.labelDevelopper.setText(nom);
	}

	
}
	
	
	


