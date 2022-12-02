package cestDuBrutalPackage;
import java.util.*;//for EVERYTHIGN



public class Zone {
    private String zoneName;
    private ControleZone enumControleZone;
    protected ArrayList<Etudiant> etuDansZone;
    private static ArrayList<Zone> zoneList = new ArrayList<Zone>();
    
    //constructeur
    public Zone(String zoneName) {
        this.etuDansZone = new ArrayList<Etudiant>();
        this.zoneName = zoneName;
    }
    
    
    public void affectation(Etudiant etu) {
        this.etuDansZone.add(etu);
    }
    
    public static void setZones() {
        zoneList.add(new Zone("La Bibliotheque"));
        zoneList.add(new Zone("Le BDE"));
        zoneList.add(new Zone("Le Quartier Administratif"));
        zoneList.add(new Zone("Les Halles Industrielles"));
        zoneList.add(new Zone("La Halle Sportive"));
    }
    
    public static void displayAllZones(){
  
            Iterator<Zone> it =zoneList.iterator();
            while(it.hasNext()){
                Zone zoneTemp = it.next();
                System.out.println(zoneTemp.getZoneName());
            }
   }
    
        
    public static Zone getZone(int index) { // called inside another getZone() to fetch the Student at the given iterator
        Zone zon = zoneList.get(index);
        return zon;
    }
    
    public Etudiant drawEtudiantDansZone(int index) {// called inside another getEtudiantDansZone() to fetch the Student at the given iterator 
        Etudiant etu = etuDansZone.get(index);         
        return etu;
    }
    public Etudiant drawEtudiantDansZone(Joueur j) throws StudentNotFoundInList{   //identical to getEtudiantDansZone except it    
        ArrayList<Etudiant>  etulist= this.etuDansZone;//
        boolean entryIsntValid = true;
        while (entryIsntValid) {
            try {//try finding a student in the zone
                int id = Integer.parseInt(Partie.getUserInput("Choisissez l'etudiant dans "+ this.zoneName));//will return a NumberFormatException if written wrong
                entryIsntValid = false;
                for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //let's find the student
                    Etudiant s = it.next();
                    if((s.getId() == id) & j.equals(s.getBelongsTo())) {
       
                        return drawEtudiantDansZone(it.previousIndex()); //return the student if the id condition is met
                    }
                    //if the id matches the student AND the student belongs to the player, return the student
                }//exits this loop if it hasnt found a student
            }
            catch (NumberFormatException e){
                System.out.println("Veuillez entrer un ID valide \n");               
            }
        }
        throw new StudentNotFoundInList();

    }
    
    
    public static void sortStudentList(ArrayList<Etudiant> studentListToSort) {
        studentListToSort.sort((etu1,etu2) -> etu2.getInitiative()-(etu1.getInitiative())); //j'ai pas cherché à comprendre en détail la syntaxe....
    //TODO à tester
    }
    
   
    public void removeStudentFromZone(Etudiant etu) {
        etuDansZone.remove(etu);
    }
    
    public static int allZoneNotEmpty() {
        
        Iterator<Zone> it =zoneList.iterator();
        while(it.hasNext()){
            Zone zoneTemp = it.next();
            if(zoneTemp.getNombreEtu()==0) return 0;
        }
        return 1;
    }
    
    public static void displayAllStudentInZones() {
        Iterator<Zone> it =zoneList.iterator();
        while(it.hasNext()){
            Zone zoneTemp = it.next();
            zoneTemp.getEtudiantDansZoneList();
        }
    }

    //getters
    public String getZoneName() {return zoneName;}
    public ControleZone getStrategie() {return enumControleZone;}
    public int getNombreEtu() {return this.etuDansZone.size();} 
    public void getEtudiantDansZoneList() {
        ArrayList<Etudiant>  etulist= this.etuDansZone;
        for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //scan through all students
            Etudiant s = it.next();
            System.out.println(s);//use the tostring method to print the student's ids
        } 
        
    }
    //setters
    public void setZoneName(String zoneName) {this.zoneName = zoneName;}
    public void setStrategie(String enumControleZone) {this.enumControleZone = ControleZone.valueOf(enumControleZone.toUpperCase());}
    public static void setZoneList(ArrayList<Zone> zoneList) {Zone.zoneList = zoneList;}


    public static ArrayList<Zone> getZoneList() {return zoneList;}
    public void addEtudiantDansZone(Etudiant etudiant) {
        this.etuDansZone.add(etudiant);
        System.out.println("L'etudiant a bien ete ajoute a la zone");
    }
    
    
}


