package Controleur;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import Model.Joueur;
import Model.Partie;
import Model.Zone;
import Vue.DistributionEtudiants;
import Vue.MainMenu;
import Vue.MiseEnReserve;
import Vue.RepartitionDesPoints;

public class main {
    public void NextInterface() {
    }
    		public static void main(String[] args) throws InvocationTargetException, InterruptedException, IOException {
			Scanner sc = new Scanner(System.in);
			EventQueue.invokeAndWait (new Runnable() {
				public void run() {
					try {
					    Partie partie;
				        partie = Partie.getInstance();
				        partie.getConnection();// ne fonctionne que apres un getInstance 
				        Joueur j1 = new Joueur(1);
				        Joueur j2 = new Joueur(2);
				        partie.addPlayer(j1);
				        partie.addPlayer(j2);

				        Zone.setZones(); 
				        
				        MainMenu gui0 = new MainMenu(partie,j1,j2);
				        //RepartitionDesPoints gui1 = new RepartitionDesPoints(partie);
						//MiseEnReserve gui2 = new MiseEnReserve(partie);	
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});		
		}
}
