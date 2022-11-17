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
    
    public static Partie getInstance() { //--> méthode qui va appeler le constructeur si besoin
        
        //create object if it's not already created
        if(partieObject == null) {
            partieObject = new Partie();
        }
        
        //returns the singleton
        return partieObject;
    }
    
    // Regarde si l'objet Partie a deja un instance 
    public void getConnection() {
        System.out.println("You now have a Partie going");
    }
    
    //Ajoute 1 joueur � la partie
    public void addPlayer(Joueur joueur){
        listJ.add(joueur);
    }
    public void repartitionPoints(Joueur j) {
        System.out.println("Vous allez pouvoir attribuer vos points � vos �tudiants :) ");        
        
        String etapeSuivante = "N";
        while (!"Y".equals(etapeSuivante)){
            
            System.out.print("Choisisez votre �tudiant" );
            int index = getUserIndex("Enter le num�ro de l'�tudiant choisit",j.getStudentList().size()-1);        
            Etudiant etuTest = j.getStudent(index);
            String choisirAutreEtu ="N";
            while (!"Y".equals(choisirAutreEtu)){
                
                String Characteristics = getUserInput("Enter la caract�ristique a modifi�");        
                int pointsAttribuee = getUserInputInt("Enter le nombre de points attribue");
                
                j.modifyCharacteristics(etuTest,Characteristics,pointsAttribuee); 
                j.setPoints(pointsAttribuee); // avoir un retour pour modifyCharacteristics pour savoir si la modif � eu lieu ou non
                
                choisirAutreEtu = getUserInput("Voulez vous passez � un autre �tudiant ? Y/N");
            }
            // TODO regarder si l'utilisateur entre une caract�ristique valable avant de continuer 
            
            etapeSuivante = getUserInput("Voulez vous passez � l'�tape suivante ? Y/N");// TODO methode qui ignore si l'entr� n'est pas = Y ou =N
        }
        /*     
        System.out.println(etuTest);
        System.out.println(j);*/
        
    }
    
    //Methode pour Lire les inputs
    public static String getUserInput(String message) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println(message);
        
        String userInput = myObj.nextLine();  // Read user input
        return userInput;  // Output user input
    }
    
    public static int getUserInputInt(String message) {        
        while(1==1) { // Attention boucle infini
            String input = getUserInput(message);
            try 
            { 
                int num = Integer.parseInt(input); 
                
                return num;
            }  
            catch (NumberFormatException e)  
            { 
                System.out.println(input + " n'est pas un numbre"); 
            }   
        }        
        
    }
    public static int getUserIndex(String message, int size) {
        int num = -5;
        System.out.println("la liste � pour taille" + size );   
        while(num>size|| num<0) {            
            num = getUserInputInt(message);            
        }             
        return num;
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
        
        //Création de la partie
        Partie partie;
        partie = Partie.getInstance();
        partie.getConnection();// ne fonctionne que après un getInstance 
        
        // cr�ation des joueurs
        
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
        
        // test avec l'arm�e d'un joueur
        j2.createStudentList();
        //j2.displayAllStudent();
        
        
        
       /*R�partition des points 
        * �a va devenir une m�thode de Partie 
        */
        
        /*
         *  getUserIndex return l'index d'un �l�ment d'une liste donn�e
         *  demande � l'utilisateur jusqu'� avoir un index valable 
         */      
        
        partie.repartitionPoints(j2);
        
        j2.displayAllStudent();
        
        
        
        
    }
}
