/**
 * Klasse fuer den Oberst
 *
 * @see Figur
 * @see Aufklaerer
 * @see Bombe
 * @see Fahne
 * @see Feldmarschall
 * @see General
 * @see Hauptmann
 * @see Leutnant
 * @see Major
 * @see Mineur
 * @see Ninja
 * @see Unteroffizier
 */
public class Oberst extends Figur {
    /**
     * Erzeugen eines Oberst
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Oberst(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(9);
        setBild("Bilder/Oberst.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Oberst";
    }
}
