package Vue;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controleur.main;
import Model.Joueur;
import Model.Partie;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Interface graphique qui affiche le vainqueur 
 *
 */
public class AfficheVainqueur extends JFrame {
    /**
     * JPanel qui contient tout l'affichage
     */
    private JPanel contentPane;

    /**
     * Launch the application.
     */
 // le joueur
    private Joueur joueur;
    /**
     * interface qui indique le joueur gagnant
     * @param j joueur qui a gagne la partie
     */
    public AfficheVainqueur(Joueur j) {
        setMinimumSize(new Dimension(1000, 500));
        joueur = j;
        this.initFenetre();
    }
    /**
     * Initialisa l'interface
     */
    public void initFenetre() {
        setTitle("Victoire!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Le Vainqueur de la partie est :",SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 38));
        lblNewLabel.setBounds(200, 67, 618, 48);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("",SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 54));
        lblNewLabel_1.setBounds(262, 174, 465, 99);
        lblNewLabel_1.setText(joueur.getUserName());        
        contentPane.add(lblNewLabel_1);
        
        JButton validation = new JButton("Mettre fin au jeu");
        validation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        validation.setFont(new Font("Tahoma", Font.PLAIN, 24));
        validation.setBounds(365, 317, 270, 83);
        contentPane.add(validation);
    }
}
