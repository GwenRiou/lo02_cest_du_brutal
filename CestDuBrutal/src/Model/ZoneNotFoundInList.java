package Model;
/**
 * erreur lancee lorsqu'aucune zone n'est trouvé dans une liste
 * @author boone
 *
 */
public class ZoneNotFoundInList extends Exception{
    /**
     * affiche le message que la zone n'existe pas
     */
    public ZoneNotFoundInList() {
        super("Cette zone n'existe pas.");

    }
}
