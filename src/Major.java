/**
 * Klasse fuer den Major
 */
public class Major extends Figur {
    /**
     * Erzeugen eines Majors
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Major(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(8);
        setBild("Bilder/Major.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Major";
    }
}
