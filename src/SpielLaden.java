/**
 * Klasse, um ein altes Spiel zu laden
 *
 * @see SpeichernLaden
 */
public class SpielLaden {

    /**
     * Konstruktor, um ein altes Spielfeld zu laden
     */
    public SpielLaden() {
        SpeichernLaden sl = new SpeichernLaden(new Spielfeld());
        sl.spielfeldLaden();
    }

}
