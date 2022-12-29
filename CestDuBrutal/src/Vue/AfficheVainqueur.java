package Vue;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Model.Joueur;
import Model.Partie;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AfficheVainqueur extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
 // le joueur
    private Joueur joueur;
    
    public AfficheVainqueur(Joueur j) {
        setMinimumSize(new Dimension(1000, 500));
        joueur = j;
        this.initFenetre();
    }

    public void initFenetre() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
        
        JButton validation = new JButton("VALIDER");
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
