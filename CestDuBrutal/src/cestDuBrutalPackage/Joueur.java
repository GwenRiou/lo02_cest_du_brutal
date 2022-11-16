package cestDuBrutalPackage;
import java.util.*;

/**
 * 
 * @author rioug
 *         Tester si le joueur 2 � un le m�me nom que le joueur 1 avant de
 *         l'autoriser le nom
 *         Impl�mentation:
 *         cr�ation d�un variable static qui compte le nombre de joueur
 *         setUsername en Private
 *         Si le joueur veut cr�er un nom : la m�thode regard� : le num�ro du
 *         joueur
 *         si c�est le joueur 1 on autorise tout --> appel de la fonction setter
 *         si c�est le joueur 2 --> regarde si le nom du joueur 1 � le m�me nom et
 *         si oui on demande un autre non si non on appel setter
 * 
 */
public class Joueur {
    private int points = 400;
    private Programme programme;
    private String userName;
    //private Programme programmeBase = Programme.ISI;
    private ArrayList<Etudiant> studentList; // car on va chercher les �tu par leur index
    
    public Joueur() {
        this.points = points;
        this.userName = "";
        this.studentList = new ArrayList <Etudiant>();
    }

    public Joueur(Programme programme, String name) {
        this.points = points;
        this.programme = programme;
        this.userName = name;
        this.studentList = new ArrayList <Etudiant>();
    }

    public void createStudentList() {
        // creer le maiter du gobit
        Etudiant etuMaitre = new Etudiant("Maitre",2,2,2,10,2);
        studentList.add(etuMaitre);
        
        //crer les soldats �lites
        for(int i=0 ; i <= 4; i++){
            Etudiant etuElite = new Etudiant("Elite",2,2,2,10,2);
            studentList.add(etuElite);            
        }
        
        //creer lest �tudiants de basse
        for(int i=0 ; i <= 4; i++){
            Etudiant etuNormal = new Etudiant("Elite",2,2,2,10,2);
            studentList.add(etuNormal);            
        }
    }
    
    // Getter & Setter
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

    // Impl�mentation plus complexe possible cf doc id�e
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void updatePoints(int pointsAenlever) {
        this.points -= pointsAenlever;
    }
}
