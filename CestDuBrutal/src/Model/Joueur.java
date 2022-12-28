package Model;
import java.util.*;

/*
 * Tester si le joueur 2 à un le même nom que le joueur 1 avant de
 *         l'autoriser le nom
 *         Implémentation:
 *         création d’un variable static qui compte le nombre de joueur
 *         setUsername en Private
 *         Si le joueur veut créer un nom : la méthode regardé : le numéro du
 *         joueur
 *         si c’est le joueur 1 on autorise tout --> appel de la fonction setter
 *         si c’est le joueur 2 --> regarde si le nom du joueur 1 à le même nom et
 *         si oui on demande un autre non si non on appel setter

 */
/**
 * Classe joueur, chaque joueur a ses etudiants, qui combattent dans les zones 
 *         @author rioug
 * 
 */
public class Joueur {
    private int id;
    private int points = 400;
    private Programme programme;
    private String userName;

    private ArrayList<Etudiant> studentList; // car on va chercher les étu par leur index
    private Reserve reserve = new Reserve();
    
    /**
     * constructeur du joueur
     * @param id Permet de distinguer joueur 1 et 2 lors du debug
     */

    public Joueur(int id) {
        this.id=id;
        this.points = points;
        this.userName = ""; 
        this.studentList = new ArrayList <Etudiant>();
        this.programme=Programme.ISI;
        this.createStudentList();     
    }
    /**
     * cree la liste d'etudiant (le camion) dans laquelle le joueur peut gerer, modifier et affecter ses etudiants
     */
    
    public void createStudentList() {
        
        // creer le maiter du gobit
        Etudiant etuMaitre = new Etudiant("Maitre",2,2,2,10,2,this);
        studentList.add(etuMaitre);
        

        //crer les soldats elites
        for(int i=0 ; i < 4; i++){
            Etudiant etuElite = new Etudiant("Elite",1,1,1,5,1,this);
            studentList.add(etuElite);            
        }
        
        //creer lest étudiants de basse==================disabled for debug======================
        
        for(int i=0 ; i < 15; i++){
            Etudiant etuNormal = new Etudiant("Base",0,0,0,0,0,this);
            studentList.add(etuNormal);            
        }
        

        setIdForArmy();
       
    }

    /**
     * creer la liste des etudiants, differe de {@link #createStudentList()} avec le fait qu'il cree automatiquement tous les etudiants sans avoir besoin de les modifier manuellement
     */
public void autoCreateStudentList() {
        
        // creer le maiter du gobit
        Etudiant etuMaitre = new Etudiant("Maitre",this);
        studentList.add(etuMaitre);
        
        //crer les soldats élites
        for(int i=0 ; i < 4; i++){
            Etudiant etuElite = new Etudiant("Elite",this);
            studentList.add(etuElite);            
        }
        
        //creer lest étudiants de basse==================disabled for debug======================
        
        for(int i=0 ; i < 15; i++){
            Etudiant etuNormal = new Etudiant("Base",this);
            studentList.add(etuNormal);            
        }
        

        setIdForArmy();
       
    }
    /**
     * parcourt la liste et set les ID de chaque étudiant
     * 
     */
    private void setIdForArmy() {

        for (ListIterator<Etudiant> it = studentList.listIterator(); it.hasNext();) {
             Etudiant s = it.next();
             s.setId(it.previousIndex()+1);            
        }
    }
    /**
     * affiche la liste de tous les etudiants dans le camion / l'inventaire de ce joueur avant l'affection aux zones
     */
    public void displayAllStudent() {
        System.out.println("Etudiants de: "+this.userName);
        ListIterator<Etudiant> iter = studentList.listIterator();
            
        while (iter.hasNext()) {
            System.out.println(iter.next());
            //System.out.println("L'etudiant n°" + iter.nextIndex() + " a " + iter.next());
        }
    }
    /**
     * Affiche tous les etudiants dans la reserve du joueur
     */
    public void displayReserveStudent() {
        System.out.println(this.userName + ": Les etudiants dans la reserves sont : ");
        ListIterator<Etudiant> iter = this.reserve.getListeEtudiantsReserve().listIterator();
            
        while (iter.hasNext()) {
            System.out.println(iter.next());
            //System.out.println("L'etudiant n°" + iter.nextIndex() + " a " + iter.next());
        }
    }
    
    /**
     * return l'étudiant choisi
     * @param index index de l'etudiant donne par l'iterator
     * @return retourne l'etudiant choisi
     */

    public Etudiant getStudent(int index) {
        Etudiant etu = studentList.get(index);
        return etu;
    }
    

    public void modifyCharacteristicsGui(Etudiant etu,int newForce, int newDexterite, int newResistance,int newConstitution, int newInitiative,String strategy) {
    	int pointAttribuee = (newForce+newDexterite+ newResistance+newConstitution+ newInitiative-etu.getForce()-etu.getDexterite()-etu.getConstitution()-etu.getResistance()-etu.getInitiative());
    	if (pointAttribuee>this.points) {
            System.out.println("Vous n'avez pas assez de points pour cette modification");
    	}else {
    		if(newForce>=0 && newForce<=10 && newDexterite>=0 && newDexterite<=10 &&newResistance>=0 && newResistance<=10 && newConstitution>=0 && newConstitution<=30 && newInitiative>=0 && newInitiative<=10) { 
                etu.setForce(newForce);         
                etu.setDexterite(newDexterite);                               
                etu.setResistance(newResistance);                              
                etu.setConstitution(newConstitution);                              
                etu.setInitiative(newInitiative);    
                points-=pointAttribuee;
                etu.setStrategie(strategy);
            }
    	}
    }
    
  /**
     * permet de modifier les caracteristiques d'un etudiant choisi
     * @param etu Etudiant a modifier
     * @param car caracteristique a changer
     * @param pointsAttribuee points a attribuer a la caracteristique
     * @return 0 si erreur, 1 si ca a fonctionne
     */
    public int modifyCharacteristics(Etudiant etu,String car, int pointsAttribuee) {   
        // methode retourne 0 si on ne peut pas faire la modification
        if (pointsAttribuee>this.points) {
            System.out.println("Vous n'avez pas assez de points pour cette modification");
            return 0;
        }else {
            //Voir si on peut pas faire une méthode au lieu de présicé pour chaque caractéristique
            switch(car) { 
                case "Force","force":
                    int newForce = etu.getForce()+pointsAttribuee;
                    //regarder si la nouvelle force est bien <=10 et >=0
                    if(newForce>=0 && newForce<=10) { 
                        etu.setForce(newForce);  
                        return 1;
                    } else {
                        System.out.println("Chaque caracteristique doit etre compris entre 0 et 10");   
                        return 0;
                    }
                 // pas de break car on sort de la boucle avant 
                case "Dexterite","dexterite":
                    int newDexterite = etu.getDexterite()+pointsAttribuee;
                    if(newDexterite>=0 && newDexterite<=10) {                   
                        etu.setDexterite(newDexterite);
                        return 1;
                    }else  {
                        System.out.println("Chaque caracteristique doit etre compris entre 0 et 10");    
                        return 0;
                    }
                case "Resistance","resistance":
                    int newResistance = etu.getResistance()+pointsAttribuee;
                    if(newResistance>=0 && newResistance<=10) {                   
                        etu.setResistance(newResistance);
                        return 1;
                    }else    {
                        System.out.println("Chaque caracteristique doit etre compris entre 0 et 10");   
                        return 0;
                    }
                case "Constitution","consitution":
                    int newConstitution = etu.getConstitution()+pointsAttribuee;
                    if(newConstitution>=0 && newConstitution<=10) {                   
                        etu.setConstitution(newConstitution);
                        return 1;
                    }else {
                        System.out.println("Chaque caracteristique doit etre compris entre 0 et 10");    
                        return 0;
                    }
                case "Initiative","initiative":
                    int newInitiative =etu.getInitiative()+pointsAttribuee;
                    if(newInitiative>=0 && newInitiative<=10) {                   
                        etu.setInitiative(newInitiative);
                        return 1;
                    }else  {
                        System.out.println("Chaque caracteristique doit etre compris entre 0 et 10");   
                        return 0;
                    }
                 
                default:
                    System.out.println("La caractéristique entrée n'est pas définie, vérifiez l'ortographe");
                    return 0;
              }

        }
    }

    public int putInReserveMVC(int id) {
        if(reserve.getListeEtudiantsReserve().size()<5) {               
            try {
                System.out.print("On met un etu dans la reserve");
                Etudiant etu = selectStudentMVC(id);
                putInReserve(etu);
                return 1;
            }catch(StudentNotFoundInList e) {
                System.out.print(e.getMessage());
            }
        }
        return 0;
    }
    // Mise en reserve

    /**
     * Mise en reserve d'un etudiant
     * @param etu etudiant a mettre en reserve
     */
    public void putInReserve(Etudiant etu) {
        this.studentList.remove(etu);// Enleve l'etudiant de la liste 
        reserve.affecterReserve(etu);
    }

    /**
     * retire l'etudiant de la liste
     * @param etu etudiant a retirer
     */
    public void removeStudentFromList(Etudiant etu) {
        this.studentList.remove(etu);
    }
    /**
     * {@link #toString()} du joueur, affiche son programme et ses points a distribuer
     */
    public String toString() {
        StringBuffer sb = new StringBuffer ("Le Joueur ");
        sb.append(this.userName);
        sb.append(" appartient au Programme");  
    
        sb.append((String)this.getProgrammeString());
        sb.append(" et a  : ");
        sb.append(this.points);
        sb.append(" points ");
        
        return sb.toString();
        }
   
  
    public Etudiant selectStudentMVC(int id)throws StudentNotFoundInList{ 
        
        ArrayList<Etudiant>  l= studentList;          
        for (ListIterator<Etudiant> it = l.listIterator(); it.hasNext();) {
             Etudiant s = it.next();
             if(s.getId()==id) return getStudent(it.previousIndex());            
        }
        throw new StudentNotFoundInList();          
    }

    // --------------------------------Getter & Setter--------------------------------

    /**
     * getter de {@link #studentList}
     * @return {@link #studentList}
     */

    public ArrayList<Etudiant> getStudentList() {
        return studentList;
    }


    /**
     * getter de {@link #points}
     * @return {@link #points}
     */
    public int getPoints() {
        return points;
    }
    /**
     * setter de {@link #points}
     * @param points {@link #points}
     */
    public void setPoints(int points) {
        this.points = points;
    }
    /**
     * getter de {@link #id}
     * @return {@link #id}
     */

    public int getId() {
        return id;
    }


    /**
     * setter de {@link #id}#
     * @param id valeur qui va remplacer la valeur actuelle
     */

    public void setId(int id) {
        this.id = id;
    }
    //returns programme as an enum

    /**
     * getter de {@link #programme}
     * @return {@link #programme}
     */
    public Programme getProgramme() {
        return programme;
    }
    //returns programme but as a string

    /**
     * retourne l'enumeration du programme {@link Programme} sous forme de texte 
     * @return l'enumeration du programme {@link Programme} sous forme de texte
     */
    public String getProgrammeString() {return programme.toString();}
    /**
     * setter de {@link #programme}
     * @param programme {@link #programme}
     */
    public void setProgramme(String programme) {
        this.programme = Programme.valueOf(programme.toUpperCase());
    }
    
    /**
     * getter de {@link #userName}
     * @return {@link #userName}
     */

    public String getUserName() {
        return userName;
    }
    

    // Implémentation plus complexe possible cf doc idée // c'est fait, on utilise des pointeurs vers les Joueurs mtn

    /**
     * setter de {@link #userName}
     * @param userName {@link #userName}
     */
    public void setUserName(String userName) {//jai mis déprécié pour l'identification fonctionnelle  mais on peut tjrs utiliser pour le nom du joueur :D
        this.userName = userName;
    }
    /**
     * retourne la liste des etudiants dans la reserve
     * @return retourne la liste des etudiants de la reserve
     */
    public ArrayList<Etudiant> getReserveArrayList() {
        return reserve.getListeEtudiantsReserve();
    }
    
    /**
     * enleve les points en fonction des modifiations du joueur
     * @param pointsAenlever nombre de points a enlever
     */

    public void updatePoints(int pointsAenlever) {
        this.points -= pointsAenlever;
    }

    /**
     * identification du joueur, et instanciation, affiche un message personnalise en fonction du nom
     */
    public void identify() {
        this.setUserName(Partie.getUserInput("Entrez votre nom"));
        if (this.userName.equalsIgnoreCase("xuan")) {
            System.out.println("\033[0;90m" +
                                "!! Quel beau prenom :D !!" +
                                "\033[0;0m");
        }
        else if(this.userName.equalsIgnoreCase("gwen")) {
            System.out.println("\033[0;90m"+
                                "un autre beau prenom ;)" +
                                "\033[0;0m");
        }
        else if(this.userName.equalsIgnoreCase("yves")) {
            System.out.println("\033[0;90m"+"(un tres tres beau prof)"+"\033[0;0m");
        }
        boolean entryIsntValid = true;
        System.out.println("Entrez votre programme:\n"
                + "- ISI\n"
                + "- RT\n"
                + "- A2I\n"
                + "- GI\n"
                + "- GM\n"
                + "- MTE\n"
                + "- MM\n");
        while (entryIsntValid) {
            try {
                this.setProgramme(Partie.getUserInput(""));
                entryIsntValid = false;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Veuillez entrer un programme valide");
            }
       }
    }
    /**
     * identie le joueur instantanement
     * @param userName nom du joueur
     * @param programme programme du joueur
     */
    public void identify(String userName,Programme programme) {
        this.setUserName(userName);
        this.programme = programme;
    }
    /**
     * permet de recuperer l'instance de la reserve
     * @return {@link #reserve}
     */
    public Reserve getReserve() {return reserve;}
}
