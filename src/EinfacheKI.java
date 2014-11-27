import java.util.ArrayList;

/**
 * Created by Dennis on 25.11.2014.
 */
public class EinfacheKI extends KI {
    // Kopieren der statischen Liste
    //ArrayList<Figur> tmp = new ArrayList<Figur>(figurenSatzKI);
    Figur figur;
    Spielfeld spielfeld;
    ArrayList<Figur> listTmp;

    public EinfacheKI(Spielfeld spielfeld) {
        listTmp  = new ArrayList<Figur>(figurenSatzKI);
        this.spielfeld=spielfeld;
        setzeStartAufstellung();
    }

    @Override
    public void setzeStartAufstellung() {
        spielfeld.panelButton.removeAll();
        for (int i=0; i<grenzeX ; i++) {
            for (int j=0; j<grenzeY; j++) {
                figur = holeFigur();
                spielfeld.figurInit(figur,i,j);
            }
        }
        spielfeld.panelAktualisieren();
    }

    public Figur holeFigur() {

        Figur figur=null;
//        while (tmp.size()>0) {
//            int zahl = (int) (Math.random() * tmp.size());
//            //figur = tmp.get(zahl);
//            figur = tmp.remove(zahl);
//        }
        //while (figurenSatzKI.size()>0) {
            int zahl = (int) (Math.random()*listTmp.size());
            figur = listTmp.get(zahl);
            listTmp.remove(zahl);
      //  }
        return figur;
    }

    @Override
    public Position macheZug() {
        return null;
    }
}
