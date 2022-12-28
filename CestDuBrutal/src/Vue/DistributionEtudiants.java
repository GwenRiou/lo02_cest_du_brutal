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
import Model.PartieMVC;
import Model.StudentNotFoundInList;
import Vue.MiseEnReserve.MonEcouteurEvenements;
import java.awt.Dimension;

public class DistributionEtudiants extends JFrame {

    private JPanel contentPane;
    

    // le joueur
    private Joueur joueur= new Joueur(0);

    public DistributionEtudiants(PartieMVC partie) {
        setMinimumSize(new Dimension(1400, 700));
        this.joueur = partie.getListJ().get(0);// on récuppère le premier joueur
        this.initFenetre();
    }

    /**
     * Create the frame.
     */
    public void initFenetre() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1286, 505);
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new CompoundBorder());
        
        this.setVisible(true);
        this.pack();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setBounds(100, 100, 1370, 650);
        

        JLabel lblNewLabel = new JLabel("Distribution des Etudiants dans les Zones");
        lblNewLabel.setBounds(100, 10, 200, 13);
        contentPane.add(lblNewLabel);
        
        JPanel panelCamion = new JPanel();
        panelCamion.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelCamion.setBounds(20, 59, 1370, 122);        
        panelCamion.setLayout(new GridLayout(1, 20, 0, 0));
        contentPane.add(panelCamion);
        
        //Les images
        Image img1 = new ImageIcon("ressources\\maitre.png").getImage(); 
        Image img2 = new ImageIcon("ressources\\elite.png").getImage(); 
        Image img3 = new ImageIcon("ressources\\etudiant.png").getImage();  
        
        JLabel lblNewLabel_1 = new JLabel("Le camion");
        lblNewLabel_1.setBounds(20, 47, 61, 13);
        contentPane.add(lblNewLabel_1);        
         
        ArrayList <Etudiant> listEtu = joueur.getStudentList();
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
            jb[k].addActionListener(   new MonEcouteurEvenements(joueur.getStudentList(), new String("Etudiant " + (k+6)),k+6));
        }
        
        JLabel lblNewLabel_2 = new JLabel("La Bibliotheque");
        lblNewLabel_2.setBounds(20, 191, 76, 13);
        contentPane.add(lblNewLabel_2);        
        
        JPanel panelBibliotheque = new JPanel();
        panelBibliotheque.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelBibliotheque.setBounds(20, 206, 1370, 122);
        contentPane.add(panelBibliotheque);
        panelBibliotheque.setLayout(new GridLayout(1, 20, 0, 0));
        
        JLabel lblNewLabel_2_1 = new JLabel("Le BDE");
        lblNewLabel_2_1.setBounds(20, 340, 61, 13);
        contentPane.add(lblNewLabel_2_1);
        
        JPanel panelBibliotheque_1 = new JPanel();
        panelBibliotheque_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelBibliotheque_1.setBounds(20, 352, 1370, 122);
        contentPane.add(panelBibliotheque_1);
        panelBibliotheque_1.setLayout(new GridLayout(1, 20, 0, 0));
         
        /*ArrayList <Etudiant> listeBDE = Zone.getStudentList();
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
            jb[k].addActionListener(   new MonEcouteurEvenements(joueur.getStudentList(), new String("Etudiant " + (k+6)),k+6));
        }*/
    }
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
            /*ouvre une Joption ? pour montre le soldat + changer son emplacement 
            configPersonnage.setText(key);
            try {
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
            }*/
        }
    }
}
