import java.util.ArrayList;

/**
 * Created by Dennis on 25.11.2014.
 */
public class EinfacheKI implements KI {
    // Kopieren der statischen Liste
    ArrayList<Figur> tmp = new ArrayList<Figur>(figuren);
    Figur figur;

    @Override
    public void setzeStartSpielfeld() {
        for (int i=0; i<grenzeX ; i++) {
            for (int j=0; j<grenzeY; j++) {
                figur = holeFigur();

            }

        }
    }

    public Figur holeFigur() {

        Figur figur=null;
        while (tmp.size()>0) {
            int zahl = (int) (Math.random() * tmp.size());
            //figur = tmp.get(zahl);
            figur = tmp.remove(zahl);
        }
        return figur;
    }

    public Figur waehleFigur(){
        return null;
    }

    @Override
    public Position macheZug() {
        return null;
    }
}
