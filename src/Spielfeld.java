import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Spielfeld extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public JPanel panelButton;
	public JPanel panelBottom;
	JTextPane infoMessage = new JTextPane();
    protected ButtonFigurVerkn spielfeld[][];
	public ArrayList<Integer> wasser = new ArrayList<Integer>(){{add(42); add(43); add(46); add(47); add(52); add(53); add(56); add(57);}}; //Wasserfelder zum einfachen ueberpruefen
	private JButton button = new JButton("abbrechen");

	// Variablen fuer das setzen von Figuren
	private static boolean firstClickPerformed = false; //wenn erster Klick getaetigt wurde
	private static Position firstClickPosition; //temporaerer Speicher fuer die zuerst angeklickte Position
	private static boolean spielstart = true;
	private static boolean warteAufSetzen = false;
	private static Figur wartendeFigur = null;

	// Hinzufuegen aller Figuren in eine Liste // Team 1
	static ArrayList<Figur> figurenSatzSpieler = new ArrayList<Figur>(){{
		add(new Fahne(1));
		add(new Bombe(1));
		add(new Bombe(1));
		add(new Bombe(1));
		add(new Bombe(1));
		add(new Bombe(1));
		add(new Bombe(1));
		add(new Ninja(1));
		add(new Aufklaerer(1));
		add(new Aufklaerer(1));
		add(new Aufklaerer(1));
		add(new Aufklaerer(1));
		add(new Aufklaerer(1));
		add(new Aufklaerer(1));
		add(new Aufklaerer(1));
		add(new Aufklaerer(1));
		add(new Mineur(1));
		add(new Mineur(1));
		add(new Mineur(1));
		add(new Mineur(1));
		add(new Mineur(1));
		add(new Unteroffizier(1));
		add(new Unteroffizier(1));
		add(new Unteroffizier(1));
		add(new Unteroffizier(1));
		add(new Leutnant(1));
		add(new Leutnant(1));
		add(new Leutnant(1));
		add(new Leutnant(1));
		add(new Hauptmann(1));
		add(new Hauptmann(1));
		add(new Hauptmann(1));
		add(new Hauptmann(1));
		add(new Major(1));
		add(new Major(1));
		add(new Major(1));
		add(new Oberst(1));
		add(new Oberst(1));
		add(new General(1));
		add(new Feldmarschall(1));
	}};

	static ArrayList<Figur> figurenSatzSpieler_clone = new ArrayList<Figur>(figurenSatzSpieler);

    public Spielfeld() {
        super("Stratego - Spiel");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);

        // Programm beim Schliessen des Fensters beenden
        // kann nachher weg, ist aber sinnvoll zum testen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(5, 5));

        //spielfeld erzeugen
        spielfeld = new ButtonFigurVerkn[10][10];

		// Feld initalisieren
        for(int i=0; i<spielfeld.length; i++) {
        	for(int j=0; j<spielfeld[0].length; j++) {
            	if(! ((i==4 || i==5) && (j==2 || j==3 || j==6 || j==7))) {
            		// Alle grasspielfelder setzen
            		spielfeld[i][j] = new ButtonFigurVerkn("grün", i*10+j);
            	} else {
            		// Wasserspielfelder
            		spielfeld[i][j] = new ButtonFigurVerkn("blau", i*10+j);
				}
        	}
        }

        // Panels auf GridLayout erzeugen
        panelButton = new JPanel(new GridLayout(10, 10));
		panelBottom = new JPanel(new GridLayout(2,1));


		// Textfeld dem BottomPanel hinzufuegen, beschriften und uneditable machen
		panelBottom.add(infoMessage);
		infoMessage.setText("Beliebigen Button druecken um zu starten");
		infoMessage.setEditable(false);

		// Roten Hintergrund für das Panel setzen, damit unsichtbare Figuren-Felder nicht grau sind
		panelButton.setBackground(new Color(207, 4, 0));
        // Buttons auf panel packen


		// Listener fuer Buttons
		for(ButtonFigurVerkn[] i:spielfeld) {
			for(ButtonFigurVerkn j: i){
				j.getButton().addActionListener(this);
			}
		}
		// abbrechen Button hinzufuegen
		button.addActionListener(this);


        //Panels auf Frame packen
        getContentPane().add(BorderLayout.CENTER, panelButton);
		getContentPane().add(BorderLayout.PAGE_END, panelBottom);
        // sichtbar machen
        setVisible(true);
		panelAktualisieren();
    }



	/*
	Setzt Spielfiguren um, reagiert auf Mausklick
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("abbrechen")) {
			// Damit count wieder auf 0 gesetzt wird
			ButtonFigurVerkn test = new ButtonFigurVerkn();
			// Spielstart zuruecksetzen
			spielstart = true;
			warteAufSetzen = false;
			figurenSatzSpieler = new ArrayList<Figur>(figurenSatzSpieler_clone);
			// Fenster schliessen
			dispose();
			// Menue wieder aufrufen
			new Menue();
		} else {

			// Figuren setzen des Spielers
			if(spielstart && !warteAufSetzen) {
				// Sind noch Figuren im pool?
				if(figurenSatzSpieler.size() > 0) {
					wartendeFigur = figurenSatzSpieler.remove(0);
					warteAufSetzen = true;
					infoMessage.setText(wartendeFigur.toString()+ " wird als erstes gesetzt. Bitte das gewuenschte Feld anklicken");
				}
			} else if(spielstart && warteAufSetzen) {
				int number = Integer.parseInt(e.getActionCommand());
				if(number/10 < 6 || spielfeld[number/10][number%10].getFigur() != null) {
					// Wenn ein falsches Feld angeklickt wurde
					infoMessage.setText(wartendeFigur.toString()+ " bitte auf eins Ihrer Felder, das noch nicht belegt ist setzen");
				} else {
					// Figur wird gesetzt
					figurInit(wartendeFigur, number/10, number%10);
					warteAufSetzen = false;

					if(figurenSatzSpieler.size() > 0) {
						wartendeFigur = figurenSatzSpieler.remove(0);
						warteAufSetzen = true;
						infoMessage.setText(wartendeFigur.toString()+ " wird als naechstes gesetzt. Bitte das gewuenschte Feld anklicken");
					} else {
						infoMessage.setText("Alle Figuren gesetzt. Spielstart!");
						spielstart = false;
						warteAufSetzen = false;
					}
				}
			} else if (Spielablauf.kiGezogen){

				int number = Integer.parseInt(e.getActionCommand());

				if (wasser.contains(number)) {
					System.out.println("das ist wasser");
				} else {
					// TODO: Reichweite der Figuren
					if (firstClickPerformed) {
						if (!(number / 10 == firstClickPosition.getX() && number % 10 == firstClickPosition.getY())) {
							// Wenn nicht der gleiche Button gedrueckt wird und keine eigene Figur dort liegt
							if(spielfeld[number/10][number%10].getFigur() == null || spielfeld[number/10][number%10].getFigur().getTeam() != 1) {
								// Zug durchfuehren
								figurSetzen(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur(), number / 10, number % 10);
							} else {
								infoMessage.setText("Zielfeld ist von einer eigenen Figur belegt");
							}
						}
						firstClickPerformed = false;
						infoMessage.setText("Sie sind am Zug, bitte eine eigene Figur auswaehlen");
					} else {
						firstClickPosition = new Position(number / 10, number % 10);
						// Wenn auf dem angeklickten Feld eine eigene Figur steht
						if (!(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur() == null) && spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur().getTeam() == 1) {
							if(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur().getIstBewegbar()){
								infoMessage.setText(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur().toString() + " ausgewahlt. Wohin soll die Figur gesetzt werden?");
								firstClickPerformed = true;
							} else {
								infoMessage.setText("Diese Figur kann sich nicht bewegen");
							}
						}
					}

				}
			}

			//panelAktualisieren();
		}// ende Else vom abbrechen Button
	}


    public static void main(String[] args) {
        //@SuppressWarnings("unused")
		//Spielfeld spielfeld = new Spielfeld();
    }

	// Aktualisiert das gesamte Panel (die Buttons)
	public void panelAktualisieren() {
		panelButton.removeAll();
		panelBottom.removeAll();

          // Durchlaueft alle Zeilen(i) des Spielfelds -> Reihe von Buttons
		for(ButtonFigurVerkn[] i: spielfeld) {
            // Durchlauft jede Spalte von i -> Jeweils 1 einziger Button
			for(ButtonFigurVerkn j : i) {
				//j.getButton().removeActionListener(this);
				panelButton.add(j.getButton());
				//j.getButton().addActionListener(this);
			}
		}
		panelBottom.add(infoMessage);
		panelBottom.add(button, BorderLayout.PAGE_END);
	}

	// Feld wieder zu einem gruenen Feld machen
    public void feldZuruecksetzen(Position pos) {
		spielfeld[pos.getX()][pos.getY()] = new ButtonFigurVerkn("green", pos.getX() * 10 + pos.getY());
		spielfeld[pos.getX()][pos.getY()].getButton().addActionListener(this);
	}

	// x und y sind Koordinaten der neuen Position
    public void figurSetzen(Figur a, int x, int y) {
		ButtonFigurVerkn tmp = spielfeld[a.getPosition().getX()][a.getPosition().getY()];
		tmp.getButton().setActionCommand(Integer.toString(x*10+y));
		feldZuruecksetzen(a.getPosition());
		//spielfeld[a.getPosition().getX()][a.getPosition().getY()] = new ButtonFigurVerkn("green", a.getPosition().getX()*10+a.getPosition().getY());
		a.setPosition(new Position(x, y));

		int sieger = -1;
		if(a.getTeam() == 2) {
			if(spielfeld[x][y].getFigur() != null && spielfeld[x][y].getFigur().getTeam() == 1) {
				setEnabled(false);
				Figurenkampf fk = new Figurenkampf(a, spielfeld[x][y].getFigur(), this);
				sieger = fk.vergleicheStaerke();
			} else if(spielfeld[x][y].getFigur() == null) {
				spielfeld[x][y] = new ButtonFigurVerkn(a, "red");
				spielfeld[x][y].getButton().addActionListener(this);
			}
			if(sieger == 0) {
				// Unentschieden
				feldZuruecksetzen(new Position(x, y));
				//spielfeld[x][y] = new ButtonFigurVerkn("green", x*10+y);
			} else if(sieger == 1) {
				// KI gewinnt
				spielfeld[x][y] = new ButtonFigurVerkn(a, "red");
				spielfeld[x][y].getButton().addActionListener(this);
			}
		} else {
			// Spieler greift an
			if(spielfeld[x][y].getFigur() != null && spielfeld[x][y].getFigur().getTeam() == 2) {
				setEnabled(false);
				Figurenkampf fk = new Figurenkampf(a, spielfeld[x][y].getFigur(), this);
				sieger = fk.vergleicheStaerke();
			} else if(spielfeld[x][y].getFigur() == null) {
				spielfeld[x][y] = tmp;
			}
			if(sieger == 0) {
				// Unentschieden
				feldZuruecksetzen(new Position(x, y));
				//spielfeld[x][y] = new ButtonFigurVerkn("green", x*10+y);
			} else if(sieger == 1) {
				// Spieler gewinnt
				spielfeld[x][y] = tmp;
			}
		}

		panelAktualisieren();
	}

	public void figurSetzen(Figur a, Position pos) {
		figurSetzen(a, pos.getX(), pos.getY());
    }

	// Methode zum initialisieren eines Feldes mit einer Figur
	public void figurInit(Figur a, int x, int y) {
		a.setPosition(new Position(x, y));
		// Setzt eine Figur und gibt ihr direkt die richtige Nummer
		if(a.getTeam() == 2) {
			spielfeld[x][y] = new ButtonFigurVerkn(a, "red");
		} else {
			spielfeld[x][y] = new ButtonFigurVerkn(a, x * 10 + y);
		}
		spielfeld[x][y].getButton().addActionListener(this);
		// Setzt die Position der Figur
		panelAktualisieren();
	}



	public boolean spielfeldVergleichen(Spielfeld spielfeld2) {
		int length = this.spielfeld.length;
		for(int i=0; i<length; i++){
			for(int j=0; j<length; j++){
				// Wenn ein Element des Spielfeldes unterschiedlich ist, gebe false zurueck
				if(this.spielfeld[i][j] != spielfeld2.spielfeld[i][j])
					return false;
			}

		}
		// Ansonsten true
		return true;
	}


    // Gibt Figur auf der Position zurueck
    // wenn Position auf Spielfeld, sonst null
    public Figur getFigur(int x, int y) {
            return spielfeld[x][y].getFigur();
    }

	public void spielfeldSpeichern() {
		// Noch uberlegen wie wir das speichern
		/*

		 * [3][3] - so ungefaehr vielleicht?
		 * typ:team typ:team null
		 *  null typ:team null
		 *  typ:team null typ:team
		 *
		 **/

	}

	public void spielfeldLaden(File datei) {
		// haengt von Speicherung ab
	}


}
