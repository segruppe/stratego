import java.util.ArrayList;

/**
 * Abstrakte Klasse fuer alle KIs
 *
 * @see EinfacheKI
 * @see MittlereKI
 * @see SchwereKI
 */
public abstract class KI {
/**
 *
 * Link: http://www.brettspiele-report.de/stratego/
 *
 */
    /** Grenze fuer Anzahl der Zeilen */
    static int grenzeX = 4;
    /** Grenze fuer Anzahl der Spalten */
    static int grenzeY = 10;
    /** Abstrakte Methode zur setzen der Startaufstellung */
    public abstract void setzeStartAufstellung();
    /** Abstrakte Methode um einen Zug zu taetigen */
    public abstract void macheZug();

    /** Liste aller Figuren */
     static ArrayList<Figur> figurenSatzKI = new ArrayList<Figur>(){{
        add(new Fahne(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Bombe(2));
        add(new Ninja(2));
        add(new Feldmarschall(2));
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

    }};
}