import java.io.*;

/**
 * Klasse, um den Spielstand zu Speichern oder zu Laden
 *
 * @see Spielfeld
 * @see Spielablauf
 */
public class SpeichernLaden {
    static String dateiname = "SAVEGAME.DAT";
    Spielfeld spielfeld;
    Spielablauf ablauf;

    /**
     * Konstruktor, um das Spielfeld zu setzen
     *
     * @param spielfeld Spielfeld auf dem Figuren liegen
     */
    public SpeichernLaden(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
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
            System.out.println("Datei konnte nicht gespeichern werden.");
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
                    spielfeld.infoKi.schreibeFigur(spielfeld.spielfeld[i][j].getFigur(), spielfeld.spielfeld[i][j].getFigur().getPosition());
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
}
