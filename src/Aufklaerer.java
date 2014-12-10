/**
 * Klasse fuer den Aufklaerer
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
