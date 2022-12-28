package Model;
import java.lang.Math;
import java.util.*;
/**
 * Classe etudiant, qui va combattre dans les zones
 * Implemente les methodes de {@link Strategie}
 * @author boone
 * 
 */
public class Etudiant implements Strategie{
    
	/**
	 * Type de l'etudiant, maitre du gobi, elite ou etudiant
	 */
    private String type;
    /**
     * id de l'etudiant, pour le reperer
     */
    private int id;
    /**
     * points de vie de l'etudiant
     */
    private int ects=30;
    /**
     * statistique de force au combat
     */
    private int force;
    /**
     * Plus il est eleve, plus letudiant a une grande chance d'accomplir son action
     */
    private int dexterite;
    /**
     * Capacite a letudiant de prendre moins de dommages d'attaque d'un autre etudiant
     */
    private int resistance;
    /**
     * plus il est eleve, plus il peut etre soigne facilement
     */
    private int constitution;
    /**
     * L'etudiant avec l'initiative la plus grande agit en premier
     */
    private int initiative;
    /*
     * permet d'apeller le joueur auquel l'etudiant appartient
     */
    private Joueur belongsTo ; // set 1 pour j1 et set � 2 pour j2
    /**
     * permet d'apeller la zone dans laquelle l'etudiant se situe
     */
    private Zone isInZone;
    /**
     * Strategie de l'etudiant, une enumeration de {@link enumStrategie}
     */
    private enumStrategie strategie;
    

    // constructeur 
    //ects = 30 pour tous les �tudiants donc pas dans l'appel construction

    /**
     * Constructeur manuelle de la classe
     * @param type Type de l'etudiant, maitre du gobi, elite ou etudiant
     * @param force Plus il est eleve, plus letudiant a une grande chance d'accomplir son action
     * @param dexterite Plus il est eleve, plus letudiant a une grande chance d'accomplir son action
     * @param resistance Capacite a letudiant de prendre moins de dommages d'attaque d'un autre etudiant
     * @param constitution plus il est eleve, plus il peut etre soigne facilement
     * @param initiative L'etudiant avec l'initiative la plus grande agit en premier
     * @param idJoueur Le joueur auquel l'etudiant appartient
     */

    public Etudiant(String type, int force, int dexterite, 
            int resistance, int constitution, int initiative,Joueur idJoueur) {
        this.type = type;
        this.ects = ects;
        this.force = force;
        this.dexterite = dexterite;
        this.resistance = resistance;
        this.constitution = constitution;
        this.initiative = initiative; //debug line
        this.belongsTo = idJoueur;
        this.strategie = enumStrategie.RANDOM;
        this.isInZone = new Zone("le camion");
    }
    /**
     * Constructeur automatique de la classe
     * @param type Type de l'etudiant, maitre du gobi, elite ou etudiant
     * @param idJoueur le joueur auquel l'etudiant appartient
     */
    public Etudiant(String type,Joueur idJoueur) {
        this.type = type;
        this.ects = ects;
        this.force = (int) (Math.random()*10);
        this.dexterite = (int) (Math.random()*20);
        this.resistance = (int) (Math.random()*20);
        this.constitution = (int) (Math.random()*20);
        this.initiative = (int) (Math.random()*20); //debug line
        this.belongsTo = idJoueur;
        this.strategie = enumStrategie.RANDOM;
        this.isInZone = new Zone("le camion");
    }
    
    
    /**
     * instantie un etudiant 
     */

    public Etudiant() {
        
    }
    
    /*
     * affiche les statistiques de l'etudiant
     */
    public String toString() {
        StringBuffer sb = new StringBuffer ("L'Etudiant n# ");
        sb.append(this.id);
        sb.append(" est de type : " );
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
        sb.append(", Joueur : ");
        sb.append(this.belongsTo.getUserName());
        
            
        return sb.toString();
    }
    
    
    /**
     * affiche les caracteristiques d'un etudiant, presque equivalent a tostring
     */
    public void displayCaracteristics(){
        System.out.println(getType()+getEcts()+getForce()+getDexterite()+getResistance()+getConstitution()+getInitiative()+getStrategieString());
    }
    
    /**
     * Tente d'attaquer un etudiant de l'autre joueur. Tue l'etudiant si l'autre n'a plus de vie, et declenche une treve lorsqu'il n'y a plus aucun etudiant de l'autre joueur dans la zones
     * @param zone permet d'appeler la zone pour compter le nombre d'etudiants dans la zone
     */

    private void attack(ZoneCombat zone) {//only attack student inside zone
        
        ArrayList<Etudiant> enemyTeam = new ArrayList<Etudiant>();
        Iterator<Etudiant> it = zone.getEtuDansZoneArrayList().iterator();
        Etudiant enemyEtu;
        while(it.hasNext()) {//insert all enemies into a list
           enemyEtu = it.next();
           if(!enemyEtu.belongsTo.equals(this.belongsTo)) {
               enemyTeam.add(enemyEtu);
           }
        }
        Iterator<Etudiant> it2 = enemyTeam.iterator();
        Etudiant target = enemyTeam.get(0);
        Etudiant etudiantTemp;
        while(it2.hasNext()) {//scan for the student with the least HP
            etudiantTemp = it2.next();
            //System.out.println(target.id+"ects:"+target.ects+target.belongsTo.getUserName());
            if (etudiantTemp.ects<=target.ects) {
                target = etudiantTemp; 
            }
        }
        //attacking...
        //System.out.println(this.belongsTo.getUserName()+" : "+this.id+" Tente d'attaquer");
        final int damageReference = 10;
        double damageCoefficient = Math.max(0, Math.min(100, 10*this.force-5*target.resistance));
        double y = Math.random();
        int x = (int) (Math.random()*100);
        int damageTaken = (int) ((y*(1+damageCoefficient))*damageReference);        
        if (x>0 && x<(40 + 3*this.dexterite)) {
            target.ects -= damageTaken;

            System.out.println("\033[0;90m"+this.belongsTo.getUserName()+" : "+this.getId()+" attacks "+target.id+", dealing "+ damageTaken + "HP"+"\033[0;0m");
        }
        if (target.ects <= 0) {//kill student
            System.out.println("\033[0;90m"+target.belongsTo.getUserName()+"'s etu #"+target.getId()+" was killed by"+this.id+"\033[0;0m");//

            target.isInZone.getEtuDansZoneArrayList().remove(target);
            //=====count number of players alive
            
            int studentCountj1 = 0;
            int studentCountj2 = 0;
            Iterator<Etudiant> iter = zone.getEtuDansZoneArrayList().iterator();
            Etudiant etu;
            do{//insert all enemies into a list
               etu = iter.next();
               if(etu.belongsTo.equals(PartieMVC.getInstance().getListJ().get(0))) {
                   studentCountj1++;
                   //System.out.println(enemyEtu.belongsTo.getUserName()+"etu #"+enemyEtu.getId()+"enemy added");
               }
               else {
                   studentCountj2++;
               }
            }while(iter.hasNext());
            if (studentCountj1 == 0) {
                zone.setControleZone(ControleZone.CONTROLEPARJOUEUR2,this.belongsTo);
            }
            else if(studentCountj2 == 0) {
                zone.setControleZone(ControleZone.CONTROLEPARJOUEUR1,this.belongsTo);
            }
        
        }   
        
    } 
    /**
     * permet aux etudiants de soigner un autre etudiant du meme joueur en fonction de ses caracteristiques
     * @param zone soigne seulement les etudiants dans cette zone
     */
    private void heal(Zone zone) {//only attack student inside zone
        ArrayList<Etudiant> allyTeam = new ArrayList<Etudiant>();
        Iterator<Etudiant> it = zone.getEtuDansZoneArrayList().iterator();
        Etudiant allyEtu;
        while(it.hasNext()) {//insert all enemies into a list
            allyEtu = it.next();
           if(allyEtu.belongsTo.equals(this.belongsTo)) {
               allyTeam.add(allyEtu);
           }
        }
        Iterator<Etudiant> it2 = allyTeam.iterator();
        Etudiant target = allyTeam.get(0);
        Etudiant etudiantTemp;
        while(it.hasNext()) {//scan for the student with the least HP
            etudiantTemp = it2.next();
            if (etudiantTemp.ects<=target.ects) {
                target = etudiantTemp;
            }
        }
        //healing...
        System.out.println("\033[0;90m"+this.belongsTo.getUserName()+" : "+this.id+" Tente de soigner"+"\033[0;0m");

        int healAmount;
        int x = (int) (Math.random()*100);
        double y = Math.random()*0.6;//0<y<0,6
        if (x>0 && x<(20 + 6*this.dexterite)) {
            healAmount = (int) (y*(10+target.constitution));
            if (healAmount < (30 + target.constitution)) {
                target.ects +=healAmount;
            }
            else {
                healAmount = 30 + this.constitution;
                target.ects += healAmount;
            }
            System.out.println("\033[0;90m"+"etu #"+target.getId()+" Got healed "+healAmount+" HP!"+"\033[0;0m");
        }
    } 
    /**
     * attaque ou soigne en fonction de la strategie de l'etudiant
     */

    public void agir() {
        if (this.strategie == strategie.OFFENSIVE) {
            attack((ZoneCombat)this.isInZone);
        }
        else if (this.strategie == strategie.DEFENSIVE) {
            heal(this.isInZone);
        }
        else if (this.strategie == strategie.RANDOM) {
            if(Math.random()>0.5) {
                
                attack((ZoneCombat)this.isInZone);
                
            }
            else {
                
                heal(this.isInZone);
                
            }
        }
        
    }
    
    
    
    
    
    
    //getters & setters
    //SETTERS
    /**
     * getter de {@link #type}
     * @return {@link #type}
     */
    public String getType(){return this.type;}
    /**
     * getter de {@link #ects}
     * @return {@link #ects}
     */
    public int getEcts(){return this.ects;}
    /**
     * getter de {@link #force}
     * @return {@link #force}
     */
    public int getForce(){return this.force;}
    /**
     * getter de {@link #dexterite}
     * @return {@link #dexterite}
     */
    public int getDexterite(){return this.dexterite;}
    /**
     * getter de {@link #resistance}
     * @return {@link #resistance}
     */
    public int getResistance(){return this.resistance;}
    /**
     * getter de {@link #constitution}
     * @return {@link #constitution}
     */
    public int getConstitution(){return this.constitution;}
    /**
     * getter de {@link #initiative}
     * @return {@link #initiative}
     */
    public int getInitiative(){return this.initiative;}
    /**
     * getter de {@link #id}
     * @return {@link #id}
     */
    public int getId() {return id;}
    /**
     * getter de {@link #strategie}
     * @return {@link #strategie}
     */
    public enumStrategie getStrategie() {return strategie;}
    /**
     * retourne le {@link #toString()} de {@link #strategie} 
     * @return le toString de {@link #strategie}
     */
    public String getStrategieString() {return strategie.toString();}
    /**
     * getter de {@link #belongsTo}
     * @return {@link #belongsTo}
     */
    public Joueur getBelongsTo() {return belongsTo;}
    /**
     * getter de {@link #isInZone}
     * @return {@link #isInZone}
     */
    public Zone getIsInZone() {return isInZone;}
    
    //SETTTERS 
    /**
     * setter de {@link #isInZone}
     * @param isInZone valeur qui remplacera la valeur actuelle
     */
    public void setIsInZone(Zone isInZone) {this.isInZone = isInZone;}
    /**
     * setter de {@link #force}
     * @param newForce valeur qui remplacera la valeur actuelle
     */
    public void setForce(int newForce){this.force=newForce;}
    /**
     * setter de {@link #dexterite}
     * @param newDexterite valeur qui remplacera la valeur actuelle
     */
    public void setDexterite(int newDexterite){this.dexterite= newDexterite;}
    /**
     * setter de {@link #resistance}
     * @param newResistance valeur qui remplacera la valeur actuelle
     */
    public void setResistance(int newResistance){this.resistance= newResistance;}
    /**
     * setter de {@link #constitution}
     * @param newConstisution valeur qui remplacera la valeur actuelle
     */
    public void setConstitution(int newConstisution){this.constitution= newConstisution;}
    /**
     * setter de {@link #initiative}
     * @param newInitiative valeur qui remplacera la valeur actuelle
     */
    public void setInitiative(int newInitiative){this.initiative= newInitiative;}
    /**
     * setter de {@link #id}
     * @param id valeur qui remplacera la valeur actuelle
     */
    public void setId(int id) {this.id = id;}
    
    /**
     * setter de {@link #strategie} a partir d'un String, vers une enumeration
     * @param strategie String qui sera convertie en enumeration de {@link enumStrategie} qui remplacera la valeur actuelle
     */

    public void setStrategie(String strategie) {
        try{
            this.strategie = enumStrategie.valueOf(strategie.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            System.out.println("Veuillez entrer une strategie valide");
        }
    }
    public int getStrategieIndex() {
        switch(strategie.toString()) {
            case "OFFENSIVE":
              return 0;
            case "DEFENSIVE":
                return 1;
            case "RANDOM":
                return 2;
            default:
                return -1;
          }

    }
}
