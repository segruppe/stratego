import java.util.ArrayList;
import java.util.HashMap;

public class EinfacheKI extends KI {
    Figur figur;
    Spielfeld spielfeld;
    ArrayList<Figur> listTmp;
    ArrayList<Figur> zugMoeglich = new ArrayList<Figur>();

    public EinfacheKI(Spielfeld spielfeld) {
        // Kopieren der statischen ArrayList
        listTmp = new ArrayList<Figur>(figurenSatzKI);
        this.spielfeld = spielfeld;
        setzeStartAufstellung();
    }

    @Override
    // Setzen einer zufaelligen Startaufstellung
    public void setzeStartAufstellung() {
        spielfeld.panelButton.removeAll();

        // Fahne soll in einer der beiden vorderen Reihen stehen
        // Zufaellige Auswahl von 2 oder 3 (Zeile)
        int fahneX = (int) Math.floor(Math.random() * (3 - 2 + 1)) + 2;
        // Zufaellige Auswahl von 0-9 (Spalte)
        int fahneY = (int) (Math.random() * 10);
        figur = listTmp.get(0); // Fahne
        listTmp.remove(0);
        spielfeld.figurInit(figur, fahneX, fahneY); // Fahne setzen

        for (int i = 0; i < grenzeX; i++) {
            for (int j = 0; j < grenzeY; j++) {
                if (!(i == fahneX && j == fahneY)) {
                    // Feld ist nicht durch die Fahne belegt -> Figur setzen
                    figur = holeFigur();
                    spielfeld.figurInit(figur, i, j);
                }
            }
        }
        spielfeld.panelAktualisieren();
        System.out.println("Position Fahne: " + fahneX + " " +fahneY );
    }

    // Funktion gibt eine zufaellige Figur, die noch nicht gesetzt wurde, zurueck
    public Figur holeFigur() {
        Figur figur;
        int zahl = (int) (Math.random() * listTmp.size());
        figur = listTmp.get(zahl);
        listTmp.remove(zahl);
        return figur;
    }

    @Override
    // Ki macht einen zufaelligen Zug
    public void macheZug() {
        figurenZugMoeglich();
        System.out.println("Anzahl möglicher Züge: " + zugMoeglich.size());
        // wenn kein Zug moeglich ist, Spieler hat gewonnen
        if (zugMoeglich.isEmpty()) {
            new SpielBeendet(1); // Spieler hat gewonnen
        }
        // Welche Figur wird gezogen?
        int t = (int) (Math.random() * zugMoeglich.size());
        Figur figur = zugMoeglich.get(t);

        int y = figur.getPosition().getY();
        int x = figur.getPosition().getX();
        // Neue Position muss auf Feld liegen, Kein Wasser sein, Feld leer, Feld von Gegner besetzt
        // nach links
        if (y-1>=0 && !spielfeld.wasser.contains(10*x+y-1) && (spielfeld.getFigur(x, y- 1) == null || spielfeld.getFigur(x, y-1).getTeam() == 1)) {
            // Figur auf dem Spielfeld auf neue Position setzen
            spielfeld.figurSetzen(figur, new Position(x, y-1));
        // nach unten
        } else if (x+1<=9 && !spielfeld.wasser.contains(10*(x+1)+y) && (spielfeld.getFigur(x+1, y) == null || spielfeld.getFigur(x+1, y).getTeam() == 1)) {
            // Figur auf dem Spielfeld auf neue Position setzen
            spielfeld.figurSetzen(figur, new Position(x+1, y));
        // nach rechts
        } else if (y+1<=9 && !spielfeld.wasser.contains(10*x+y+1) && (spielfeld.getFigur(x, y+1) == null || spielfeld.getFigur(x, y+1).getTeam() == 1)) {
            // Figur auf dem Spielfeld auf neue Position setzen
            spielfeld.figurSetzen(figur, new Position(x, y+1));
        // nach oben
        } else {
            if (x-1>=0 && !spielfeld.wasser.contains(10*(x-1)+y) ) {
                // Figur auf dem Spielfeld auf neue Position setzen
                spielfeld.figurSetzen(figur, new Position(x-1, y));
            }
        }
        // Liste wieder leeren
        zugMoeglich.clear();
        //spielfeld.panelAktualisieren();
    }

    // Erstellen einer Liste mit Figuren, die im Zug bewegt werden koennen
    public void figurenZugMoeglich() {
        // Zeilen
        for (int i = 0; i < 10; i++) {
            // Spalten
            for (int j = 0; j < 10; j++) {
                // Auf dem Feld muss eine Figur (bewegbare) sein und sie muss KI Team gehoeren
                if (spielfeld.getFigur(i, j) != null && spielfeld.getFigur(i, j).getTeam() == 2 && spielfeld.getFigur(i, j).getIstBewegbar()) {
                    // Ein benachbartes Feld muss null oder vom Gegner belegt sein, damit Figur bewegt werden kann
                    // nach oben setzen
                    if (i>=1 && !spielfeld.wasser.contains(10*(i-1)+j) && (spielfeld.getFigur(i-1,j)==null || spielfeld.getFigur(i-1,j).getTeam()==1)) {
                        zugMoeglich.add(spielfeld.getFigur(i,j));
                    // nach unten setzen
                    } else if (i<9 && !spielfeld.wasser.contains(10*(i+1)+j) && (spielfeld.getFigur(i+1,j)==null || spielfeld.getFigur(i+1,j).getTeam()==1)) {
                        zugMoeglich.add(spielfeld.getFigur(i,j));
                    // nach links setzen
                    } else if (j>=1 && !spielfeld.wasser.contains(10*i+(j-1)) && (spielfeld.getFigur(i,j-1)==null || spielfeld.getFigur(i,j-1).getTeam()==1)) {
                        zugMoeglich.add(spielfeld.getFigur(i,j));
                    // nach rechts setzen
                    } else if (j<9 && !spielfeld.wasser.contains(10*i+(j+1)) && (spielfeld.getFigur(i,j+1)==null || spielfeld.getFigur(i,j+1).getTeam()==1)) {
                        zugMoeglich.add(spielfeld.getFigur(i,j));
                    }
                }
            }
        }
    }

}