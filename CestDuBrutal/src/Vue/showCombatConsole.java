package Vue;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Model.Joueur;
import Model.ZoneCombat;

public class showCombatConsole extends JFrame {
    private JTextArea textArea;
    private Joueur gagnantDerniereTreve;
    private ZoneCombat derniereZoneTreve;
    private static showCombatConsole currentGUI;
    
    private JButton btnNewButton;
    
    public showCombatConsole() {
        setTitle("Combat");
        currentGUI = this;
        textArea = new JTextArea();
        textArea.setDragEnabled(true);
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnNewButton = new JButton("TREVE");
        btnNewButton.setEnabled(false);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {   
                    Treve guiTreve = new Treve(gagnantDerniereTreve,derniereZoneTreve);
                    guiTreve.setVisible(true);  
                    dispose();
                } catch (Exception e1) { 
                    e1.printStackTrace();    
                }
            }
        });
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(96)
                    .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);

        setSize(494, 364);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        redirectSystemStreams();
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }
    public void setTreveButtonEnabled(boolean bool) {
        btnNewButton.setEnabled(bool);
    }
    
    public Joueur getGagnantDerniereTreve() {
        return gagnantDerniereTreve;
    }

    public void setGagnantDerniereTreve(Joueur joueur) {
        gagnantDerniereTreve = joueur;
    }

    public ZoneCombat getDerniereZoneTreve() {
        return derniereZoneTreve;
    }

    public void setDerniereZoneTreve(ZoneCombat zone) {
        derniereZoneTreve = zone;
    }
    public void disposeConsole() {
        currentGUI.dispose();
    }
    public static showCombatConsole getInstance() {
        return currentGUI;
    }
}
