/**
 * Klasse fuer Bombe
 *
 * @see Figur
 * @see Aufklaerer
 * @see Fahne
 * @see Feldmarschall
 * @see General
 * @see Hauptmann
 * @see Leutnant
 * @see Major
 * @see Mineur
 * @see Ninja
 * @see Oberst
 * @see Unteroffizier
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
