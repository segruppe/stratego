/**
 * Created by a.weger on 20.11.14.
 */
public class Feldmarschall extends Figur {
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
}
