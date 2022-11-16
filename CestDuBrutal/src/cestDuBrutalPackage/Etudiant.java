package cestDuBrutalPackage;
import java.lang.Math;

public class Etudiant implements Strategie{
    
	
    private String type;
    private int ects=30;
    private int force;
    private int dexterite;
    private int resistance;
    private int constitution;
    private int initiative;
    private enumStrategie strategie;
    

    // constructeur 
    //ects = 30 pour tous les étudiants donc pas dans l'appel construction
    public Etudiant(String type, int force, int dexterite, 
            int resistance, int constitution, int initiative) {
        this.type = type;
        this.ects = ects;
        this.force = force;
        this.dexterite = dexterite;
        this.resistance = resistance;
        this.constitution = constitution;
        this.initiative = initiative;
        this.strategie = strategie;
    }
    
    
    //TODO do we make a string tostring?
    public void displayCaracteristics(){
        System.out.println(getEcts()+getForce()+getDexterite()+getResistance()+getConstitution()+getInitiative());
    }

    
    
    private void attack(Etudiant target) {}
    private void heal(Etudiant target) {}
    
    private void agir(Etudiant target) {
        if (this.strategie == strategie.OFFENSIVE) {
            attack(target);
        }
        else if (this.strategie == strategie.DEFENSIVE) {
            heal(target);
        }
        else if (this.strategie == strategie.RANDOM) {
            if(Math.random()>0.5) {
                attack(target);
            }
            else {
                heal(target);
            }
        }
        
    }
    
    
    
    
    
    
    //getters & setters
    
    public String getType(){return this.type;}
    public int getEcts(){return this.ects;}
    public int getForce(){return this.force;}
    public int getDexterite(){return this.dexterite;}
    public int getResistance(){return this.resistance;}
    public int getConstitution(){return this.constitution;}
    public int getInitiative(){return this.initiative;}
    
    public void setForce(int newForce){this.force=newForce;}
    public void setDexterite(int newDexterite){this.dexterite= newDexterite;}
    public void setResistance(int newResistance){this.resistance= newResistance;}
    public void setConstitution(int newConstisution){this.constitution= newConstisution;}
    public void setInitiative(int newInitiative){this.initiative= newInitiative;}
    

}
