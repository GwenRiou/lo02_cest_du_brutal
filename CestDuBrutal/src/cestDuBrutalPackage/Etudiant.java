package cestDuBrutalPackage;
import java.lang.Math;

public class Etudiant implements Strategie{
    
	
    private String type;
    private int id;
    private int ects=30;
    private int force;
    private int dexterite;
    private int resistance;
    private int constitution;
    private int initiative;
    
    private int belongsTo ; // set 1 pour j1 et set à 2 pour j2
    
    private enumStrategie strategie;
    

    // constructeur 
    //ects = 30 pour tous les ï¿½tudiants donc pas dans l'appel construction
    public Etudiant(String type, int force, int dexterite, 
            int resistance, int constitution, int initiative,int idJoueur) {
        this.type = type;
        this.ects = ects;
        this.force = force;
        this.dexterite = dexterite;
        this.resistance = resistance;
        this.constitution = constitution;
        this.initiative = initiative;
        this.belongsTo = idJoueur;
        this.strategie = strategie;
    }
    
    
    public String toString() {
        StringBuffer sb = new StringBuffer ("L'Etudiant nï¿½ ");
        sb.append(this.id);
        sb.append(" et de type : " );
        sb.append(this.type);
        sb.append(" a PV : ");
        sb.append(this.ects);
        sb.append(", Force : ");
        sb.append(this.force);
        sb.append(", Dexterite : ");
        sb.append(this.dexterite);
        sb.append(", Resistance : ");
        sb.append(this.resistance);
        sb.append(", Constitution : ");
        sb.append(this.constitution);
        sb.append(", Initiative : ");
        sb.append(this.initiative);
        sb.append(", Stratï¿½gie : ");
        //sb.append(getStraterie); TODO
            
        return sb.toString();
    }


    public void displayCaracteristics(){
        System.out.println(getType()+getEcts()+getForce()+getDexterite()+getResistance()+getConstitution()+getInitiative());
    }

    
    
    private void attack(Etudiant target) {} //TODO a dï¿½finir
    private void heal(Etudiant target) {} //TODO a dï¿½finir
    
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
    public int getId() {return id;}
    public enumStrategie getStrategie() {return strategie;}
    
  
    public void setForce(int newForce){this.force=newForce;}
    public void setDexterite(int newDexterite){this.dexterite= newDexterite;}
    public void setResistance(int newResistance){this.resistance= newResistance;}
    public void setConstitution(int newConstisution){this.constitution= newConstisution;}
    public void setInitiative(int newInitiative){this.initiative= newInitiative;}
    public void setId(int id) {this.id = id;}
    public void setStrategie(String strategie) {this.strategie = enumStrategie.valueOf(strategie.toUpperCase());}

    

}
