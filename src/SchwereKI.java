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
        // zuerst Fahne setzen:
        int r=(int)(Math.random()*4);
        switch (r){
            case 0: this.figurenSatzKI.get(0).setPosition(new Position(0,0)); // linke Ecke
                    int b=(int)(Math.random()*3);
                    switch(b){
                        case 0: this.figurenSatzKI.get(listTmp.size()-1).setPosition(new Position(0,1));
                                this.figurenSatzKI.get(listTmp.size()-2).setPosition(new Position(1,0));
                    }
            case 1: this.figurenSatzKI.get(0).setPosition(new Position(0, 9));break; // rechte Ecke
            case 2: this.figurenSatzKI.get(0).setPosition(new Position(0, 4));break; // Mitte oberste Reihe
            case 3: this.figurenSatzKI.get(0).setPosition(new Position((int)(Math.random()*10), (int)(Math.random()*2)));
        }

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

    private Position setzeFahne(){
        int r=(int)(Math.random()*4);
        switch (r){
            case 0: return new Position(0,0); // linke Ecke

            case 1: return new Position(0,9); // rechte Ecke
            case 2: return new Position(0,4); // Mitte oberste Reihe
            case 3: return new Position((int)(Math.random()*10), (int)(Math.random()*2));
        }
        return new Position(0,0);
    }

    private Position[] setzeBomben(){
        int z=(int)(Math.random()*4);
        Position fahne= listTmp.get(0).getPosition(); // Position der Fahne
        Position bomben [] = new Position[6];
        switch(z){
            case 0:
                if(fahne.getX()==0 && fahne.getY()==0) {
                    bomben[0].setX(fahne.getX() + 1);
                    bomben[0].setY(fahne.getY() + 1);
                }
        }
        return null;
    }
}

