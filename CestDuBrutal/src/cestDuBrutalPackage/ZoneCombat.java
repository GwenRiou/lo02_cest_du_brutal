package cestDuBrutalPackage;

import java.util.ArrayList;

public class ZoneCombat extends Zone {

    ArrayList <Etudiant> listeEtudiantsZone = new ArrayList <Etudiant>();
    private ControleZone control;
    

    public void verifierControle() {
        
    }


    //afficher toutes les zones
    public static void displayAllZones() {
        
    }
    
    
    //instanciation
    public ZoneCombat() {
        
    }
    
    public void affecterZone(Etudiant etudiant) {
        listeEtudiantsZone.add(etudiant);
    }
    
    //TODO    /!\ à changer: ID étudiant 
    public void removeZone(Etudiant etudiant) {
        listeEtudiantsZone.remove(etudiant);
    }
    
    //getters
    private ControleZone getControleZone() {return this.control;}
    //setters
    private void setControleZone(ControleZone control) {this.control = control;}
}
