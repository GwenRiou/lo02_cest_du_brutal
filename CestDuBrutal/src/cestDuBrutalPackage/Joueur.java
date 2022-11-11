package cestDuBrutalPackage;

public class Joueur {
    private int points;
    private Programme programme;
    
    public Joueur(int point,Programme programme) {
         this.points = points;
         this.programme=programme;
     }
    
    public void updatePoints (int pointsAenlever){
        this.points-=pointsAenlever;
    }
}
