package cestDuBrutalPackage;
import java.util.*;//for EVERYTHIGN



public class Zone {
    private String zoneName;
    protected ArrayList<Etudiant> etuDansZone;
    private static ArrayList<ZoneCombat> zoneList = new ArrayList<ZoneCombat>();
    
    //constructeur
    public Zone(String zoneName) {
        this.etuDansZone = new ArrayList<Etudiant>();
        this.zoneName = zoneName;
    }
    
    
    public void affectation(Etudiant etu) {
        this.etuDansZone.add(etu);
    }
    
    public static void setZones() {
        zoneList.add(new ZoneCombat("La Bibliotheque"));
        zoneList.add(new ZoneCombat("Le BDE"));
        zoneList.add(new ZoneCombat("Le Quartier Administratif"));
        /*
        -----------------------------------------------------------------------------------------------------Pour TESTER -------------------------------------------------
        
        zoneList.add(new ZoneCombat("Les Halles Industrielles"));
        zoneList.add(new ZoneCombat("La Halle Sportive"));*/
    }
    
    public static void displayAllZones(){
  
            Iterator<ZoneCombat> it =zoneList.iterator();
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
    public Etudiant drawEtudiantDansZone(Joueur j) throws StudentNotFoundInList{
        ArrayList<Etudiant>  etulist= this.etuDansZone;//
        boolean entryIsntValid = true;
        while (entryIsntValid) {
            try {//try finding a student in the zone
                int id = Integer.parseInt(Partie.getUserInput("Choisissez l'etudiant dans "+ this.zoneName));//will return a NumberFormatException if written wrong
                entryIsntValid = false;
                for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //let's find the student
                    Etudiant s = it.next();
                    if((s.getId() == id) & j.equals(s.getBelongsTo())) { //TODO set student isInZone to the zone hes affected in
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
        studentListToSort.sort((etu1,etu2) -> etu2.getInitiative()-(etu1.getInitiative())); //j'ai pas cherche a comprendre en détail la syntaxe....
    }
    
   
    public void removeStudentFromZone(Etudiant etu) {
        etuDansZone.remove(etu);
    }
    
    public static int allZoneNotEmpty() {
        
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            Zone zoneTemp = it.next();
            if(zoneTemp.getNombreEtu()==0) return 0;
        }
        return 1;
    }
    
    public static void displayAllStudentInZones() {
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            Zone zoneTemp = it.next();
            zoneTemp.getEtudiantDansZoneList();
        }
    }
    
    public static void melee() {
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            zoneTemp.combat();
        }
    }
    
    public static void initialiserZone() {// order the list of etu by initiative on every zone 
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            zoneTemp.initialiser();
        }
    }

    //getters
    public String getZoneName() {return zoneName;}
    public int getNombreEtu() {return this.etuDansZone.size();} 
    public void getEtudiantDansZoneList() {
        ArrayList<Etudiant>  etulist= this.etuDansZone;
        System.out.println("\n==Etudiants dans "+this.zoneName+":==");
        for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //scan through all students
            Etudiant s = it.next();
            System.out.println(s);//use the tostring method to print the student's ids
        }    
    }
    public ArrayList<Etudiant> getEtuDansZoneArrayList() {return etuDansZone;}
    //setters
    public void setZoneName(String zoneName) {this.zoneName = zoneName;}
    public static void setZoneList(ArrayList<ZoneCombat> zoneList) {Zone.zoneList = zoneList;}


    public static ArrayList<ZoneCombat> getZoneList() {return zoneList;}
    public void addEtudiantDansZone(Etudiant etudiant) {
        this.etuDansZone.add(etudiant);
        System.out.println("L'etudiant a bien ete ajoute a la zone");
    }
    /*
    public void publicSetChanged() {
        this.setChanged();
    }
    public void publicNotifyObservers() {
        this.notifyObservers();
    }
    public void publicClearChanged() {
        this.clearChanged();
    }
    */
    
}


