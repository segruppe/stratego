/**
 * Created by a.weger on 20.11.14.
 */
public class Fahne extends Figur{
    
    public Fahne(int team) {
        if (team==1) {
            zaehler1++;
            setId(zaehler1);
        } else {
            zaehler2++;
            setId(zaehler2);
        }
        setIstBewegbar(false);
        setStaerke(1);
        setBild("Bilder/fahne.jpg");
        setTeam(team);
    }

}
