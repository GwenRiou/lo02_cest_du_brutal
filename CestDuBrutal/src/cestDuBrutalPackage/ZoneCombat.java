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
    public void initialiser() {
        this.sortStudentList(etuDansZone);
    }
            
    public void run() {
        initialiser();
        while(controleZone ==ControleZone.DISPUTE) {   // on se bat si la zone n'est pas contorlee     
            try {
                    
                //action d'un combat
                
                // on test si la zone est control�e --> si oui, controleZone=0 et appel de la depose message avec "0"
                // on ne crer pas de m�thode car plusieurs thread y aurait acc�s au m�me moment
                
                
                
                //methode pour regarder verifier si la zone est contorlee 
                if(Math.random()>0.1) {// check si le combat est fini dans cette zone 
                    System.out.println("La zone "+Thread.currentThread().getName()+" n'est pas controlee");
                    partie.declancherTreve(Thread.currentThread().getName(), "Pas de tr�ve");
                    
                }else {// la zone est controle
                    controleZone=ControleZone.CONTROLEPARJOUEUR1;
                    partie.declancherTreve(Thread.currentThread().getName(), "0");             
                }       
                
                
                        
                //Affiche l'�tat de la zone // c'est optionnel 
                if (controleZone ==ControleZone.DISPUTE)
                    System.out.println(Thread.currentThread().getName() + " n'est pas control�e (prod)");
                else
                    System.out.println(Thread.currentThread().getName() +  " est controlee (prod)");
                
                
                //sleep present pour ralentir l'�xecution 
                Thread.sleep((long)(Math.random()*1000));               
            } catch (InterruptedException e) {
                e.printStackTrace();
            }           
        }
    }
    
    
    
    public void verifierControle() {
        
    }
    
    //getters
    private ControleZone getControleZone() {return this.controleZone;}
    //setters
    private void setControleZone(ControleZone control) {this.controleZone = control;}
}
