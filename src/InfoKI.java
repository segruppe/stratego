import java.util.ArrayList;

/**
 * Klasse, damit KI sich Infos zum aktuellen Spiel speichern kann
 */
public class InfoKI {
    private String[][] spielerFiguren = new String[10][10];
    private ArrayList<String> geschlageneSpielerFiguren = new ArrayList<String>();
    private ArrayList<Integer> geschlageneKiFiguren = new ArrayList<Integer>();
    private ArrayList<Position>gegnerischeBomben=new ArrayList<Position>();

    /**
     * Konstruktor fuer InfoKI
     * schreibt fuer alle Figuren des Spielers "unbewegt" in das String-Array spielerFiguren
     */
    public InfoKI() {
        for(int i=6; i<10; i++){
            for(int j=0; j<10; j++){
                spielerFiguren[i][j]="unbewegt";
            }
        }
    }

    /**
     * Figur des Spielers wird im String-Array als "bewegt" oder mit ihrer Staerke gespeichert
     * nach einem Figurenkampf wird die Staerke gespeichert, da die Figur dann der KI bekannt ist
     * Aufklaerer werden mit ihrer Stärke gespeichert sobald sie sich weiter als ein Feld bewegen
     * @param figur Figur des Spielers, die bewegt wurde
     * @param altePosition alte Position der bewegten Figur
     */
    // Umschreiben des Feldes, nach jedem Zug der KI
    public void schreibeFigur (Figur figur, Position altePosition) {
        // Figur auf neue Position schreiben
        if (!figur.istBekannt()) {
            // wenn Figur mehr als 1 Feld gezogen wurde, muss es sich um einen Aufklaerer handeln
            if(Math.abs(figur.getPosition().getX()-altePosition.getX())>1 || Math.abs(figur.getPosition().getY()-altePosition.getY())>1) {
                System.out.println("Aufklaerer");
                spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = figur.getStaerke() + "";
                // jetzt ist die Figur bekannt
                figur.setIstBekannt(true);
            } else if(Character.isDigit(spielerFiguren[altePosition.getX()][altePosition.getY()].charAt(0))){
                spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()]=figur.getStaerke()+"";
                if(figur.getStaerke()==12){
                    // eine Bombe des Spielers wurde gefunden
                    gegnerischeBomben.add(figur.getPosition());
                }
                figur.setIstBekannt(true);
            } else {
                spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = "bewegt";
            }

        } else {
            spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = figur.getStaerke()+"";
            if(figur.getStaerke()==12){
                // eine Bombe des Spielers wurde gefunden
                gegnerischeBomben.add(figur.getPosition());
            }
        }
        // alte Position loeschen
        spielerFiguren[altePosition.getX()][altePosition.getY()] = null;
    }

    /**
     * Fuegt die geschlagene Figur des Spielers zur entsprechenden ArrayList hinzu
     * @param figur geschlagene Figur des Spielers
     */
    // Geschlagene des Spielers
    public void loescheFigur(Figur figur) {
        // Alte Position der Figur auf null setzen. KI muss diese nicht mehr kennen
        System.out.println("x,y:" + figur.getPosition().getX() + " , " + figur.getPosition().getY());
        spielerFiguren[figur.getPosition().getX()][figur.getPosition().getY()] = null;
        // Hinzufuegen zur Liste, der geschlagenen Figuren
        geschlageneSpielerFiguren.add(figur.getStaerke()+"");
        //ausgabe();
    }

    /**
     * Figur soll in ArrayList gespeichert werden
     * @param figur KI-Figur, die geschlagen wurde
     *
     */
    // geschlagene Figuren der KI
    public void loescheKiFigur(Figur figur){
        System.out.println(figur.toString() + "  " + figur.getId());
        getGeschlageneKiFiguren().add(figur.getId());
    }

    /**
     * ueberschreiben der ToString() Methode
     * @return String zur Ausgabe des String Arrays
     */
    public String toString(){
        String s="";
          for (int i=0 ; i<=9; i++) {
            for (int j=0; j<=9 ; j++) {
                s+=spielerFiguren[i][j] + ";";
            }
            s+="\n";
        }
        return s;
    }

    /**
     * gibt das String Array zurueck(getter)
     * @return String Array spielerFiguren
     */
    public String[][] getSpielerFiguren () {
        return spielerFiguren;
    }

    /**
     * Speichern eines String-Arrays in spielerFiguren (beim Laden nötig)
     * @param s String Array welches Daten für spielerFiguren enthält
     */
    public void setSpielerFiguren(String [][]s){
        spielerFiguren=s;
    }

    /**
     * fuegt Figuren über ihre Staerke bzw. id zu der entsprechenden ArrayList der geloeschten Figuren hinzu
     * (entweder geschlageneKIFiguren oder geschlageneSpielerFiguren
     * @param i wenn KI figur: id der Figur, sonst Staerke
     * @param c wenn es eine KI Figur ist 'k'
     */
    public void setGeloeschteFiguren(int i, char c){
        if(c=='k'){
            geschlageneKiFiguren.add(i);
        } else {
            geschlageneSpielerFiguren.add(i+"");
        }
    }

    /**
     * getter für ArrayList geschlageneSpielerFiguren
     * @return ArrayList geschlageneSpielerFiguren
     */
    public ArrayList<String> getGeschlageneSpielerFiguren () {
        return geschlageneSpielerFiguren;
    }

    /**
     * Getter für ArrayList geschlageneKiFiguren
     * @return ArrayList geschlageneKiFiguren
     */
    public ArrayList<Integer> getGeschlageneKiFiguren() { return geschlageneKiFiguren;}

    /**
     * Getter für ArrayList gegnerischeBomben
     * @return ArrayList gegnerischeBomben
     */
    public ArrayList<Position>getGegnerischeBomben() { return gegnerischeBomben;}
}
