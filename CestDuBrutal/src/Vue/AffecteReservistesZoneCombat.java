package Vue;

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

public class AffecteReservistesZoneCombat extends JFrame {

    private JPanel contentPane;
    

    // le joueur
    private Joueur joueur= new Joueur(0);
    private Partie partie; 
    private Zone zone;
    public AffecteReservistesZoneCombat(Joueur j,Zone zone) {
        setMinimumSize(new Dimension(1400, 800));
        this.joueur = j;
        this.zone = zone;
        this.initFenetre();
    }

    /**
     * Create the frame.
     */
    public void initFenetre() {
        setTitle("Affecter depuis la reserve");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1286, 505);
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new CompoundBorder());
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setBounds(100, 100, 1370, 650);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        JPanel panelCamion = new JPanel();
        panelCamion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelCamion.setBounds(20, 26, 394, 594);        
        panelCamion.setLayout(new GridLayout(5, 4, 0, 0));
        contentPane.add(panelCamion);
        
        //Les images
        Image img1 = new ImageIcon("ressources\\maitre.png").getImage(); 
        Image img2 = new ImageIcon("ressources\\elite.png").getImage(); 
        Image img3 = new ImageIcon("ressources\\etudiant.png").getImage();  
        
        JLabel lblNewLabel_1 = new JLabel("La reserve");
        lblNewLabel_1.setBounds(20, 10, 93, 13);
        contentPane.add(lblNewLabel_1);        
         
        ArrayList <Etudiant> listEtu = joueur.getReserveArrayList();
        int sizeCamion = listEtu.size();
        JButton[] jb = new JButton[sizeCamion];
        for (int k = 0; k < sizeCamion; k++) {
            jb[k] = new JButton();
            //On met l'icon correspondant
            switch(listEtu.get(k).getType()) {
                case "Maitre":
                    jb[k].setIcon(new ImageIcon(img1));
                    break;
                case "Elite":
                    jb[k].setIcon(new ImageIcon(img2));
                    break;
                default:
                    jb[k].setIcon(new ImageIcon(img3));
                    break;                    
            }
            panelCamion.add(jb[k]);
            Etudiant etu0 = listEtu.get(k);
            jb[k].addActionListener(   new MonEcouteurEvenements(joueur.getReserveArrayList(),etu0 ));
        }
        
        JLabel lblNewLabel_2 = new JLabel("La Bibliotheque");
        lblNewLabel_2.setBounds(450, 10, 126, 13);
        contentPane.add(lblNewLabel_2);        
        
        JPanel panelBibliotheque = new JPanel();
        panelBibliotheque.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelBibliotheque.setBounds(440, 26, 928, 100);
        contentPane.add(panelBibliotheque);
        panelBibliotheque.setLayout(new GridLayout(1, 20, 0, 0));
      //habiage du panel bibliotheque 

        //Gere si on peut affecter des etu sur cette zone 
        /*boolean selectedZoneIsActive = false;
        ZoneCombat toZone = (ZoneCombat) Zone.getZone(0);
        if(toZone.getControlePar()==null) {
            selectedZoneIsActive = true;
            System.out.println(toZone.getZoneName()+" est en combat");
        }*/
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
          //Gere si on peut affecter des etu sur cette zone AUSSi
            //if (!selectedZoneIsActive) jb1[k].addActionListener(   new MonEcouteurEvenements(listeBiblio, etu));
        }
        
        JLabel lblNewLabel_3 = new JLabel("Le BDE");
        lblNewLabel_3.setBounds(450, 136, 61, 13);
        contentPane.add(lblNewLabel_3);
        
        JPanel panelBDE = new JPanel();
        panelBDE.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelBDE.setBounds(440, 148, 922, 100);
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
        }
        
        JLabel lblNewLabel = new JLabel("Le Quartier Administratif");
        lblNewLabel.setBounds(450, 258, 209, 13);
        contentPane.add(lblNewLabel);
        
        JPanel panelAdministratif = new JPanel();
        panelAdministratif.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelAdministratif.setBounds(440, 281, 922, 100);
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
        }
        JLabel lblNewLabel_4 = new JLabel("Les Halles Industrielles");
        lblNewLabel_4.setBounds(450, 391, 167, 13);
        contentPane.add(lblNewLabel_4);
        
        JPanel panelIndustrielle = new JPanel();
        panelIndustrielle.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelIndustrielle.setBounds(440, 405, 922, 100);
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
        }
        JLabel lblNewLabel_5 = new JLabel("La Halle Sportive");
        lblNewLabel_5.setBounds(450, 515, 146, 13);
        contentPane.add(lblNewLabel_5);
        
        JPanel panelSportive = new JPanel();
        panelSportive.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelSportive.setBounds(440, 527, 922, 100);
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
        }
        
        
        JButton validation = new JButton("VALIDER");
        validation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
             dispose();             
            }
        });
        validation.setFont(new Font("Tahoma", Font.PLAIN, 24));
        validation.setBounds(1098, 648, 270, 83);
        contentPane.add(validation);
    }
    final class MonEcouteurEvenements implements ActionListener {
        private ArrayList<Etudiant> liste;
        private Etudiant etu;

        public MonEcouteurEvenements(ArrayList<Etudiant> liste, Etudiant etudiant) {
            this.liste = liste;
            this.etu=etudiant;
        }
        
        public void actionPerformed(ActionEvent e) {            
            PopupChoiceZoneForStudentTreve hu = new PopupChoiceZoneForStudentTreve(joueur,zone,etu);            
            hu.setVisible(true);
            dispose();
            
        }
    }
}
