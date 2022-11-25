package cestDuBrutalPackage;

import java.util.ArrayList;
import java.util.ListIterator;

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
  //Getter & setter
    public ArrayList<Etudiant> getListeEtudiantsReserve() {
         return listeEtudiantsReserve;
     }
     public void setListeEtudiantsReserve(ArrayList<Etudiant> listeEtudiantsReserve) {
         this.listeEtudiantsReserve = listeEtudiantsReserve;
     }
}
