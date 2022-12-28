package Model;
import java.util.*;//for EVERYTHIGN

/**
 * Classe mere comprenant les zones de combat et a reserve
 * @author boone, rioug
 *
 */
public class Zone {
    /**
     * Nom de la zone
     */
    private String zoneName;
    /**
     * Liste des etudiants dans la zone
     */
    protected ArrayList<Etudiant> etuDansZone;
    /**
     * Liste statique de toutes les zones de combat
     */
    private static ArrayList<ZoneCombat> zoneList = new ArrayList<ZoneCombat>();
    
    /**
     * constructeur de la zone
     * @param zoneName Nom de la zone pour instancier
     */

    public Zone(String zoneName) {
        this.etuDansZone = new ArrayList<Etudiant>();
        this.zoneName = zoneName;
    }
    

    /**
     * affecter un etudiant a la zone
     * @param etu etudiant a ajouter dans la liste de {@link #etuDansZone}
     */
    public void affectation(Etudiant etu) {
        this.etuDansZone.add(etu);
    }
    /**
     * Initialisation des zones
     */
    public static void setZones() {
        zoneList.add(new ZoneCombat("La Bibliotheque"));
        zoneList.add(new ZoneCombat("Le BDE"));
        zoneList.add(new ZoneCombat("Le Quartier Administratif"));
        zoneList.add(new ZoneCombat("Les Halles Industrielles"));
        zoneList.add(new ZoneCombat("La Halle Sportive"));
    }
    
    /**
     * Affiche toutes les zones le la liste de zone statique {@link #zoneList} sur la console
     */
    public static void displayAllZones(){
  
            Iterator<ZoneCombat> it =zoneList.iterator();
            while(it.hasNext()){
                Zone zoneTemp = it.next();
                System.out.println("- " + zoneTemp.getZoneName());
            }

   }
    /**
     * affiche toutes les zones deja controlee par le joueur j
     * @param j permet dafficher que les zones controlees par le joueur j
     */
    public static void displayControlledZones(Joueur j) {
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            if(zoneTemp.getControlePar() != null) {
                if(zoneTemp.getControlePar().equals(j))
                    System.out.println("- " + zoneTemp.getZoneName());
            }
        }
    }

    /**
     * affiche toutes les zones toujours en bataille
     * @param j pas utilisee dans cette methode
     */
    public static void displayActiveZones(Joueur j) {
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            if(zoneTemp.getControlePar() == null) {
                    System.out.println("- " + zoneTemp.getZoneName());
            }
        }
    }
    
    /**
     * est appele dans un autre {@link #getZone(int)} pour attrapper la zone sur l'index donne par l'iterator 
     * @param index donne par l'iterator
     * @return la zone choisie par l'index
     */
    public static Zone getZone(int index) { // called inside another getZone() to fetch the Student at the given iterator
        Zone zon = zoneList.get(index);
        return zon;
    }

    /**
     * est appele dans {@link #drawEtudiantDansZone(Joueur)} pour rattraper l'etudiant sur l'index donne par l'iterator
     * @param index index donne par l'iterator
     * @return l'etudiant choisi par l'index
     */
    public Etudiant drawEtudiantDansZone(int index) {// called inside another getEtudiantDansZone() to fetch the Student at the given iterator 
        Etudiant etu = etuDansZone.get(index);         
        return etu;
    }
    /**
     * Demande l'entree du joueur afin de parcourir la liste d'etudiant dans une zone de combat
     * 
     * @param j permet de parcourir seulement les etudiants appartenant au joueur j
     * @return retourne l'etudiant retire de la zone precedente. Il va etre ajoute dans la liste appelant cette methode
     * @throws StudentNotFoundInList erreur lancee lorsqu'aucun etudiant n'est trouvé dans une liste
     */
    public Etudiant drawEtudiantDansZone(Joueur j) throws StudentNotFoundInList{
        ArrayList<Etudiant>  etulist= this.etuDansZone;//
        boolean entryIsntValid = true;
        while (entryIsntValid) {
            try {//try finding a student in the zone
                int id = Integer.parseInt(Partie.getUserInput("Choisissez l'etudiant dans "+ this.zoneName));//will return a NumberFormatException if written wrong
                entryIsntValid = false;
                for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //let's find the student
                    Etudiant s = it.next();
                    if((s.getId() == id) & j.equals(s.getBelongsTo())) { //TODO set student isInZone to the zone hes affected in
                        return drawEtudiantDansZone(it.previousIndex()); //return the student if the id condition is met
                    }
                    //if the id matches the student AND the student belongs to the player, return the student
                }//exits this loop if it hasnt found a student
            }
            catch (NumberFormatException e){
                System.out.println("Veuillez entrer un ID valide \n");               
            }
        }
        throw new StudentNotFoundInList();

    }
    public Etudiant drawEtudiantDansZoneMVC(Joueur j,int id) throws StudentNotFoundInList{
        ArrayList<Etudiant>  etulist= this.etuDansZone;//
            try {//try finding a student in the zone
                for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //let's find the student
                    Etudiant s = it.next();
                    if((s.getId() == id) & j.equals(s.getBelongsTo())) { //TODO set student isInZone to the zone hes affected in
                        return drawEtudiantDansZone(it.previousIndex()); //return the student if the id condition is met
                    }
                    //if the id matches the student AND the student belongs to the player, return the student
                }//exits this loop if it hasnt found a student
            }
            catch (NumberFormatException e){
                System.out.println("Veuillez entrer un ID valide \n");               
            }
        
        throw new StudentNotFoundInList();

    }
    /**
     * Trie une liste d'etudiant le la trie en fonction de leur initiative
     * @param studentListToSort liste que l'on veut trier
     */
    public static void sortStudentList(ArrayList<Etudiant> studentListToSort) {
        studentListToSort.sort((etu1,etu2) -> etu2.getInitiative()-(etu1.getInitiative())); //j'ai pas cherche a comprendre en dÃ©tail la syntaxe....
    }
    

   /**
    * retire un etudiant de la zone
    * @param etu etudiant a retirer
    */
    public void removeStudentFromZone(Etudiant etu) {
        etuDansZone.remove(etu);
    }

    /**
     * Verifie si toutes les zones ne sont pas vides. permet d'assurer que l'on peut passer a l'etape suivante
     * @return retourne un booleen. Vrai si aucune zone n'est vide, faux s'il existe une zone non vide
     */
    public static boolean allZoneNotEmpty() {        
        ListIterator<ZoneCombat> it =zoneList.listIterator();
        while(it.hasNext()){
            Zone zoneTemp = it.next();
            if(zoneTemp.getNombreEtu()==0) return false;// si on a une zone vide
        }
        return true;
    }
    
    /**
     * Affirme que que le j2 a bien un etu dans chaque zone
     * @param j joueur concerne
     * @return un booleen, vrai s'il existe au moins un etudiant dans chaque zone du j2, faux s'il y a une zone sans etudiant j2.
     */

    public static boolean allZoneWithTwoStudent(Joueur j) {
        ListIterator<ZoneCombat> it =zoneList.listIterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            if(!zoneTemp.zoneWithTwoEtu(j)) return false; //si on a une zone ou il manque un étudiant du j2 
        }
        return true;
    }
    
    /**
     * affiche tous les etudiants dans toutes les zones
     */
    public static void displayAllStudentInZones() {
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            Zone zoneTemp = it.next();
            zoneTemp.displayEtudiantDansZoneList();
        }
    }
    
    /**
     * declenche la phase melee
     */
    public static void melee() {
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            zoneTemp.combat();
        }
    }
    /**
     * interompt tous les threads actives pendant la melee
     */
    public static void interrupteAll() {
        Iterator<ZoneCombat> it =zoneList.iterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            zoneTemp.interrupted();
        }
    }
    
    /**
     * initialise la zone de combat, apelle la methode {@link ZoneCombat#initialiser()} de chaque zone dans la liste
     */
    public static void initialiserZone() {// order the list of etu by initiative on every zone 
        Iterator<ZoneCombat> it = zoneList.iterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            zoneTemp.initialiser();
        }
    }
    /**
     * verifie le nombre detudiants dans chaque zone de chaque joueur, et declenche la fin de la partie si besoin
     * @return retourne true si la partie est finie, false sinon
     */
    public static boolean FinDePartie() {
        Iterator<ZoneCombat> it =zoneList.iterator();
        int numZoneControlByPlayer1=0;
        int numZoneControlByPlayer2=0;  
        int numZoneToEndGame=((zoneList.size()/2)+1);
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            if(zoneTemp.getControleZone()==ControleZone.CONTROLEPARJOUEUR1) {numZoneControlByPlayer1++;};
            
            if(zoneTemp.getControleZone()==ControleZone.CONTROLEPARJOUEUR2) {numZoneControlByPlayer2++;};
        }
        System.out.println("Le nombre de zone controle par j1 = "+ numZoneControlByPlayer1);
        System.out.println("Le nombre de zone controle par j2 = "+ numZoneControlByPlayer2+"\n");
        if(numZoneControlByPlayer1>=numZoneToEndGame) {
            System.out.println( Partie.getNamePlayer(1) +" a gagne");
            return true;
        }
        if(numZoneControlByPlayer2>=numZoneToEndGame) {
            System.out.println( Partie.getNamePlayer(2) +" a gagne");
            
            return true;
        };
        return false;
        
    }

    /**
     * affiche tous les etudiants dans cette zone particuliere
     */
    public void displayEtudiantDansZoneList() {
        ArrayList<Etudiant>  etulist= this.etuDansZone;
        System.out.println("\033[0;1m"+"\n==Etudiants dans "+this.zoneName+":=="+"\033[0;0m");

        for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //scan through all students
            Etudiant s = it.next();
            System.out.println(s);//use the tostring method to print the student's ids
        }    
    }
    /**
     * surcharge de {@link Zone#displayEtudiantDansZoneList()}, affiche tous les etudiatns dans la zone, en affichant seulement les etudiants du joueur j
     * @param j joueur dont lequel on affiche les etudiants
     */
    public void displayEtudiantDansZoneList(Joueur j) {
        ArrayList<Etudiant>  etulist= this.etuDansZone;
        System.out.println("\n==Etudiants dans "+this.zoneName+":==");
        for (ListIterator<Etudiant> it = etulist.listIterator(); it.hasNext();) { //scan through all students
            Etudiant s = it.next();
            if(s.getBelongsTo()==j) System.out.println(s);//use the tostring method to print the student's ids
        }    
    }
    /**
     * affiche la somme des points ECTS de la zone
     * @return retourne le nombre total d'ECTS dans la zone
     */
    public static boolean displayECTSPerZone() {
        ListIterator<ZoneCombat> it =zoneList.listIterator();
        while(it.hasNext()){
            ZoneCombat zoneTemp = it.next();
            System.out.println("==Dans la zone "+zoneTemp.getZoneName()+":==");
            zoneTemp.displayECTS();
            }
        return true;
    }
    
    //getters
    /**
     * getter de {@link #zoneList}
     * @return zoneList 
     */
    public static ArrayList<ZoneCombat> getZoneList() {return zoneList;}
    /**
     * getter de {@link #getZoneName()}
     * @return zoneName
     */
    public String getZoneName() {return zoneName;}
    /**
     * getter de {@link #etuDansZone}
     * @return etuDansZone
     */
    public int getNombreEtu() {return this.etuDansZone.size();} 
    /**
     * getter de {@link #getEtuDansZoneArrayList()}
     * @return etuDansZone
     */
    public ArrayList<Etudiant> getEtuDansZoneArrayList() {return etuDansZone;}
    //setters
    
    /**
     * setter de {@link #zoneName}
     * @param zoneName nom de la zone
     */
    public void setZoneName(String zoneName) {this.zoneName = zoneName;}

    /**
     * ajoute un etudiant dans la zone
     * @param etudiant etudiant a ajouter
     */
    public void addEtudiantDansZone(Etudiant etudiant) {
        this.etuDansZone.add(etudiant);
        System.out.println("L'etudiant a bien ete ajoute a la zone");
    }


    public ArrayList<Etudiant> getEtuDansZone() {
        return etuDansZone;
    }

    
}


