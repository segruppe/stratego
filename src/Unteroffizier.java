/**
 * Created by a.weger on 20.11.14.
 */
public class Unteroffizier extends Figur {
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
}
