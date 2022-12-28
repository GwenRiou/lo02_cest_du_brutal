package Model;

/**
 * erreur lancee lorsqu'aucun etudiant n'est trouvé dans une liste
 * @author boone,riou
 *
 */
public class StudentNotFoundInList extends Exception{
    /**
     * message affiche lorsqu'il y a l'erreur
     */
public StudentNotFoundInList() {
        super("Etudiant inexistant dans cette liste \n");
    }
}
