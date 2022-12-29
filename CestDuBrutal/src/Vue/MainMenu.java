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

public class MainMenu extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    
    /**
     * Create the frame.
     */
    
    public MainMenu(Partie partie, Joueur j1, Joueur j2) {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 185);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("C'EST DU BRUTAL");
        lblNewLabel.setBounds(168, 11, 115, 14);
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
        btnNewButton.setBounds(64, 48, 134, 23);
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
        btnNewButton_1.setBounds(232, 48, 134, 23);
        contentPane.add(btnNewButton_1);
    }
}
