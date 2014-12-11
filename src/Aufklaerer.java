/**
 * Klasse fuer den Aufklaerer
 *
 * @see Figur
 * @see Bombe
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
public class Aufklaerer  extends Figur {
    /**
     * Erzeugen eines Aufklaerers
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Aufklaerer(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(3);
        setBild("Bilder/Fernglas.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Aufklaerer";
    }
}
