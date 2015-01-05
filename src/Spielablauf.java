/**
 * Klasse, um den Spielablauf zu starten (steuern)
 *
 * @see KI
 * @see EinfacheKI
 * @see MittlereKI
 * @see SchwereKI
 * @see Spielfeld
 */
public class Spielablauf {
    //private static ArrayList<Spielfeld> alteSpielfelder;
    protected static KI gegner;
    public static Spielfeld spielfeld;
    protected static boolean kiGezogen = true;

    /**
     * Konstruktor, um die KI zu erzeugen
     *
     * @param schwierigkeit Schwierigkeit der zu erzeugenden KI
     */
    public Spielablauf (String schwierigkeit) {
        spielfeld = new Spielfeld();

        // Erzeugen der KI, je nach ausgewaehlter Schwierigkeit
        if (schwierigkeit.equals("einfach")) {
            gegner = new EinfacheKI(spielfeld);
        } else if (schwierigkeit.equals("mittel")) {
            gegner = new MittlereKI(spielfeld);
        } else if (schwierigkeit.equals("schwer")) {
            gegner = new SchwereKI(spielfeld,false);
        }
        // Beim Start des Spiels soll die KI ihre Figuren setzen
        gegner.setzeStartAufstellung();

    }

    /**
     * Konstruktor, der beim SpielLaden das Spielfeld wieder herstellt
     *
     * @param spielfeld Spielfeld, auf dem die Figuren gesetzt werden
     * @param gegner KI
     */
    public Spielablauf(Spielfeld spielfeld, KI gegner) {
        Spielablauf.spielfeld = spielfeld;
        Spielablauf.gegner = gegner;
    }
}