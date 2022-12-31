package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Zone;
import Model.ZoneCombat;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class visualiserECTS extends JFrame {

    private JPanel contentPane;
    private ArrayList<ZoneCombat> zoneList;
    /**
     * Create the frame.
     */
    public visualiserECTS() {
        this.setVisible(true);
        zoneList = Zone.getZoneList();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        initFenetre();
    }
    public void initFenetre() {
        JLabel lblNewLabel = new JLabel("Nombre de points ECTS total dans chaque zone");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 10, 434, 24);
        contentPane.add(lblNewLabel);
        System.out.println(zoneList.get(0).getZoneName());
        JLabel lblNewLabel_0 = new JLabel(zoneList.get(0).getZoneName());
        lblNewLabel_0.setBounds(69, 45, 170, 20);
        contentPane.add(lblNewLabel_0);
        
        JLabel lblNewLabel_1 = new JLabel(zoneList.get(1).getZoneName());
        lblNewLabel_1.setBounds(69, 70, 170, 20);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel(zoneList.get(2).getZoneName());
        lblNewLabel_2.setBounds(69, 95, 170, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel(zoneList.get(3).getZoneName());
        lblNewLabel_3.setBounds(69, 120, 170, 20);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel(zoneList.get(4).getZoneName());
        lblNewLabel_4.setBounds(69, 145, 170, 20);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_00 = new JLabel(zoneList.get(0).getTotalECTS(true) + " "
                + "(avec " + zoneList.get(0).getTotalECTS(false)+ " etudiants)" );
        lblNewLabel_00.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_00.setBounds(165, 45, 201, 20);
        contentPane.add(lblNewLabel_00);
        
        JLabel lblNewLabel_01 = new JLabel(zoneList.get(1).getTotalECTS(true) + " "
                + "(avec " + zoneList.get(1).getTotalECTS(false)+ " etudiants)" );
        lblNewLabel_01.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_01.setBounds(165, 70, 201, 20);
        contentPane.add(lblNewLabel_01);
        
        JLabel lblNewLabel_02 = new JLabel(zoneList.get(2).getTotalECTS(true) + " "
                + "(avec " + zoneList.get(2).getTotalECTS(false)+ " etudiants)" );
        lblNewLabel_02.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_02.setBounds(165, 95, 201, 20);
        contentPane.add(lblNewLabel_02);
        
        JLabel lblNewLabel_03 = new JLabel(zoneList.get(3).getTotalECTS(true) + " "
                + "(avec " + zoneList.get(3).getTotalECTS(false)+ " etudiants)" );
        lblNewLabel_03.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_03.setBounds(165, 120, 201, 20);
        contentPane.add(lblNewLabel_03);
        
        JLabel lblNewLabel_04 = new JLabel(zoneList.get(4).getTotalECTS(true) + " "
                + "(avec " + zoneList.get(4).getTotalECTS(false)+ " etudiants)" );
        lblNewLabel_04.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_04.setBounds(165, 145, 201, 20);
        contentPane.add(lblNewLabel_04);
        
        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton.setBounds(165, 180, 89, 35);
        contentPane.add(btnNewButton);
        
    }
}
