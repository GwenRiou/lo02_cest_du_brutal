package Model;
/**
 * enumeration qui sert a etiquetter une zone par quel joueur elle est controlee, et si elle est disputee
 * @author boone
 *
 */
public enum ControleZone {
    /**
     * lorsque la zone est controlee par le joueur 1
     */
    CONTROLEPARJOUEUR1,
    /**
     * lorsque la zone est controlee par le joueur 2
     */
    CONTROLEPARJOUEUR2,
    /**
     * lorsque la zone est controlee par aucun joueur
     */
    DISPUTE;
}
