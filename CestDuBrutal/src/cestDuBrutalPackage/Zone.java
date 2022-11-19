package cestDuBrutalPackage;
import java.util.*;//VERY IMPORTANT

public class Zone {
    private String zoneName;
    private ControleZone enumControleZone;
    
    public void affectation() {
        
    }
    
    public static void main(String args[]) {
        
        
    }
    
    
    
    //getters
    public String getZoneName() {return zoneName;}
    public ControleZone getStrategie() {return enumControleZone;}
    //setters
    public void setZoneName(String zoneName) {this.zoneName = zoneName;}
    public void setStrategie(String enumControleZone) {this.enumControleZone = ControleZone.valueOf(enumControleZone.toUpperCase());}
    
}
