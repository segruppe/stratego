import java.util.ArrayList;

/**
 * Created by Dennis on 25.11.2014.
 */
public class SchwereKI extends KI {

    Figur figur;
    Spielfeld spielfeld;
    ArrayList<Figur> listTmp;

    public SchwereKI(Spielfeld spielfeld) {
        listTmp = new ArrayList<Figur>(figurenSatzKI);
        this.spielfeld=spielfeld;
        setzeStartAufstellung();
    }

    @Override
    public void setzeStartAufstellung() {
        int index=2;
        spielfeld.panelButton.removeAll();
        for (int i=0; i<grenzeX; i++) {
            for (int j=0; j<grenzeY; j++) {
                figur = listTmp.get(index);
                spielfeld.figurInit(figur,i,j);
            }
        }
        spielfeld.panelAktualisieren();
    }

    public Figur holeFigur(){
        return null;
    }

    @Override
    public Position macheZug() {
        return null;
    }
}
