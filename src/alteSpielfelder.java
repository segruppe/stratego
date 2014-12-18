import java.util.ArrayList;

public class alteSpielfelder {
    private ArrayList<Spielfeld> alteFelder = new ArrayList<Spielfeld>();
    private Spielfeld temp;


    public alteSpielfelder(Spielfeld spielfeld) {
        alteFelder.add(spielfeld);
    }

    public void fuegeSpielfeldHinzu(Spielfeld spielfeld) {
        alteFelder.add(spielfeld);
    }

    public boolean contains(Spielfeld spielfeld) {
        if(alteFelder.contains(spielfeld)) return true;
        else return false;
    }

    public int getSize() {
        return alteFelder.size();
    }

    public void clear() {
        alteFelder.clear();
    }
}
