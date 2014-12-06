import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Spielablauf {
    private static ArrayList<Spielfeld> alteSpielfelder;
    protected static KI gegner;
    public static Spielfeld spielfeld;
    protected static boolean kiGezogen = true;

//    public Spielablauf() {
//        main("einfach");
//    }

    public Spielablauf() {}
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
        // Beim Start des Spiels soll die KI ihre Figuren setzen
        gegner.setzeStartAufstellung();
    }

    public Spielablauf(Spielfeld spielfeld, KI gegner) {
        this.spielfeld = spielfeld;
        this.gegner = gegner;
    }
}