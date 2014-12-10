/**
 * Klasse fuer den General
 */
public class General extends Figur {
    /**
     * Erzeugen eines Generals
     *
     * @param team Bestimmt zu welchem Team die Figur gehoert
     */
    public General(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(10);
        setBild("Bilder/General.jpg");
        setTeam(team);
    }

    /**
     * Ueberschreiben der toString Methode
     *
     * @return Name der Figur
     */
    public String toString() {
        return "General";
    }
}
