/**
 * Klasse fuer den Unteroffizier
 */
public class Unteroffizier extends Figur {
    /**
     * Erzeugen eines Unteroffiziers
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Unteroffizier(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(5);
        setBild("Bilder/Unteroffizier.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Unteroffizier";
    }
}
