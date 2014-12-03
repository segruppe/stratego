/**
 * Created by a.weger on 20.11.14.
 */
public class Ninja extends Figur{
    public Ninja(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(2);
        setBild("Bilder/Wurfstern.jpg");
        setTeam(team);
    }

    public String toString() {
        return "Ninja";
    }
}
