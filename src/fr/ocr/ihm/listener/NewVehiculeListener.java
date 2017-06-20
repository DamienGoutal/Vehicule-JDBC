package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.MarqueDAO;
import dao.MoteurDAO;
import dao.OptionDAO;
import dao.TypeMoteurDAO;
import fr.ocr.sql.HsqldbConnection;
import voiture.Marque;
import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;
import voiture.option.Option;

public class NewVehiculeListener implements ActionListener {

	private JFrame frame;

	public NewVehiculeListener(JFrame f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 
		 Vous devez définir cette méthode afin d'afficher
		 une popup personnalisée pour ainsi pouvoir créer un nouveau véhicule
		 
		 */
		MarqueDAO marqueDao = new MarqueDAO(HsqldbConnection.getInstance());
		MoteurDAO moteurDao = new MoteurDAO(HsqldbConnection.getInstance());
		OptionDAO optionDao = new OptionDAO(HsqldbConnection.getInstance());
		
		JDialog jd = new JDialog(frame, "Ajouter un nouveau véhicule", true);
		jd.setSize(550, 350);
		jd.setBackground(Color.WHITE);
		jd.setLocationRelativeTo(frame);
		jd.setResizable(false);
		jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		// Init component
		
		// Nom du véhicule
		JPanel panelNom = new JPanel();
		panelNom.setBackground(Color.WHITE);
		panelNom.setPreferredSize(new Dimension(220, 60));
		panelNom.setBorder(BorderFactory.createTitledBorder("Nom du véhicule"));
		JTextField nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 25));
		JLabel nomLabel = new JLabel("Saisir un nom :");
		panelNom.add(nomLabel);
		panelNom.add(nom);
		
		// Marque du véhicule
		JPanel panelMarque = new JPanel();
		panelMarque.setBackground(Color.WHITE);
		panelMarque.setPreferredSize(new Dimension(220, 60));
		panelMarque.setBorder(BorderFactory.createTitledBorder("Marque du véhicule"));
		JComboBox marques = new JComboBox();
		List<Marque> marquesList = marqueDao.list();
		for (Marque mar : marquesList) {
			marques.addItem(mar);
		}
		JLabel marqueLabel = new JLabel("Marque :");
		panelMarque.add(marqueLabel);
		panelMarque.add(marques);
		
		// Type de moteur du véhicule
		JPanel panelMoteur = new JPanel();
		panelMoteur.setBackground(Color.WHITE);
		panelMoteur.setPreferredSize(new Dimension(440, 60));
		panelMoteur.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
		JComboBox moteurs = new JComboBox();
		List<Moteur> moteursList = moteurDao.list();
		for (Moteur mot : moteursList) {
			moteurs.addItem(mot);
		}
		JLabel typeMoteurLabel = new JLabel("Marque :");
		panelMoteur.add(typeMoteurLabel);
		panelMoteur.add(moteurs);
		
		// Prix du véhicule
		JPanel panelPrix = new JPanel();
		panelPrix.setBackground(Color.WHITE);
		panelPrix.setPreferredSize(new Dimension(220, 60));
		panelPrix.setBorder(BorderFactory.createTitledBorder("Prix du véhicule"));
		JTextField prix = new JTextField();
		prix.setPreferredSize(new Dimension(100, 25));
		JLabel prixLabel = new JLabel("Prix :");
		panelPrix.add(prixLabel);
		panelPrix.add(prix);
		
		// Options du véhicule
		JPanel panelOptions = new JPanel();
		panelOptions.setBackground(Color.WHITE);
		panelOptions.setPreferredSize(new Dimension(530, 60));
		panelOptions.setBorder(BorderFactory.createTitledBorder("Options Disponibles"));
		List<Option> optionsList = optionDao.list();
		JCheckBox option = null;
		for (Option opt : optionsList) {
			 option = new JCheckBox(opt.toString());
			 panelOptions.add(option);
		}
		
		/* */
		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		content.add(panelNom);
		content.add(panelMarque);
		content.add(panelMoteur);
		content.add(panelPrix);
		content.add(panelOptions);
		
		jd.getContentPane().add(content, BorderLayout.CENTER);
		
		jd.setVisible(true);
	}
}
