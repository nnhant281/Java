/*
 * gestion les clients 
 * chercher, ajouter, modifier, consulter les client 
 */
package GUI;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import BUS.client_BUS;
import Custom.monButton;
import Custom.monTableau;
import Custom.transparentPanel;
import DTO.client;

import static Main.main.changeLNF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

@SuppressWarnings("serial")
public class client_GUI extends JPanel{
	
	public client_GUI() {
		changeLNF("Nimbus");
		addControls();
        addEvents();
	}
	
	
	private client_BUS clientBUS = new client_BUS();

    final Color colorPanel = new Color(250, 240, 230);
    JTextField txtIdClient, txtNom, txtPrenom, txtNumTel, txtEmail, txtPoint, txtCleRecherche;
	JTextArea txtAdresse;
    JButton btnAjoute, btnModif, btnReset;
    monTableau tabClient;
    DefaultTableModel modelTabClient;

    /*
     * l'interface 
     */
    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 18);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);


        /*
        =========================================================================
                                    PANEL CLIENT
        =========================================================================
         */
        JPanel panelClient = new transparentPanel();
        panelClient.setLayout(new BoxLayout(panelClient, BoxLayout.Y_AXIS));

        JPanel panelTop = new transparentPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

        JPanel pnTitle = new transparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>Gestion de clients</h1></html>");
        pnTitle.add(lblTitle);
        panelTop.add(pnTitle);

        //======PANEL TEXT FIELD=======
        JPanel pnTextField = new transparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblIdClient, lblNom, lblPrenom, lblAdresse, lblPoint,lblNumTel,lblEmail;
        lblIdClient = new JLabel("ID client");
        lblNom = new JLabel("Nom *");
        lblPrenom = new JLabel("Prénom *");
        lblAdresse = new JLabel("Adresse *");
        lblNumTel = new JLabel("Tel *");
        lblEmail = new JLabel("Email");
        lblPoint = new JLabel("Point");

        lblIdClient.setFont(font);
        lblNom.setFont(font);
        lblPrenom.setFont(font);
        lblAdresse.setFont(font);
        lblNumTel.setFont(font);
        lblEmail.setFont(font);
        lblPoint.setFont(font);

        txtIdClient = new JTextField(20);
        txtIdClient.setEditable(false);
        txtNom = new JTextField(20);
        txtPrenom = new JTextField(20);
        txtAdresse = new JTextArea(3,20);
        txtNumTel = new JTextField(20);
        txtEmail = new JTextField(20);
        txtPoint = new JTextField(20);
        txtPoint.setEditable(false);

        txtIdClient.setFont(font);
        txtNom.setFont(font);
        txtPrenom.setFont(font);
        txtAdresse.setFont(font);
        txtNumTel.setFont(font);
        txtEmail.setFont(font);
        txtPoint.setFont(font);

        JPanel panelIdClient = new transparentPanel();
        panelIdClient.add(lblIdClient);
        panelIdClient.add(txtIdClient);
        pnTextField.add(panelIdClient);

        JPanel panelNom = new transparentPanel();
        panelNom.add(lblNom);
        panelNom.add(txtNom);
        pnTextField.add(panelNom);

        JPanel panelPrenom = new transparentPanel();
        panelPrenom.add(lblPrenom);
        panelPrenom.add(txtPrenom);
        pnTextField.add(panelPrenom);
        
        JPanel panelAdresse = new transparentPanel();
        panelAdresse.add(lblAdresse);
        panelAdresse.add(txtAdresse);
        pnTextField.add(panelAdresse);

        JPanel panelNumTel = new transparentPanel();
        panelNumTel.add(lblNumTel);
        panelNumTel.add(txtNumTel);
        pnTextField.add(panelNumTel);

        JPanel panelEmail = new transparentPanel();
        panelEmail.add(lblEmail);
        panelEmail.add(txtEmail);
        pnTextField.add(panelEmail);

        JPanel panelPoint = new transparentPanel();
        panelPoint.add(lblPoint);
        panelPoint.add(txtPoint);
        pnTextField.add(panelPoint);

        Dimension lblSize = new Dimension(120,35);
        lblIdClient.setPreferredSize(lblSize);
        lblNom.setPreferredSize(lblSize);
        lblPrenom.setPreferredSize(lblSize);
        lblAdresse.setPreferredSize(lblSize);
        lblNumTel.setPreferredSize(lblSize);
        lblEmail.setPreferredSize(lblSize);
        lblPoint.setPreferredSize(lblSize);

        panelTop.add(pnTextField);
        panelClient.add(panelTop);

        //===============PANEL BUTTON=============
        JPanel pnButton = new transparentPanel();
        ImageIcon iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconModifier = new ImageIcon(new ImageIcon("images/Buttons/maj.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		btnAjoute = new monButton("Ajouter", iconAjoute);		
		btnModif = new monButton("Modifier", iconModifier);		
		btnReset = new monButton("Réinitialiser", iconReset);
        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);

        btnAjoute.setFont(fontButton);
        btnModif.setFont(fontButton);
        btnReset.setFont(fontButton);
        panelClient.add(pnButton);

        pnButton.add(btnAjoute);
        pnButton.add(btnModif);
        pnButton.add(btnReset);

        Dimension btnSize = new Dimension(160,35);
        btnAjoute.setPreferredSize(btnSize);
        btnModif.setPreferredSize(btnSize);
        btnReset.setPreferredSize(btnSize);

        //====PANEL RECHERCHE=====
        JPanel panelRecherche = new transparentPanel();
        JLabel lblRecherche = new JLabel("Recherche");
        lblRecherche.setFont(font);
        new JTextField("Recherche par libellé...",20);
        txtCleRecherche = new JTextField("par nom, prénom, tel ou email",20);
        txtCleRecherche.setForeground(Color.GRAY);
        txtCleRecherche.setFont(font);
        panelRecherche.add(lblRecherche);
        panelRecherche.add(txtCleRecherche);
        panelClient.add(panelRecherche);

        
        //=========================TABLEAU=====================
        modelTabClient = new DefaultTableModel();
        modelTabClient.addColumn("ID client");
        modelTabClient.addColumn("Nom");
        modelTabClient.addColumn("Prénom");
        modelTabClient.addColumn("Adresse");
        modelTabClient.addColumn("Tel");
        modelTabClient.addColumn("Email");
        modelTabClient.addColumn("Point");

        tabClient = new monTableau();
        tabClient.setModel(modelTabClient);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabClient.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabClient.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tabClient.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        TableColumnModel columnModel = tabClient.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(50);
        columnModel.getColumn(6).setPreferredWidth(30);
        
        JScrollPane scrtabClient = new JScrollPane(tabClient);
		scrtabClient.setBounds(10,10,10,10);
		scrtabClient.getViewport().setBackground(new Color(250, 240, 230));

        this.add(panelClient, BorderLayout.NORTH);
        this.add(scrtabClient, BorderLayout.CENTER);

        loadTabClient();
    }

    /*
     * lier des événements avec des fonctions 
     */
    private void addEvents() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPage();

            }
        });

        tabClient.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CliqueTabClient();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        txtCleRecherche.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                traiteRechercheClient();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                traiteRechercheClient();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                traiteRechercheClient();
            }
        });

       
        txtCleRecherche.addFocusListener((FocusListener) new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {		
					txtCleRecherche.setText("");
					txtCleRecherche.setForeground(Color.BLACK);		
				}
				
				/*
				 * En sortant la zone de recherche :
				 * Remettre le placeholder si l'utilisateur ne remplit pas
				 */
				
			public void focusLost(FocusEvent e) {	
					if (txtCleRecherche.getText().toString().length() == 0) {			
						txtCleRecherche.setText("par nom,pr�nom,tel ou email");
						txtCleRecherche.setForeground(Color.GRAY);		
					}	
				}
		});

        btnAjoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteAjouteClient();
            }
        });

        btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteModifClient();
            }
        });

    }

    /*
     * r�cup�rer la liste de clients  
     */
    private void loadTabClient() {
        ArrayList<client> liste = clientBUS.getlisteClient();
        loadTabClient(liste);
    }

    /*
     * recharger la table client
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadTabClient(ArrayList<client> liste) {
        modelTabClient.setRowCount(0);
        for (client c : liste) {
            Vector vec = new Vector();
            vec.add(c.getIdClient());
            vec.add(c.getNom());
            vec.add(c.getPrenom());
            vec.add(c.getAdresse());
            vec.add(c.getNumTel());
            vec.add(c.getEmail());
            vec.add(c.getPoint());
            modelTabClient.addRow(vec);
        }
    }

    /*
     * afficher les informations de client cliqué sur le tableau
     */
    private void CliqueTabClient() {
        int row = tabClient.getSelectedRow();
        if (row > -1) {
            txtIdClient.setText(tabClient.getValueAt(row, 0) + "");
            txtNom.setText(tabClient.getValueAt(row, 1) + "");
            txtPrenom.setText(tabClient.getValueAt(row, 2) + "");
            txtAdresse.setText(tabClient.getValueAt(row, 3) + "");
            txtNumTel.setText(tabClient.getValueAt(row, 4) + "");
            txtEmail.setText(tabClient.getValueAt(row, 5) + "");
            txtPoint.setText(tabClient.getValueAt(row, 6) + "");
        }
    }

    
    private void traiteRechercheClient() {
        ArrayList<client> dskh = clientBUS.rechercheClient(txtCleRecherche.getText());
        loadTabClient(dskh);
    }

    private void traiteAjouteClient() {
        if (clientBUS.ajouteClient(txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtNumTel.getText(), txtEmail.getText()))
            btnReset.doClick();
    }

    private void traiteModifClient() {
        if (clientBUS.majClient(txtIdClient.getText(), txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtNumTel.getText(), txtEmail.getText()))
            btnReset.doClick();
    }

    private void loadPage() {
        txtIdClient.setText("");
        txtNom.setText("");
        txtPrenom.setText("");
        txtAdresse.setText("");
        txtNumTel.setText("");
        txtEmail.setText("");
        txtPoint.setText("");
        txtCleRecherche.setText("par nom,prénom,tel ou email");
    	loadTabClient();
    }
}
