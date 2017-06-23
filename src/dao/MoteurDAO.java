package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;

/**
 * Classe de liaison entre la classe Moteur et la table MOTEUR
 * @author admin
 *
 */
public class MoteurDAO extends DAO<Moteur> {

	/**
	 * Constructeur
	 * @param connection
	 */
	public MoteurDAO(Connection connection) {
		super(connection);
	}

	/**
	 * Création d'un Moteur
	 */
	@Override
	public boolean create(Moteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 
	 * Suppression d'un Moteur
	 */
	@Override
	public boolean delete(Moteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Mise à jour d'un Moteur
	 */
	@Override
	public boolean update(Moteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Recherche d'un Moteur à partir de son id
	 */
	@Override
	public Moteur find(int id) {
		Moteur moteur = new Moteur();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM MOTEUR "
						+ "LEFT JOIN TYPE_MOTEUR ON TYPE_MOTEUR.ID = MOTEUR.MOTEUR "
						+ "AND MOTEUR.ID = " + id);
			
			if (result.first()) {
				moteur = new Moteur(
					id,
					null,
					result.getString("CYLINDRE"),
					result.getDouble("PRIX")
				);
				
				TypeMoteurDAO typeMoteurDao = new TypeMoteurDAO(this.connection);
				TypeMoteur typeMoteur = typeMoteurDao.find(result.getInt("MOTEUR"));
				if (typeMoteur != null) {
					moteur.setType(typeMoteur);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return moteur;
	}

	@Override
	public List<Moteur> list() {
		List<Moteur> moteurs = new ArrayList<>();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM MOTEUR");
			
			while(result.next()) {
				Moteur moteur = new Moteur(
						result.getInt("ID"),
						null,
						result.getString("CYLINDRE"),
						result.getDouble("PRIX")
				);
				
				TypeMoteurDAO typeMoteurDao = new TypeMoteurDAO(this.connection);
				TypeMoteur typeMoteur = typeMoteurDao.find(result.getInt("MOTEUR"));
				if (typeMoteur != null) {
					moteur.setType(typeMoteur);
				}
				
				moteurs.add(moteur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return moteurs;
	}

}
