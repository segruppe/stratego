/**
 * Created by a.weger on 20.11.14.
 */
public class Hauptmann extends Figur {
    public Hauptmann(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(7);
        setBild("Bilder/Hauptmann.jpg");
        setTeam(team);
    }
}
