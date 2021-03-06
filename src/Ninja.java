/**
 * Klasse fuer den Ninja
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
 * @see Oberst
 * @see Unteroffizier
 */
public class Ninja extends Figur{
    /**
     * Erzeugen eines Ninjas
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Ninja(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(2);
        setBild("Bilder/Wurfstern.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Ninja";
    }
}
