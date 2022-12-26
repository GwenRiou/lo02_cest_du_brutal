package Controleur;
import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import Model.Joueur;
import Vue.RepartitionDesPoints;

public class main {
		public static void main(String[] args) throws InvocationTargetException, InterruptedException, IOException {
			Scanner sc = new Scanner(System.in);
			EventQueue.invokeAndWait (new Runnable() {
				public void run() {
					Joueur joueur1 = new Joueur(1);
                    Joueur joueur2 = new Joueur(2);
					try {
						RepartitionDesPoints gui1 = new RepartitionDesPoints(joueur1);
						//RepartitionDesPoints gui2 = new RepartitionDesPoints(joueur2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});		
		}
}
