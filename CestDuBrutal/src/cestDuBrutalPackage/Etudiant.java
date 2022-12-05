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
    
    private Joueur belongsTo ; // set 1 pour j1 et set � 2 pour j2
    private Zone isInZone;
    private enumStrategie strategie;
    

    // constructeur 
    //ects = 30 pour tous les �tudiants donc pas dans l'appel construction
    public Etudiant(String type, int force, int dexterite, 
            int resistance, int constitution, int initiative,Joueur idJoueur) {
        this.type = type;
        this.ects = ects;
        this.force = force;
        this.dexterite = dexterite;
        this.resistance = resistance;
        this.constitution = constitution;
        this.initiative = initiative; //(int) (Math.random()*100) //debug line
        this.belongsTo = idJoueur;
        this.strategie = enumStrategie.RANDOM;
    }
    
    

    public Etudiant() {
        
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer ("L'Etudiant n� ");
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
        sb.append(", Strategie : ");
        sb.append(this.strategie);
            
        return sb.toString();
    }
    
    

    public void displayCaracteristics(){
        System.out.println(getType()+getEcts()+getForce()+getDexterite()+getResistance()+getConstitution()+getInitiative()+getStrategieString());
    }

    
    
    private void attack(Etudiant target, Zone zone) {//TODO only attack student inside zone
        if (!this.belongsTo.equals(target.belongsTo)) {//excludes friendly fire  (&& this.isInZone.equals(target.isInZone)) but too intensive, prefer to check on call
            final int damageReference = 10;
            double damageCoefficient = Math.max(0, Math.min(100, 10*this.force-5*target.resistance));
            double y = Math.random();
            int x = ((int) Math.random()*100);
            if (x>0 && x<(40 + 3*this.dexterite)) {
                target.ects -= (int) ((y*(1+damageCoefficient))*damageReference);
            }
            if (target.ects <= 0) {//kill student
                target.isInZone.getEtuDansZoneArrayList().remove(target);
                //=====EXPERIMENTAL PART=======
                zone.publicSetChanged();
                zone.publicNotifyObservers();//notify observers when a student dies
                zone.publicClearChanged();
            }
        }   
    } 
    
    private void heal(Etudiant target) {//TODO only attack student inside zone
        if (this.belongsTo.equals(target.belongsTo)) {//excludes healing enemies
            int healAmount;
            int x = ((int) Math.random()*100);
            double y = Math.random()*0.6;//0<y<0,6
            if (x>0 && x<(20 + 6*this.dexterite)) {
                healAmount = (int) (y*(10+target.constitution));
                if (healAmount < (30 + target.constitution)) {
                    target.ects +=healAmount;
                }
                else {
                    target.ects += 30 + this.constitution;
                }
            }
        }
        
            
        
    } 
    
    private void agir(Etudiant target) {
        if (this.strategie == strategie.OFFENSIVE) {
            attack(target, target.isInZone);
        }
        else if (this.strategie == strategie.DEFENSIVE) {
            heal(target);
        }
        else if (this.strategie == strategie.RANDOM) {
            if(Math.random()>0.5) {
                attack(target, target.isInZone);
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
    public String getStrategieString() {return strategie.toString();}
    public Joueur getBelongsTo() {return belongsTo;}
    public Zone getIsInZone() {return isInZone;}

    public void setIsInZone(Zone isInZone) {this.isInZone = isInZone;}
    public void setForce(int newForce){this.force=newForce;}
    public void setDexterite(int newDexterite){this.dexterite= newDexterite;}
    public void setResistance(int newResistance){this.resistance= newResistance;}
    public void setConstitution(int newConstisution){this.constitution= newConstisution;}
    public void setInitiative(int newInitiative){this.initiative= newInitiative;}
    public void setId(int id) {this.id = id;}
    public void setStrategie(String strategie) {this.strategie = enumStrategie.valueOf(strategie.toUpperCase());}

    

}
