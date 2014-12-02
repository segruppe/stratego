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
        boolean tarnung;
        // zuerst Fahne setzen:
        int r=(int)(Math.random()*4);
        switch (r){
            case 0: this.figurenSatzKI.get(0).setPosition(new Position(0,0)); // linke Ecke
                    break;

            case 1: this.figurenSatzKI.get(0).setPosition(new Position(0, 9));break; // rechte Ecke
            case 2: this.figurenSatzKI.get(0).setPosition(new Position(0, 4));break; // Mitte oberste Reihe
            case 3: this.figurenSatzKI.get(0).setPosition(new Position((int)(Math.random()*2), (int)(Math.random()*10)));
        }
        spielfeld.panelButton.removeAll();

        spielfeld.figurInit(figurenSatzKI.get(0), figurenSatzKI.get(0).getPosition().getX(), figurenSatzKI.get(0).getPosition().getY());

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
        Position fahne=figurenSatzKI.get(0).getPosition(); // Position der Fahne
        Position bomben [] = new Position[6];
        int fahneX=fahne.getX();
        int fahneY=fahne.getY();
        // Fahne steht in einer der Ecken
        if(fahneX==0 && (fahneY==0 || fahneY==9)) {
            switch (z) {
                // Fahne wird direkt eingekesselt und Tarnung aufgebaut
                case 0:
                    bomben[0] = new Position(0, 1); // Fahne einkesslen
                    bomben[1] = new Position(1, 0);
                    bomben[2] = new Position(0, 8); // andere Ecke zur Tarnung einkesseln
                    bomben[3] = new Position(1, 9); // restliche Bomben wahllos setzen
                    break;
                // Fahne wird direkt eingekesselt, aber keine Tarnung
                case 1:
                    if (fahneY == 0) {
                        bomben[0] = new Position(0, 1); // Fahne einkesslen
                        bomben[1] = new Position(1, 0);
                    } else {
                        bomben[0] = new Position(0, 8); // andere Ecke zur Tarnung einkesseln
                        bomben[1] = new Position(1, 9);
                    }
                    break;
                // Fahne wird von Unteroffizieren und aehnlichen geschuetzt davor Bomben
                case 2:
                    break;
                case 3:
                    break;
            }
        // Fahne steht irgendwo in den ersten beiden Reihen
        } else{

        }
        // bisher nicht gesetzte Bomben muessen (zufaellig) gesetzt werden
        for(int i=0; i<bomben.length; i++){
            if(bomben[i]==null){
                int x= (int)(Math.random()*3);
                int y=(int)(Math.random()*10);
                // Test ob Feld belegt
            }
        }
        return null;
    }
}

