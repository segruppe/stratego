/**
 * Created by a.weger on 20.11.14.
 */
public class Aufklaerer  extends Figur {
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
}
