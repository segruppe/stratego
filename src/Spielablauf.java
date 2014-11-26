import java.util.ArrayList;


public class Spielablauf {
    private static ArrayList<Spielfeld> alteSpielfelder;
    private static KI gegner;
    public static Spielfeld spielfeld;
    private static boolean kiGezogen = true;

    public Spielablauf() {
        main("einfach");
    }

    public static void main(String schwierigkeit) {
        //gegner = new EinfacheKI();
        spielfeld = new Spielfeld();
        gegner = new EinfacheKI(spielfeld);

    }


}
