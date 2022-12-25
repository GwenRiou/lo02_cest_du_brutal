package cestDuBrutalPackage;
/**
 * Caracteristique d'un etudiant, determine comment l'etudiant agit
 * @author boone
 *
 */
public enum enumStrategie {
    /**
     * l'etudiant ne peut que attaquer
     */
    OFFENSIVE,
    /**
     * l'etudiant ne peut que soigner
     */
    DEFENSIVE,
    /**
     * l'etudiant choisit aleatoirement de soigner ou d'attaquer. C'est la valeur mise par defaut
     */
    RANDOM;
}
