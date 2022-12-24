package cestDuBrutalPackage;
import java.util.*;

/**
 * Classe partie
 * Contient le main principal
 * @author boone, riou
 *
 */
public class Partie {
    /**
     * est utilisé pour appeler l'ojet Partie, utile pour récupérer la liste des joueurs
     */
    private static Partie partieObject;
    /**
     * Permet de savoir si la treve est declenchee
     */
    private String treve;
    /**
     * Permet de savoir si la fin de la partie est déclenchée
     */
    private boolean finDePartie;   
    /**
     * Une liste qui contient les joueurs. Le jeu est optimisé pour l'utilisation de seulement deux joueurs. il est facilement possible d'étendre l'utilisation de plusieurs joueurs.
     */
    private static ArrayList<Joueur> listJ;
    
        
    /**
     * Instancie l'objet partie.
     */
    private Partie(){ // constructeur en Private car singleton Et pas en void :)
        this.finDePartie= false;
        this.listJ = new ArrayList<Joueur>();    
        this.treve=null;
    }
    
    /**
     * methode qui va appeler le constructeur lorsque l'on a besoin
     * @return retourne l'instance de la partie active
     */
    public static Partie getInstance() {
        
        //create object if it's not already created
        if(partieObject == null) {
            partieObject = new Partie();
        }
        
        //returns the singleton
        return partieObject;
    }
    
    /**
     * Regarde si l'objet Partie a deja ete instanciee, elle n'est plus utile.
     */
    public void getConnection() {
        //System.out.println("You now have a Partie running");
    }
    
    /**
     * instancie un nouveau joueur et l'ajoute a la liste des joueurs
     */
    public void addPlayer(Joueur joueur){
        System.out.println("--Creation dun nouveau joueur--");
        listJ.add(joueur);
        joueur.identify();
    }
    
    /*
     * Methode qui instancie les joueurs automatiquement, il les ajoute a la liste des joueurs
     */
    public void autoAddPlayers(Joueur j1,Joueur j2){
        System.out.println("--AutoAddPlayersV2.0 IS INITIALIZING, STAND BACK!!!--");
        listJ.add(j1);
        j1.identify("Xuan",Programme.A2I);
        listJ.add(j2);
        j2.identify("Gwen",Programme.A2I);
        
    }
    
    /**
     * Permet au joueur de choisir un etudiant parmi une liste en fonction de leur ID
     * @param j joueur pour concerner seulement les etudiants d'un joueur
     * @return retourne le joueur choisi
     * @throws StudentNotFoundInList erreur lancee lorsqu'aucun etudiant n'est trouvé dans la liste
     */
    public Etudiant selectStudent(Joueur j)throws StudentNotFoundInList{
        
            ArrayList<Etudiant>  l= j.getStudentList();       
            int id = getUserInputInt("Entrez le numero d'un etudiant");     
            for (ListIterator<Etudiant> it = l.listIterator(); it.hasNext();) {
                 Etudiant s = it.next();
                 if(s.getId()==id) {
                     return j.getStudent(it.previousIndex()); 
                 }
            }
            throw new StudentNotFoundInList();          
    }
    /**
     * Permet au joueur de choisir une zone parmi une liste en fonction de leur nom
     * @param id ID de la zone concernée (nom de la zone)
     * @return retourne la zone choisie
     * @throws ZoneNotFoundInList erreur lancee lorsqu'aucune zone n'est trouvé dans une liste
     */
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
                    if (retour==1) j.updatePoints(pointsAttribuee); // avoir un retour pour modifyCharacteristics pour savoir si la modif Ã¯Â¿Â½ eu lieu ou non
                    
                    choix=5;
                    while(choix==5 ) {
                        choix = getUserChoix(""
                                + "1. Continuer a modifier l'etudiant\n"
                                + "2. Changer la strategie de l'etudiant\n"
                                + "3. Passer a l'etudiant suivant\n"
                                + "4. Passer a l'etape suivante\n"
                                + "5. Affichier tous les etudiants",5);
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
        System.out.print("\n"+"\033[0;1m"+j.getUserName()+": Selectionnez les 5 etudiants a mettre dans la reserve \n"+"\033[0;0m");
        while(j.getReserveArrayList().size()<5) {
               
            try {
                Etudiant etu = selectStudent(j);
                j.putInReserve(etu);
                System.out.println("\033[0;90m"+"l'etudiant n#"+etu.getId()+" a bien ete deplace vers la reserve\n"+"\033[0;0m");
            }catch(StudentNotFoundInList e) {
                System.out.print(e.getMessage());
            }
        }
    }
    
   //Mise en zones
    public void affecterEtudiantZone(Joueur j) {
        boolean condition=false;
        if(j.getId()==1) condition=Zone.allZoneNotEmpty();// condidtion pour que le j1 a un etu dasn chaque zone 
        else condition=Zone.allZoneWithTwoStudent(j);// condition pour que le j2 a un etu dans chaque zone 
        while (j.getStudentList().size()!=0 || !condition) {
            boolean entryIsntValid = true;
            while(entryIsntValid) {
                try {
                    Etudiant studentToMove = new Etudiant();
                    Zone fromZone = new Zone ("zone vide");
                    System.out.println("Deplacer un etudiant de:");
                    Zone.displayAllZones();
                    System.out.println("- Le camion");
                    
                    //On prend un etudiant dans une zone ou dans le camion
                    String id = getUserInput("Choisissez une zone");
                    if(id.equalsIgnoreCase("Le camion")) {
                        
                        j.displayAllStudent();
                        studentToMove = selectStudent(j);                            
                        
                    }else  { // choix une zone  

                        fromZone = selectZone(id);//Choisit la zone                     
                        fromZone.displayEtudiantDansZoneList(j); //Shows a list of students inside the zone                    
                        studentToMove = fromZone.drawEtudiantDansZone(j);
                    } 
                    
                    // on choisie la zone de deploiement & on dï¿½polie l'etu choisi

                    
                    System.out.println("Vers");
                    String idToZone = getUserInput("Choisissez une zone");
                    Zone toZone = selectZone(idToZone);                              
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
            if(j.getId()==1) condition=Zone.allZoneNotEmpty();// condidtion pour que le j1 a un etu dasn chaque zone 
            else condition=Zone.allZoneWithTwoStudent(j);// condition pour que le j2 a un etu dans chaque zone 
        }
            Zone.displayAllZones();
            // affiche toutes les ï¿½tudiants par zones
            Zone.displayAllStudentInZones();
            System.out.println("la repartition dans les zones est fini");     //TODO    
    }
    public void affecterEtudiantReserveTreve(Joueur j) {
        if(j.getReserveArrayList().size() != 0) {
            boolean stayInLoop = true;
            while (stayInLoop) {
                boolean entryIsntValid = true;
                while(entryIsntValid) {
                    try {
                        Etudiant studentToMove = new Etudiant();
                        Zone reserve = j.getReserve();
                        System.out.println("Deplacer un etudiant depuis la reserve:");
                       //====================================================================================================
                        j.displayReserveStudent(); //Shows a list of students inside the reserve 
                        
                        //On prend un etudiant dans une zone 
                        studentToMove = reserve.drawEtudiantDansZone(j);
                        
                        // on choisie la zone de deploiement & on d�polie l'etu choisi
                        System.out.println("Vers");
                        String idToZone = "";
                        Zone.displayActiveZones(j);  
                        ZoneCombat toZone = new ZoneCombat("");
                        boolean selectedZoneIsActive = false;
                        while(!selectedZoneIsActive) {
                            idToZone = getUserInput("Choisissez une zone");
                            toZone = (ZoneCombat) selectZone(idToZone);
                            //Choisit la zone, 
                            if(toZone.getControlePar()==null) {
                                selectedZoneIsActive = true;
                            }
                        }
                        studentToMove.setIsInZone(toZone);
                        toZone.addEtudiantDansZone(studentToMove);
                        reserve.removeStudentFromZone(studentToMove); // on retire l'etu de la zone d'origine
                        entryIsntValid = false;
                        String input = Partie.getUserInput("Voulez-vous continuer a affecter des etudiants? (y/n)");
                        if (input.equalsIgnoreCase("n")||input.equalsIgnoreCase("non")) {
                            stayInLoop = false;
                        }
                    }
                    catch (ZoneNotFoundInList e){
                        System.out.println("Vous n'avez pas rentre une zone existante.");
                    }
                    catch (StudentNotFoundInList e) {
                        System.out.println("Cet etudiant n'est pas dans cette zone.");
                    }
                }
            }
        }
        else {
            System.out.println("La reserve est vide!");
        }
    }
    
    public void affecterEtudiantPendantTreve(Joueur j) {
        boolean stayInLoop = true;
        while (stayInLoop) {
            boolean entryIsntValid = true;
            while(entryIsntValid) {
                try {
                    Etudiant studentToMove = new Etudiant();
                    ZoneCombat fromZone = new ZoneCombat ("zone vide");
                    System.out.println("Deplacer un etudiant de:");
                   //====================================================================================================
                    Zone.displayControlledZones(j); 
                    
                    //On prend un etudiant dans une zone 
                    boolean selectedZoneBelongsToUser = false;
                    while(!selectedZoneBelongsToUser) {
                        String id = getUserInput("Choisissez une zone");//TODO ne peut que etre une zone controllee  
                        fromZone = (ZoneCombat) selectZone(id);//Choisit la zone,  
                        if(fromZone.getControlePar()!=null) {
                            if (fromZone.getControlePar().equals(j)) {
                                selectedZoneBelongsToUser = true;
                            } else System.out.println("Vous ne controllez pas cette zone.");
                        } else System.out.println("La zone n'est pas controllee");
                    }
                    if (!(fromZone.etuDansZone.size()<2)) {// il y a au moins une personne dans a la zone
                        fromZone.displayEtudiantDansZoneList(); //Shows a list of students inside the zone                    
                        studentToMove = fromZone.drawEtudiantDansZone(j);
                        
                        // on choisie la zone de deploiement & on d�polie l'etu choisi
                        System.out.println("Vers");
                        String idToZone = "";
                        Zone.displayActiveZones(j);
                        //pas grave, tant pis s'il décide de le deplacer mettre la meme zone mdrr                                
                        boolean selectedZoneIsActive = false;
                        while(!selectedZoneIsActive) {
                            idToZone = getUserInput("Choisissez une zone");
                            
                            if(fromZone.getControlePar()!=null) {
                                selectedZoneIsActive = true;
                            }
                        }
                        Zone toZone = selectZone(idToZone);//Choisit la zone, 
                        String input = Partie.getUserInput("Voulez-vous changer la strategie de cet etudiant? (y/n)");
                        if (input.equalsIgnoreCase("y")||input.equalsIgnoreCase("oui")) {
                            stayInLoop = false;
                            boolean invalidEntry = true;
                            while(invalidEntry) { 
                                System.out.println(input);
                                input = Partie.getUserInput("Changez la strategie de "+ studentToMove.getStrategieString()+" vers:\n"
                                            + "- Random\n"
                                            + "- Offensive\n"
                                            + "- Defensive");
                                studentToMove.setStrategie(input);
                                if((input.equalsIgnoreCase("random")||input.equalsIgnoreCase("Offensive"))||input.equalsIgnoreCase("defensive")) {
                                    invalidEntry = false;
                                }
                            }                    
                        }
                        studentToMove.setIsInZone(toZone);
                        toZone.addEtudiantDansZone(studentToMove);
                        fromZone.removeStudentFromZone(studentToMove); // on retire l'etu de la zone d'origine
                        entryIsntValid = false;
                        input = Partie.getUserInput("Voulez-vous continuer a affecter des etudiants? (y/n)");
                        if (input.equalsIgnoreCase("n")||input.equalsIgnoreCase("non")) {
                            stayInLoop = false;
                        }
                    } 
                    else {
                        System.out.println("Vous devez avoir au moins un etudiant dans une zone controlee");
                        stayInLoop = false;
                        entryIsntValid = false;
                    }
                }
                catch (ZoneNotFoundInList e){
                    System.out.println("Vous n'avez pas rentre une zone existante.");
                }
                catch (StudentNotFoundInList e) {
                    System.out.println("Cet etudiant n'est pas dans cette zone.");
                }
            }
        }  
    }
    public synchronized void declencherTreve(Joueur gagnantTreve, ZoneCombat zone, String etatDeControle) throws InterruptedException {
       
        while(treve!=null) {// si la treve est en cour
            
            System.out.println(zone.getZoneName() + "declenche la treve");
            this.wait();
        }
        if(etatDeControle.equalsIgnoreCase("0")) {// si la zone est controlee
            
            System.out.println("La treve est en cours car cette zone " + Thread.currentThread().getName() + " a fini son combat\n\n");
            treve=etatDeControle;//
       
            treve(gagnantTreve,zone); // --------------------------------ajout de la treve
        }       
    }
    
    public void treve(Joueur gagnantTreve, ZoneCombat zone) {
        
        this.finDePartie = Zone.FinDePartie();
        
        if(finDePartie==false) {
            treve=null; // premier trucs
            System.out.println("\033[0;1m"+"==TREVE: UNE ZONE A ETE CONTROLEE=="+"\033[0;0m");// on pourra l'enlevé
            int input = 0;
            while (!(input == 4)) {
                //trucs de la treve
                input = getUserChoix("\033[0;1m\033[096m"+gagnantTreve.getUserName()+"\033[0;0m\033[0;1m: Que voulez-vous faire? (entrez 1-4)\n"
                        + "\033[0;31m1.\033[0;1m Affecter des etudiants des zones controlees\n"
                        + "\033[0;31m2.\033[0;1m Affecter des reservistes sur des zones de combat\n"
                        + "\033[0;31m3.\033[0;1m Visualiser le nombre de points ECTS par zone de combat\n"
                        + "\033[0;31m4.\033[0;1m Continuer la bataille\033[0;0m",4);
                switch(input) {
                    case 1:
                        affecterEtudiantPendantTreve(gagnantTreve);
                        break;
                    case 2:
						affecterEtudiantReserveTreve(gagnantTreve);
                        break;
                    case 3:
                        Zone.displayECTSPerZone();
                        break;
                    case 4:
                        System.out.println("Retour au combat...");
                        Zone.initialiserZone();
                        notifyAll(); // on reprend le combat
                        break;
                }
            }
        }else {
            Zone.interrupteAll();
            System.out.println("-------------La Partie est finie----------------------");
            //System.exit(0);
        }
    }
    public static void autoAffecterEtudiantZone(Joueur j) {
        System.out.println("Distribution automatique des etudiants de:"+j.getUserName()+"est effectuee");
        int i = 0;
        while (j.getStudentList().size()!=1) {
            i++;
            i = i%Zone.getZoneList().size();
            Etudiant studentToMove = j.getStudentList().get(1);//ite.next();
            ZoneCombat toZone = Zone.getZoneList().get(i);
            toZone.getEtuDansZoneArrayList().add(studentToMove);
            studentToMove.setIsInZone(toZone);
            j.getStudentList().remove(studentToMove);
            //System.out.println("AutoAffect vers:" + studentToMove.getIsInZone().getZoneName());
        }
    }
    //Methodes pour Lire les inputs
    public static String getUserInput(String message) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println(message+"\033[0;91m");
        
        String userInput = myObj.nextLine();  // Read user input
        System.out.println("\033[0;0m");
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
    
    
    public ArrayList<Joueur> getListJ() {
        return listJ;
    }
    
    public static String getNamePlayer(int j) { return listJ.get(j-1).getUserName(); }

    public void setListJ(ArrayList<Joueur> listJ) {
        this.listJ = listJ;
    }

    
    //THE MAIN
    public static void main(String[] args) {
        System.out.println("\033[0;1m"+"note: Ce code utilise des couleurs format ANSI, utilisation d'Eclipse est reccomendee\n"
                + "=+=!!- C'EST DU BRUTAL V1.1 -!!=+="+"\033[0;0m");
        //Creation de la partie
        String debug = getUserInput("Mode automatique? (pour debug) y/n ");
        if (debug.equalsIgnoreCase("Y")) {
            System.out.println("\033[0;90m"+"Mode automatique actif\n"+"\033[0;0m");
        }
        else {
            System.out.println("\033[0;90m"+"Mode automatique inactif\n"+"\033[0;0m");
        }
        Partie partie;
        partie = Partie.getInstance();
        partie.getConnection();// ne fonctionne que apres un getInstance 
        Joueur j1 = new Joueur(1);
        Joueur j2 = new Joueur(2);
        partie.addPlayer(j1);
        partie.addPlayer(j2);//partie.autoAddPlayers(j1, j2);
        
        Zone.setZones();  
        
        System.out.println("Le joueur 1 s'appelle " +j1.getUserName());   
        System.out.println("Le joueur 2 s'appelle " +j2.getUserName());
        
        
        
        System.out.println("\033[0;1m"+"========REPARTITION DES POINTS======="+"\033[0;0m");
        if (debug.equalsIgnoreCase("Y")){
            System.out.println("> La repartition est automatique");
            j1.autoCreateStudentList();
            j2.autoCreateStudentList();
        }
        else {
            j1.createStudentList();
            j2.createStudentList();
            
            j1.displayAllStudent();
            partie.repartitionPoints(j1);  
            j2.displayAllStudent();
            partie.repartitionPoints(j2); 
        }
       
        System.out.println("\033[0;1m"+"========MISE EN RESERVE======="+"\033[0;0m");
        
        j1.displayAllStudent();
        partie.putInReserve(j1);
        j1.displayReserveStudent();
        System.out.println("\n");
        j2.displayAllStudent();
        partie.putInReserve(j2); 
        j2.displayReserveStudent();
        
        System.out.println("\033[0;1m"+"========DISTRIBUTION DES ETUDIANTS======="+"\033[0;0m");
        
        
        if (debug.equalsIgnoreCase("Y")) {
            autoAffecterEtudiantZone(j1);
            autoAffecterEtudiantZone(j2);
        }
        else {
          j1.displayAllStudent();
          partie.affecterEtudiantZone(j1);  
          j2.displayAllStudent();
          partie.affecterEtudiantZone(j2);  
        }
        
        Zone.displayAllStudentInZones();
       
        String empty = getUserInput("\n---"+"\033[0;1m"+"Appuyez sur entree pour proceder a la melee"+"\033[0;1m"+"---");
        Zone.melee();        
        System.out.println("exit(0)");
    }
}
