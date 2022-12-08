package cestDuBrutalPackage;

import java.util.ArrayList;

public class ZoneCombat extends Zone implements Runnable{

    private ControleZone controleZone; // il renplacera la deuxieme ligne // CONTROLEPARJOUEUR1,CONTROLEPARJOUEUR2,DISPUTE;
    //private int controleZone; //0 controler par un joueur , 1 pas de controle
    private Partie partie;
    

    public ZoneCombat(String name) {
        super(name);
        this.controleZone=ControleZone.DISPUTE;
        this.partie= Partie.getInstance();
    }
    
    public void combat() {
        String nom = getZoneName();
        Thread t = new Thread(this, nom);
        t.start();
    }
    
    public void interrupted() {        
            Thread.currentThread().interrupt();
    }
    
    public void initialiser() {
        this.sortStudentList(etuDansZone);
    }
            
    public void run() {
        initialiser();
        int i = 0;
        while(controleZone == ControleZone.DISPUTE) {   // on se bat si la zone n'est pas contorlee     
            try { 
                    
                //action d'un combat
                
                // on test si la zone est control�e --> si oui, controleZone=0 et appel de la depose message avec "0"
                // on ne crer pas de m�thode car plusieurs thread y aurait acc�s au m�me moment       
                this.etuDansZone.get(i).agir();//TODO DEPLACER A LA FIN DE LA LISTE apres agir
                i++;
                i = i%etuDansZone.size();
                        
                //Affiche l'�tat de la zone // c'est optionnel 
                if (controleZone ==ControleZone.DISPUTE) {
                    partie.declencherTreve(Thread.currentThread().getName(), "Pas de treve");
                    //System.out.println(Thread.currentThread().getName() + " n'est pas controlee (prod)");
                }
                else {
                    System.out.println(Thread.currentThread().getName() +  " est controlee");
                    partie.declencherTreve(Thread.currentThread().getName(), "0");    
                }
                
                //sleep present pour ralentir l'execution 
                //Thread.sleep((long)(Math.random()*0));               
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }           
        }
    }
    
    

    
    //getters
    public ControleZone getControleZone() {return this.controleZone;}
    //setters
    public void setControleZone(ControleZone control) {this.controleZone = control;}
}
