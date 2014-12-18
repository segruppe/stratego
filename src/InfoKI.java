import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;

public class InfoKI {
    private String[][] spielerFiguren = new String[10][10];
    private ArrayList<String> geschlageneSpielerFiguren = new ArrayList<String>();

    public InfoKI() {

    }

    // Nach figurSetzen ausfuehren!!!!!

    // Umschreiben des Feldes, nach jedem Zug der KI
    public void schreibeFigur (Figur figur, Position altePosition) {
        // Figur auf neue Position schreiben
        if (!figur.istBekannt()) {
            spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = "unbekannt";
        } else {
            spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = figur.toString();
        }
        // alte Position loeschen
        //spielerFiguren[altePosition.getX()][altePosition.getY()] = null;
        //ausgabe();
    }

    // Vor dem eigentlichen figurSetzen aufrufen !!!

    // Geschlagene Figuren
    public void loescheFigur(Figur figur) {
        // Alte Position der Figur auf null setzen. KI muss diese nicht mehr kennen
        spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = null;
        // Hinzufuegen zur Liste, der geschlagenen Figuren
        geschlageneSpielerFiguren.add(figur.toString());
        //ausgabe();
    }

    public void ausgabe() {
        for (int i=0 ; i<=9; i++) {
            for (int j=0; j<=9 ; j++) {
                System.out.print(spielerFiguren[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
    }

    public String[][] getSpielerFiguren () {
        return spielerFiguren;
    }

    public ArrayList<String> getGeschlageneSpielerFiguren () {
        return geschlageneSpielerFiguren;
    }
}
