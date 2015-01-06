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
    private ArrayList<Zug> zugMoeglich;

    /**
     * Erzeugen der Schweren KI
     *
     * @param spielfeld Spielfeld auf dem die Figuren gesetzt werden
     */
    public SchwereKI(Spielfeld spielfeld,boolean laden) {
        listTmp = new ArrayList<Figur>(figurenSatzKI); // leichter zu pruefen welche figuren gesetzt sind

        this.spielfeld = spielfeld;
        zugMoeglich = new ArrayList<Zug>();
        if(laden) {
            figuren=new ArrayList<Figur>();
            for(int zeile=0; zeile<10; zeile++){
                for(int spalte=0; spalte<10; spalte++){
                    if(spielfeld.spielfeld[zeile][spalte].getFigur()!=null && spielfeld.getFigur(zeile,spalte).getTeam()==2) {
                        figuren.add(spielfeld.spielfeld[zeile][spalte].getFigur());
                    }
                }
            }
        } else {
            figuren = new ArrayList<Figur>(figurenSatzKI);  // Zugriff auf jede Figur und deren Position
        }

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
                    // in diese Ecke einen Unteroffizier, Leutnant oder Hauptmann (Liste 21 - 32)
                    int zufall=(int)(Math.random()* (32-21)+21);

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
                    int z1=(int)(Math.random()*(32-21)+21);
                    int z2=(int)(Math.random()*(31-21)+21);
                    int z3=(int)(Math.random()*(30-21)+21);
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
        ArrayList <Zug> tmp = zuegeGeschlagen();
        if(tmp.size()!=0){
            zugMoeglich=tmp;
        }
        try {
            Thread.sleep(500);
            System.out.println("SLEEP");
        } catch (Exception e) {
            System.exit(-1);
        }
        Zug besterZug=waehleBestenZug();
        // wenn kein bester Zug existiert, wird zufaellig ein Zug ausgewaehlt
        if(besterZug==null){
            int zufall = (int) (Math.random() * zugMoeglich.size());
            besterZug = zugMoeglich.get(zufall);
        }
        // betroffene Figur in ArrayList suchen und an die entsprechende Stelle setzen
        for(int i=0; i<figuren.size(); i++){
                if(figuren.get(i).getId()==besterZug.getFigur().getId()){
                    spielfeld.figurSetzen(figuren.get(i),besterZug.getPosition());
                    break;
                }
        }
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



    // prueft ob gesamte Reihe belegt
    private boolean reiheBelegt(int x){
        for(int i=0; i<10; i++){
            if(spielfeld.spielfeld[x][i].getFigur()==null && (i==0 || i==1 || i==4 || i==5 || i==8 || i==9)){
                return false;
            }
        }
        return true;
    }

    // sortiert alle Zuege aus, bei denen eine Figur der KI geschlagen werden wuerde
    private ArrayList<Zug> zuegeGeschlagen(){
        ArrayList<Zug> uebrigeFiguren = new ArrayList<Zug>();
        // iteriert ueber jede Figur in Liste zugMoeglich
        for(int j=0; j<zugMoeglich.size(); j++ ){
            Figur aktFigur=zugMoeglich.get(j).getFigur();
            Position neuePosition=zugMoeglich.get(j).getPosition();
            // trifft auf eine gegnerische Figur
            if(spielfeld.getFigur(neuePosition.getX(),neuePosition.getY())!=null &&spielfeld.getFigur(neuePosition.getX(),neuePosition.getY()).getTeam()==1){
                // wenn die Figur bekannt ist und die KI-Figur die Spieler-Figur schlaegt kann der Zug als weiterhin
                // moeglich betrachtet werden
                if (figurBekannt(neuePosition) && vergleiche(aktFigur,Integer.parseInt(spielfeld.infoKi.getSpielerFiguren()[neuePosition.getX()][neuePosition.getY()])) ) {
                    uebrigeFiguren.add(new Zug(neuePosition,aktFigur));
                // gegnerische Figur ist nicht bekannt, jedoch definitiv keine Bombe, aber angreifende Figur ist nicht sehr wichtige Figur
                } else if(!figurBekannt(neuePosition) && spielfeld.infoKi.getSpielerFiguren()[neuePosition.getX()][neuePosition.getY()].equals("bewegt") && aktFigur.getStaerke()<8 && aktFigur.getStaerke()>4){
                    uebrigeFiguren.add(new Zug(neuePosition,aktFigur));
                // gegnerische Figuren die staerker sind als eigene wurden bereits geschlagen
                // } else if(hoehereFigurGeschlagen(aktFigur.getStaerke())){
                // uebrigeFiguren.add(aktFigur);
                } else if(!figurBekannt(neuePosition) ){
                    uebrigeFiguren.add(new Zug(neuePosition,aktFigur));
                }

            //  leeres Feld
            } else if(spielfeld.getFigur(neuePosition.getX(),neuePosition.getY())==null){
                uebrigeFiguren.add(new Zug(neuePosition,aktFigur));
            }
        }
        return uebrigeFiguren;
    }

    // prueft ob eine Figur bekannt ist
    private boolean figurBekannt(Position pos) {
        if (!(spielfeld.infoKi.getSpielerFiguren()[pos.getX()][pos.getY()]==null || spielfeld.infoKi.getSpielerFiguren()[pos.getX()][pos.getY()].equals("unbewegt")
                || spielfeld.infoKi.getSpielerFiguren()[pos.getX()][pos.getY()].equals("bewegt"))) {
            System.out.println(true);
            return true;
        } else{
            return false;
        }
    }
    //prueft ob KI-Figur gewinnt
    private boolean vergleiche(Figur figur, int staerke){
        // Fahne wird geschlagen
        if (staerke==1) {
            return true;
        }
        // Unentschieden
        if (figur.getStaerke() == staerke) {
            return false;
        // Mineur schlaegt Bombe
        } else if (figur.getStaerke() == 4 && staerke == 12) {
            return true;
        // Ninja schlaegt Feldmarschall, wenn er angreift
        } else if (figur.getStaerke() == 2 && staerke == 11) {
            return true;
        // eigene Figur staerker als Gegner
        } else if(figur.getStaerke() > staerke) {
            return true;
        // sonst gewinnt gegner
        } else {
            return false;
        }
    }

    // prueft ob Figuren die staerker sind, als eigene Staerke bereits geschlagen wurden
    // TODO: richtige ueberpruefung
    private boolean hoehereFigurGeschlagen(int staerke){
        for(String figur: spielfeld.infoKi.getGeschlageneSpielerFiguren()){
            int gegnerStaerke=Integer.parseInt(figur);
            if(gegnerStaerke>staerke){
                return false;
            }
        }
        return true;
    }

    // sortiert Liste nach bestmoeglichem Zug
    private Zug waehleBestenZug(){
        // vergleicht jeden Zug mit jedem anderen und positioniert den besten Zug an den anfang der Liste
        for(int i=0; i<zugMoeglich.size()-1; i++){
            Zug aktuellerZug=zugMoeglich.get(i);
            // Zug endet auf einem Feld mit gegnerischer Figur
            if(spielfeld.spielfeld[aktuellerZug.getPosition().getX()][aktuellerZug.getPosition().getY()].getFigur()!=null){
                // Fahne des Gegners wird geschlagen
                if(figurBekannt(aktuellerZug.getPosition()) && spielfeld.spielfeld[aktuellerZug.getPosition().getX()][aktuellerZug.getPosition().getY()].getFigur().getStaerke()==1){
                    return aktuellerZug;
                }
            }
            int aktuellerWert=bewerteZug(aktuellerZug);
            for(int j=i+1; j<zugMoeglich.size();j++){
                Zug vergleichsZug=zugMoeglich.get(j);
                if(spielfeld.spielfeld[vergleichsZug.getPosition().getX()][vergleichsZug.getPosition().getY()].getFigur()!=null){
                    // Fahne des Gegners wird geschlagen
                    if(figurBekannt(vergleichsZug.getPosition()) && spielfeld.spielfeld[vergleichsZug.getPosition().getX()][vergleichsZug.getPosition().getY()].getFigur().getStaerke()==1){
                        return aktuellerZug;
                    }
                }
                int vergleichsWert=bewerteZug(vergleichsZug);
                if(aktuellerWert<vergleichsWert){
                    // aktueller Zug schlechter als vergleichsZug, also tauschen...
                } else {
                    // aktuellerZug besser als vergleichsZug, nichts tun...
                }
            }
        }
        return null;
    }

    // bewertet einen Zug von 0 bis 5
    private int bewerteZug(Zug aktuell){
        Position neuePos=aktuell.getPosition();
        Figur aktuelleFigur=aktuell.getFigur();
        Figur gegner=null;
        if(spielfeld.spielfeld[neuePos.getX()][neuePos.getY()].getFigur()!=null){
            gegner=spielfeld.getFigur(neuePos.getX(),neuePos.getY());
        }
        // wenn eine Bombe sicher geschlagen werden kann, dann oberste Prioritaet (5)
        if(gegner!=null && figurBekannt(neuePos) && gegner.getStaerke()==12 && aktuelleFigur.getStaerke()==4 ){
            return 5;
        }
        // der gegnerische Feldmarschall wird geschlagen
        if(gegner!=null && figurBekannt(neuePos) && gegner.getStaerke()==11 && aktuelleFigur.getStaerke()==2){
            return 4;
        }
        // eine gegnerische Figur wird sicher geschlagen
        if(gegner!=null && figurBekannt(neuePos) && vergleiche(aktuelleFigur,gegner.getStaerke())){
            return 3;
        }
        // Aufklaerer ist die aktuelle Figur und kann sich weit bewegen
        //if(aktuelleFigur.getStaerke()==3 && )
        return 0;
    }
}


