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
    
    //TODO    /!\ à changer: ID étudiant 
    public void removeReserve(Etudiant etudiant) {
        listeEtudiantsReserve.remove(etudiant);
    }
}
