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
public class Joueur /*extends  Reserve*/{
    private int points = 400;
    private Programme programme;
    private String userName;
    //private Programme programmeBase = Programme.ISI;
    private ArrayList<Etudiant> studentList; // car on va chercher les �tu par leur index
    private Reserve reserve = new Reserve();
    
    public Joueur() {
        this.points = points;
        this.userName = "";
        this.studentList = new ArrayList <Etudiant>();        
    }
    

    public void createStudentList() {
        // creer le maiter du gobit
        Etudiant etuMaitre = new Etudiant("Maitre",2,2,2,10,2);
        studentList.add(etuMaitre);
        
        //crer les soldats �lites
        for(int i=0 ; i < 4; i++){
            Etudiant etuElite = new Etudiant("Elite",2,2,2,10,2);
            studentList.add(etuElite);            
        }
        
        //creer lest �tudiants de basse
        for(int i=0 ; i < 15; i++){
            Etudiant etuNormal = new Etudiant("Base",0,0,0,0,0);
            studentList.add(etuNormal);            
        }
        setIdForArmy();
       
    }
    private void setIdForArmy() {// parcour la liste et seID des �tudiants
        
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
            //System.out.println("L'etudiant n�" + iter.nextIndex() + " a " + iter.next());
        }
    }
    
    // return l'�tudiant choisit
    public Etudiant getStudent(int index) {
        Etudiant etu = studentList.get(index);
        return etu;
    }
    
    
    public int modifyCharacteristics(Etudiant etu,String car, int pointsAttribuee) {   
        // methode retourne 0 si on ne peut pas faire la modification
        if (pointsAttribuee>this.points) {
            System.out.println("Vous n 'avez pas assez de points pour cette modification");
            return 0;
        }else {
            //Voir si on peut pas faire une m�thode au lieu de pr�sic� pour chaque caract�ristique
            switch(car) { 
                case "Force":
                    int newForce = etu.getForce()+pointsAttribuee;
                    //regarder si la nouvelle force est bien <=10 et >=0
                    if(newForce>=0 && newForce<=10) { 
                        etu.setForce(newForce);  
                        return 1;
                    } else {
                        System.out.println("Vous ne pouvez pas avoir de caract�ristiques >=10 ou <=0");   
                        return 0;
                    }
                 // pas de break car on sort de la boucle avant 
                case "Dexterite":
                    int newDexterite = etu.getDexterite()+pointsAttribuee;
                    if(newDexterite>=0 && newDexterite<=10) {                   
                        etu.setDexterite(newDexterite);
                        return 1;
                    }else  {
                        System.out.println("Vous ne pouvez pas avoir de caract�ristiques >=10 ou <=0");    
                        return 0;
                    }
                case "Resistance":
                    int newResistance = etu.getResistance()+pointsAttribuee;
                    if(newResistance>=0 && newResistance<=10) {                   
                        etu.setResistance(newResistance);
                        return 1;
                    }else    {
                        System.out.println("Vous ne pouvez pas avoir de caract�ristiques >=10 ou <=0");   
                        return 0;
                    }
                case "Constitution":
                    int newConstitution = etu.getConstitution()+pointsAttribuee;
                    if(newConstitution>=0 && newConstitution<=10) {                   
                        etu.setConstitution(newConstitution);
                        return 1;
                    }else {
                        System.out.println("Vous ne pouvez pas avoir de caract�ristiques >=10 ou <=0");    
                        return 0;
                    }
                case "Initiative":
                    int newInitiative =etu.getInitiative()+pointsAttribuee;
                    if(newInitiative>=0 && newInitiative<=10) {                   
                        etu.setInitiative(newInitiative);
                        return 1;
                    }else  {
                        System.out.println("Vous ne pouvez pas avoir de caract�ristiques >=10 ou <=0");   
                        return 0;
                    }
                 
                default:
                    System.out.println("La caract�ristique entr�e n'est pas d�finit, v�rifier l'ortographe");
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

    // Impl�mentation plus complexe possible cf doc id�e
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void updatePoints(int pointsAenlever) {
        this.points -= pointsAenlever;
    }
}
