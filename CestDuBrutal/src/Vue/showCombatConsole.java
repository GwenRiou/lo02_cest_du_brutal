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
/**
 * Interface graphique qui montre le derouler du combat 
 */
public class showCombatConsole extends JFrame {
    /**
     * Zone d'affichage d'un style console
     */
    private JTextArea textArea;
    /**
     * joueur gagnant de la zone qui declanche la treve
     */
    private Joueur gagnantDerniereTreve;
    /**
     *  recuper la derniere zone qui lance la treve
     */
    private ZoneCombat derniereZoneTreve;
    private static showCombatConsole currentGUI;
    /**
     * bouton pour lancer la nouvelle interface, une fois le combat en treve
     */
    private JButton btnNewButton;
    /**
     * constructeur de l'interface  
     */
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
    /**
     * Active le bouton de la treve
     * @param bool active le bouton de la treve
     */
    public void setTreveButtonEnabled(boolean bool) {
        btnNewButton.setEnabled(bool);
    }
    /**
     * recupere le joueur gagnant
     * @return le joueur gagnant
     */
    public Joueur getGagnantDerniereTreve() {
        return gagnantDerniereTreve;
    }
    /**
     * affecte le joueur gagne de la treve
     * @param joueur prends le joueur qui a gagne la derniere zone mise en treve
     */
    public void setGagnantDerniereTreve(Joueur joueur) {
        gagnantDerniereTreve = joueur;
    }
    /**
     * redonne la zone qui declanche la treve
     * @return la zone qui a declanche la treve
     */
    public ZoneCombat getDerniereZoneTreve() {
        return derniereZoneTreve;
    }
    /**
     * donnne la zone qui declanche la treve
     * @param zone set la zone qui a declancher la treve
     */
    public void setDerniereZoneTreve(ZoneCombat zone) {
        derniereZoneTreve = zone;
    }
    /**
     * ferme l'interface graphique 
     */
    public void disposeConsole() {
        currentGUI.dispose();
    }
    /**
     * return l'interface graphique en cours
     * @return recupere l'interface graphique en cours ( le combat ici)
     */
    public static showCombatConsole getInstance() {
        return currentGUI;
    }
}
