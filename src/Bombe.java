/**
 * Klasse fuer Bombe
 */
public class Bombe extends Figur {

    /**
     * Erzeugen einer Bombe
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Bombe(int team){
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(false);
        setStaerke(12);
        setBild("Bilder/bombe.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Bombe";
    }
}
