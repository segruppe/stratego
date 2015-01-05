import java.util.ArrayList;

/**
 * GUI-Klasse fuer die mittlere KI. Erbt von KI
 *
 * @see Figur
 * @see Spielfeld
 */
public class MittlereKI extends KI {

    Figur figur;
    Spielfeld spielfeld;
    ArrayList<Figur> listTmp;
    private ArrayList<Figur> figuren;
    private ArrayList<Zug> zugMoeglich;

    /**
     * Erzeugen der Mittleren KI
     *
     * @param spielfeld Spielfeld auf dem die Figuren gesetzt werden
     */
    public MittlereKI(Spielfeld spielfeld) {
        listTmp = new ArrayList<Figur>(figurenSatzKI);
        figuren= new ArrayList<Figur>(figurenSatzKI);  // Zugriff auf jede Figur und deren Position
        this.spielfeld=spielfeld;
        zugMoeglich=new ArrayList<Zug>();
    }

    @Override
    /**
     * Setzen einer Startaufstellung
     */
    public void setzeStartAufstellung() {
        // zuerst Fahne setzen: Fahne an Position 0 in Liste
        int r=(int)(Math.random()*3);
        switch (r){
            case 0:
                spielfeld.figurInit(figuren.get(0), 0, 0);// linke Ecke
                break;
            case 1:
                spielfeld.figurInit(figuren.get(0), 0, 9); // rechte Ecke
                break;
            case 2:
                // zufaellige Position in ersten 3 Zeilen
                spielfeld.figurInit(figuren.get(0), (int) (Math.random() * 3), (int) (Math.random() * 10));
                break;
        }
        listTmp.remove(0); // Fahne loeschen
        setzeBomben();
        setzeNinjaFeldmarschall();
        setzeAufklaerer();

        // restliche Figuren setzen, ab Position 17 in der ArrayList
        int i=17;
        while(listTmp.size()>0){
            boolean figurGesetzt=false;

            // Probe ob Figur schon gesetzt ist
            if(listTmp.get(0).getId()!=figuren.get(i).getId()) {
                figurGesetzt = true;
            }
            // solange nicht gesetzt, freie Position suchen
            while(!figurGesetzt){
                int x=(int)(Math.random()*4);
                int y=(int)(Math.random()*10);
                if(spielfeld.spielfeld[x][y].getFigur()==null){
                    spielfeld.figurInit(figuren.get(i), x, y);
                    listTmp.remove(0);
                    figurGesetzt=true;
                }
            }
            i++;
        }
    }

    // setze Bomben, an bestimmte Positionen, in Liste von Position 1-6
    private void setzeBomben(){
        int z=(int)(Math.random()*2);
        Position fahne=figuren.get(0).getPosition(); // Position der Fahne
        int anzahlBomben=0;
        int fahneX=fahne.getX();
        int fahneY=fahne.getY();

        // Fahne steht in einer der Ecken
        if(fahneX==0 && (fahneY==0 || fahneY==9)) {
            switch (z) {
                // Fahne wird direkt eingekesselt und Tarnung aufgebaut
                case 0:
                    // Fahne einkesseln
                    spielfeld.figurInit(figuren.get(1), 0, 1);  // Bombe auf Spielfeld
                    spielfeld.figurInit(figuren.get(2), 1, 0);
                    // andere Ecke zur Tarnung einkesseln
                    spielfeld.figurInit(figuren.get(3), 0, 8);
                    spielfeld.figurInit(figuren.get(4), 1, 9);
                    // in diese Ecke einen Unteroffizier, Leutnant oder Hauptmann (Liste 22 - 33)
                    int zufall = (int) (Math.random() * (32 - 21) + 21);

                    // Fahne in linker Ecke
                    if (fahneY == 0) {
                        spielfeld.figurInit(figuren.get(zufall), 0, 9);
                    } else {
                        spielfeld.figurInit(figuren.get(zufall), 0, 0);
                    }
                    listTmp.remove(zufall - 1);
                    // restliche Bomben wahllos setzen
                    anzahlBomben += 4;
                    break;

                // Fahne wird direkt eingekesselt, aber keine Tarnung
                case 1:
                    if (fahneY == 0) {
                        spielfeld.figurInit(figuren.get(1), 0, 1); // Fahne wird eingekesselt
                        spielfeld.figurInit(figuren.get(2), 1, 0);
                    } else {
                        spielfeld.figurInit(figuren.get(1), 0, 8); // andere Ecke
                        spielfeld.figurInit(figuren.get(2), 1, 9);
                    }
                    anzahlBomben += 2;
                    break;
            }

            // Fahne steht irgendwo in den ersten drei Reihen
        } else {
            switch(z) {
                // Fahne schuetzen
                case 0:
                    // vor die Fahne eine Bombe
                    spielfeld.figurInit(figuren.get(1), fahneX + 1, fahneY);
                    //Fahne steht nicht am Rand
                    if(fahneY>0 && fahneY<9){
                        // rechts und links daneben eine Bombe
                        spielfeld.figurInit(figuren.get(2),fahneX,fahneY-1);
                        spielfeld.figurInit(figuren.get(3), fahneX, fahneY + 1);
                        anzahlBomben+=3;
                        // Fahne steht am linken Rand
                    } else if(fahneY==0){
                        // Bombe rechts setzen
                        spielfeld.figurInit(figuren.get(2), fahneX, fahneY + 1);
                        anzahlBomben+=2;
                        // Fahne steht am rechten Rand
                    } else {
                        // Bombe links setzen
                        spielfeld.figurInit(figuren.get(2), fahneX, fahneY - 1);
                        anzahlBomben+=2;
                    }
                    break;
                case 1:
                    // vor die Fahne eine Bombe
                    spielfeld.figurInit(figuren.get(1), fahneX + 1, fahneY);
                    anzahlBomben+=1;
                    break;
            }
        }
        // bisher nicht gesetzte Bomben muessen (zufaellig) gesetzt werden
        while(anzahlBomben<6) {
            boolean bombeGesetzt=false;
            while(!bombeGesetzt) {
                // zufaellige Position bestimmen
                int x = (int) (Math.random() * 4);
                int y = (int) (Math.random() * 10);
                // Test ob Feld belegt
                if (spielfeld.spielfeld[x][y].getFigur() == null) {
                    spielfeld.figurInit(figuren.get(anzahlBomben + 1), x, y);
                    bombeGesetzt = true;
                }
            }
            anzahlBomben++;
        }
        // Alle Bomben aus Liste loeschen
        for(int i=0; i<6; i++){
            listTmp.remove(i);
        }
    }

    // Ninja und Feldmarschall zufaellig, aber unabhaengig voneinander, in letzten beiden Reihen setzen
    private void setzeNinjaFeldmarschall() {
        // Erst Ninja dann Feldmarschall setzen
        for (int i=0 ; i<=1 ; i++) {
            while (true) {
                int x = (int) (Math.random() * 2);
                int y = (int) (Math.random() * 10);
                if (spielfeld.spielfeld[x][y].getFigur() == null) {
                    spielfeld.figurInit(figuren.get(i + 7), x, y);
                    listTmp.remove(0);
                    break;
                }
            }
        }
    }

    private void setzeAufklaerer() {
        // mind. ein Aufklärer in jeder Zeile
        boolean yGefunden;
        int anzAufklaerer=0;
        while(anzAufklaerer<8) {
            yGefunden = false;
            int x;
            if (anzAufklaerer < 4) {
                x = anzAufklaerer;
            } else {
                x = (int) (Math.random() * 4);

            }

            // y-Koordinate bestimmen
            while (!yGefunden) {
                int y;
                if (anzAufklaerer<4) { // Ersten 4 Aufklaerer stehen nicht in einer Spalte mit Wasser
                    y = (int) (Math.random() * 6);
                    if (y == 2 || y == 3) {
                        y += 2;
                    } else if (y == 4 || y == 5) {
                        y += 4;
                    }
                } else {
                    y = (int) (Math.random() * 10);
                }
                if (spielfeld.spielfeld[x][y].getFigur() == null) {
                    spielfeld.figurInit(figuren.get(8 + anzAufklaerer+1), x, y);
                    listTmp.remove(0);
                    yGefunden = true;
                }
            }
            anzAufklaerer++;
        }
    }


    @Override
    /**
     * KI macht einen Zug
     */
    public void macheZug() {
        // Liste fuellen, welche Zuege moeglich
        moeglicheZuege();
        // kann die KI keine Figur bewegen, hat der Spieler gewonnen
        if(zugMoeglich.isEmpty()){
            new SpielBeendet(1);
        }

        try {
            Thread.sleep(500);
            System.out.println("SLEEP");
        } catch (Exception e) {
            System.exit(-1);
        }

        int zufall = (int) (Math.random() * zugMoeglich.size());
        Zug aktZug = zugMoeglich.get(zufall);
        spielfeld.figurSetzen(aktZug.getFigur(), aktZug.getPosition());

        zugMoeglich.clear();
    }

    // sammelt die moeglichen Zuege der KI
    private void moeglicheZuege(){
        // iteriert ueber Liste der aktuellen Figuren
        // zuerst Probe ob im letzten Zug eine KI-Figur geschlagen wurde, diese
        // wird aus der Liste entfernt
        for(int k=0;k<figuren.size(); k++){
            if(spielfeld.infoKi.getGeschlageneKiFiguren().contains(figuren.get(k).getId())){
                figuren.remove(k);
            }
        }
        for(Figur i: figuren){

            // ueberpruefung ob aktuelle Figur bewegt werden darf
            if(i.getIstBewegbar() ){
                System.out.println(i.toString());
                // Position der Figur
                int x=i.getPosition().getX();
                int y=i.getPosition().getY();
                // moegliche Zuege vom Aufklaerer
                if(i.getStaerke()==3){
                    System.out.println("Aufklaerer: x" + x + " y: " + y);
                    int felder=1;
                    while(x+felder<=9 && !spielfeld.wasser.contains(10*(x+felder)+y) && spielfeld.getFigur(x+felder,y)==null) {
                        zugMoeglich.add(new Zug(new Position(x+felder,y),i));
                        felder++;
                    }
                    // Aufklaerer koennte gegnerische Figur schlagen, die auf der Position liegt
                    if (x+felder<=9 && spielfeld.getFigur(x+felder,y)!=null && spielfeld.getFigur(x+felder,y).getTeam()==1) {
                        //felder++;
                        zugMoeglich.add(new Zug(new Position(x+felder,y),i));
                    }
                    System.out.println(felder + " nach unten");
                    felder=1;
                    while(x-felder>=0 && !spielfeld.wasser.contains(10*(x-felder)+y) && spielfeld.getFigur(x-felder,y)==null) {
                        zugMoeglich.add(new Zug(new Position(x-felder,y),i));
                        felder++;
                    }
                    // Aufklaerer koennte gegnerische Figur schlagen, die auf der Position liegt
                    if (x-felder>=0 && spielfeld.getFigur(x-felder,y)!=null &&spielfeld.getFigur(x-felder,y).getTeam()==1) {
                        //felder++;
                        zugMoeglich.add(new Zug(new Position(x-felder,y),i));
                    }
                    felder=1;
                    while(y+felder<=9 && !spielfeld.wasser.contains(10*x+y+felder) && spielfeld.getFigur(x,y+felder)==null) {
                        zugMoeglich.add(new Zug(new Position(x,y+felder),i));
                        felder++;
                    }
                    // Aufklaerer koennte gegnerische Figur schlagen, die auf der Position liegt
                    if (y+felder<=9 && spielfeld.getFigur(x,y+felder)!=null && spielfeld.getFigur(x,y+felder).getTeam()==1) {
                        //felder++;
                        zugMoeglich.add(new Zug(new Position(x,y+felder),i));
                    }
                    felder=1;
                    while(y-felder>=0 && !spielfeld.wasser.contains(10*x+y-felder) && spielfeld.getFigur(x,y-felder)==null) {
                        zugMoeglich.add(new Zug(new Position(x,y-felder),i));
                        felder++;
                    }
                    // Aufklaerer koennte gegnerische Figur schlagen, die auf der Position liegt
                    if (y-felder>=0 && spielfeld.getFigur(x,y-felder)!=null && spielfeld.getFigur(x,y-felder).getTeam()==1) {
                        //felder++;
                        zugMoeglich.add(new Zug(new Position(x,y-felder),i));
                    }
                } else {
                    // benachbartes Feld entweder frei oder von gegnerischer Figur belegt, darf nicht in Wasser liegen
                    if (x < 9 && !spielfeld.wasser.contains(10 * (x + 1) + y) && (spielfeld.getFigur(x + 1, y) == null || spielfeld.getFigur(x + 1, y).getTeam() == 1)) {
                        zugMoeglich.add(new Zug(new Position(x + 1, y), i));
                    }
                    if (x > 0 && !spielfeld.wasser.contains(10 * (x - 1) + y) && (spielfeld.getFigur(x - 1, y) == null || spielfeld.getFigur(x - 1, y).getTeam() == 1)) {
                        zugMoeglich.add(new Zug(new Position(x - 1, y), i));
                    }
                    if (y < 9 && !spielfeld.wasser.contains(10 * x + y + 1) && (spielfeld.getFigur(x, y + 1) == null || spielfeld.getFigur(x, y + 1).getTeam() == 1)) {
                        zugMoeglich.add(new Zug(new Position(x, y + 1), i));
                    }
                    if (y > 0 && !spielfeld.wasser.contains(10 * x + y - 1) && (spielfeld.getFigur(x, y - 1) == null || spielfeld.getFigur(x, y - 1).getTeam() == 1)) {
                        zugMoeglich.add(new Zug(new Position(x, y - 1), i));
                    }
                }
            }
        }
        System.out.println("Moegliche Zuege: " + zugMoeglich.size());
    }


}
