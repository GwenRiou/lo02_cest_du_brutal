package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
/**
 * zone de combat dans laquelle se trouve les etudiants en combat
 * @author boone
 *
 */
public class ZoneCombat extends Zone implements Runnable{

    private ControleZone controleZone; // il renplacera la deuxieme ligne // CONTROLEPARJOUEUR1,CONTROLEPARJOUEUR2,DISPUTE;
    //private int controleZone; //0 controler par un joueur , 1 pas de controle
    private Partie partie;
    private Joueur controlePar;
    Thread t ;
    int numAction;
    
    
    /**
     * Constructeur de la zone de combat, initialise un nouveau thread pour le multithreading
     * @param name nom de la zone de combat, nommera la thread dans laquelle elle est activee
     */
    public ZoneCombat(String name) {
        super(name);
        this.controleZone=ControleZone.DISPUTE;
        this.partie= Partie.getInstance();
        t = new Thread(this, name);
        numAction=0;
    }

    /**
     * active les threads
     */
    public void combat() {
        String nom = getZoneName();
        t.start();
    }


    /**
     * interrompt les theads si besoin
     */
    public void interrupted() {          
            t.interrupt();
    }

    /**
     * Trie la liste des etudiants dans la zone de combat
     */
    public void initialiser() {
        this.sortStudentList(etuDansZone);
    }

    /**
     * Augmente la force de tout le monde lorsque la bataille se deroule pendant trop longtemps
     */
    public void frenesie() {
        System.out.println("######################################################################################################");
        System.out.println("                                         FRENESIE                                                     ");
        System.out.println("######################################################################################################");
        Iterator<Etudiant> it =etuDansZone.iterator();
        while(it.hasNext()){
            Etudiant etu = it.next();
            etu.setStrategie("OFFENSIVE");
            etu.setForce(etu.getForce()+100);
        }   
    }
    
    /**
     * chaque zone fait agir letudiant en haut de la liste, puis le deplace vers la fin lorsqu'il a fini d'agir.
     * repete cette action jusque lorsqu'un etudiant tue le dernier etudiant de la zone, ou si une autre zone se fait controler        
     */

    public void run() {
        initialiser();
        int i = 0;
        while(controleZone == ControleZone.DISPUTE) {   // on se bat si la zone n'est pas contorlee     
            try { 
                 numAction++;
                 if(numAction>500) {
                     numAction=0;
                     frenesie();
                 }
                //action d'un combat
                
                this.etuDansZone.get(i).agir();//TODO DEPLACER A LA FIN DE LA LISTE apres agir
                i++;
                i = i%etuDansZone.size();
                        
                if (controleZone ==ControleZone.DISPUTE) {
                    partie.declencherTreve(this.controlePar,this, "Pas de treve");
                    //System.out.println(Thread.currentThread().getName() + " n'est pas controlee (prod)");
                }
                else {
                    
                    System.out.println(this.getZoneName() +  " est controlee par "+ this.controlePar.getUserName());
                    
                    partie.declencherTreve(this.controlePar,this, "0");    
                }
                
                //sleep present pour ralentir l'execution 
                Thread.sleep((long)(Math.random()*500));               
            } catch (InterruptedException e) {
                //e.printStackTrace();
                break;// on sort du while
            }           
        }
    }
    

    /**
     * verifie s'il existe au moins un etudiant dans la zone appartenant au joueur 2
     * @param j joueur 2
     * @return retourne un booleen, true s'il existe au moins un etudiant appartenant au joueur 2, false sinon 
     */

    public boolean zoneWithTwoEtu(Joueur j) {
        Iterator<Etudiant> it =etuDansZone.iterator();
        while(it.hasNext()){
            Etudiant etu = it.next();
            if(etu.getBelongsTo()==j) return true; // on a un �tudiant du joueur deux dedans
        }
        return false;
    }
    
    /**
     * Affiche le nombre total de points ECTS dans cette zone.
     */
    public void displayECTS() {
        ArrayList<Etudiant>  etulist= this.etuDansZone;
        int nbECTS=0;
        int nbEtu=0;
        for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //scan through all students
            Etudiant s = it.next();
            nbECTS+=s.getEcts();
            nbEtu++;
            
        }    
        System.out.println("nombre de credits ECTS "+nbECTS);
        System.out.println("nombre d'etudiant dans la zone "+nbEtu);
        System.out.println();
    }
    /**
     * Retourne le nombre d'Etudiants ou le nombre total de points ECTS dans la zone
     * @param ECTS vrai pour retourner le nombre d'ECTS, faux pour le nombre d'etudiants
     * @return nbECTS ou nbEtu
     */
    public int getTotalECTS(boolean ECTS) {
        ArrayList<Etudiant>  etulist= this.etuDansZone;
        int nbECTS=0;
        int nbEtu=0;
        for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //scan through all students
            Etudiant s = it.next();
            nbECTS+=s.getEcts();
            nbEtu++;
        }
        if(ECTS) {
            return nbECTS;
        }
        else {
            return nbEtu;
        } 
    }

    
    //getters

    /**
     * getter de {@link #controleZone}
     * @return {@link #controleZone}
     */
    public ControleZone getControleZone() {return this.controleZone;}
    /**
     * apelle le joueur qui controle la zone
     * @return {@link #controlePar}
     */
    public Joueur getControlePar() {return controlePar;}
    //setters
    /**
     * setter de {@link #controleZone}
     * @param control l'enumeration a remplacer {@link #controleZone}
     * @param j le joueur qui controle la zone
     */
    public void setControleZone(ControleZone control,Joueur j) {this.controleZone = control;this.controlePar = j;}
    /**
     * setter de {@link #controlePar}
     * @param controlePar Joueur qui controle la zone
     */
    public void setControlePar(Joueur controlePar) {this.controlePar = controlePar;}

}
