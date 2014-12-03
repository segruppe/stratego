import java.util.ArrayList;

/**
 * Created by Dennis on 25.11.2014.
 */
public class MittlereKI extends KI {

    Figur figur;
    Spielfeld spielfeld;
    ArrayList<Figur> listTmp;

    public MittlereKI(Spielfeld spielfeld) {
        listTmp = new ArrayList<Figur>(figurenSatzKI);
        this.spielfeld=spielfeld;
        setzeStartAufstellung();
    }

    @Override
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

    public Figur holeFigur(){
        return null;
    }

    @Override
    public void macheZug() {}

}
