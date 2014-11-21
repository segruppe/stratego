import java.util.ArrayList;

/**
 * Created by Merten on 21.11.2014.
 */
public class Spielablauf {
    private static ArrayList<Spielfeld> alteSpielfelder;
    private static KI gegner;
    public static Spielfeld spielfeld;
    private static boolean kiGezogen = true;

    public Spielablauf() {
        main("einfach");
    }

    public static void main(String schwierigkeit) {
        spielfeld = new Spielfeld();
        gegner = new KI(schwierigkeit);

    }

    // Figur auswaehlen
    public void firstClickListener() {

    }

    // Figur setzen
    public void secondClickListener() {

    }

}
