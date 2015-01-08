import java.util.ArrayList;

public class InfoKI {
    private String[][] spielerFiguren = new String[10][10];
    private ArrayList<String> geschlageneSpielerFiguren = new ArrayList<String>();
    private ArrayList<Integer> geschlageneKiFiguren = new ArrayList<Integer>();

    public InfoKI() {
        for(int i=6; i<10; i++){
            for(int j=0; j<10; j++){
                spielerFiguren[i][j]="unbewegt";
            }
        }
    }

    // Nach figurSetzen ausfuehren!!!!!

    // TODO: JavaDoc
    // Umschreiben des Feldes, nach jedem Zug der KI
    public void schreibeFigur (Figur figur, Position altePosition) {
        // Figur auf neue Position schreiben
        if (!figur.istBekannt()) {
            // wenn Figur mehr als 1 Feld gezogen wurde, muss es sich um einen Aufklaerer handeln
            if(Math.abs(figur.getPosition().getX()-altePosition.getX())>1 || Math.abs(figur.getPosition().getY()-altePosition.getY())>1){
                System.out.println("Aufklaerer");
                spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = figur.getStaerke()+"";
                // jetzt ist die Figur bekannt
                figur.setIstBekannt(true);
            } else {
                spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = "bewegt";
            }

        } else {
            spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = figur.getStaerke()+"";
        }
        // alte Position loeschen
        spielerFiguren[altePosition.getX()][altePosition.getY()] = null;
        ausgabe();
    }

    // Vor dem eigentlichen figurSetzen aufrufen !!!
// TODO: JavaDoc
    // Geschlagene des Spielers
    public void loescheFigur(Figur figur) {
        // Alte Position der Figur auf null setzen. KI muss diese nicht mehr kennen
        System.out.println("x,y:" + figur.getPosition().getX() + " , " + figur.getPosition().getY());
        spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = null;
        // Hinzufuegen zur Liste, der geschlagenen Figuren
        geschlageneSpielerFiguren.add(figur.getStaerke()+"");
        //ausgabe();
    }
    // TODO: JavaDoc
    // geschlagene Figuren der KI
    public void loescheKiFigur(Figur figur){
        System.out.println(figur.toString() + "  " + figur.getId());
        getGeschlageneKiFiguren().add(figur.getId());
    }
    // TODO: loeschen
    public void ausgabe() {
        for (int i=0 ; i<=9; i++) {
            for (int j=0; j<=9 ; j++) {
                System.out.print(spielerFiguren[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
    }
    // TODO: JavaDoc
    public String toString(){
        String s="";
          for (int i=0 ; i<=9; i++) {
            for (int j=0; j<=9 ; j++) {
                s+=spielerFiguren[i][j] + "|";
            }
            s+="\n";
        }
        return s;
    }
    // TODO: JavaDoc
    public String[][] getSpielerFiguren () {
        return spielerFiguren;
    }
    // TODO: JavaDoc
    public ArrayList<String> getGeschlageneSpielerFiguren () {
        return geschlageneSpielerFiguren;
    }
    // TODO: JavaDoc
    public ArrayList<Integer> getGeschlageneKiFiguren() { return geschlageneKiFiguren;}
}
