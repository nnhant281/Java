package GUI;


import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import DTO.compteModele;


public class mainGUI {
	JFrame fenetre;
	JTabbedPane tabs;
	JPanel tabEmploye, tabMenu, tabCommande, tabClient, tabCompte, tabCA;
	compteModele user = new compteModele();
	
	public mainGUI(compteModele user) {
				
		this.user = user;	
		addComponents();
		afficheModuleParHabilitation();	
	}
	
	public void addComponents() {
		
		
		employe_GUI employe = new employe_GUI();
		compte_GUI compte = new compte_GUI();
		client_GUI client = new client_GUI();
		chiffreAffaire_GUI ca = new chiffreAffaire_GUI();
		facture_GUI facture = new facture_GUI();
		table_GUI table = new table_GUI();
		produit_GUI produit = new produit_GUI();
		commande_GUI commande = new commande_GUI(user);	
		
	
		fenetre = new JFrame("Mon application");
		Container cprincipal = fenetre.getContentPane();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabs = new JTabbedPane(JTabbedPane.LEFT);
		tabs.setBackground(Color.DARK_GRAY);
		
		
		tabs.addTab("Commande",commande);
		tabs.addTab("Table", table);
		tabs.addTab("Facture", facture);
		tabs.addTab("Client", client);
		tabs.addTab("Employe",employe);
		tabs.addTab("Produit",produit);
		tabs.addTab("Chiffre d'affaire",ca);
		tabs.addTab("Compte",compte);
		
		
		
		
		
		tabs.setSelectedIndex(0);
			
		cprincipal.add(tabs);

		fenetre.pack(); // calcul de la taille adéquate		
		fenetre.setVisible(true); // permet de faire apparaître l'interface graphique à l'écran	
		
	}
	
	public void afficheModuleParHabilitation() {
				
		if (user.getHabilitation() == 2) {			
			tabs.setEnabledAt(4, false);
			tabs.setEnabledAt(5, false);
			tabs.setEnabledAt(6, false);
			tabs.setEnabledAt(7, false);		
		}
	}
}
