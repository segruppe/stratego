/**
 * Created by a.weger on 20.11.14.
 */
public class Mineur extends Figur {
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
}
