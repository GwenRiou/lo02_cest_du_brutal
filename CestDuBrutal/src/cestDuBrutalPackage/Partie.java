package cestDuBrutalPackage;

public class Partie {
    private static Partie partieObject;
    private int etape;
    private boolean finDePartie;
    
    
    private void Parite(){ // constructeur en Private car singleton
        this.etape=0;
        this.finDePartie= false;
        
    }
    
    public static Partie getInstance() { //--> m�thode qui va appeler le constructeur si besoin
        
        //create objet if it's not already created
        if(partieObject == null) {
            partieObject = new Partie();
        }
        
        //returns the singleton
        return partieObject;
    }
    
    // Regarde si l'objet Partie a �t� cr�e
    public void getConnection() {
        System.out.println("You have now a Partie going");
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
        
        //Cr�ation de la partie
        Partie partie;
        partie = Partie.getInstance();
        partie.getConnection();// ne fonctionne que apr�s un getInstance 
        
        // cr�ation des joueurs
        
        Joueur j1 = new Joueur();
        

    }
}
