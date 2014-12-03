/**
 * Created by a.weger on 20.11.14.
 */
public class Leutnant extends Figur {
    public Leutnant(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(6);
        setBild("Bilder/Leutnant.jpg");
        setTeam(team);
    }

    public String toString() {
        return "Leutnant";
    }
}
