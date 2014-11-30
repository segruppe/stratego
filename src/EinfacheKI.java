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
        // Fahne soll in einer der beiden vorderen Reihen stehen
        int fahneX=(int) Math.floor(Math.random() * (3 - 2 + 1)) + 2;
        int fahneY=(int) (Math.random()*10);
        System.out.println("x:"+fahneX+" y:" + fahneY);
        figur = listTmp.get(0);
        listTmp.remove(0);
        spielfeld.figurInit(figur, fahneX,fahneY);

        for (int i=0; i<grenzeX ; i++) {
            for (int j=0; j<grenzeY; j++) {
                if (i==fahneX && j==fahneY) {
                    continue;
                } else {
                    figur = holeFigur();
                    spielfeld.figurInit(figur, i, j);
                }
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
