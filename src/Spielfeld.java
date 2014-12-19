import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * GUI-Klasse, die das Spielfeld darstellt und alle Aktionen des Benutzers verarbeitet
 *
 * @see ButtonFigurVerkn
 * @see SpeichernLaden
 * @see Position
 * @see Figur
 */
public class Spielfeld extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected JPanel panelButton;
	private JPanel panelBottom;
    private JPanel panelText;
	JTextPane infoMessage = new JTextPane();
    protected ButtonFigurVerkn spielfeld[][];
    protected InfoKI infoKi = new InfoKI();
	protected ArrayList<Integer> wasser = new ArrayList<Integer>(){{add(42); add(43); add(46); add(47); add(52); add(53); add(56); add(57);}}; //Wasserfelder zum einfachen ueberpruefen
	private JButton button = new JButton("abbrechen");
    private JButton kiZiehenButton = new JButton("KI ziehen");
	private SpeichernLaden save = new SpeichernLaden(this);
	protected boolean figurenkampfOffen = false;
	protected SpeichernLaden alteFelder = new SpeichernLaden();

	// Variablen fuer das setzen von Figuren
	private static boolean firstClickPerformed = false; //wenn erster Klick getaetigt wurde
	private static Position firstClickPosition; //temporaerer Speicher fuer die zuerst angeklickte Position
	private boolean spielstart = true;
	private static boolean warteAufSetzen = false;
	private static Figur wartendeFigur = null;

    /**
     * Setter um den Spielstart zu setzen
     *
     * @param bool Wurde ein neues Spiel gestartet (Hat der Benutzer schon alle Figuren gesetzt)
     */
	public void setSpielstart(boolean bool) {
		this.spielstart = bool;
	}

    /** Alle Figuren, die der Spieler zur Verfügung hat */
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

    /** Kopie der Liste, die alle Figuren des Spielers enthaelt */
	static ArrayList<Figur> figurenSatzSpieler_clone = new ArrayList<Figur>(figurenSatzSpieler);

	/**
	 * Hilfskontruktor fuer ein temporaeres Spielfeld zum Vergleichen
	 *
	 * @param spielfeld ButtonFigurVerkn[][] spielfeld zum Vergleichen
	 */
	public Spielfeld(ButtonFigurVerkn[][] spielfeld) {
		this.spielfeld = spielfeld;
	}

    /**
     * Erzeugen des Fensters, auf dem das Spielfeld liegt
     */
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
		panelBottom = new JPanel(new GridLayout(1,2));
        panelText = new JPanel(new GridLayout(1,1));


		// Textfeld dem BottomPanel hinzufuegen, beschriften und uneditable machen
		panelText.add(infoMessage);
		infoMessage.setText("Beliebigen Button druecken um zu starten");
		infoMessage.setEditable(false);


		// Roten Hintergrund für das Panel setzen, damit unsichtbare Figuren-Felder nicht grau sind
		panelButton.setBackground(new Color(207, 4, 0));

		// Listener fuer Buttons
		for(ButtonFigurVerkn[] i:spielfeld) {
			for(ButtonFigurVerkn j: i){
				j.getButton().addActionListener(this);
			}
		}
		// abbrechen Button hinzufuegen
        kiZiehenButton.addActionListener(this);
		button.addActionListener(this);

        //Panels auf Frame packen
        getContentPane().add(BorderLayout.CENTER, panelButton);
        getContentPane().add(BorderLayout.NORTH, panelText);
		getContentPane().add(BorderLayout.PAGE_END, panelBottom);
        // sichtbar machen
        setVisible(true);
		panelAktualisieren();
    }


    /**
     * Startaufstellung des Spielers setzen. Waehrend des Spiels umsetzen der Spieler Figuren.
     * Die Methode reagiert bei einem Mausklick auf die Figur eines Spielers
     *
     * @param e ActionEvent, welches durch den Mausklick gesendet wird
     */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("abbrechen")) {
			// Damit count wieder auf 0 gesetzt wird
			new ButtonFigurVerkn();
			// Spielstart zuruecksetzen
			spielstart = true;
			warteAufSetzen = false;
			figurenSatzSpieler = new ArrayList<Figur>(figurenSatzSpieler_clone);
//			Spielablauf.gegner.setzePositionNull();
			// Fenster schliessen
			dispose();
			// Menue wieder aufrufen
			new Menue();
		} else if (e.getActionCommand().equals("KI ziehen")) {
            if (!Spielablauf.kiGezogen && !figurenkampfOffen) {
                Spielablauf.gegner.macheZug();
                Spielablauf.kiGezogen = true;
                kiZiehenButton.doClick();
            } else {
               infoMessage.setText("Du bist dran mit ziehen");
            }
        }  else if (e.getActionCommand().equals("53")) {
            System.out.println("SIMUL");

        } else {

			// Figuren setzen des Spielers
			if(spielstart && !warteAufSetzen) {
				// Sind noch Figuren im pool?
				if(figurenSatzSpieler.size() > 0) {
					wartendeFigur = figurenSatzSpieler.remove(0);
					warteAufSetzen = true;
					infoMessage.setText(wartendeFigur.toString()+ " wird als erstes gesetzt. Bitte das gewuenschte Feld anklicken");
				}
			} else if(spielstart) {
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
			} else if (Spielablauf.kiGezogen) {
				// Spieler zieht
				int number = Integer.parseInt(e.getActionCommand());

				if (wasser.contains(number)) {
					System.out.println("das ist wasser");
					if (firstClickPerformed) {
						firstClickPerformed = false;
						infoMessage.setText("Ihre Figuren koennen nicht schwimmen. Bitte eine Figur auswaehlen.");
					}
				} else {
					if (firstClickPerformed) {
						if (!(number / 10 == firstClickPosition.getX() && number % 10 == firstClickPosition.getY())) {
							// Abstaende der Positionen darf nicht groesser als 1 sein und beim Aufklaerer muss es auf der gleichen Gerade sein
							if (((Math.abs(firstClickPosition.getX() - number / 10) == 1 && firstClickPosition.getY() == number % 10) || (Math.abs(firstClickPosition.getY() - number % 10) == 1) && firstClickPosition.getX() == number / 10) || /* Aufklaerer */ (spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur() instanceof Aufklaerer && (firstClickPosition.getX() == number / 10 || firstClickPosition.getY() == number % 10))) {
								// Wenn nicht der gleiche Button gedrueckt wird und keine eigene Figur dort liegt
								if (spielfeld[number / 10][number % 10].getFigur() == null || spielfeld[number / 10][number % 10].getFigur().getTeam() != 1) {
									if (spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur() instanceof Aufklaerer && wegBelegt(firstClickPosition, new Position(number / 10, number % 10))) {

										infoMessage.setText("Der Aufklaerer kann nicht ueber Wasser oder andere Figuren ziehen. Bitte erneut Figur ausawehlen.");
										firstClickPerformed = false;
										return;
									}

									// Gewollten Zug auf temporaeren Spielfeld durchfuehren


									// Pruefen ob das gewollte Feld schonmal existiert hat, wenn nicht ..

									// Vor dem eigenen Zug das Spielfeld in die ArrayList legen
									//vorherigeSpielfelder.add(this);
									//alteFelder.alteFelderSpeichern(this.spielfeld);

										if(alteFelder.contains(this.spielfeld, firstClickPosition, new Position(number/10, number%10))) {
											// Gib dem Spieler eine Meldung dass der Zug ungueltig ist
											infoMessage.setText("Die Spielfeld-Aufstellung ist schonmal vorgekommen. Bitte einen anderen Zug machen.");
											System.out.println("Spielfeld ist enthalten");
										} else {
											// fuehre den Zug durch
											figurSetzen(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur(), number / 10, number % 10);
                                            Spielablauf.kiGezogen = false;
											// Wenn kein Fenster vom Figurenkampf geoeffnet ist, kann die KI ziehen
											//if (!figurenkampfOffen) {
                                                // TODO: KI wieder ziehen lassen
												//Spielablauf.gegner.macheZug();
											//}
                                            if (Spielablauf.kiGezogen) {
                                                infoMessage.setText("Bitte Figur auswaehlen mit der Sie ziehen wollen");
                                            } else {
                                                infoMessage.setText("KI ziehen lassen");
                                            }
										}



									} else {
										infoMessage.setText("Zielfeld ist von einer eigenen Figur belegt");
									}
								} else {
									infoMessage.setText("So weit kann die Figur nicht laufen. Nur maximal 1 Feld.");
								}
							} else {
								infoMessage.setText("Zurueckgesetzt. Bitte eine Figur auswaehlen.");
							}
							firstClickPerformed = false;
						} else {
							firstClickPosition = new Position(number / 10, number % 10);
							// Wenn auf dem angeklickten Feld eine eigene Figur steht
							if (!(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur() == null) && spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur().getTeam() == 1) {
								if (spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur().getIstBewegbar()) {
									infoMessage.setText(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur().toString() + " ausgewahlt. Wohin soll die Figur gesetzt werden?");
									firstClickPerformed = true;
								} else {
									infoMessage.setText("Diese Figur kann sich nicht bewegen");
								}
							}
						}
					}
				} else {
                infoMessage.setText("Die KI muss zuerst ziehen");
            }

		}// ende Else vom abbrechen Button
	}

    /**
     * Aktualisieren des gesammten Panels. Alten Buttons und Textfeld werden vom Panel geloescht und dann
     * die neuen hinzugefuegt
     */
	public void panelAktualisieren() {
        panelText.removeAll();
		panelButton.removeAll();
		panelBottom.removeAll();

          // Durchlaueft alle Zeilen(i) des Spielfelds -> Reihe von Buttons
		for(ButtonFigurVerkn[] i: spielfeld) {
            // Durchlauft jede Spalte von i -> Jeweils 1 einziger Button
			for(ButtonFigurVerkn j : i) {
				panelButton.add(j.getButton());
			}
		}
        panelText.add(infoMessage);
        panelBottom.add(kiZiehenButton, BorderLayout.EAST);
		panelBottom.add(button, BorderLayout.WEST);
	}

	// Feld wieder zu einem gruenen Feld machen
    private void feldZuruecksetzen(Position pos) {
		spielfeld[pos.getX()][pos.getY()] = new ButtonFigurVerkn("green", pos.getX() * 10 + pos.getY());
		spielfeld[pos.getX()][pos.getY()].getButton().addActionListener(this);
	}

    /**
     * Figur auf eine neue Position setzen
     *
     * @param figur Figur, deren Position gesetzt wird
     * @param x neue xKoordinate der Figur
     * @param y neue yKoordinate der Figur
     */
    public void figurSetzen(Figur figur, int x, int y) {
		ButtonFigurVerkn tmp = spielfeld[figur.getPosition().getX()][figur.getPosition().getY()];
		tmp.getButton().setActionCommand(Integer.toString(x*10+y));
        infoKi.loescheFigur(figur);
		feldZuruecksetzen(figur.getPosition());
        figur.setPosition(new Position(x, y));

		int sieger = -1;
		if(figur.getTeam() == 2) {
			if(spielfeld[x][y].getFigur() != null && spielfeld[x][y].getFigur().getTeam() == 1) {
				setEnabled(false);
				Figurenkampf fk = new Figurenkampf(figur, spielfeld[x][y].getFigur(), this);
				sieger = fk.getSieger();
			} else if(spielfeld[x][y].getFigur() == null) {
				spielfeld[x][y] = new ButtonFigurVerkn(figur, "red");
				spielfeld[x][y].getButton().addActionListener(this);
			}
			if(sieger == 0) {
				// Unentschieden
                //infoKi.loescheFigur(spielfeld[x][y].getFigur());
				feldZuruecksetzen(new Position(x, y));
				// Alle bisherigen Spielfelder koennen nicht mehr auftreten
				alteFelder.clear();
			} else if(sieger == 1) {
				// KI gewinnt
				this.spielfeld[x][y] = new ButtonFigurVerkn(figur, "red");
				this.spielfeld[x][y].getButton().addActionListener(this);

				// Alle bisherigen Spielfelder koennen nicht mehr auftreten
				alteFelder.clear();
               // infoKi.loescheFigur(spielfeld[x][y].getFigur());
				spielfeld[x][y] = new ButtonFigurVerkn(figur, "red");
				spielfeld[x][y].getButton().addActionListener(this);
			}
		} else {
			// Spieler greift an
			if(this.spielfeld[x][y].getFigur() != null && this.spielfeld[x][y].getFigur().getTeam() == 2) {
				setEnabled(false);
				Figurenkampf fk = new Figurenkampf(figur, spielfeld[x][y].getFigur(), this);
				sieger = fk.getSieger();
			} else if(this.spielfeld[x][y].getFigur() == null) {
				// Spieler zieht auf leeres Feld
				this.spielfeld[x][y] = tmp;
			} else if(spielfeld[x][y].getFigur() == null) {
                // Spieler zieht auf leeres Feld
                infoKi.schreibeFigur(tmp.getFigur(), new Position(x,y));
				spielfeld[x][y] = tmp;
			}
			if(sieger == 0) {
				// Unentschieden
                //infoKi.loescheFigur(spielfeld[x][y].getFigur());
				feldZuruecksetzen(new Position(x, y));
				// Alle bisherigen Spielfelder koennen nicht mehr auftreten
				alteFelder.clear();
			} else if(sieger == 1) {
				// Spieler gewinnt
				this.spielfeld[x][y] = tmp;

				// Alle bisherigen Spielfelder koennen nicht mehr auftreten
				alteFelder.clear();
                infoKi.schreibeFigur(tmp.getFigur(), new Position(x,y));
				spielfeld[x][y] = tmp;
			}
		}

		// aktuelles Feld den alten Feldern hinzufuegen
		alteFelder.alteFelderSpeichern(this.spielfeld);
		save.spielfeldSpeichern();
		panelAktualisieren();
	}

    /**
     * Figur auf eine neue Position setzen
     *
     * @param a Figur, deren Position gesetzt wird
     * @param pos Neue Position der Figur
     */
	public void figurSetzen(Figur a, Position pos) {
		figurSetzen(a, pos.getX(), pos.getY());
    }

    /**
     * Initialisieren eines Feldes mit einer Figur
     *
     * @param figur Figur, die zum ersten mal gesetzt werden soll
     * @param x xKoordinate der Figur
     * @param y yKoordinate der Figur
     */
	public void figurInit(Figur figur, int x, int y) {
        // Setzt die Position der Figur
        figur.setPosition(new Position(x, y));
		// Setzt eine Figur und gibt ihr direkt die richtige Nummer
		if(figur.getTeam() == 2) {
			spielfeld[x][y] = new ButtonFigurVerkn(figur, "red");
		} else {
			spielfeld[x][y] = new ButtonFigurVerkn(figur, x * 10 + y);
		}
		spielfeld[x][y].getButton().addActionListener(this);
        alteFelder.alteFelderSpeichern(this.spielfeld);
        save.spielfeldSpeichern();
		panelAktualisieren();
	}

	/**
	 * Bestimmt aus zwei Positionen die Laufrichtung
	 * 1: Position ist die Startposition
	 * 2: Position ist die Zielposition
	 *
	 * return (Uhrzeigersinn):
	 * 0: nach oben
	 * 1: nach rechts
	 * 2: nach unten
	 * 3: nach links
	 * -1: -
	 */
	private int richtungBestimmen(Position von, Position nach) {
		if(von.getX() > nach.getX()) {
			System.out.println("Nach oben");
			return 0;
		} else if(von.getY() < nach.getY()) {
			System.out.println("Nach rechts");
			return 1;
		} else if (von.getX() < nach.getX()) {
			System.out.println("Nach unten");
			return 2;
		} else if(von.getY() > nach.getY()) {
			System.out.println("Nach links");
			return 3;
		}
		return -1;
	}

	/** Ueberprueft ob der Pfad zwischen zwei Positionen belegt ist
	 *
	 * @param von Position
	 * @param nach Position
	 * @return boolean true, wenn belegt
	 * 					false, wenn frei
	 */
	private boolean wegBelegt(Position von, Position nach) {
		int direction = richtungBestimmen(von, nach);
		if(direction == 0) {
			int it = von.getX()-1;
			while(it != nach.getX()) {
		    //it--;
				// Wenn auf dem Weg nach oben Wasser oder eine andere Figur im Weg ist, return true
				if(wasser.contains(it*10+nach.getY()) || spielfeld[it][nach.getY()].getFigur() != null) {
                    return true;
                }
                it--;
			}
		} else if(direction == 1) {
			int it = von.getY()+1;
			while(it != nach.getY()) {
				//it++;
				// Wenn auf dem Weg nach rechts Wasser oder eine andere Figur im Weg ist, return true
				if(wasser.contains(it+nach.getX()*10) || spielfeld[nach.getX()][it].getFigur() != null) {
                    return true;
                }
                it++;
			}
		} else if(direction == 2) {
			int it = von.getX()+1;
			while(it != nach.getX()) {
				//it++;
				// Wenn auf dem Weg nach unten Wasser oder eine andere Figur im Weg ist, return true
				if(wasser.contains(it*10+nach.getY()) || spielfeld[it][nach.getY()].getFigur() != null) {
                    return true;
                }
                it++;
            }
        } else if(direction == 3) {
			int it = von.getY()-1;
			while(it != nach.getY()) {
				//it--;
				// Wenn auf dem Weg nach links Wasser oder eine andere Figur im Weg ist, return true
				if(wasser.contains(it+nach.getX()*10) || spielfeld[nach.getX()][it].getFigur() != null) {
                    return true;
                }
                it--;
			}
		}

		return false;
	}


    /**
     * Vergleich, ob ein Spielfeld schonmal vorhanden war
     *
     * @param spielfeld2 Altes Spielfeld
     * @return false - Spielfeld gab es so noch nicht
     *          true - Spielfeld gab es so schon einmal
     */
	public boolean spielfeldVergleichen(Spielfeld spielfeld2) {
		int length = this.spielfeld.length;
		for(int i=0; i<length; i++){
			for(int j=0; j<length; j++){
				// Wenn ein Element des Spielfeldes unterschiedlich ist, gebe false zurueck
				if(this.spielfeld[i][j].getFigur().getId() != spielfeld2.spielfeld[i][j].getFigur().getId())
					return false;
			}

		}
		// Ansonsten true
		return true;
	}

	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		//if (other == this) return true;
		if (!(other instanceof Spielfeld))return false;
		Spielfeld otherFeld = (Spielfeld)other;

		for(int i=0; i<this.spielfeld.length; i++) {
			for(int j=0; j < this.spielfeld[0].length; j++) {
				if(this.spielfeld[i][j].getFigur() == null && otherFeld.spielfeld[i][j].getFigur() != null) return false;
				if(this.spielfeld[i][j].getFigur() != null && otherFeld.spielfeld[i][j].getFigur() == null) return false;
				if(this.spielfeld[i][j].getFigur() == null && otherFeld.spielfeld[i][j].getFigur() == null) continue;
				//System.out.println("this FigurId: "+this.spielfeld[i][j].getFigur().getId()+ " other: "+otherFeld.spielfeld[i][j].getFigur().getId());
				//if(this.spielfeld[i][j].getFigur().getId() != otherFeld.spielfeld[i][j].getFigur().getId()) {
					// Wenn eine ButtonFigurVerkn anders ist, ist das Spielfeld unterschiedlich
				//	return false;
				//}
			}
		}
		return true;
	}

    /**
     * Liefert die Figur, die auf der Position liegt (null, wenn keine)
     * @param x xKoordinate
     * @param y yKoordinate
     * @return Figur auf der Position
     */
    public Figur getFigur(int x, int y) {
            return spielfeld[x][y].getFigur();
    }


}
