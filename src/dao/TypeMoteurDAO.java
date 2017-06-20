package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voiture.Marque;
import voiture.moteur.TypeMoteur;

/**
 * Classe de liaison entre la classe TypeMoteur et la table TYPE_MOTEUR
 * @author admin
 *
 */
public class TypeMoteurDAO extends DAO<TypeMoteur> {
	
	/**
	 * Constructeur
	 * @param connection
	 */
	public TypeMoteurDAO(Connection connection) {
		super(connection);
	}

	/**
	 * Création d'un TypeMoteur
	 */
	@Override
	public boolean create(TypeMoteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 
	 * Suppression d'un TypeMoteur
	 */
	@Override
	public boolean delete(TypeMoteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Mise à jour d'un TypeMoteur
	 */
	@Override
	public boolean update(TypeMoteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Recherche d'un TypeMoteur à partir de son id
	 */
	@Override
	public TypeMoteur find(int id) {
		TypeMoteur typeMoteur = new TypeMoteur();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM TYPE_MOTEUR WHERE ID = " + id);
			
			if (result.first()) {
				typeMoteur = new TypeMoteur(
					id,
					result.getString("DESCRIPTION")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return typeMoteur;
	}

	@Override
	public List<TypeMoteur> list() {
		List<TypeMoteur> typesMoteur = new ArrayList<>();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM TYPE_MOTEUR ORDER BY DESCRIPTION");
			
			while(result.next()) {
				TypeMoteur typeMoteur = new TypeMoteur(
					result.getInt("ID"),
					result.getString("DESCRIPTION")
				);
				
				typesMoteur.add(typeMoteur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return typesMoteur;
	}

}
