import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Spielablauf {
    private static ArrayList<Spielfeld> alteSpielfelder;
    private static KI gegner;
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
        System.out.println("Spiel beginnt");

        /*while (true) {
            if (kiGezogen) {
                // spieler muss ziehen
                // TODO: Aufruf der Methode actionPerformed???


                kiGezogen = false;
            } else {
                gegner.macheZug();
                kiGezogen = true;
            }
        }*/


        // new Figurenkampf(new Major(1),new Ninja(2));
    }
}
