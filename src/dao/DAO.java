package dao;

import java.sql.Connection;
import java.util.List;

/**
 * Classe abstraite d'acc�s aux tables de la BDD
 * @author admin
 *
 * @param <T>
 */
public abstract class DAO<T> {
	
	protected Connection connection = null;
	
	/**
	 * Constructeur
	 * @param connection
	 */
	public DAO(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Cr�ation d'un objet
	 * @param obj
	 * @return
	 */
	public abstract boolean create(T obj);
	
	/**
	 * Suppression d'un objet
	 * @param obj
	 * @return
	 */
	public abstract boolean delete(T obj);
	
	/** 
	 * Mise � jour d'un objet
	 * @param obj
	 * @return
	 */
	public abstract boolean update(T obj);
	
	/**
	 * Recherche d'un objet � partir de son id
	 * @param id
	 * @return
	 */
	public abstract T find(int id);
	
	/**
	 * R�cup�ration de tous les objets
	 * @return
	 */
	public abstract List<T> list();

}
