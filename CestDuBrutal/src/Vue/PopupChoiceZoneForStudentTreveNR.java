package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Etudiant;
import Model.Joueur;
import Model.Partie;
import Model.Zone;
import Model.ZoneCombat;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Label;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Fenetre popup qui permet deplacer un etudiant d'une zone a une autre (durant la treve) 
 * @author rioug
 *
 */
public class PopupChoiceZoneForStudentTreveNR extends JFrame {
    /**
     * JPanel qui contient tout l'affichage
     */
    private JPanel contentPane;
    /**
     * joueur qui est conserne par l'interface 
     */
    private Joueur joueur;
    /**
     * est utilise pour appeler l'objet Partie en cours
     */
    private Partie partie;
    /**
     * Etudiant a affecte
     */
    private Etudiant etu;
    /**
     * Permet de recuperer les zones de la partie
     */
    private Zone zone;
    /**
     * 
     * @param joueur joueur qui affecter ses etudiants 
     * @param zone liste des zones 
     * @param etudiant etudiant a affecter
     */
    public PopupChoiceZoneForStudentTreveNR(Joueur joueur,Zone zone, Etudiant etudiant) {
        setMinimumSize(new Dimension(800, 500));
        this.joueur = joueur;
        this.partie=Partie.getInstance();
        this.zone=zone;
        this.etu=etudiant;
        this.initFenetre();
    }
    

    /**
     * Create the frame.
     */
    public void initFenetre() {
        setTitle("Deplacer un etudiant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 728, 408);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_5 = new JLabel("Force");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_5.setBounds(111, 81, 61, 37);
        contentPane.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Dext\u00E9rit\u00E9");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_6.setBounds(77, 124, 105, 37);
        contentPane.add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("R\u00E9sistance");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_7.setBounds(65, 171, 117, 37);
        contentPane.add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Constitution");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_8.setBounds(54, 214, 128, 37);
        contentPane.add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Initiative");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_9.setBounds(89, 261, 93, 29);
        contentPane.add(lblNewLabel_9);
        
        Label force = new Label();
        force.setText(Integer.toString(etu.getForce()));
        force.setFont(new Font("Tahoma", Font.PLAIN, 24));
        force.setBounds(197, 81, 61, 37);
        contentPane.add(force);
        
        Label dexterite = new Label();
        dexterite.setText(Integer.toString(etu.getDexterite()));
        dexterite.setFont(new Font("Tahoma", Font.PLAIN, 24));
        dexterite.setBounds(197, 124, 61, 37);
        contentPane.add(dexterite);
        
        Label resistance = new Label();
        resistance.setText(Integer.toString(etu.getResistance()));
        resistance.setFont(new Font("Tahoma", Font.PLAIN, 24));
        resistance.setBounds(197, 167, 61, 37);
        contentPane.add(resistance);
        
        Label constitution = new Label();
        constitution.setText(Integer.toString(etu.getConstitution()));
        constitution.setFont(new Font("Tahoma", Font.PLAIN, 24));
        constitution.setBounds(197, 210, 61, 37);
        contentPane.add(constitution);
        
        Label initiative = new Label();
        initiative.setText(Integer.toString(etu.getInitiative()));
        initiative.setFont(new Font("Tahoma", Font.PLAIN, 24));
        initiative.setBounds(197, 253, 61, 37);
        contentPane.add(initiative);
        
        Label configPersonnage = new Label();
        configPersonnage.setText(etu.getType()+etu.getId());
        configPersonnage.setForeground(Color.BLACK);
        configPersonnage.setFont(new Font("Tahoma", Font.ITALIC, 20));
        configPersonnage.setBackground(Color.YELLOW);
        configPersonnage.setBounds(158, 10, 360, 34);
        contentPane.add(configPersonnage);
        
        JLabel lblNewLabel_11 = new JLabel("Strategie");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_11.setBounds(86, 300, 96, 37);
        contentPane.add(lblNewLabel_11);
        
        Label strategy = new Label();
        strategy.setText(etu.getStrategieString());
        strategy.setFont(new Font("Tahoma", Font.PLAIN, 20));
        strategy.setBounds(197, 296, 224, 31);
        contentPane.add(strategy);
        
        JLabel lblNewLabel_10 = new JLabel("Affectation");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_10.setBounds(326, 125, 128, 45);
        contentPane.add(lblNewLabel_10);
        
        Choice choice = new Choice();
        choice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        choice.setBounds(461, 131, 313, 31);
        ZoneCombat toZone = (ZoneCombat) Zone.getZone(0);
        if(toZone.getControlePar()==null) {
            choice.add("la Bibliotheque");
        }
        toZone = (ZoneCombat) Zone.getZone(1);
        if(toZone.getControlePar()==null) {
            choice.add("le BDE");
        }
        toZone = (ZoneCombat) Zone.getZone(2);
        if(toZone.getControlePar()==null) {
            choice.add("le Quartier administratif");
        }
        toZone = (ZoneCombat) Zone.getZone(3);
        if(toZone.getControlePar()==null) {
            choice.add("Les Halles Industrielles");
        }
        toZone = (ZoneCombat) Zone.getZone(4);
        if(toZone.getControlePar()==null) {
            choice.add("la Halle sportive");
        }
        contentPane.add(choice);
        
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                partie.affecterEtudiantZoneMVC(etu,choice.getSelectedItem());
                AffecteEntreZoneCombat hui5 =new AffecteEntreZoneCombat(partie,zone, joueur);
                dispose();                
            }
        });
        ok.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ok.setBounds(439, 213, 59, 45);
        contentPane.add(ok);
    }
}
