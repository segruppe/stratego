import java.io.*;
import java.util.ArrayList;

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
    static boolean spielBeendet = false;

    /**
     * Konstruktor, um das Spielfeld zu setzen
     *
     * @param spielfeld Spielfeld auf dem Figuren liegen
     */
    public SpeichernLaden(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    /**
     * Konstruktor um alteFelder in Spielfeld zu initalisieren
     */
    public SpeichernLaden() {
    }

    /**
     * Speichern des aktuellen Spielfeldes in einer Datei
     */
    public void spielfeldSpeichern() {
        String string;
        try {
            FileWriter fw = new FileWriter(new File(dateiname));
            BufferedWriter bw = new BufferedWriter(fw);

            for (ButtonFigurVerkn[] i : spielfeld.spielfeld) {
                for (ButtonFigurVerkn j : i) {
                    if (j.getFigur() != null) {
                        string = j.getFigur().toString() + "," + j.getFigur().getTeam() + "," + j.getFigur().getId() + ";";
                        bw.append(string);
                    } else {
                        if (j.getButton().getBackground() == j.blue)
                            bw.append("w;");
                        else
                            bw.append("g;");
                    }
                }
                bw.append("\n");
            }

            string = "KI: " + Spielablauf.gegner + "\n";
            bw.append(string);
            string = "KI ist dran: " + Spielablauf.kiGezogen + "\n";
            bw.append(string);
            // Daten fuer die 2-Felder-Regel in Datei schreiben
            string = Spielfeld.letzteFigurKi + "\n";
            bw.append(string);
            string = Spielfeld.letzteKiRichtung + "\n";
            bw.append(string);
            string = Spielfeld.anzahlKi + "\n";
            bw.append(string);
            string = Spielfeld.letzteFigurSpieler + "\n";
            bw.append(string);
            string = Spielfeld.letzteSpielerRichtung + "\n";
            bw.append(string);
            string = Spielfeld.anzahlSpieler + "\n";
            bw.append(string);

            // speichert werte der InfoKI fuer schwere und mittlere KI
            bw.append(spielfeld.infoKi.toString());
            // geschlagene Figuren des Spielers
            bw.append(spielfeld.infoKi.getGeschlageneSpielerFiguren().size()+";");
            for (String i : spielfeld.infoKi.getGeschlageneSpielerFiguren()) {
                string = i + ";";
                bw.append(string);
            }
            bw.append("\n");
            // geschlagene Figuren der KI
            bw.append(spielfeld.infoKi.getGeschlageneKiFiguren().size() + ";");
            for (Integer j : spielfeld.infoKi.getGeschlageneKiFiguren()) {
                string = j + ";";
                bw.append(string);
            }
            bw.append("\n");
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


            for (int i = 0; i < spielfeld.spielfeld.length; i++) {
                // Array aus Figuren - in Reihenfolge fuer das Spielfeld
                String[] figuren = br.readLine().split(";");
                for (int j = 0; j < spielfeld.spielfeld[0].length; j++) {

                    // figur[0] = Figurname, figur[1] = Team der Figur, figur[2] = ID der Figur
                    String[] figur = figuren[j].split(",");

                    if (figur.length == 3) {
                        if (figur[0].equals("Aufklaerer")) {
                            spielfeld.figurInit(new Aufklaerer(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Bombe")) {
                            spielfeld.figurInit(new Bombe(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Fahne")) {
                            spielfeld.figurInit(new Fahne(Integer.parseInt(figur[1])), i, j);
                            if (Integer.parseInt(figur[1]) == 1) {
                                fahneTeam1 = true;
                            } else {
                                fahneTeam2 = true;
                            }
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("General")) {
                            spielfeld.figurInit(new General(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Hauptmann")) {
                            spielfeld.figurInit(new Hauptmann(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Leutnant")) {
                            spielfeld.figurInit(new Leutnant(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Major")) {
                            spielfeld.figurInit(new Major(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Mineur")) {
                            spielfeld.figurInit(new Mineur(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Ninja")) {
                            spielfeld.figurInit(new Ninja(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Oberst")) {
                            spielfeld.figurInit(new Oberst(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Unteroffizier")) {
                            spielfeld.figurInit(new Unteroffizier(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        } else if (figur[0].equals("Feldmarschall")) {
                            spielfeld.figurInit(new Feldmarschall(Integer.parseInt(figur[1])), i, j);
                            spielfeld.spielfeld[i][j].getFigur().setId(Integer.parseInt(figur[2]));
                        }
                    }

                    // InfoKI Figuren bekannt machen
                    //if (spielfeld.spielfeld[i][j].getFigur() != null && Integer.parseInt(figur[1]) == 1) {
                      //  spielfeld.infoKi.schreibeFigur(spielfeld.spielfeld[i][j].getFigur(), spielfeld.spielfeld[i][j].getFigur().getPosition());
                    //}
                }
            }

            // Wenn ein Spieler schon verloren hat
            if (!fahneTeam1 || !fahneTeam2) {
                spielBeendet = true;
                for (ButtonFigurVerkn[] i : spielfeld.spielfeld) {
                    for (ButtonFigurVerkn j : i) {
                        j.getButton().removeActionListener(spielfeld);
                    }
                }
                if (!fahneTeam2) {
                    spielfeld.infoMessage.setText("Spiel wurde bereits gewonnen");
                } else {
                    spielfeld.infoMessage.setText("Spiel wurde bereits verloren");
                }
                return;
            }

            // KI setzen
            String ki = br.readLine();
            if (ki.contains("Einfach")) {
                ablauf = new Spielablauf(spielfeld, new EinfacheKI(spielfeld));
            } else if (ki.contains("Mittel")) {
                ablauf = new Spielablauf(spielfeld, new MittlereKI(spielfeld));
            } else if (ki.contains("Schwer")) {
                ablauf = new Spielablauf(spielfeld, new SchwereKI(spielfeld, true));
            }

            // Setzen wer als naechstes zieht
            String zug = br.readLine();
            Spielablauf.kiGezogen = zug.contains("false");

            // Daten fuer die 2-Felder-Regel einlesen
            Spielfeld.letzteFigurKi = Integer.parseInt(br.readLine());
            Spielfeld.letzteKiRichtung = Integer.parseInt(br.readLine());
            Spielfeld.anzahlKi = Integer.parseInt(br.readLine());
            Spielfeld.letzteFigurSpieler = Integer.parseInt(br.readLine());
            Spielfeld.letzteSpielerRichtung = Integer.parseInt(br.readLine());
            Spielfeld.anzahlSpieler = Integer.parseInt(br.readLine());

            spielfeld.alteFelder.alteFelderSpeichern(spielfeld.spielfeld);
            spielfeld.panelAktualisieren();
            spielfeld.setSpielstart(false);

            // String Array der InfoKi wieder fuellen
            String infoKi[][]=new String[10][10];
            for(int i=0; i<10; i++){
                String zeile[]=br.readLine().split(";");
                System.arraycopy(zeile, 0, infoKi[i], 0, zeile.length);
            }
            spielfeld.infoKi.setSpielerFiguren(infoKi);
            // Liste der geschlagenen Spielerfiguren eventuell fuellen
            String spielerFiguren[]=br.readLine().split(";");
            for(int i=1; i<spielerFiguren.length; i++){
                spielfeld.infoKi.setGeloeschteFiguren(Integer.parseInt(spielerFiguren[i]),'s');
            }
            // Liste der geschlagenen KI-Figuren eventuell fuellen
            String kiFiguren[]=br.readLine().split(";");
            for(int j=1; j<kiFiguren.length; j++){
                spielfeld.infoKi.setGeloeschteFiguren(Integer.parseInt(kiFiguren[j]),'k');
            }

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
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[0].length; j++) {
                if (feld[i][j].getFigur() != null) {
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
     * <p/>
     * Speichert dazu alle bisher in der Datei "alteFelder.sav" gespeicherten Felder temporaer im Speicher
     * und vergleich alle mit dem uebergebenen Feld.
     *
     * @param search ButtonFigurVerkn[][]
     *               nach uebergebenem Feld wird in der Datei gesucht
     * @param von    Position
     * @param nach   Position
     *               von-nach: potentieller Zug
     */
    public boolean contains(ButtonFigurVerkn[][] search, Position von, Position nach) {
        Integer[][] aktuellesFeld = new Integer[10][10];

        for (int i = 0; i < search.length; i++) {
            for (int j = 0; j < search[0].length; j++) {
                if (search[i][j].getFigur() != null) {
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


        felderschleife:
        for (Integer[][] iteriertesFeld : alteFelder) {
            for (int i = 0; i < aktuellesFeld.length; i++) {
                for (int j = 0; j < aktuellesFeld[0].length; j++) {
                    if (!aktuellesFeld[i][j].equals(iteriertesFeld[i][j])) {
                        // Gehe zum naechsten alten Feld wenn das aktuelle unterschiedlich ist
                        continue felderschleife;
                    }
                }
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
