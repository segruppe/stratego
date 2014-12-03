/**
 * Created by a.weger on 20.11.14.
 */
public class Bombe extends Figur {

    public Bombe(int team){
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(false);
        setStaerke(12);
        setBild("Bilder/bombe.jpg");
        setTeam(team);
    }

    public String toString() {
        return "Bombe";
    }
}
