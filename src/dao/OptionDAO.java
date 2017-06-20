package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voiture.option.Option;

/**
 * Classe de liaison entre la classe Option et la table OPTION
 * @author admin
 *
 */
public class OptionDAO extends DAO<Option> {

	/**
	 * Constrcteur
	 * @param connection
	 */
	public OptionDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Création d'une Option
	 */
	@Override
	public boolean create(Option obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Suppression d'une Option
	 */
	@Override
	public boolean delete(Option obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Mise à jour d'une Option
	 */
	@Override
	public boolean update(Option obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Recherche d'une option à partir de son id
	 */
	@Override
	public Option find(int id) {
		Option option = new Option();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM OPTION WHERE ID = " + id);
			
			if (result.first()) {
				option = new Option(
					id,
					result.getString("DESCRIPTION"),
					result.getDouble("PRIX")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return option;
	}

	@Override
	public List<Option> list() {
		List<Option> options = new ArrayList<>();
		
		try {
			ResultSet result = this.connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM OPTION");
			
			while (result.next()) {
				Option option = new Option(
					result.getInt("ID"),
					result.getString("DESCRIPTION"),
					result.getDouble("PRIX")
				);
				
				options.add(option);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return options;
	}

}
