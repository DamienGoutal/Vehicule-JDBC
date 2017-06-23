package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;

/**
 * Classe de liaison entre la classe Vehicule et la table VEHICULE
 * @author admin
 *
 */
public class VehiculeDAO extends DAO<Vehicule> {

	/**
	 * Constructeur
	 * @param connection
	 */
	public VehiculeDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Création d'un Vehicule
	 */
	@Override
	public boolean create(Vehicule obj) {
		// TODO Auto-generated method stub
		System.out.println("Création véhicule");
		return false;
	}

	/**
	 * Suppression d'un Vehicule
	 */
	@Override
	public boolean delete(Vehicule obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Mise à jour d'un Vehicule
	 */
	@Override
	public boolean update(Vehicule obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Recherche d'un Vehicule à partir de son id
	 */
	@Override
	public Vehicule find(int id) {
		Vehicule vehicule = new Vehicule();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM VEHICULE WHERE VEHICULE.ID = " + id);
			
			if (result.first()) {
				vehicule = new Vehicule(
					id,
					result.getString("NOM"),
					null, // Marque
					null, // Moteur
					new ArrayList<Option>(), // Options
					result.getDouble("PRIX")
				);
				
				/* */
				MarqueDAO marqueDao = new MarqueDAO(this.connection);
				Marque marque = marqueDao.find(result.getInt("MARQUE"));
				if (marque != null) {
					vehicule.setMarque(marque);
				}
				
				MoteurDAO moteurDao = new MoteurDAO(this.connection);
				Moteur moteur = moteurDao.find(result.getInt("MOTEUR"));
				if (moteur != null) {
					vehicule.setMoteur(moteur);
				}
				
				/* */
				result = this.connection.createStatement().executeQuery(
						"SELECT * FROM OPTION "
						+ "INNER JOIN VEHICULE_OPTION ON VEHICULE_OPTION.ID_OPTION = OPTION.ID AND VEHICULE_OPTION.ID_VEHICULE = " + id);
				OptionDAO optionDao = new OptionDAO(this.connection);
				
				while (result.next()) {
					Option option = optionDao.find(result.getInt("ID"));
					if (option != null) {
						vehicule.addOption(option);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicule;
	}

	@Override
	public List<Vehicule> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
