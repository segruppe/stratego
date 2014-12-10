/**
 * Klasse fuer den Oberst
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
