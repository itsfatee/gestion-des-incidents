package com.sgi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sgi.entities.Incident;
import com.sgi.entities.User;
import com.sgi.jdbc.DBManager;
import com.sgi.utils.Utilitaire;

public class IncidentDaoImpl implements IDao<Incident> {

	@SuppressWarnings("static-access")
	@Override
	public void create(Incident incident) throws Exception {
		
		User user = null ;
		int idRapporteur = user.getConnectedUserId();
		incident.setIdRapporteur(idRapporteur);
		//idRapporteur = incident.getIdRapporteur();
		
		Connection connection = DBManager.getConnection() ;
		

		String query = "Insert Into T_Incidents (open_date,description, application, gravite,idRapporteur,idDeveloppeur) values (?, ?, ?, ?, ?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, Utilitaire.getCurrentTime());
		preparedStatement.setString(2, incident.getDescription());
		preparedStatement.setString(3, incident.getApplication());
		preparedStatement.setString(4, incident.getGravite());
		preparedStatement.setInt(5, incident.getIdRapporteur());
		preparedStatement.setInt(6, 0);

		preparedStatement.execute();
		
		connection.close();

	}

	@Override
	public Incident read(int id) throws Exception {
		Incident incident = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Incidents Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	incident = new Incident (id, 
	        		resultSet.getString("description"),
	        		resultSet.getString("application"),
	        		resultSet.getString("gravite"));
	    }

	    connection.close();
	    
		return incident;
	}

	@Override
	public void update(Incident incident) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update T_Incidents Set description=? Where id=?";

	    PreparedStatement prepareStatement = connection.prepareStatement(query);
	        
	    prepareStatement.setString(1, incident.getDescription());
	    prepareStatement.setInt(2, incident.getId());
	       
	    prepareStatement.execute();
	    
	    connection.close();
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Delete From T_Incidents Where id=?";
	       
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		
		connection.close();
	}

	@Override
	public List<Incident> list() throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<Incident> incidents = new ArrayList<>();
		String query = "Select * From T_Incidents";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String open_date = resultSet.getString("open_date");
			String description = resultSet.getString("description");
			String application = resultSet.getString("application");
			String gravite = resultSet.getString("gravite");
			int idRapporteur = resultSet.getInt("idRapporteur");
			int idDeveloppeur = resultSet.getInt("idDeveloppeur");
			String statut = resultSet.getString("statut");
			String close_date = resultSet.getString("close_date");
			
			Incident incident = new Incident (id,open_date, description, application, gravite,idRapporteur,idDeveloppeur,statut,close_date);
			
			incidents.add(incident);
		}
		
		connection.close();
		
		return incidents;
	}

	@Override
	public Incident readByNom(String nom) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Incident incident, int idDev) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update T_Incidents Set idDeveloppeur=?, statut=? Where id=?";

	    PreparedStatement prepareStatement = connection.prepareStatement(query);
	    
	    prepareStatement.setInt(3, incident.getId());
	    prepareStatement.setString(2, incident.getStatut());
	    
	    prepareStatement.setInt(1, idDev);
	   
	       
	    prepareStatement.execute();
	    
	    connection.close();
		
	}

	@Override
	public List<User> listDeveloppeurs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDateCloturee(Incident incident) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatus(Incident incident) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
