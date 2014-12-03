/**
 * Created by a.weger on 20.11.14.
 */
public class Oberst extends Figur {
    public Oberst(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(true);
        setStaerke(9);
        setBild("Bilder/Oberst.jpg");
        setTeam(team);
    }

    public String toString() {
        return "Oberst";
    }
}
