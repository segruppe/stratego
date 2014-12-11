/**
 * Klasse fuer den Leutnant
 *
 * @see Figur
 * @see Aufklaerer
 * @see Bombe
 * @see Fahne
 * @see Feldmarschall
 * @see General
 * @see Hauptmann
 * @see Major
 * @see Mineur
 * @see Ninja
 * @see Oberst
 * @see Unteroffizier
 */
public class Leutnant extends Figur {
    /**
     * Erzeugen eines Leutnants
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Leutnant(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(6);
        setBild("Bilder/Leutnant.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Leutnant";
    }
}
