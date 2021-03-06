package com.sgi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sgi.entities.Incident;
import com.sgi.entities.User;
import com.sgi.jdbc.DBManager;

public class UserDaoImpl implements IDao<User> {

	@Override
	public void create(User user) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Insert Into T_Users (nom, prenom, telephone, login, password, type) values (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, user.getNom());
		preparedStatement.setString(2, user.getPrenom());
		preparedStatement.setString(3, user.getTelephone());
		preparedStatement.setString(4, user.getLogin());
		preparedStatement.setString(5, user.getPassword());
		preparedStatement.setString(6, user.getType());

		preparedStatement.execute();
		
		connection.close();

	}

	@Override
	public User read(int id) throws Exception {
		User user = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Users Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	user = new User (id, 
	    			resultSet.getString("nom"),
	        		resultSet.getString("prenom"),
	        		resultSet.getString("telephone"),
	        		resultSet.getString("login"),
	        		resultSet.getString("password"),
	        		resultSet.getString("type"));
	    }

	    connection.close();
	    
		return user;
	}

	@Override
	public void update(User user) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update T_Users Set login=?, password=?, type=? Where id=?";

	    PreparedStatement prepareStatement = connection.prepareStatement(query);
	        
	    prepareStatement.setString(1, user.getLogin());
	    prepareStatement.setString(2, user.getPassword());
	    prepareStatement.setString(3, user.getType());
	    prepareStatement.setInt(4, user.getId());
	       
	    prepareStatement.execute();
	    
	    connection.close();
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Delete From T_Users Where id=?";
	       
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		
		connection.close();
	}

	@Override
	public List<User> list() throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<User> users = new ArrayList<>();
		String query = "Select * From T_Users";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String nom = resultSet.getString("nom");
			String prenom = resultSet.getString("prenom");
			String telephone = resultSet.getString("telephone");
			String login = resultSet.getString("login");
			String password = resultSet.getString("password");
			String type = resultSet.getString("type");
			
			User user = new User (id, nom, prenom, telephone, login, password, type);
			
			users.add(user);
		}
		
		connection.close();
		
		return users;
	}

	public User readByLoginPassword(String login, String password) throws Exception {
		User user = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Users Where login = ? and password = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setString(1, login);
	    preparedStatement.setString(2, password);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	user = new User (resultSet.getInt("id"), 
	    			resultSet.getString("nom"),
	        		resultSet.getString("prenom"),
	        		resultSet.getString("telephone"),
	        		resultSet.getString("login"),
	        		resultSet.getString("password"),
	        		resultSet.getString("type"));
	    }

	    connection.close();
	    
		return user;
	}
	
	public User readByLogin(String login) throws Exception {
		User user = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Users Where login = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setString(1, login);
	    
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	user = new User (resultSet.getInt("id"), 
	    			resultSet.getString("nom"),
	        		resultSet.getString("prenom"),
	        		resultSet.getString("telephone"),
	        		resultSet.getString("login"),
	        		resultSet.getString("password"),
	        		resultSet.getString("type"));
	    }

	    connection.close();
	    
		return user;
	}

	@Override
	public User readByNom(String nom) throws Exception {
		User user = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Users Where nom = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setString(1, nom);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	user = new User (resultSet.getInt(1), 
	    			resultSet.getString("nom"),
	    			resultSet.getString("prenom"),
	    			resultSet.getString("telephone"),
	    			resultSet.getString("login"), 
	        		resultSet.getString("password"), 
	        		resultSet.getString("type"));
	    }

	    connection.close();
	    
		return user;
	}

	@Override
	public void update(Incident incident, int idDev) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> listDeveloppeurs() throws Exception {

		Connection connection = DBManager.getConnection() ;

		List<User> users = new ArrayList<>();
		String query = "Select * From T_Users where type = 'DEVELOPPEUR'";		 
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String nom = resultSet.getString("nom");
			String prenom = resultSet.getString("prenom");
			String fonction = resultSet.getString("telephone");
			String login = resultSet.getString("login");
			String password = resultSet.getString("password");
			String type = resultSet.getString("type");
			
			User user = new User (id,nom, prenom, fonction, login, password, type);
			
			users.add(user);
		}
		
		connection.close();
		
		return users;
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


