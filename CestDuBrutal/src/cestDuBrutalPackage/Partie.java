package cestDuBrutalPackage;
import java.util.*;

public class Partie {
    private static Partie partieObject;
    
    private int etape;
    private boolean finDePartie;    
    private ArrayList<Joueur> listJ;
    
    
    private Partie(){ // constructeur en Private car singleton Et pas en void :)
        this.etape=0;
        this.finDePartie= false;
        this.listJ = new ArrayList<Joueur>();        
    }
    
    public static Partie getInstance() { //--> mÃ©thode qui va appeler le constructeur si besoin
        
        //create object if it's not already created
        if(partieObject == null) {
            partieObject = new Partie();
        }
        
        //returns the singleton
        return partieObject;
    }
    
    // Regarde si l'objet Partie a Ã©tÃ© crÃ©e
    public void getConnection() {
        System.out.println("You now have a Partie going");
    }
    
    //Ajoute 1 joueur à la partie
    public void addPlayer(Joueur joueur){
        listJ.add(joueur);
    }
    
    
    public static String getUserInput(String message) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println(message);
        
        String userInput = myObj.nextLine();  // Read user input
        return userInput;  // Output user input
    }
    
    //setter & getter
    public void setEtape(int etape) {
        this.etape=etape;
    }
    
    public ArrayList<Joueur> getListJ() {
        return listJ;
    }

    public void setListJ(ArrayList<Joueur> listJ) {
        this.listJ = listJ;
    }

    public int getEtape() {
        return this.etape;
    }
    
    //THE MAIN
    public static void main(String[] args) {
        
        //CrÃ©ation de la partie
        Partie partie;
        partie = Partie.getInstance();
        partie.getConnection();// ne fonctionne que aprÃ¨s un getInstance 
        
        // création des joueurs
        
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        
        j1.setUserName("Xuan"); 
        
        partie.addPlayer(j1);
        partie.addPlayer(j2);
        
        System.out.println("Le joueur 1 s'appelle " +partie.getListJ());
        //methode pour mettre un nom de joueur
        
        /*
         j2.setUserName(getUserInput("Enter username"));       
        System.out.println("Le joueur 1 s'appelle " +j1.getUserName());      
        System.out.println("Le joueur 2 s'appelle " +j2.getUserName());
        */
        
        // test avec l'armée d'un joueur
        j2.createStudentList();
        j2.displayAllStudent();
        
        
        
       /*Répartition des points 
        * ça va devenir une méthode de Partie 
        */
        //un joueur choisit un étudiant selon sont index/numero
        
        //TODO faire une boucle tant que getUserInput n'est pas un integer
        int number = Integer.parseInt(getUserInput("Enter le numéro de l'étudiant choisit"));
        
        Etudiant etuTest = j2.getStudent(number);
        
        String Characteristics = getUserInput("Enter la caractéristique a modifié");
        int pointsAttribuee = Integer.parseInt(getUserInput("Enter le nombre de points attribue"));
        j2.modifyCharacteristics(etuTest,Characteristics,pointsAttribuee); 
        j2.setPoints(pointsAttribuee);
               
        System.out.println(etuTest);
        System.out.println(j2);
        
        //j2.displayAllStudent();
        
        
        
        
    }
}
