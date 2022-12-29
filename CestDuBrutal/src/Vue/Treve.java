package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Joueur;
import Model.PartieMVC;
import Model.Zone;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Treve extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Treve frame = new Treve(null,null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Treve(Joueur joueur, Zone zone) {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel(joueur.getUserName()+": Que voulez-vous faire?");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(104, 38, 248, 14);
        contentPane.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Affecter des etudiants des zones controllees");
        btnNewButton.setBounds(83, 63, 303, 23);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Affecter des reservistes sur des zones de combat");
        btnNewButton_1.setBounds(83, 97, 303, 23);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Visualiser le nombre de points ECTS par zone de combat");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Zone.displayECTSPerZone();
            }
        });
        btnNewButton_2.setBounds(83, 131, 303, 23);
        contentPane.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Continuer la bataille");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        btnNewButton_3.setBounds(83, 165, 310, 59);
        contentPane.add(btnNewButton_3);
        
        JLabel lblNewLabel_1 = new JLabel("TREVE");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(203, 13, 47, 14);
        contentPane.add(lblNewLabel_1);
    }
}
