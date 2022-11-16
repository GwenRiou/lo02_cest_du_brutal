package cestDuBrutalPackage;
import java.util.*;

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
        
        // cr�ation des joueurs
        
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        
        j1.setUserName("Xuan");
        j2.setUserName("Odette"); 
        
        //lire un input de l'utilisateur
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");

        j2.setUserName(myObj.nextLine());   // Read user input
        System.out.println("Username is: " + j2.getUserName());  // Output user input
    }
}
