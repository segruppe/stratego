/**
 * Klasse fuer die Fahne
 *
 * @see Figur
 * @see Aufklaerer
 * @see Bombe
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
public class Fahne extends Figur{

    /**
     * Erzeugen einer Fahne
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Fahne(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(false);
        setStaerke(1);
        setBild("Bilder/fahne.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Fahne";
    }
}
