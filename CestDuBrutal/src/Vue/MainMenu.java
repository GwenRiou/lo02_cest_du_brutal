package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Joueur;
import Model.Partie;

import javax.swing.JTextPane;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    
    /**
     * Create the frame.
     */
    
    public MainMenu(Partie partie, Joueur j1, Joueur j2) {
        //this.pack();
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 236);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("C'EST DU BRUTAL");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel.setBounds(10, 24, 415, 30);
        contentPane.add(lblNewLabel);
        MainMenu menu = this;
        JButton btnNewButton = new JButton("Jouer");
        //BOUTON JOUER
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                partie.setAuto(false);
                j1.createStudentList();
                j2.createStudentList();
                RepartitionDesPoints gui1 = new RepartitionDesPoints(partie);
                menu.dispose();
            }
        });
        btnNewButton.setBounds(89, 82, 254, 51);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Mode automatique");
        //BOUTON AUTO
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                partie.setAuto(true);
                System.out.println("> La repartition est automatique");
                j1.setUserName("Joueur 1");
                j2.setUserName("Joueur 2");
                j1.autoCreateStudentList();
                j2.autoCreateStudentList();
                MiseEnReserve gui = new MiseEnReserve(partie);
                menu.dispose();
            }
        });
        btnNewButton_1.setBounds(133, 144, 162, 23);
        contentPane.add(btnNewButton_1);
        
        JLabel lblNewLabel_1 = new JLabel("Gwen Riou, Xuan Boone, UTT A22");
        lblNewLabel_1.setBounds(0, 183, 329, 14);
        contentPane.add(lblNewLabel_1);
    }
}
