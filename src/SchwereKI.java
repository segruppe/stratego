import java.util.ArrayList;

public class SchwereKI extends KI {

    Figur figur;
    Spielfeld spielfeld;
    ArrayList<Figur> listTmp;
    ArrayList<Figur> figuren;


    public SchwereKI(Spielfeld spielfeld) {
        listTmp = new ArrayList<Figur>(figurenSatzKI); // leichter zu pruefen welche figuren gesetzt sind
        figuren= new ArrayList<Figur>(figurenSatzKI);  // Zugriff auf jede Figur und deren Position
        this.spielfeld=spielfeld;
        //setzeStartAufstellung();
    }


    @Override
    public void setzeStartAufstellung() {
        // zuerst Fahne setzen:
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
        setzeNinFM();
        setzeAufklaerer();

        // restliche Figuren setzen
        int i=0;
        while(listTmp.size()>0){
            boolean figurGesetzt=false;
            while(!figurGesetzt){
                int x=(int)(Math.random()*4);
                int y=(int)(Math.random()*10);
                if(spielfeld.spielfeld[x][y].getFigur()==null){
                    spielfeld.figurInit(figuren.get(16+i+1),x,y);
                    listTmp.remove(0);
                    figurGesetzt=true;
                }
            }
            i++;
        }
    }

    public Figur holeFigur(){
        return null;
    }

    @Override
    public void macheZug() {}

    // setze Bomben, an bestimmte Positionen
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
                    // Fahne einkesslen
                    spielfeld.figurInit(figuren.get(1),0, 1);  // Bombe auf Spielfeld
                    listTmp.remove(0);
                    spielfeld.figurInit(figuren.get(2),1,0);
                    listTmp.remove(0);
                    // andere Ecke zur Tarnung einkesseln
                    spielfeld.figurInit(figuren.get(3),0,8);
                    listTmp.remove(0);
                    spielfeld.figurInit(figuren.get(4),1,9);
                    listTmp.remove(0);
                    // restliche Bomben wahllos setzen
                    anzahlBomben+=4;
                    break;

                // Fahne wird direkt eingekesselt, aber keine Tarnung
                case 1:
                    if (fahneY == 0) {
                        spielfeld.figurInit(figuren.get(1),0, 1); // Fahne wird eingekesselt
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(2),1,0);
                    } else {
                        spielfeld.figurInit(figuren.get(1),0,8); // andere Ecke
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(2),1,9);
                    }
                    listTmp.remove(0);
                    anzahlBomben+=2;
                    break;

                // Fahne wird von Unteroffizieren und aehnlichen geschuetzt, davor Bomben
                case 2:
                    // Fahne in linker Ecke
                    if (fahneY==0) {
                        spielfeld.figurInit(figuren.get(1),2,0);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(2), 2, 1);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(3),2,2);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(4),0,2);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(5),1,2);
                        listTmp.remove(0);

                    // Fahne in rechter Ecke
                    } else {
                        spielfeld.figurInit(figuren.get(1),2,9);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(2), 2, 8);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(3),2,7);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(4),0,7);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(5),1,7);
                        listTmp.remove(0);
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
                    spielfeld.figurInit(figuren.get(1),fahneX+1,fahneY);
                    listTmp.remove(0);
                    //Fahne steht nicht am Rand
                    if(fahneY>0 && fahneY<9){
                        System.out.println("recht links davor bombe");
                        // rechts und links daneben eine Bombe
                        spielfeld.figurInit(figuren.get(2),fahneX,fahneY-1);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(3),fahneX,fahneY+1);
                        listTmp.remove(0);
                        anzahlBomben+=3;
                    // Fahne steht am linken Rand
                    } else if(fahneY==0){
                        System.out.println("bombe davor und rechts");
                        // Bombe rechts setzen
                        spielfeld.figurInit(figuren.get(2),fahneX,fahneY+1);
                        listTmp.remove(0);
                        anzahlBomben+=2;
                    // Fahne steht am rechten Rand
                    } else {
                        System.out.println("bombe links und davor");
                        // Bombe links setzen
                        spielfeld.figurInit(figuren.get(2),fahneX,fahneY-1);
                        listTmp.remove(0);
                        anzahlBomben+=2;
                    }
                    break;
                case 1:
                    // vor die Fahne eine Figur dann Bombe
                    spielfeld.figurInit(figuren.get(1),fahneX+2,fahneY);
                    listTmp.remove(0);
                    //Fahne steht nicht am Rand
                    if(fahneY>1 && fahneY<8){
                        System.out.println("Figuren und Bomben davor und neben");
                        // rechts und links daneben eine Bombe
                        spielfeld.figurInit(figuren.get(2),fahneX,fahneY-2);
                        listTmp.remove(0);
                        spielfeld.figurInit(figuren.get(3),fahneX,fahneY+2);
                        listTmp.remove(0);
                        anzahlBomben+=3;
                        // Fahne steht am linken Rand
                    } else if(fahneY==0){
                        // Bombe rechts setzen
                        System.out.println("Figuren und Bomben davor und rechts");
                        spielfeld.figurInit(figuren.get(2),fahneX,fahneY+2);
                        listTmp.remove(0);
                        anzahlBomben+=2;
                        // Fahne steht am rechten Rand
                    } else {
                        // Bombe links setzen
                        System.out.println("Figuren und Bomben davor und links");
                        spielfeld.figurInit(figuren.get(2),fahneX,fahneY-2);
                        listTmp.remove(0);
                        anzahlBomben+=2;
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
                    spielfeld.figurInit(figuren.get(anzahlBomben+1), x, y);
                    listTmp.remove(0);
                    bombeGesetzt = true;
                }
            }
            anzahlBomben++;
        }
    }

    // Ninja und Feldmarschall sollen nebeneinander/hintereinander stehen
    private void setzeNinFM() {
        boolean ninjaGesetzt=false;
        int xNinja=0;
        int yNinja=0;
        // Ninja zufaellig setzen
        while(!ninjaGesetzt) {
            xNinja = (int) (Math.random() * 2);
            yNinja = (int) (Math.random() * 10);
            if (spielfeld.spielfeld[xNinja][yNinja].getFigur() == null) {
                spielfeld.figurInit(figuren.get(7), xNinja, yNinja);
                listTmp.remove(0);
                ninjaGesetzt=true;
            }
        }
        // Position Feldmarschall
        int zufall=(int)(Math.random()*4);
        // Ninja steht auf keinem Randfeld
        if(xNinja>0 && yNinja>0 && yNinja<9){
            switch (zufall){
                case 0:
                    if(spielfeld.spielfeld[xNinja+1][yNinja].getFigur()== null) {
                         spielfeld.figurInit(figuren.get(8), xNinja + 1, yNinja);
                     }
                    listTmp.remove(0);
                    break;
                case 1:
                    if(spielfeld.spielfeld[xNinja-1][yNinja].getFigur()== null) {
                        spielfeld.figurInit(figuren.get(8), xNinja - 1, yNinja);
                    }
                    listTmp.remove(0);
                    break;
                case 2: spielfeld.figurInit(figuren.get(8),xNinja,yNinja+1);
                        listTmp.remove(0);
                        break;
                case 3: spielfeld.figurInit(figuren.get(8),xNinja,yNinja-1);
                        listTmp.remove(0);
                        break;
            }
        // falls Ninja auf Randfeld steht
        // TODO: pruefen ob ausgewaehltes Feld frei ist
        } else if(xNinja==0 && yNinja!=0 && yNinja!=9) {
            spielfeld.figurInit(figuren.get(8),xNinja+1,yNinja);
            listTmp.remove(0);
        } else if(xNinja==3) {
            spielfeld.figurInit(figuren.get(8),xNinja+1,yNinja);
            listTmp.remove(0);
        } else if(yNinja>0){
            spielfeld.figurInit(figuren.get(8),xNinja,yNinja-1);
            listTmp.remove(0);
        } else {
            spielfeld.figurInit(figuren.get(8),xNinja,yNinja+1);
            listTmp.remove(0);
        }
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
                anzAufklaerer++;
            } else {
                x = (int) (Math.random() * 4);
                if(reiheBelegt(x)){
                    if(x<3) {
                        x++;
                    } else {
                        x--;
                    }
                }
                anzAufklaerer++;
            }
            // y-Koordinate bestimmen aus 0,1,4,5,8,9
            while (!yGefunden) {
                int y = (int) (Math.random() * 6);
                System.out.println(y);
                if (y == 2 || y == 3) {
                    y += 2;
                } else if (y == 4 || y == 5) {
                    y += 4;
                }
                if (spielfeld.spielfeld[x][y].getFigur() == null) {
                    spielfeld.figurInit(figuren.get(8 + anzAufklaerer), x, y);
                    listTmp.remove(0);
                    yGefunden = true;
                }
            }
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


