package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dao.VehiculeDAO;
import fr.ocr.sql.HsqldbConnection;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.option.Option;

public class ViewDetailVehiculeListener extends ButtonListener {
	private Integer id;

	public void actionPerformed(ActionEvent e) {
		/*
		
		Vous devez définir comment afficher le détail d'un véhicule
		ceci dans un popup personnalisée
		
		*/
		
		id = Integer.parseInt((String) this.table.getValueAt(row, column - 1));
		
		VehiculeDAO vehiculeDao = new VehiculeDAO(HsqldbConnection.getInstance());
		Vehicule vehicule = vehiculeDao.find(id);
		
		Frame frameParent = (Frame) SwingUtilities.windowForComponent((JButton) e.getSource());
		
		final JDialog jd = new JDialog(frameParent, "Ajouter un nouveau véhicule", true);
		jd.setTitle("Ajouter un nouveau véhicule");
		jd.setModal(true);
		jd.setSize(550, 350);
		jd.setBackground(Color.WHITE);
		jd.setLocationRelativeTo(frameParent);
		jd.setResizable(false);
		jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		JPanel panelNom = new JPanel();
		panelNom.setBackground(Color.WHITE);
		panelNom.setPreferredSize(new Dimension(220, 50));
		panelNom.setBorder(BorderFactory.createTitledBorder("Nom du véhicule"));
		JLabel nom = new JLabel(vehicule.getNom());
		panelNom.add(nom);
		
		JPanel panelMarque = new JPanel();
		panelMarque.setBackground(Color.WHITE);
		panelMarque.setPreferredSize(new Dimension(220, 50));
		panelMarque.setBorder(BorderFactory.createTitledBorder("Marque du véhicule"));
		JLabel marque = new JLabel(vehicule.getMarque().toString());
		panelMarque.add(marque);
		
		JPanel panelMoteur = new JPanel();
		panelMoteur.setBackground(Color.WHITE);
		panelMoteur.setPreferredSize(new Dimension(440, 50));
		panelMoteur.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
		JLabel typeMoteur = new JLabel(vehicule.getMoteur().toString());
		panelMoteur.add(typeMoteur);
		
		JPanel panelPrix = new JPanel();
		panelPrix.setBackground(Color.WHITE);
		panelPrix.setPreferredSize(new Dimension(220, 50));
		panelPrix.setBorder(BorderFactory.createTitledBorder("Prix du véhicule"));
		JLabel prix = new JLabel("Prix sans option :" + vehicule.getPrix() + " €");
		panelPrix.add(prix);
		
		final JPanel panelOptions = new JPanel();
		panelOptions.setBackground(Color.WHITE);
		panelOptions.setPreferredSize(new Dimension(530, 80));
		panelOptions.setBorder(BorderFactory.createTitledBorder("Options Disponibles"));
		JLabel options = new JLabel("");
		if (vehicule.getOptions() != null) {
			for (Option opt : vehicule.getOptions()) {
				options.setText(options.getText() + opt.getNom() +"(" + opt.getPrix() + " €)  ");
			}
		}
		panelOptions.add(options);
		
		JPanel panelPrixTotal = new JPanel();
		panelPrixTotal.setBackground(Color.GREEN);
		panelPrixTotal.setPreferredSize(new Dimension(330, 50));
		panelPrixTotal.setBorder(BorderFactory.createTitledBorder("Prix TOTAL du véhicule"));
		JLabel prixTotal = new JLabel(vehicule.getPrixTotal() + " €");
		panelPrixTotal.add(prixTotal);
		
		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		content.add(panelNom);
		content.add(panelMarque);
		content.add(panelMoteur);
		content.add(panelPrix);
		content.add(panelOptions);
		content.add(panelPrixTotal);
		
		jd.getContentPane().add(content, BorderLayout.CENTER);
		
		jd.setVisible(true);
	}
}
