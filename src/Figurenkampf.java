/**
 * Created by Dennis on 19.11.2014.
 */
public class Figurenkampf {
    Figur figur1;
    Figur figur2;

    public Figurenkampf(Figur figurA, Figur figurB) {
        figur1 = figurA;
        figur2 = figurB;
    }

    // Vergleicht die Staerken der beiden Figuren
    // 0: Unentschieden, 1: Figur1 gewinnt, 2: Figur2 gewinnt

    // Figur 1 MUSS die angreifende Figur sein
    int vergleicheStaerke() {
        if (figur1.getStaerke() == figur2.getStaerke()) {
            return 0;
        } else if (figur1.getStaerke() == 12 && figur2.getStaerke() == 4) {
            // Mineur schlaegt Bombe
            return 2;
        } else if(figur2.getStaerke() == 12 && figur1.getStaerke() == 4) {
            // Mineur schlaegt Bombe
            return 1;
        } else if (figur1.getStaerke() == 2 && figur2.getStaerke() == 11) {
            // Ninja schlaegt Feldmarschall, wenn er angreift
            return 1;
        } else if(figur1.getStaerke() > figur2.getStaerke()) {
            return 1;
        } else {
            return 2;
        }
    }
}
