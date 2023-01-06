package Vue;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

import Model.Etudiant;
import Model.Joueur;
import Model.Partie;
import Model.Partie;
import Model.StudentNotFoundInList;
import Model.Zone;
import Model.ZoneCombat;
import Vue.MiseEnReserve.MonEcouteurEvenements;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
/**
 * Interface graphique de l'affectation des  etudiants lors de la treve
 *
 */
public class AffecteEntreZoneCombat extends JFrame {
    /**
     * JPanel qui contient tout l'affichage
     */
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    private boolean[] zoneBoxAppearsCondition = new boolean[Zone.getZoneList().size()];
    /**
     * joueur qui est conserne par l'interface 
     */
    private Joueur joueur;
    /**
     * est utilise pour appeler l'objet Partie en cours
     */
    private Partie partie;
    /**
     * Permet de recuperer les zones qui sont controlee par un joueur
     */
    private Zone zonecontrolee;
    /**
     * zoneList qui contient toutes les zone de combats de la partie
     */
    private ArrayList<ZoneCombat> zoneList;
    public AffecteEntreZoneCombat(Partie partie, Zone zonecontrolee, Joueur joueur) {
        setBackground(new Color(192, 192, 192));
        setMinimumSize(new Dimension(1000, 800));
        this.joueur = joueur;// on r�cupp�re le premier joueur
        this.partie=partie;
        this.zonecontrolee = zonecontrolee;
        this.zoneList = (ArrayList<ZoneCombat>) Zone.getZoneList();
        conditionsAreMet();
        Zone.displayAllStudentInZones();
        
        this.initFenetre();
    }

   private void conditionsAreMet() {
       for (int i = 0; i<Zone.getZoneList().size(); i++) {
           
           zoneBoxAppearsCondition[i] = (zoneList.get(i).getControlePar() == null || zoneList.get(i).getControlePar() == joueur);
           //DEBUG
           //System.out.println((zoneList.get(i).getControlePar() == null) +" and "+ (zoneList.get(i).getControlePar() == joueur)+ ""
           //        + " by "+zoneList.get(i).getControlePar()+ "with"+joueur.getUserName() );
           //System.out.println(zoneBoxAppearsCondition[i]);
       }
       
   }

    /**
     * Create the frame.
     */
    public void initFenetre() {
        setTitle("Deplacer un etudiant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new CompoundBorder());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
       
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //Les images
        Image img1 = new ImageIcon("ressources\\maitre.png").getImage(); 
        Image img2 = new ImageIcon("ressources\\elite.png").getImage(); 
        Image img3 = new ImageIcon("ressources\\etudiant.png").getImage();
        
        JLabel lblNewLabel_2 = new JLabel("La Bibliotheque");
        lblNewLabel_2.setBounds(40, 11, 375, 13);
        contentPane.add(lblNewLabel_2);        
        
        if(zoneBoxAppearsCondition[0]) {
            JPanel panelBibliotheque = new JPanel();
            panelBibliotheque.setBorder(new LineBorder(new Color(0, 0, 0)));
            panelBibliotheque.setBounds(25, 25, 928, 100);
            contentPane.add(panelBibliotheque);
            panelBibliotheque.setLayout(new GridLayout(1, 20, 0, 0));
          //habiage du panel bibliotheque
            ArrayList <Etudiant> listeBiblio = Zone.getZone(0).getEtuDansZoneArrayList(joueur);        
            int sizeBiblio = listeBiblio.size();
            JButton[] jb1 = new JButton[sizeBiblio];
            for (int k = 0; k < sizeBiblio; k++) {
                jb1[k] = new JButton();
                //On met l'icon correspondant
                switch(listeBiblio.get(k).getType()) {
                    case "Maitre":
                        jb1[k].setIcon(new ImageIcon(img1));
                        break;
                    case "Elite":
                        jb1[k].setIcon(new ImageIcon(img2));
                        break;
                    default:
                        jb1[k].setIcon(new ImageIcon(img3));
                        break;                    
                }
                panelBibliotheque.add(jb1[k]);
                Etudiant etu = listeBiblio.get(k);
                jb1[k].addActionListener(   new MonEcouteurEvenements(listeBiblio, etu));
                if(zoneList.get(0).getControlePar() == null||listeBiblio.size() == 1) {
                    jb1[k].setEnabled(false);
                }
            }
        }
        JLabel lblNewLabel_3 = new JLabel("Le BDE");
        lblNewLabel_3.setBounds(40, 136, 263, 13);
        contentPane.add(lblNewLabel_3);
        
        if(zoneBoxAppearsCondition[1]) {
            JPanel panelBDE = new JPanel();
            panelBDE.setBorder(new LineBorder(new Color(0, 0, 0)));
            panelBDE.setBounds(25, 148, 922, 100);
            contentPane.add(panelBDE);
            panelBDE.setLayout(new GridLayout(1, 20, 0, 0));
            //habiage du panel BDE
            ArrayList <Etudiant> listeBDE = Zone.getZone(1).getEtuDansZoneArrayList(joueur); 
            int sizeBDE = listeBDE.size();
            JButton[] jb2 = new JButton[sizeBDE];
            for (int k = 0; k < sizeBDE; k++) {
                jb2[k] = new JButton();
                //On met l'icon correspondant
                switch(listeBDE.get(k).getType()) {
                    case "Maitre":
                        jb2[k].setIcon(new ImageIcon(img1));
                        break;
                    case "Elite":
                        jb2[k].setIcon(new ImageIcon(img2));
                        break;
                    default:
                        jb2[k].setIcon(new ImageIcon(img3));
                        break;                    
                }
                panelBDE.add(jb2[k]);
                Etudiant etu = listeBDE.get(k);
                jb2[k].addActionListener(   new MonEcouteurEvenements(listeBDE, etu));
                if(zoneList.get(1).getControlePar() == null||listeBDE.size() == 1) {
                    jb2[k].setEnabled(false);
                }
            }
        }
        JLabel lblNewLabel = new JLabel("Le Quartier Administratif");
        lblNewLabel.setBounds(40, 258, 353, 13);
        contentPane.add(lblNewLabel);
        
        if(zoneBoxAppearsCondition[2]) {
            JPanel panelAdministratif = new JPanel();
            panelAdministratif.setBorder(new LineBorder(new Color(0, 0, 0)));
            panelAdministratif.setBounds(25, 281, 922, 100);
            contentPane.add(panelAdministratif);
            panelAdministratif.setLayout(new GridLayout(1, 20, 0, 0));
          //habiage du panelAdministratif 
            ArrayList <Etudiant> listeAdministratif = Zone.getZone(2).getEtuDansZoneArrayList(joueur); 
            int sizeAdministratif = listeAdministratif.size();
            JButton[] jb3 = new JButton[sizeAdministratif];
            for (int k = 0; k < sizeAdministratif; k++) {
                jb3[k] = new JButton();
                //On met l'icon correspondant
                switch(listeAdministratif.get(k).getType()) {
                    case "Maitre":
                        jb3[k].setIcon(new ImageIcon(img1));
                        break;
                    case "Elite":
                        jb3[k].setIcon(new ImageIcon(img2));
                        break;
                    default:
                        jb3[k].setIcon(new ImageIcon(img3));
                        break;                    
                }
                panelAdministratif.add(jb3[k]);
                Etudiant etu = listeAdministratif.get(k);
                jb3[k].addActionListener(   new MonEcouteurEvenements(listeAdministratif, etu));
                if(zoneList.get(2).getControlePar() == null||listeAdministratif.size() == 1) {
                    jb3[k].setEnabled(false);
                }
            }
        }
        JLabel lblNewLabel_4 = new JLabel("Les Halles Industrielles");
        lblNewLabel_4.setBounds(40, 391, 321, 13);
        contentPane.add(lblNewLabel_4);
        
        if(zoneBoxAppearsCondition[3]) {
            JPanel panelIndustrielle = new JPanel();
            panelIndustrielle.setBorder(new LineBorder(new Color(0, 0, 0)));
            panelIndustrielle.setBounds(25, 405, 922, 100);
            contentPane.add(panelIndustrielle);
            panelIndustrielle.setLayout(new GridLayout(1, 20, 0, 0));
          //habiage du panelIndustrielle 
            ArrayList <Etudiant> listeIndustrielle = Zone.getZone(3).getEtuDansZoneArrayList(joueur); 
            int sizeIndustrielle = listeIndustrielle.size();
            JButton[] jb4 = new JButton[sizeIndustrielle];
            for (int k = 0; k < sizeIndustrielle; k++) {
                jb4[k] = new JButton();
                //On met l'icon correspondant
                switch(listeIndustrielle.get(k).getType()) {
                    case "Maitre":
                        jb4[k].setIcon(new ImageIcon(img1));
                        break;
                    case "Elite":
                        jb4[k].setIcon(new ImageIcon(img2));
                        break;
                    default:
                        jb4[k].setIcon(new ImageIcon(img3));
                        break;                    
                }
                panelIndustrielle.add(jb4[k]);
                Etudiant etu = listeIndustrielle.get(k);
                jb4[k].addActionListener(new MonEcouteurEvenements(listeIndustrielle, etu));
                if(zoneList.get(3).getControlePar() == null||listeIndustrielle.size() == 1) {
                    jb4[k].setEnabled(false);
                }
            }
        }
        JLabel lblNewLabel_5 = new JLabel("La Halle Sportive");
        lblNewLabel_5.setBounds(40, 515, 375, 13);
        contentPane.add(lblNewLabel_5);
        
        if(zoneBoxAppearsCondition[4]) {
            JPanel panelSportive = new JPanel();
            panelSportive.setBorder(new LineBorder(new Color(0, 0, 0)));
            panelSportive.setBounds(25, 527, 922, 100);
            contentPane.add(panelSportive);
            panelSportive.setLayout(new GridLayout(1, 20, 0, 0));
            ArrayList <Etudiant> listeSportive = Zone.getZone(4).getEtuDansZoneArrayList(joueur); 
            int sizeSportive = listeSportive.size();
            JButton[] jb5 = new JButton[sizeSportive];
            for (int k = 0; k < sizeSportive; k++) {
                jb5[k] = new JButton();
                //On met l'icon correspondant
                switch(listeSportive.get(k).getType()) {
                    case "Maitre":
                        jb5[k].setIcon(new ImageIcon(img1));
                        break;
                    case "Elite":
                        jb5[k].setIcon(new ImageIcon(img2));
                        break;
                    default:
                        jb5[k].setIcon(new ImageIcon(img3));
                        break;                    
                }
                panelSportive.add(jb5[k]);
                Etudiant etu = listeSportive.get(k);
                jb5[k].addActionListener(   new MonEcouteurEvenements(listeSportive, etu));
                if(zoneList.get(4).getControlePar() == null||(listeSportive.size() == 1)) {
                    jb5[k].setEnabled(false);
                }
            }
        }
        
        JButton validation = new JButton("VALIDER");
        boolean condition=false;
        if(joueur.getId()==1) condition=Zone.allZoneNotEmpty();// condidtion pour que le j1 a un etu dasn chaque zone 
        else condition=Zone.allZoneWithTwoStudent(joueur);
        if( condition) validation.setEnabled(true);//---------------------------------------------> DEBUG
        //if(joueur.getStudentList().size()==0 && condition) validation.setEnabled(true);---------> true version
        validation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             // fermer la fenetre graphique              
             dispose();              
                           
            }
        });
        validation.setFont(new Font("Tahoma", Font.PLAIN, 24));
        validation.setBounds(677, 638, 270, 83);
        contentPane.add(validation);
        
        JLabel lblNewLabel_1 = new JLabel("Controle: "+ zoneControlString((ZoneCombat)Zone.getZone(0)));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(720, 10, 227, 14);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Controle: "+ zoneControlString((ZoneCombat)Zone.getZone(1)));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1_1.setBounds(720, 135, 227, 14);
        contentPane.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Controle: "+ zoneControlString((ZoneCombat)Zone.getZone(2)));
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1_1_1.setBounds(720, 257, 227, 14);
        contentPane.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Controle: "+ zoneControlString((ZoneCombat)Zone.getZone(3)));
        lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1_1_1_1.setBounds(720, 390, 227, 14);
        contentPane.add(lblNewLabel_1_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Controle: "+ zoneControlString((ZoneCombat)Zone.getZone(4)));
        lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1_1_1_1_1.setBounds(720, 514, 227, 14);
        contentPane.add(lblNewLabel_1_1_1_1_1);
    }
    public String zoneControlString(ZoneCombat zone) {
        if (zone.getControlePar() == null) {
            return "En Dispute";
        }
        else if (zone.getControlePar() == joueur) {
            return "Par vous";
        }
        else{
            return "Par l'adversaire";
        }
    }
    final class MonEcouteurEvenements implements ActionListener {
        private ArrayList<Etudiant> liste;
        private Etudiant etu;

        public MonEcouteurEvenements(ArrayList<Etudiant> liste, Etudiant etudiant) {
            this.liste = liste;
            this.etu=etudiant;
        }
        
        public void actionPerformed(ActionEvent e) {            
            PopupChoiceZoneForStudentTreveNR hu = new PopupChoiceZoneForStudentTreveNR(joueur,zonecontrolee,etu);            
            hu.setVisible(true);
            dispose();
            
        }
    }
}

