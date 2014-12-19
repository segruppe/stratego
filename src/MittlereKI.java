import java.util.ArrayList;

/**
 * GUI-Klasse fuer die mittlere KI. Erbt von KI
 *
 * @see Figur
 * @see Spielfeld
 */
public class MittlereKI extends KI {

    Figur figur;
    Spielfeld spielfeld;
    ArrayList<Figur> listTmp;

    /**
     * Erzeugen der Mittleren KI
     *
     * @param spielfeld Spielfeld auf dem die Figuren gesetzt werden
     */
    public MittlereKI(Spielfeld spielfeld) {
        listTmp = new ArrayList<Figur>(figurenSatzKI);
        this.spielfeld=spielfeld;
        setzeStartAufstellung();
    }

    @Override
    /**
     * Setzen einer Startaufstellung
     */
    public void setzeStartAufstellung() {
       int index=0;
        spielfeld.panelButton.removeAll();
        for (int i=0; i<grenzeX; i++) {
            for (int j=0; j<grenzeY; j++) {
                figur = listTmp.get(index);
                spielfeld.figurInit(figur,i,j);
            }
        }
        spielfeld.panelAktualisieren();
    }

    @Override
    /**
     * KI macht einen Zug
     */
    public void macheZug() {}


}
