/**
 * Klasse fuer den Mineur
 */
public class Mineur extends Figur {
    /**
     * Erzeugen eines Mineurs
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public Mineur(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(4);
        setBild("Bilder/Schaufel.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "Mineur";
    }
}
