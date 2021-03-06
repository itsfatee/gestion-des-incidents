package com.sgi.dao;

import java.util.List;

import com.sgi.entities.Incident;
import com.sgi.entities.User;

public interface IDao<T> {
	
	public void create(T obj) throws Exception;
	
	public T read(int id) throws Exception;
	
	public void update(T obj) throws Exception;
	
	public void delete(Integer id) throws Exception;
	
	public List<T> list() throws Exception;
	
	public T readByNom(String nom) throws Exception;

	public void update(Incident incident, int idDev)throws Exception;
	
	void updateStatus(Incident incident) throws Exception;
	
	public void updateDateCloturee(Incident incident) throws Exception;

	public List<User> listDeveloppeurs()throws Exception;
}
