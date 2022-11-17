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
public class Joueur {
    private int points = 400;
    private Programme programme;
    private String userName;
    //private Programme programmeBase = Programme.ISI;
    private ArrayList<Etudiant> studentList; // car on va chercher les étu par leur index
    
    public Joueur() {
        this.points = points;
        this.userName = "";
        this.studentList = new ArrayList <Etudiant>();
    }
    

    public void createStudentList() {
        // creer le maiter du gobit
        Etudiant etuMaitre = new Etudiant("Maitre",2,2,2,10,2);
        studentList.add(etuMaitre);
        
        //crer les soldats élites
        for(int i=0 ; i < 4; i++){
            Etudiant etuElite = new Etudiant("Elite",2,2,2,10,2);
            studentList.add(etuElite);            
        }
        
        //creer lest étudiants de basse
        for(int i=0 ; i < 15; i++){
            Etudiant etuNormal = new Etudiant("Base",0,0,0,0,0);
            studentList.add(etuNormal);            
        }
    }
    
    public void displayAllStudent() {

        ListIterator<Etudiant> iter = studentList.listIterator();
            
        while (iter.hasNext()) {
            System.out.println("L'etudiant n°" + iter.nextIndex() + " a " + iter.next());
        }
    }
    
    // return l'étudiant choisit
    public Etudiant getStudent(int index) {
        Etudiant etu = studentList.get(index);
        return etu;
    }
    
    
    public int modifyCharacteristics(Etudiant etu,String car, int pointsAttribuee) {   
        // methode retourne 0 si on ne peut pas faire la modification
        if (pointsAttribuee>=this.points) {
            System.out.println("Vous n 'avez pas assez de points pour cette modification");
            return 0;
        }else {
            //Voir si on peut pas faire une méthode au lieu de présicé pour chaque caractéristique
            switch(car) { 
                case "Force":
                    int force = etu.getForce();
                    if(force+pointsAttribuee>=0) { //TODO il doit avoit un pb ici
                        etu.setForce(force+pointsAttribuee);  
                        return 1;
                    } else {
                        System.out.println("Vous ne pouvez pas avoir de caractéristiques négatives");   
                        return 0;
                    }
                 // pas de break car on sort de la boucle avant 
                case "Dexterite":
                    if(etu.getDexterite()+pointsAttribuee>=0) {                   
                        etu.setDexterite(etu.getDexterite()+pointsAttribuee);
                        return 1;
                    }else  {
                        System.out.println("Vous ne pouvez pas avoir de caractéristiques négatives");    
                        return 0;
                    }
                case "Resistance":
                    if(etu.getResistance()+pointsAttribuee>=0) {                   
                        etu.setResistance(etu.getResistance()+pointsAttribuee);
                        return 1;
                    }else    {
                        System.out.println("Vous ne pouvez pas avoir de caractéristiques négatives");   
                        return 0;
                    }
                case "Constitution":
                    if(etu.getConstitution()+pointsAttribuee>=0) {                   
                        etu.setConstitution(etu.getConstitution()+pointsAttribuee);
                        return 1;
                    }else {
                        System.out.println("Vous ne pouvez pas avoir de caractéristiques négatives");    
                        return 0;
                    }
                case "Initiative":
                    if(etu.getInitiative()+pointsAttribuee>=0) {                   
                        etu.setInitiative(etu.getInitiative()+pointsAttribuee);
                        return 1;
                    }else  {
                        System.out.println("Vous ne pouvez pas avoir de caractéristiques négatives");   
                        return 0;
                    }
                 
                default:
                    System.out.println("La caractéristique entrée n'est pas définit, vérifier l'ortographe");
                    return 0;
              }

        }
    }
    public String toString() {
        StringBuffer sb = new StringBuffer ("Le Joueur ");
        sb.append(this.userName);
        sb.append(" appartient au Programme");
        //sb.append((String)this.Programme);TODO
        sb.append(" et a  : ");
        sb.append(this.points);
        sb.append(" points ");
        
        return sb.toString();
        }
   


    // Getter & Setter
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

    public Programme getProgramme() {
        return programme;
    }

    public String getUserName() {
        return userName;
    }

    // Implémentation plus complexe possible cf doc idée
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void updatePoints(int pointsAenlever) {
        this.points -= pointsAenlever;
    }
}
