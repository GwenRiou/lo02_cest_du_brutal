package cestDuBrutalPackage;

/**
 * erreur lancee lorsqu'aucun etudiant n'est trouvé dans une liste
 * @author boone,riou
 *
 */
public class StudentNotFoundInList extends Exception{
    public StudentNotFoundInList() {
        super("Etudiant inexistant dans cette liste \n");
    }
}
