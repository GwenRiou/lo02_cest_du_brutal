package cestDuBrutalPackage;

/**
 * interface implementee par la classe {@link Etudiant}
 * permet d'implementer les methodes attaquer et soigner dans {@link Etudiant#agir()}
 * @author boone
 *
 */
public interface Strategie {
 private void attack() {};
 private void heal() {};
}
