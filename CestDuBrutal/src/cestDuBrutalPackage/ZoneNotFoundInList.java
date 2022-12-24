package cestDuBrutalPackage;
/**
 * erreur lancee lorsqu'aucune zone n'est trouvé dans une liste
 * @author boone
 *
 */
public class ZoneNotFoundInList extends Exception{
    public ZoneNotFoundInList() {
        super("Cette zone n'existe pas.");//TODO changer cette phrase elle est moche
    }
}
