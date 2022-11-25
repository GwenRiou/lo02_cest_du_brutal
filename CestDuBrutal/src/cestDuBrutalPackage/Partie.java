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
    
    // Regarde si l'objet Partie a deja un instance 
    public void getConnection() {
        System.out.println("You now have a Partie running");
    }
    
    //Ajoute 1 joueur ï¿½ la partie
    public void addPlayer(Joueur joueur){
        listJ.add(joueur);
    }
    
    // Choit un étudiant TODO à mettre dans la class joueur ?
    public Etudiant selectStudent(Joueur j)throws StudentNotFoundInList{
        
            ArrayList<Etudiant>  l= j.getStudentList();
            System.out.print("Choisisez votre étudiant" );        
            int id = getUserInputInt("Enter le numero de l'etudiant choisit");     
            for (ListIterator<Etudiant> it = l.listIterator(); it.hasNext();) {
                 Etudiant s = it.next();
                 if(s.getId()==id) return j.getStudent(it.previousIndex());            
            }
            throw new StudentNotFoundInList();          
    }
    
    public void repartitionPoints(Joueur j) {
        System.out.println("Vous allez pouvoir attribuer vos points a vos etudiants :) ");        
        
        String etapeSuivante = "N";
        while (!"Y".equals(etapeSuivante)){
            try {
                Etudiant etuTest= selectStudent(j);         
                String choisirAutreEtu ="N";
                while (!"Y".equals(choisirAutreEtu)){
                    
                    String Characteristics = getUserInput("Enter la caracteristique a modifier");        
                    int pointsAttribuee = getUserInputInt("Enter le nombre de points Ã  attribuer");
                    
                   int retour =  j.modifyCharacteristics(etuTest,Characteristics,pointsAttribuee); 
                    if (retour==1) j.updatePoints(pointsAttribuee); // avoir un retour pour modifyCharacteristics pour savoir si la modif ï¿½ eu lieu ou non
                    
                    choisirAutreEtu = getUserInput("Voulez vous passer a un autre etudiant ? Y/N").toUpperCase();
                }
                // TODO regarder si l'utilisateur entre une caracteristique valable avant de continuer 
                System.out.println("Il reste "+j.getPoints()+" points");
                etapeSuivante = getUserInput("Voulez vous passer a l'etape suivante ? Y/N").toUpperCase();// TODO methode qui ignore si l'entrï¿½ n'est pas = Y ou =N
                
            }catch(StudentNotFoundInList e) {
                System.out.print(e.getMessage());
            }
        }        
    }
    
 // Mise en reserve
    public void putInReserve(Joueur j) {
        
        while(j.getReserve().size()<5) {
               
            try {
                Etudiant etu = selectStudent(j);
                j.putInReserve(etu);
            }catch(StudentNotFoundInList e) {
                System.out.print(e.getMessage());
            }
        }
    }
    
   //===============================================================================================
   //Mise en zones
    public void affecterEtudiantsZone(Joueur j) {
        while (j.getStudentList().size()!=0) {
            while() {
                String userInput = getUserInput("Entrez l'étudiant à affecter, entrez 'afficher' pour afficher la liste d'Etudiants");
                try {
                int studentID = Integer.parseInt(userInput); 
                }
                catch (NumberFormatException e){
                    if (userInput.equals("afficher")) {
                        j.displayAllStudent();
                    }
                }
            }
            Zone.displayAllZones();
            getUserInput("");     //TODO    
        }
    }
    
    //Methodes pour Lire les inputs
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
                System.out.println("Erreur: "+ input + " n'est pas un nombre"); 
            }   
        }        
        
    }
    public static int getUserIndex(String message, int size) {
        int num = -5;
        System.out.println("choisissez un nombre entre 0 et " + size );   
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
        
        //CrÃ©ation de la partie
        Partie partie;
        partie = Partie.getInstance();
        partie.getConnection();// ne fonctionne que aprÃ¨s un getInstance 
        
        // crï¿½ation des joueurs
        
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
               
        partie.addPlayer(j1);
        partie.addPlayer(j2);
        
        j1.setUserName("Gwen lol");        
        //methode pour mettre un nom de joueur      
         j2.setUserName(getUserInput("Enter username"));       
        System.out.println("Le joueur 1 s'appelle " +j1.getUserName());    
        
        
        System.out.println("Le joueur 2 s'appelle " +j2.getUserName());
        
        

        // test avec l'armée d'un joueur
        j2.createStudentList(2);

        j2.displayAllStudent();
        
        
        
       /*Repartition des points 
        */        

        
        //partie.repartitionPoints(j2);        
        //j2.displayAllStudent();
        
        //
        System.out.print("Selectioner les étudiants à mettre dans la reserve \n");
        /*
         * Mettre une valeur max à la reserve
         * whilde dans la methode jusque la reserve soit pleine 
         * test pour voir si l'étudiant est enleve de la liste des étudiant du joueur ( c'est bien l'objetif)
         */
        //partie.putInReserve(j2);
       // j2.putInReserve(partie.selectStudent(j2)); // c'est moche que la gestion des input soit dans Partie
        
        //j2.displayReserveStudent();
        
        
        //repartition des etudiants dans les zones
       //initier les zones
        
        Zone.setZones();
        partie.affecterEtudiantsZone(j2);
        
        
        
        
        
    }
}
