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
        zoneList.add(new Zone("Bibliotheque"));
        zoneList.add(new Zone("BDE"));
        zoneList.add(new Zone("Quartier Administratif"));
        zoneList.add(new Zone("Halles Industrielles"));
        zoneList.add(new Zone("Halle Sportive"));
    }
    
    public static void displayAllZones(){
  
            Iterator<Zone> it =zoneList.iterator();
            while(it.hasNext()){
                Zone zoneTemp = it.next();
                System.out.println(zoneTemp.getZoneName());
            }
        }
        
    


    //getters
    public String getZoneName() {return zoneName;}
    public ControleZone getStrategie() {return enumControleZone;}
    public int getNombreEtu() {return this.etuDansZone.size();}

    //setters
    public void setZoneName(String zoneName) {this.zoneName = zoneName;}
    public void setStrategie(String enumControleZone) {this.enumControleZone = ControleZone.valueOf(enumControleZone.toUpperCase());}
    public static void setZoneList(ArrayList<Zone> zoneList) {Zone.zoneList = zoneList;}


    public static ArrayList<Zone> getZoneList() {
        return zoneList;
    }
}


