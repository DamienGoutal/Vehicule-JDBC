package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import dao.VehiculeDAO;
import fr.ocr.sql.HsqldbConnection;
import voiture.Vehicule;

//Notre listener pour le bouton
public class ButtonListener implements ActionListener {
	protected int column, row, id;
	protected JTable table;


	public void setColumn(int col) {
		this.column = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void actionPerformed(ActionEvent event) {
		/*
		
		Ici, il vous faut définir comment supprimer un véhicule
		et n'oubliez pas de supprimer toutes les options de ceui-ci...
		
		*/
		
		id = Integer.parseInt((String) this.table.getValueAt(row, column - 2));
		
		VehiculeDAO vehiculeDao = new VehiculeDAO(HsqldbConnection.getInstance());
		Vehicule vehicule = vehiculeDao.find(id);
		
		System.out.println("Suppression du véhicule - " + vehicule.toString());
		
		// TODO supprimer ligne du tableau
		//((AbstractTableModel)table.getModel()).
		
		
		// TODO appel DAO pour delete véhicule
		//vehiculeDao.delete(vehicule);
	}
}