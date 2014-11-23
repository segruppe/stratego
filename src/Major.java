/**
 * Created by a.weger on 20.11.14.
 */
public class Major extends Figur {
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
}
