package cestDuBrutalPackage;
import java.util.*;

public class Partie {
    private static Partie partieObject;
    
    private int etape;
    private String treve;
    private boolean finDePartie;    
    private ArrayList<Joueur> listJ;
    
        

    private Partie(){ // constructeur en Private car singleton Et pas en void :)
        this.etape=0;
        this.finDePartie= false;
        this.listJ = new ArrayList<Joueur>();    
        this.treve=null;
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
            int id = getUserInputInt("Entez le numero d'un etudiant");     
            for (ListIterator<Etudiant> it = l.listIterator(); it.hasNext();) {
                 Etudiant s = it.next();
                 if(s.getId()==id) return j.getStudent(it.previousIndex());            
            }
            throw new StudentNotFoundInList();          
    }
    
    public Zone selectZone(String id)throws ZoneNotFoundInList{
        
        ArrayList<ZoneCombat>  l= Zone.getZoneList();
         
        for (ListIterator<ZoneCombat> it = l.listIterator(); it.hasNext();) {
             Zone z = it.next();
             if(z.getZoneName().toLowerCase().equals(id.toLowerCase())) {
                 return Zone.getZone(it.previousIndex());  //fails for some reason          
             }
        }   
        throw new ZoneNotFoundInList();          
    }
    
    
    public void repartitionPoints(Joueur j) {
        System.out.println("Vous allez pouvoir attribuer vos points a vos etudiants. ");        
        int choix=-1;
        while (choix!=4){
            try {
                Etudiant etuTest= selectStudent(j);         
                String choisirAutreEtu ="N";
                choix=-1;
                while (choix!=3 && choix!=4){
                    
                    String Characteristics = getUserInput("Enter la caracteristique a modifier");        
                    int pointsAttribuee = getUserInputInt("Enter le nombre de points a attribuer");
                    
                    int retour =  j.modifyCharacteristics(etuTest,Characteristics,pointsAttribuee); 
                    if (retour==1) j.updatePoints(pointsAttribuee); // avoir un retour pour modifyCharacteristics pour savoir si la modif ï¿½ eu lieu ou non
                    
                    choix=5;
                    while(choix==5 ) {
                        choix = getUserChoix("Pour continuer a modifier l'etudiant ? 1 Changer la strategie de l'etudiant ? 2 Passer a l'etudiant suivante ? 3  A l'etape suivante ? 4 Affichier tous les etudiants? 5 ",5);
                        if (choix==5) {j.displayAllStudent();}
                        if (choix==2) {
                            try {
                                choix=5;
                                String Strategie = getUserInput("Enter la Strategie");
                                etuTest.setStrategie(Strategie);
                            }catch(IllegalArgumentException e) {
                                System.out.println("La Strategie entre n'est pas valable, (OFFENSIVE,DEFENSIVE,RANDOM;) ");
                            }                        
                        }
                    }
                    
                }
                // TODO regarder si l'utilisateur entre une caracteristique valable avant de continuer 
                System.out.println("Il reste "+j.getPoints()+" points");
               
                
            }catch(StudentNotFoundInList e) {
                System.out.print(e.getMessage());
            }
        }        
    }
    
 // Mise en reserve
    public void putInReserve(Joueur j) {
        System.out.print(j.getUserName()+": Selectioner les etudiants a mettre dans la reserve \n");
        while(j.getReserve().size()<5) {
               
            try {
                Etudiant etu = selectStudent(j);
                j.putInReserve(etu);
            }catch(StudentNotFoundInList e) {
                System.out.print(e.getMessage());
            }
        }
    }
    
   //Mise en zones
    public void affecterEtudiantZone(Joueur j) {
        while (j.getStudentList().size()!=0 || Zone.allZoneNotEmpty()==0) {
            boolean entryIsntValid = true;
            while(entryIsntValid) {
                try {
                    Etudiant studentToMove = new Etudiant();
                    Zone fromZone = new Zone ("zone vide");
                    System.out.println("Deplacer un etudiant de:");
                    Zone.displayAllZones();
                    System.out.println("Le camion");
                    
                    //On prend un etudiant dans une zone ou dans le camion
                    String id = getUserInput("Choisissez une zone");
                    if(id.equalsIgnoreCase("Le camion")) {
                        
                        j.displayAllStudent();
                        studentToMove = selectStudent(j);                            
                        
                    }else  { // choix une zone  

                        fromZone = selectZone(id);//Choisit la zone                     
                        fromZone.getEtudiantDansZoneList(); //Shows a list of students inside the zone                    
                        studentToMove = fromZone.drawEtudiantDansZone(j);
                    } 
                    
                    // on choisie la zone de deploiement & on d�polie l'etu choisi
                    System.out.println("Vers");
                    String idToZone = getUserInput("Choisissez une zone");
                    Zone toZone = selectZone(idToZone);//pas grave, tant pis s'il décide de le deplacer mettre la meme zone mdrr                                
                    studentToMove.setIsInZone(toZone);
                    toZone.addEtudiantDansZone(studentToMove);
                    
                    
                    // on retire l'etu de la zone d'origine
                    if(id.equalsIgnoreCase("le camion")) {
                        j.removeStudentFromList(studentToMove);
                    }else {
                        fromZone.removeStudentFromZone(studentToMove);
                    }
                    entryIsntValid = false;
                }
                catch (ZoneNotFoundInList e){
                    System.out.println("Vous n'avez pas rentre une zone existante.");
                }
                catch (StudentNotFoundInList e) {
                    System.out.println("Cet etudiant n'est pas dans cette zone.");
                }
                
            }
        }
            Zone.displayAllZones();
            // affiche toutes les �tudiants par zones
            Zone.displayAllStudentInZones();
            System.out.println("la repartition dans les zones est fini");     //TODO    
    }

    
    public void melee() {
        
    }
    
    public synchronized void declencherTreve(String nomZone, String etatDeControle) throws InterruptedException {
        while(treve!=null) {// si la treve est en cour
            
            System.out.println(nomZone + "est en pose la treve est en cour");
            this.wait();
        }
        if(etatDeControle.equalsIgnoreCase("0")) {// si la zone est controlee
            System.out.println( "etat de la zone est "+etatDeControle);
            System.out.println("La treve est en cours car cette zone " + Thread.currentThread().getName() + " a fini son combat\n\n");
            treve=etatDeControle;//
            
            System.out.println("On appel la treve");
            treve(); // --------------------------------ajout de la tr�ve
        }
        
            
    }
    
    public void treve() {
        Zone.initialiserZone(); 
        System.out.println("C'est la tr�ve ");
        treve=null;
        
        String input = "n";
        while (!input.equalsIgnoreCase("y")) {
            System.out.println("On fait des trucs de la tr�ve");
            
            input= getUserInput("Voulez vous finir la tr�ve");
            if (input=="y") {
                System.out.println("Fin de la tr�ve  on notify tous le monde !!");
                notifyAll(); // on reprend le combat
            }
        }
    }
    public static void autoAffecterEtudiantZone(Joueur j) {
        System.out.println("========AutoAffect:"+j.getUserName()+"=======");
        int i = 0;
        while (j.getStudentList().size()!=1) {
            i++;
            i = i%Zone.getZoneList().size();
            Etudiant studentToMove = j.getStudentList().get(1);//ite.next();
            ZoneCombat toZone = Zone.getZoneList().get(i);
            toZone.getEtuDansZoneArrayList().add(studentToMove);
            studentToMove.setIsInZone(toZone);
            j.getStudentList().remove(studentToMove);
            System.out.println("AutoAffect vers:" + studentToMove.getIsInZone().getZoneName());
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
    public static int getUserChoix(String message, int max) {
        int num = -5;
        System.out.println("choisissez un nombre entre 1 et " + max );   
        while(num>max|| num<0) {            
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
        partie.getConnection();// ne fonctionne que apres un getInstance 
        
        // crï¿½ation des joueurs
        
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
               
        partie.addPlayer(j1);
        partie.addPlayer(j2);
        
        j1.setUserName("Gwen");        
        //methode pour mettre un nom de joueur      
        j2.setUserName("Xuan");       
        System.out.println("Le joueur 1 s'appelle " +j1.getUserName());    
        System.out.println("Le joueur 2 s'appelle " +j2.getUserName());
        //  l'armée d'un joueur
        j1.createStudentList();
        j2.createStudentList();

        System.out.println("========REPARTITION DES POINTS=======");
        //partie.repartitionPoints(j2);    
        //j1.displayAllStudent();
        //j2.displayAllStudent();
        
        
       
        /*
        * Mettre une valeur max à la reserve
        * whilde dans la methode jusque la reserve soit pleine 
        * test pour voir si l'étudiant est enleve de la liste des étudiant du joueur ( c'est bien l'objetif)
        */
        System.out.println("========MISE EN RESERVE=======");
        /*
        partie.putInReserve(j2);
        partie.putInReserve(j1); 
        
        j2.displayReserveStudent();
        
        
        //repartition des etudiants dans les zones
       //initier les zones
        
        */      
        
        System.out.println("========DISTRIBUTION DES ETUDIANTS=======");
        
        Zone.setZones();
        /*
        partie.affecterEtudiantZone(j2);    //TODO affecter depuis la réserve vers les zones, sachant que la reserve n'est pas dans la liste de zones 
        */
        j1.displayAllStudent();
        autoAffecterEtudiantZone(j1);
        autoAffecterEtudiantZone(j2);
        //Zone.displayAllStudentInZones();
       
        
        Zone.melee();
        
        
        
        
    }
}
