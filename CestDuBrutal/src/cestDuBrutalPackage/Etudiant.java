package cestDuBrutalPackage;

public class Etudiant {
    //eclipse
	private String type;
    private int ects=30;
    private int force;
    private int dexterite;
    private int resistance;
    private int constitution;
    private int initiative;
    /*private Strategie strategie;*/
//commentaire test
    
    // constructeur
    public Etudiant(String type, int ects, int force, int dexterite, int resistance,
                   int constitution, int initiative /* Strategie strategie*/) {
        this.type = type;
        this.ects=ects;
        this.force =force;
        this.dexterite=dexterite;
        this.resistance=resistance;
        this.constitution=constitution;
        this.initiative=initiative;
        //this.strategie=strategie;
    }

    public void displayCaracteristics(){
        System.out.println(getEcts()+getForce()+getDexterite()+getResistance()+getConstitution()+getInitiative());
    }

    //getter
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
