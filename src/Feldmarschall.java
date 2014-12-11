/**
 * Klasse fuer den Feldmarschall
 *
 * @see Figur
 * @see Aufklaerer
 * @see Bombe
 * @see Fahne
 * @see General
 * @see Hauptmann
 * @see Leutnant
 * @see Major
 * @see Mineur
 * @see Ninja
 * @see Oberst
 * @see Unteroffizier
 */
public class Feldmarschall extends Figur {
    /**
     * Erzeugen eines Feldmarschalls
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Feldmarschall(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(11);
        setBild("Bilder/Feldmarschall.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Feldmarschall";
    }
}
