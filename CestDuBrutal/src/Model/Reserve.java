package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
/**
 * herite de zone, est la zone dans laquelle le joueur peut affecter 5 etudiant au debut de la partie
 * @author boone
 *
 */
public class Reserve extends Zone {
    
   /**
    * constructeur de la reserve
    */
    public Reserve() {
     super("Reserve");
    }

    /**
     * Verifie si la reserve est vide
     * @return booleen: vrai si la reserve est vide, faux si elle ne l'est pas
     */
    public boolean isEmpty() {
       return this.etuDansZone.isEmpty(); 
   }
    /**
     * affecte un etudiant a la reserve
     * @param etudiant etudiant a affecter
     */
    public void affecterReserve(Etudiant etudiant) {
        this.etuDansZone.add(etudiant);
    }
    
    //TODO    /!\ à changer: ID étudiant 
    /**
     * retire un etudiant de la reserve
     * @param etudiant etudiant a retirer
     */
    public void removeReserve(Etudiant etudiant) {
        this.etuDansZone.remove(etudiant);
    }
  //Getter & setter
    /**
     * getter de la liste des etudiants dans la reserve {@link Zone#etuDansZone}
     * @return {@link Zone#etuDansZone}
     */
    public ArrayList<Etudiant> getListeEtudiantsReserve() {
         return this.etuDansZone;
     }
    
     /**
      * retourne le nombre d'etudiants dans la reserve
      * @return retourne la taille de {@link Zone#etuDansZone}
      */
     public int getNombreEtuReserve() {return this.etuDansZone.size();}
} 
