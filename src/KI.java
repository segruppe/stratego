import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public interface KI {
    // Grenzen für das Gebiet der KI
    static int grenzeX = 10;
    static int grenzeY = 3;

    public abstract void setzeStartSpielfeld();
    public abstract Figur waehleFigur();
    public abstract Position macheZug();

    // Hinzufuegen aller Figuren in eine Liste
     static ArrayList<Figur> figuren = new ArrayList<Figur>(){{
        add(new Fahne(2));
        add(new Ninja(2));
        add(new Aufklaerer(2));
        add(new Aufklaerer(2));
        add(new Aufklaerer(2));
        add(new Aufklaerer(2));
        add(new Aufklaerer(2));
        add(new Aufklaerer(2));
        add(new Aufklaerer(2));
        add(new Aufklaerer(2));
        add(new Mineur(2));
        add(new Mineur(2));
        add(new Mineur(2));
        add(new Mineur(2));
        add(new Mineur(2));
        add(new Unteroffizier(2));
        add(new Unteroffizier(2));
        add(new Unteroffizier(2));
        add(new Unteroffizier(2));
        add(new Leutnant(2));
        add(new Leutnant(2));
        add(new Leutnant(2));
        add(new Leutnant(2));
        add(new Hauptmann(2));
        add(new Hauptmann(2));
        add(new Hauptmann(2));
        add(new Hauptmann(2));
        add(new Major(2));
        add(new Major(2));
        add(new Major(2));
        add(new Oberst(2));
        add(new Oberst(2));
        add(new General(2));
        add(new Feldmarschall(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Bombe(2));
    }};

//
//    public KI(String schwierigkeit) {
//        this.schwierigkeit = schwierigkeit;
//    }
//
//    /*
//    Vereinfacht das nutzen der KI
//     */
//    public Position macheZug() {
//        if (this.schwierigkeit == "leicht") {
//            return this.leichteKI();
//        } else if (this.schwierigkeit == "mittel") {
//            return this.mittlereKI();
//        } else if (this.schwierigkeit == "schwer") {
//            return this.schwereKI();
//        } else {
//            System.out.println("Keine KI ausgewaehlt!");
//            return new Position(0, 0);
//        }
//    }
//
//    private Position leichteKI() {
//        // ich mache den Zug einer leichten KI
//        return new Position(0, 0);
//    }
//
//    private Position mittlereKI() {
//        // ich mache den Zug einer mittleren KI
//        return new Position(0, 0);
//    }
//
//    private Position schwereKI() {
//        // ich mache den Zug einer schweren KI
//        return new Position(0, 0);
//    }

}