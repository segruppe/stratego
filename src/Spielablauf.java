import java.util.ArrayList;


public class Spielablauf {
    private static ArrayList<Spielfeld> alteSpielfelder;
    private static KI gegner;
    public static Spielfeld spielfeld;
    private static boolean kiGezogen = true;

//    public Spielablauf() {
//        main("einfach");
//    }

    public Spielablauf (String schwierigkeit) {
        spielfeld = new Spielfeld();

        // Erzeugen der KI, je nach ausgewaehlter Schwierigkeit
        if (schwierigkeit.equals("einfach")) {
            gegner = new EinfacheKI(spielfeld);
        } else if (schwierigkeit.equals("mittel")) {
            gegner = new MittlereKI(spielfeld);
        } else if (schwierigkeit.equals("schwer")) {
            gegner = new SchwereKI(spielfeld);
        }
        System.out.println("Gegner macht Zug");
        gegner.macheZug();
        gegner.macheZug();
        gegner.macheZug();
       // new Figurenkampf(new Major(1),new Ninja(2));
    }
}
