package cestDuBrutalPackage;

public class Partie {
    private static Partie partieObject;
    private int etape;
    private boolean finDePartie;
    
    
    private void Partie(){ // constructeur en Private car singleton
        this.etape=0;
        this.finDePartie= false;
        
    }
    
    public static Partie getInstance() { //--> méthode qui va appeler le constructeur si besoin
        
        //create object if it's not already created
        if(partieObject == null) {
            partieObject = new Partie();
        }
        
        //returns the singleton
        return partieObject;
    }
    
    // Regarde si l'objet Partie a été crée
    public void getConnection() {
        System.out.println("You now have a Partie going");
    }
    
    //setter & getter
    public void setEtape(int etape) {
        this.etape=etape;
    }
    
    public int getEtape() {
        return this.etape;
    }
    
    //THE MAIN
    public static void main(String[] args) {
        
        //Création de la partie
        Partie partie;
        partie = Partie.getInstance();
        partie.getConnection();// ne fonctionne que après un getInstance 
        

    }
}
