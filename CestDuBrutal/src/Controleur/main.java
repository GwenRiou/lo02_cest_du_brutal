package Controleur;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import Model.Joueur;
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
					    Joueur j1 = new Joueur(1);
	                    Joueur j2 = new Joueur(2);
						RepartitionDesPoints gui1 = new RepartitionDesPoints(j1);	
						
						MiseEnReserve gui2 = new MiseEnReserve(j1);
						
	                     //MiseEnReserve hu2 = new MiseEnReserve(joueur1);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});		
		}
}
