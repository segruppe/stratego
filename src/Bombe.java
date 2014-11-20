/**
 * Created by a.weger on 20.11.14.
 */
public class Bombe extends Figur {

    public Bombe(int id, int team){
        this.setId(id);
        this.setTeam(team);
        this.setStaerke(12);
        this.setBild("Bilder/bombe.jpg");
        this.setIstBewegbar(false);
    }
}
