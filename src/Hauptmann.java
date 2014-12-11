/**
 * Klasse fuer den Hauptmann
 *
 * @see Figur
 * @see Aufklaerer
 * @see Bombe
 * @see Fahne
 * @see Feldmarschall
 * @see General
 * @see Leutnant
 * @see Major
 * @see Mineur
 * @see Ninja
 * @see Oberst
 * @see Unteroffizier
 */
public class Hauptmann extends Figur {
    /**
     * Erzeugen eines Hauptmanns
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Hauptmann(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(7);
        setBild("Bilder/Hauptmann.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Hauptmann";
    }
}
