/**
 * Klasse fuer den Feldmarschall
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
