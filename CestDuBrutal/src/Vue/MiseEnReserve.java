package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
//import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JLabel;
import java.awt.Label;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Model.Etudiant;
import Model.Joueur;
import Model.PartieMVC;
import Model.StudentNotFoundInList;
import Model.Zone;

import javax.swing.border.CompoundBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.TextField;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Dimension;


public class MiseEnReserve extends JFrame {

    // les composants de la vue
    private JPanel contentPane;
    private Label configPersonnage = new Label();
    private Label force;
    private Label dexterite;
    private Label resistance;
    private Label constitution;
    private Label initiative;
    private Label  nom;
    private Label programme;
    private Label strategy;
    private JButton validation;

    private JButton reserviste;


    // le joueur
    private Joueur joueur= new Joueur(0);
    private PartieMVC partie;
    
    public MiseEnReserve(PartieMVC partie) {
        setMinimumSize(new Dimension(1200, 700));
        this.joueur = partie.getJoueurToPlay();
        this.partie=partie;
        this.initFenetre();
    }

    public void initFenetre() {
        // ++++++++++++++++++++++++++++++++++++++ Panneau principal +++++++++++++++++++++++++++++++++++++++++++
        setForeground(Color.BLUE);
        setTitle("Configuration Equipe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        contentPane = new JPanel();
        contentPane.setBorder(new CompoundBorder());
        contentPane.setBackground(new Color(128, 128, 192));
        setContentPane(contentPane);
        contentPane.setLayout(null); // politique de placement des composants dans la fen�tre
        setBounds(100, 100, 1130, 650);
        // +++++++++++++++++++++++++++++++++++++ config personnage  ++++++++++++++++++++++++++++++++++++++++++++
        configPersonnage.setBackground(Color.YELLOW);
        configPersonnage.setText("Selectionnez un etudiant");
        configPersonnage.setForeground(Color.BLACK);
        configPersonnage.setFont(new Font("Tahoma", Font.ITALIC, 20));
        configPersonnage.setBounds(426, 331, 360, 34);
        contentPane.add(configPersonnage);
        // +++++++++++++++++++++++++++++++++++++ Maitre Gobi  +++++++++++++++++++++++++++++++++++++++++++++++++
        // Panel du maitre Gobi
        JPanel panelMaitre = new JPanel();
        panelMaitre.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panelMaitre.setBackground(Color.CYAN);
        panelMaitre.setBounds(79, 102, 61, 122);
        panelMaitre.setLayout(new GridLayout(1, 1, 0, 0)); // politique de placement des composants dans ce panel
        JButton jb1 = new JButton(); // pour repr�senter un personnage, utilisation d'un JButton
        panelMaitre.add(jb1);
        jb1.setForeground(Color.CYAN);
        Image img1 = new ImageIcon("ressources\\maitre.png").getImage();
        jb1.setIcon(new ImageIcon(img1)); // habillage du JButon
        jb1.addActionListener(new MonEcouteurEvenements(joueur.getStudentList(), new String("Capitaine Gobi 1"),1));
        contentPane.add(panelMaitre);
        // Etiquette Capitaine Gobi
        JLabel lblNewLabel = new JLabel("Capitaine Gobi");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(51, 75, 128, 24);
        contentPane.add(lblNewLabel);
        // +++++++++++++++++++++++++++++++++++++ Les �lites +++++++++++++++++++++++++++++++++++++++++++++++++++
        // Idem pour les �lites
        JPanel panelElite = new JPanel();
        panelElite.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panelElite.setBackground(Color.CYAN);
        panelElite.setBounds(354, 102, 274, 122);
        panelElite.setLayout(new GridLayout(1, 4, 0, 0));
        Image img2 = new ImageIcon("ressources\\elite.png").getImage();
        JButton[] jb = new JButton[4];
        for (int i = 0; i < 4; i++) {
            jb[i] = new JButton();
            jb[i].setIcon(new ImageIcon(img2));
            panelElite.add(jb[i]);
            jb[i].addActionListener(new MonEcouteurEvenements(joueur.getStudentList(), new String("Elite " + (i+2)),i+2));
        }
        contentPane.add(panelElite);
        // Etiquette Les Elites
        JLabel lblNewLabel_1 = new JLabel("Les Elites");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(449, 77, 83, 21);
        contentPane.add(lblNewLabel_1);
        // +++++++++++++++++++++++++++++++++++++ Les �tudiants +++++++++++++++++++++++++++++++++++++++++++++++++
        // Idem pour les �tudiants de base
        JPanel panelEtu = new JPanel();
        panelEtu.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panelEtu.setBackground(Color.CYAN);
        panelEtu.setBounds(830, 102, 274, 411);
        panelEtu.setLayout(new GridLayout(4, 4, 2, 0));
        contentPane.add(panelEtu);
        // Etiquette Les �tudiants de base
        JLabel lblNewLabel_2 = new JLabel("Les Etudiants de base");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(885, 76, 192, 20);
        contentPane.add(lblNewLabel_2);
        Image img3 = new ImageIcon("ressources\\etudiant.png").getImage();
        JButton[] jb2 = new JButton[15];
        for (int k = 0; k < 15; k++) {
            jb2[k] = new JButton();
            jb2[k].setIcon(new ImageIcon(img3));
            panelEtu.add(jb2[k]);
            jb2[k].addActionListener(new MonEcouteurEvenements(joueur.getStudentList(), new String("Etudiant " + (k+6)),k+6));
        }
        // +++++++++++++++++++++++++++++++++++++++++ Joueur +++++++++++++++++++++++++++++++++++++++++++++++++++
        JLabel lblNewLabel_3 = new JLabel("Joueur");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_3.setBounds(254, 24, 76, 37);
        contentPane.add(lblNewLabel_3);
        nom = new Label();   
        nom.setText(joueur.getUserName());
        nom.setFont(new Font("Tahoma", Font.PLAIN, 24));
        nom.setBounds(336, 24, 152, 37);
        contentPane.add(nom);
        // +++++++++++++++++++++++++++++++++++++++++ Programme  ++++++++++++++++++++++++++++++++++++++++++++++++
        JLabel lblNewLabel_13 = new JLabel("Programme");
        lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_13.setBounds(562, 24, 130, 37);
        contentPane.add(lblNewLabel_13);
        programme = new Label();
        programme.setText(joueur.getProgrammeString());
        programme.setFont(new Font("Tahoma", Font.PLAIN, 24));
        programme.setBounds(698, 27, 72, 34);
        contentPane.add(programme);
        // ++++++++++++++++++++++++++++++++++++++++++ Configuration des personnages ++++++++++++++++++++++++++++++++
        

        // Force
        JLabel lblNewLabel_5 = new JLabel("Force");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_5.setBounds(193, 389, 61, 37);
        contentPane.add(lblNewLabel_5);
        force = new Label();
        force.setBackground(new Color(128, 255, 255));
        force.setFont(new Font("Tahoma", Font.PLAIN, 24));
        force.setText("0");
        force.setBounds(297, 389, 61, 37);
        contentPane.add(force);

        // Dext�rit�
        JLabel lblNewLabel_6 = new JLabel("Dexterite");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_6.setBounds(159, 432, 105, 37);
        contentPane.add(lblNewLabel_6);
        dexterite = new Label();
        dexterite.setBackground(new Color(128, 255, 255));
        dexterite.setFont(new Font("Tahoma", Font.PLAIN, 24));
        dexterite.setText("0");
        dexterite.setBounds(297, 432, 61, 37);
        contentPane.add(dexterite);

        // R�sistance
        JLabel lblNewLabel_7 = new JLabel("Resistance");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_7.setBounds(147, 479, 117, 37);
        contentPane.add(lblNewLabel_7);
        resistance = new Label();
        resistance.setBackground(new Color(128, 255, 255));
        resistance.setFont(new Font("Tahoma", Font.PLAIN, 24));
        resistance.setText("0");
        resistance.setBounds(297, 475, 61, 37);
        contentPane.add(resistance);

        // Constitution
        JLabel lblNewLabel_8 = new JLabel("Constitution");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_8.setBounds(136, 522, 128, 37);
        contentPane.add(lblNewLabel_8);
        constitution = new Label();
        constitution.setBackground(new Color(128, 255, 255));
        constitution.setFont(new Font("Tahoma", Font.PLAIN, 24));
        constitution.setText("0");
        constitution.setBounds(297, 518, 61, 37);
        contentPane.add(constitution);

        // Initiative
        JLabel lblNewLabel_9 = new JLabel("Initiative");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_9.setBounds(171, 569, 93, 29);
        contentPane.add(lblNewLabel_9);
        initiative = new Label();
        initiative.setBackground(new Color(128, 255, 255));
        initiative.setFont(new Font("Tahoma", Font.PLAIN, 24));
        initiative.setText("0");
        initiative.setBounds(297, 561, 61, 37);
        contentPane.add(initiative);

        // type de strat�gie
        JLabel lblNewLabel_11 = new JLabel("Strategie");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_11.setBounds(411, 389, 128, 37);
        contentPane.add(lblNewLabel_11);
        strategy = new Label();
        strategy.setBackground(new Color(128, 255, 255));
        strategy.setText("RANDOM");
        strategy.setFont(new Font("Tahoma", Font.PLAIN, 20));
        strategy.setBounds(562, 389, 224, 31);
        contentPane.add(strategy);
        
     // ++++++++++++++++++++++++++++++++++++++++++R�serviste ++++++++++++++++++++++++++++++
        reserviste = new JButton("Deplacer vers la reserve");
        reserviste.setEnabled(false);
        reserviste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(joueur.getReserve().getListeEtudiantsReserve().size() == 4){
                    validation.setEnabled(true);
                }
                reserviste.setEnabled(false);
                String key = configPersonnage.getText();
                int id = Integer.parseInt(key.substring(key.lastIndexOf(" ")+1));
                int resultat = joueur.putInReserveMVC(id);
                //mise � jour de l'affichage si l'etu est dans la reserve
                if (resultat==1) {
                    Image img1 =new ImageIcon("ressources\\etudiant.png").getImage();
                    switch(id) {
                        case 1:
                             img1 =new ImageIcon("ressources\\maitreDark.png").getImage();   
                             jb1.setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 2:
                            img1 =new ImageIcon("ressources\\EliteDark.png").getImage();   
                            jb[0].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 3:
                            img1 =new ImageIcon("ressources\\EliteDark.png").getImage();   
                            jb[1].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 4:
                            img1 =new ImageIcon("ressources\\EliteDark.png").getImage();   
                            jb[2].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 5:
                            img1 =new ImageIcon("ressources\\EliteDark.png").getImage();   
                            jb[3].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 6:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[0].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 7:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[1].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 8:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[2].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 9:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[3].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 10:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[4].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 11:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[5].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 12:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[6].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 13:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[7].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 14:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[8].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 15:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[9].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 16:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[10].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 17:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[10].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 18:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[12].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 19:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[13].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;
                        case 20:
                            img1 =new ImageIcon("ressources\\EtudiantDark.png").getImage();   
                            jb2[14].setIcon(new ImageIcon(img1)); // habillage du JButon
                            break;                            
                    }
                }
                
            }
        });
        reserviste.setBackground(new Color(255, 255, 255));
        reserviste.setForeground(Color.BLACK);
        reserviste.setFont(new Font("Tahoma", Font.PLAIN, 24));
        reserviste.setBounds(426, 546, 340, 34);
        contentPane.add(reserviste);        

        // ++++++++++++++++++++++++++++++++++++++++++ Valider configuration �quipe ++++++++++++++++++++++++++++++
        validation = new JButton("VALIDER");
        validation.setEnabled(false);
        validation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Affichage des caracteristiques du joueur.
                System.out.println(joueur);
                //Affichage des etudiants dans la reserve du joueur
                joueur.displayReserveStudent();
                if(partie.isJoueur1Ajoue()==true) {

                    //affichie la suite
                    if(partie.getInstance().isAuto()) {
                        partie.autoAffecterEtudiantZone();
                        Zone.melee();
                    }
                    else {
                        DistributionEtudiants gui3 = new DistributionEtudiants(partie);
                    }
                    
                    // fermer la fenetre graphique              
                    dispose();              
                }else {
                    partie.setJoueur1Ajoue(true);
                    MiseEnReserve gui2 = new MiseEnReserve(partie); 
                    dispose();
                }
            }
        });
        validation.setFont(new Font("Tahoma", Font.PLAIN, 24));
        validation.setBounds(830, 523, 270, 83);
        contentPane.add(validation);
        // ++++++++++++++++++++++++++++++++++++++++++ Habillage ++++++++++++++++++++++++++++++++++++++++++++
        // panneau configuration
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panel.setBounds(51, 345, 769, 261);
        contentPane.add(panel);
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // classe locale pour �couter les composants associ�s aux combattants
    final class MonEcouteurEvenements implements ActionListener {
        private ArrayList<Etudiant> liste;
        private String key;
        private int id;

        public MonEcouteurEvenements(ArrayList<Etudiant> liste, String key,int id) {
            this.liste = liste;
            this.key = key;
            this.id= id;
        }
        
        public void actionPerformed(ActionEvent e) {//
            configPersonnage.setText(key);
            try {
                if(joueur.getReserve().getListeEtudiantsReserve().size() < 5){
                    reserviste.setEnabled(true);
                }
                Etudiant comb = joueur.selectStudentMVC(id);                
                force.setText(Integer.toString(comb.getForce()));
                dexterite.setText(Integer.toString(comb.getDexterite()));
                resistance.setText(Integer.toString(comb.getResistance()));
                constitution.setText(Integer.toString(comb.getConstitution()));
                initiative.setText(Integer.toString(comb.getInitiative()));
                strategy.setText(comb.getStrategieString());
                reserviste.setSelected(false);
            }catch(StudentNotFoundInList err) {
                System.out.print(err.getMessage()); 
                reserviste.setSelected(true);
            }
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
