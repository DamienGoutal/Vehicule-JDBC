package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voiture.Marque;

/**
 * Classe de liaison entre la classe Marque et la table MARQUE
 * @author admin
 *
 */
public class MarqueDAO extends DAO<Marque> {

	/**
	 * Constructeur
	 * @param connection
	 */
	public MarqueDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Création d'une Marque
	 */
	@Override
	public boolean create(Marque obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Suppression d'une marque
	 */
	@Override
	public boolean delete(Marque obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Mise à jour d'une Marque
	 */
	@Override
	public boolean update(Marque obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Recherche d'une Marque à partir de son id
	 */
	@Override
	public Marque find(int id) {
		Marque marque = new Marque();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM MARQUE WHERE ID = " + id);
			
			if (result.first()) {
				marque = new Marque(
					id,
					result.getString("NOM")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return marque;
	}
	
	/**
	 * Récupération de toutes les marques
	 */
	@Override
	public List<Marque> list() {
		List<Marque> marques = new ArrayList<>();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM MARQUE ORDER BY NOM");
			
			while(result.next()) {
				Marque marque = new Marque(
					result.getInt("ID"),
					result.getString("NOM")
				);
				
				marques.add(marque);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return marques;
	}

}
