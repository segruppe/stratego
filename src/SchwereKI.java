import java.util.ArrayList;

/**
 * GUI-Klasse fuer die schwere KI. Erbt von KI
 *
 * @see Figur
 * @see Spielfeld
 */
public class SchwereKI extends KI {

    private Spielfeld spielfeld;
    private ArrayList<Figur> listTmp;
    private ArrayList<Figur> figuren;
    private ArrayList<Figur> zugMoeglich;

    /**
     * Erzeugen der Schweren KI
     *
     * @param spielfeld Spielfeld auf dem die Figuren gesetzt werden
     */
    public SchwereKI(Spielfeld spielfeld) {
        listTmp = new ArrayList<Figur>(figurenSatzKI); // leichter zu pruefen welche figuren gesetzt sind
        figuren= new ArrayList<Figur>(figurenSatzKI);  // Zugriff auf jede Figur und deren Position
        this.spielfeld=spielfeld;
        zugMoeglich=new ArrayList<Figur>();
    }

    @Override
    /**
     * Setzen einer Startaufstellung
     */
    public void setzeStartAufstellung() {
        // zuerst Fahne setzen: Fahne an Position 0 in Liste
        int r=(int)(Math.random()*4);
        switch (r){
            case 0:
                spielfeld.figurInit(figuren.get(0), 0, 0);// linke Ecke
                break;
            case 1:
                spielfeld.figurInit(figuren.get(0), 0, 9); // rechte Ecke
                break;
            case 2:
                spielfeld.figurInit(figuren.get(0), 0, 4); // Mitte oberste Reihe
                break;
            case 3:
                spielfeld.figurInit(figuren.get(0), (int) (Math.random() * 2), (int) (Math.random() * 10)); // zufaellige Position
                break;
        }
        listTmp.remove(0);
        setzeBomben();
        setzeNinjaFeldmarschall();
        setzeAufklaerer();

        // restliche Figuren setzen, ab Position 17 in der ArrayList
        int i=17;
        while(listTmp.size()>0){
            boolean figurGesetzt=false;

            // Probe ob Figur schon gesetzt ist
            if(figuren.get(i).getPosition()!=null) {
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

    private Figur holeFigur(){
        return null;
    }

    @Override
    /**
     * KI macht einen Zug
     */
    public void macheZug() {

        moeglicheZuege();
        zugMoeglich.clear();
    }

    // sammelt die moeglichen Zuege der KI
    private void moeglicheZuege(){
        // iteriert ueber Liste der aktuellen Figuren
        for(Figur i: figuren){
            // ueberpruefung ob aktuelle Figur bewegt werden darf
            if(i.getIstBewegbar() ){
                // Position der Figur
                int x=i.getPosition().getX();
                int y=i.getPosition().getY();
                // benachbartes Feld entweder frei oder von gegnerischer Figur belegt, darf nicht in Wasser liegen
                if(x<9 && !spielfeld.wasser.contains(10*(x+1)+y) && (spielfeld.getFigur(x+1,y)==null || spielfeld.getFigur(x+1,y).getTeam()==1)){
                    zugMoeglich.add(i);
                }
                if(x>0 && !spielfeld.wasser.contains(10*(x-1)+y) && (spielfeld.getFigur(x-1,y)==null || spielfeld.getFigur(x-1,y).getTeam()==1)){
                    zugMoeglich.add(i);
                }
                if(y<9  &&  !spielfeld.wasser.contains(10*x+y+1) && (spielfeld.getFigur(x,y+1)==null || spielfeld.getFigur(x,y+1).getTeam()==1)){
                    zugMoeglich.add(i);
                }
                if(y>0  &&  !spielfeld.wasser.contains(10*x+y-1)&& (spielfeld.getFigur(x,y-1)==null || spielfeld.getFigur(x,y-1).getTeam()==1)){
                    zugMoeglich.add(i);
                }
            }
        }
        System.out.println("Moegliche Zuege: " + zugMoeglich.size());
    }

    // setze Bomben, an bestimmte Positionen, in Liste von Position 1-6
    private void setzeBomben(){
        int z=(int)(Math.random()*3);
        Position fahne=figuren.get(0).getPosition(); // Position der Fahne
        int anzahlBomben=0;
        int fahneX=fahne.getX();
        int fahneY=fahne.getY();
        System.out.println("Fahne :" + fahneX + " " + fahneY);

        // Fahne steht in einer der Ecken
        if(fahneX==0 && (fahneY==0 || fahneY==9)) {
            switch (z) {
                // Fahne wird direkt eingekesselt und Tarnung aufgebaut
                case 0:
                    // Fahne einkesseln
                    spielfeld.figurInit(figuren.get(1),0, 1);  // Bombe auf Spielfeld
                    spielfeld.figurInit(figuren.get(2), 1, 0);
                    // andere Ecke zur Tarnung einkesseln
                    spielfeld.figurInit(figuren.get(3),0,8);
                    spielfeld.figurInit(figuren.get(4), 1, 9);
                    // in diese Ecke einen Unteroffizier, Leutnant oder Hauptmann (Liste 22 - 33)
                    int zufall=(int)(Math.random()* (33-22)+22);

                    // Fahne in linker Ecke
                    if(fahneY==0) {
                        spielfeld.figurInit(figuren.get(zufall), 0, 9);
                    } else {
                        spielfeld.figurInit(figuren.get(zufall), 0, 0);
                    }
                    listTmp.remove(zufall-1);
                    // restliche Bomben wahllos setzen
                    anzahlBomben+=4;
                    break;

                // Fahne wird direkt eingekesselt, aber keine Tarnung
                case 1:
                    if (fahneY == 0) {
                        spielfeld.figurInit(figuren.get(1), 0, 1); // Fahne wird eingekesselt
                        spielfeld.figurInit(figuren.get(2),1,0);
                    } else {
                        spielfeld.figurInit(figuren.get(1), 0, 8); // andere Ecke
                        spielfeld.figurInit(figuren.get(2),1,9);
                    }
                    anzahlBomben+=2;
                    break;

                // Fahne wird von Unteroffizieren und aehnlichen geschuetzt, davor Bomben
                case 2:
                    // Figuren zum Schutz
                    int z1=(int)(Math.random()*(33-22)+22);
                    int z2=(int)(Math.random()*(32-22)+22);
                    int z3=(int)(Math.random()*(31-22)+22);
                    // Fahne in linker Ecke
                    if (fahneY==0) {
                        spielfeld.figurInit(figuren.get(1), 2, 0);
                        spielfeld.figurInit(figuren.get(2), 2, 1);
                        spielfeld.figurInit(figuren.get(3), 2, 2);
                        spielfeld.figurInit(figuren.get(4),0,2);
                        spielfeld.figurInit(figuren.get(5), 1, 2);
                        // zuvor zufaellig bestimmte Figuren setzen und aus Liste loeschen
                        spielfeld.figurInit(figuren.get(z1),1,0);
                        listTmp.remove(z1 - 1);
                        spielfeld.figurInit(figuren.get(z2), 1, 1);
                        listTmp.remove(z2-2);
                        spielfeld.figurInit(figuren.get(z3),0,1);
                        listTmp.remove(z3-3);


                    // Fahne in rechter Ecke
                    } else {
                        spielfeld.figurInit(figuren.get(1), 2, 9);
                        spielfeld.figurInit(figuren.get(2), 2, 8);
                        spielfeld.figurInit(figuren.get(3), 2, 7);
                        spielfeld.figurInit(figuren.get(4),0,7);
                        spielfeld.figurInit(figuren.get(5), 1, 7);
                        // zuvor zufaellig bestimmte Figuren setzen und aus Liste loeschen
                        spielfeld.figurInit(figuren.get(z1), 1, 8);
                        listTmp.remove(z1 - 1);
                        spielfeld.figurInit(figuren.get(z2), 1, 9);
                        listTmp.remove(z2 - 2);
                        spielfeld.figurInit(figuren.get(z3), 0, 8);
                        listTmp.remove(z3 - 3);
                    }
                    anzahlBomben+=5;
                    break;
            }
        // Fahne steht irgendwo in den ersten beiden Reihen
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
                    // vor die Fahne eine Figur dann Bombe
                    spielfeld.figurInit(figuren.get(1), fahneX + 2, fahneY);
                    //Fahne steht nicht am Rand
                    if(fahneY>1 && fahneY<8){
                        // rechts und links daneben eine Bombe
                        spielfeld.figurInit(figuren.get(2),fahneX,fahneY-2);
                        spielfeld.figurInit(figuren.get(3), fahneX, fahneY + 2);
                        anzahlBomben+=3;
                        // Fahne steht am linken Rand
                    } else if(fahneY<8){
                        // Bombe rechts setzen
                        spielfeld.figurInit(figuren.get(2), fahneX, fahneY + 2);
                        anzahlBomben+=2;
                        // Fahne steht am rechten Rand
                    } else if(fahneY>1) {
                        // Bombe links setzen
                        spielfeld.figurInit(figuren.get(2), fahneX, fahneY - 2);
                        anzahlBomben += 2;
                        // gilt keiner der Faelle werden alle Bomben zufaellig gesetzt
                    }
                    break;
                case 2:
                    break;


            }
        }
        // bisher nicht gesetzte Bomben muessen (zufaellig) gesetzt werden
        while(anzahlBomben<6) {
            boolean bombeGesetzt=false;
            while(!bombeGesetzt) {
                // zufaellige Position bestimmen
                int x = (int) (Math.random() * 3);
                int y = (int) (Math.random() * 10);
                // Test ob Feld belegt
                if (spielfeld.spielfeld[x][y].getFigur() == null) {
                    spielfeld.figurInit(figuren.get(anzahlBomben + 1), x, y);
                    bombeGesetzt = true;
                }
            }
            anzahlBomben++;
        }
        // Bomben aus Liste loeschen
        for(int i=0; i<6; i++){
            listTmp.remove(i);
        }
    }

    // Ninja und Feldmarschall sollen nebeneinander/hintereinander stehen
    private void setzeNinjaFeldmarschall() {

        int xNinja=0;
        int yNinja=0;
        // speichern der moeglichen Positionen fuer Feldmarschall
        ArrayList<Position> freiesFeld = new ArrayList<Position>();

        // Ninja zufaellig setzen und pruefen ob mind. ein freies Feld neben/vor oder hinter Ninja fuer Feldmarschall
        while(true) {
            xNinja = (int) (Math.random() * 2);
            yNinja = (int) (Math.random() * 10);
            // zufaellige Position ist noch frei
            if (spielfeld.spielfeld[xNinja][yNinja].getFigur() == null) {
                // Position fuer Feldmarschall suchen
                // Feld liegt auf dem Spielfeld und ist frei
                if(spielfeld.spielfeld[xNinja+1][yNinja].getFigur()==null){
                    freiesFeld.add(new Position(xNinja+1,yNinja));
                }
                if(xNinja-1>=0 && spielfeld.spielfeld[xNinja-1][yNinja].getFigur()==null){
                    freiesFeld.add(new Position(xNinja-1,yNinja));
                }
                if(yNinja+1<=9 && spielfeld.spielfeld[xNinja][yNinja+1].getFigur()==null){
                    freiesFeld.add(new Position(xNinja,yNinja+1));
                }
                if(yNinja-1>=0 &&spielfeld.spielfeld[xNinja][yNinja-1].getFigur()==null){
                    freiesFeld.add(new Position(xNinja,yNinja-1));
                }
                if(freiesFeld.size()!=0) {
                    break;
                }
            }
        }
        // Ninja setzen
        spielfeld.figurInit(figuren.get(7), xNinja, yNinja);
        listTmp.remove(0);
        // zufaellige Position aus ArrayList auswaehlen
        int zufall=(int)(Math.random()*freiesFeld.size());
        // Feldmarschall an Position setzen und Liste leeren
        spielfeld.figurInit(figuren.get(8), freiesFeld.get(zufall).getX(), freiesFeld.get(zufall).getY());
        listTmp.remove(0);
    }

    // Aufklaerer moeglichst nicht auf einer hoehe mit Wasser, damit
    // sprungweite ausgenutzt werden kann
    private void setzeAufklaerer(){
        // mind. ein Aufklärer in jeder Zeile
        boolean yGefunden=false;
        int anzAufklaerer=0;
        while(anzAufklaerer<8) {
            yGefunden = false;
            int x;
            if (anzAufklaerer < 4) {
                x = anzAufklaerer;
            } else {
                x = (int) (Math.random() * 4);

            }
            while(reiheBelegt(x)){
                if(x<3) {
                    x++;
                } else {
                    x--;
                }
            }

            // y-Koordinate bestimmen aus 0,1,4,5,8,9
            while (!yGefunden) {
                int y = (int) (Math.random() * 6);
                if (y == 2 || y == 3) {
                    y += 2;
                } else if (y == 4 || y == 5) {
                    y += 4;
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

    // prueft ob gesamte Reihe belegt
    private boolean reiheBelegt(int x){
        for(int i=0; i<10; i++){
            if(spielfeld.spielfeld[x][i].getFigur()==null && (i==0 || i==1 || i==4 || i==5 || i==8 || i==9)){
                return false;
            }
        }
        return true;
    }
}


