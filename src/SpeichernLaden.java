import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.geometry.Pos;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Klasse, um den Spielstand zu Speichern oder zu Laden
 *
 * @see Spielfeld
 * @see Spielablauf
 */
public class SpeichernLaden {
    String dateiname = "SAVEGAME.DAT";
    Spielfeld spielfeld;
    Spielablauf ablauf;
    ArrayList<Integer[][]> alteFelder = new ArrayList<Integer[][]>();

    /**
     * Konstruktor, um das Spielfeld zu setzen
     *
     * @param spielfeld Spielfeld auf dem Figuren liegen
     */
    public SpeichernLaden(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    /**
     * Konstruktor um alte Spielfelder in einer Datei zu speichern
     */
    public SpeichernLaden() {
        this.dateiname = "alteFelder.sav";
    }

    /**
     * Speichern des aktuellen Spielfeldes in einer Datei
     */
    public void spielfeldSpeichern() {
                try {
                    FileWriter fw = new FileWriter(new File(dateiname));
                    BufferedWriter bw = new BufferedWriter(fw);

                    for(ButtonFigurVerkn[] i: spielfeld.spielfeld) {
                        for(ButtonFigurVerkn j: i) {
                            if(j.getFigur() != null) {
                                bw.append(j.getFigur().toString()+","+j.getFigur().getTeam() + ";");
                            } else {
                                if (j.getButton().getBackground() == j.blue)
                                    bw.append("w;");
                                else
                                    bw.append("g;");
                            }
                        }
                        bw.append("\n");
                    }

                    bw.append("KI: "+Spielablauf.gegner+"\n");
                    bw.append("Spieler ist dran: "+Spielablauf.kiGezogen+"\n");

                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Datei konnte nicht gespeichert werden.");
                    e.printStackTrace();
                }
            }

            /**
             * Laden eines gespeicherten Spielstandes aus der Datei und setzen der Figuren auf das Spielfeld
             */
        public void spielfeldLaden() {
            // Beide Teams muessen noch jeweils eine Fahne haben, sonst ist das Spiel bereits beendet
            boolean fahneTeam1 = false;
            boolean fahneTeam2 = false;
            try {
                FileReader fr = new FileReader(new File(dateiname));
                BufferedReader br = new BufferedReader(fr);


            for(int i=0; i<spielfeld.spielfeld.length; i++) {
                // Array aus Figuren - in Reihenfolge fuer das Spielfeld
                String[] figuren = br.readLine().split(";");
                for(int j=0; j<spielfeld.spielfeld[0].length; j++) {

                    // figur[0] = Figurname, figur[1] = Team der Figur
                    String[] figur = figuren[j].split(",");

                    if(figur.length == 2) {
                        if (figur[0].equals("Aufklaerer")) {
                            spielfeld.figurInit(new Aufklaerer(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Bombe")) {
                            spielfeld.figurInit(new Bombe(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Fahne")) {
                            spielfeld.figurInit(new Fahne(Integer.parseInt(figur[1])), i, j);
                            if(Integer.parseInt(figur[1]) == 1) {
                                fahneTeam1 = true;
                            } else {
                                fahneTeam2 = true;
                            }
                        } else if (figur[0].equals("General")) {
                            spielfeld.figurInit(new General(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Hauptmann")) {
                            spielfeld.figurInit(new Hauptmann(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Leutnant")) {
                            spielfeld.figurInit(new Leutnant(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Major")) {
                            spielfeld.figurInit(new Major(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Mineur")) {
                            spielfeld.figurInit(new Mineur(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Ninja")) {
                            spielfeld.figurInit(new Ninja(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Oberst")) {
                            spielfeld.figurInit(new Oberst(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Unteroffizier")) {
                            spielfeld.figurInit(new Unteroffizier(Integer.parseInt(figur[1])), i, j);
                        } else if (figur[0].equals("Feldmarschall")) {
                            spielfeld.figurInit(new Feldmarschall(Integer.parseInt(figur[1])), i, j);
                        }
                    }

                    // InfoKI Figuren bekannt machen
                    if(spielfeld.spielfeld[i][j].getFigur() != null && Integer.parseInt(figur[1]) == 1) {
                        spielfeld.infoKi.schreibeFigur(spielfeld.spielfeld[i][j].getFigur(), spielfeld.spielfeld[i][j].getFigur().getPosition());
                    }
                }
            }

            if(!fahneTeam1 || !fahneTeam2) {
                for(ButtonFigurVerkn[] i : spielfeld.spielfeld) {
                    for(ButtonFigurVerkn j : i) {
                        j.getButton().removeActionListener(spielfeld);
                    }
                }
                spielfeld.infoMessage.setText("Spiel wurde bereits gewonnen");
                return;
            }

            String ki = br.readLine();
            if(ki.contains("Einfach")) {
                ablauf = new Spielablauf(spielfeld, new EinfacheKI(spielfeld));
            } else if(ki.contains("Mittel")) {
                ablauf = new Spielablauf(spielfeld, new MittlereKI(spielfeld));
            } else if(ki.contains("Schwer")) {
                ablauf = new Spielablauf(spielfeld, new SchwereKI(spielfeld));
            }

            String zug = br.readLine();
            if(zug.contains("true")) {
                ablauf.kiGezogen = true;
            } else {
                ablauf.kiGezogen = false;
            }

            spielfeld.panelAktualisieren();
            spielfeld.setSpielstart(false);
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Spielstand konnte nicht gefunden werden");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fehler beim Laden");
            e.printStackTrace();
        }
    }


    /**
     * Methode um alte Spielfelder zu schreiben
     */
    public void alteFelderSpeichern(ButtonFigurVerkn[][] feld) {
        Integer[][] temp = new Integer[10][10];

        // Konvertiere ButtonFigurVerkn[][] zu Integer[][]
        for(int i=0; i<feld.length; i++) {
            for(int j=0; j<feld[0].length; j++) {
                if(feld[i][j].getFigur() != null) {
                    temp[i][j] = feld[i][j].getFigur().getId();
                } else {
                    if (feld[i][j].getButton().getBackground() == feld[i][j].blue)
                        temp[i][j] = -2;
                    else
                        temp[i][j] = -1;
                }
            }
        }

        // Fuege temporär angelegtes temp Feld den altenFeldern hinzu
        alteFelder.add(temp);
    }


    /**
     * Methode um ein Spielfeld in gespeicherten Feldern zu finden
     *
     * Speichert dazu alle bisher in der Datei "alteFelder.sav" gespeicherten Felder temporaer im Speicher
     * und vergleich alle mit dem uebergebenen Feld.
     *
     * @param search ButtonFigurVerkn[][]
     *               nach uebergebenem Feld wird in der Datei gesucht
     * @param von Position
     * @param nach Position
     *             von-nach: potentieller Zug
     *
     */
    public boolean contains(ButtonFigurVerkn[][] search, Position von, Position nach) {
        Integer[][] aktuellesFeld = new Integer[10][10];

        for(int i=0; i<search.length; i++) {
            for(int j=0; j<search[0].length; j++) {
                if(search[i][j].getFigur() != null) {
                    aktuellesFeld[i][j] = search[i][j].getFigur().getId();
                } else {
                    if (search[i][j].getButton().getBackground() == search[i][j].blue)
                        aktuellesFeld[i][j] = -2;
                    else
                        aktuellesFeld[i][j] = -1;
                }
            }
        }
        // Zug auf dem zu testendem Spielfeld ausprobieren
        aktuellesFeld[nach.getX()][nach.getY()] = aktuellesFeld[von.getX()][von.getY()];
        aktuellesFeld[von.getX()][von.getY()] = -1;



        felderschleife: for(Integer[][] iteriertesFeld : alteFelder) {
            for(int i=0; i<aktuellesFeld.length; i++) {
                for(int j=0; j<aktuellesFeld[0].length; j++) {
                    if(aktuellesFeld[i][j] != iteriertesFeld[i][j]) {
                        // Gehe zum naechsten alten Feld wenn das aktuelle unterschiedlich ist
                        continue felderschleife;
                    }
                    //System.out.print(aktuellesFeld[i][j] + " = " + iteriertesFeld[i][j] + " | ");
                }
                //System.out.println();
            }
            // Wenn das Feld nicht uebersprungen wurde, ist alles gleich, also gebe true zurück
            return true;
        }
        return false;
    }


    /**
     * Methode zum leeren aller alten Felder
     */
    public void clear() {
        alteFelder.clear();
    }

}
