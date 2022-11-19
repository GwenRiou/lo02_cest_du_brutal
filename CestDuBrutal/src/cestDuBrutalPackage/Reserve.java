package cestDuBrutalPackage;

import java.util.ArrayList;

public class Reserve extends Zone {
    
    ArrayList <Etudiant> listeEtudiantsReserve = new ArrayList <Etudiant>();
   
    //instantiation
    public Reserve() {
        
    }
    
    
   public boolean isEmpty() {
       return listeEtudiantsReserve.isEmpty();
   }
    
    public void affecterReserve(Etudiant etudiant) {
        listeEtudiantsReserve.add(etudiant);
    }
    
    //TODO    /!\ Ã  changer: ID Ã©tudiant 
    public void removeReserve(Etudiant etudiant) {
        listeEtudiantsReserve.remove(etudiant); // on va faire des copies , je pense que laisse la liste des étu dans le joueur est une bonne idée
    }
}
