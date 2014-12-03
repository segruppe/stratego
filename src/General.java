/**
 * Created by a.weger on 20.11.14.
 */
public class General extends Figur {
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

    public String toString() {
        return "General";
    }
}
