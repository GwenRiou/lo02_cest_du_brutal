package cestDuBrutalPackage;
import java.util.*;

/**
 * 
 * @author rioug
 *         Tester si le joueur 2 à un le même nom que le joueur 1 avant de
 *         l'autoriser le nom
 *         Implémentation:
 *         création d’un variable static qui compte le nombre de joueur
 *         setUsername en Private
 *         Si le joueur veut créer un nom : la méthode regardé : le numéro du
 *         joueur
 *         si c’est le joueur 1 on autorise tout --> appel de la fonction setter
 *         si c’est le joueur 2 --> regarde si le nom du joueur 1 à le même nom et
 *         si oui on demande un autre non si non on appel setter
 * 
 */
public class Joueur /*extends  Reserve*/{
    private int points = 400;
    private Programme programme;
    private String userName;
    //private Programme programmeBase = Programme.ISI;
    private ArrayList<Etudiant> studentList; // car on va chercher les étu par leur index
    private Reserve reserve = new Reserve();
    
    public Joueur() {
        this.points = points;
        this.userName = "";
        this.studentList = new ArrayList <Etudiant>();        
    }
    

    public void createStudentList(int idJoueur) {
        // creer le maiter du gobit
        Etudiant etuMaitre = new Etudiant("Maitre",2,2,2,10,2,idJoueur);
        studentList.add(etuMaitre);
        
        //crer les soldats élites
        for(int i=0 ; i < 4; i++){
            Etudiant etuElite = new Etudiant("Elite",2,2,2,10,2,idJoueur);
            studentList.add(etuElite);            
        }
        
        //creer lest étudiants de basse
        for(int i=0 ; i < 15; i++){
            Etudiant etuNormal = new Etudiant("Base",0,0,0,0,0,idJoueur);
            studentList.add(etuNormal);            
        }
        setIdForArmy();
       
    }
    private void setIdForArmy() {// parcour la liste et seID des étudiants
        
        System.out.println(" List :");
        

        for (ListIterator<Etudiant> it = studentList.listIterator(); it.hasNext();) {
             Etudiant s = it.next();
             s.setId(it.previousIndex());            
        }
    }
    
    public void displayAllStudent() {

        ListIterator<Etudiant> iter = studentList.listIterator();
            
        while (iter.hasNext()) {
            System.out.println(iter.next());
            //System.out.println("L'etudiant n°" + iter.nextIndex() + " a " + iter.next());
        }
    }
    public void displayReserveStudent() {

        ListIterator<Etudiant> iter = this.reserve.getListeEtudiantsReserve().listIterator();
            
        while (iter.hasNext()) {
            System.out.println(iter.next());
            //System.out.println("L'etudiant n°" + iter.nextIndex() + " a " + iter.next());
        }
    }
    
    // return l'étudiant choisit
    public Etudiant getStudent(int index) {
        Etudiant etu = studentList.get(index);
        return etu;
    }
    
    
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
    
    // Mise en reserve
    public void putInReserve(Etudiant etu) {
        this.studentList.remove(etu);// Enl�ve l'�tudiant de la liste 
        reserve.affecterReserve(etu);
    }
    
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
   


    // --------------------------------Getter & Setter--------------------------------
    public ArrayList<Etudiant> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Etudiant> studentList) {
        this.studentList = studentList;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    //returns programme as an enum
    public Programme getProgramme() {
        return programme;
    }
    //returns programme but as a string
    public String getProgrammeString() {return programme.toString();}
    
    public void setProgramme(String programme) {
        this.programme = Programme.valueOf(programme.toUpperCase());
    }

    public String getUserName() {
        return userName;
    }

    // Implémentation plus complexe possible cf doc idée
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public ArrayList<Etudiant> getReserve() {
        return reserve.getListeEtudiantsReserve();
    }

    public void updatePoints(int pointsAenlever) {
        this.points -= pointsAenlever;
    }
}
