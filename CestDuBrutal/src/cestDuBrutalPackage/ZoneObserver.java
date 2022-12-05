package cestDuBrutalPackage;

import java.util.*;

public class ZoneObserver implements Observer {
    private int[] studentCount;
    private boolean foundEmptyStudentList;
    
    public ZoneObserver(Zone z) {
        z.addObserver(this);
        foundEmptyStudentList = false;
        this.studentCount = new int[Partie.getInstance().getListJ().size()];//make an array with the student count for each player
    }
    
    public void update(Observable obs, Object zon) {
        if (zon instanceof Zone){
            Zone zoneToCount = (Zone) zon;
            //COUNT PLAYERS
            Iterator<Etudiant> it = zoneToCount.getEtuDansZoneArrayList().iterator(); 
            while (it.hasNext()) { //scan all students in the zone
                Etudiant e = it.next();
                for (int i = 0;i < Partie.getInstance().getListJ().size();i++) {//count for each player
                    studentCount[i]++;//count number of players alive on each team in the zone
                }
            }
            
            for (int i = 0; i<studentCount.length; i++) {//scan for players without students in a zone;
                if (studentCount[i] == 0) {
                    foundEmptyStudentList = true;
                }
            }
        }
        if (foundEmptyStudentList) {//if an empty student list is found, we trigger the treve
            foundEmptyStudentList = false;
            
            //TODO treve                
        }
    }
}
