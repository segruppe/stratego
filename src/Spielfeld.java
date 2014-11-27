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
    private ButtonFigurVerkn spielfeld[][];
	private static ArrayList<Integer> wasser = new ArrayList<Integer>(){{add(42); add(43); add(46); add(47); add(52); add(53); add(56); add(57);}}; //Wasserfelder zum einfachen ueberpruefen
	private static boolean firstClickPerformed = false; //wenn erster Klick getaetigt wurde
	private static Position firstClickPosition; //temporaerer Speicher fuer die zuerst angeklickte Position
	private JButton button = new JButton("abbrechen");

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
            		spielfeld[i][j] = new ButtonFigurVerkn("grün");
            	} else {
            		// Wasserspielfelder
            		spielfeld[i][j] = new ButtonFigurVerkn("blau");
				}
        	}
        }

        // Panels auf GridLayout erzeugen
        panelButton = new JPanel(new GridLayout(11, 10));
		// Roten Hintergrund für das Panel setzen, damit unsichtbare Figuren-Felder nicht grau sind
		panelButton.setBackground(new Color(207, 4, 0));
        // Buttons auf panel packen


		/*
		Setzt Figuren auf das Spielfeld (Test für leichte KI)
		 */
		int flagge = 5;
		int count=0;
		// Wenn es noch nicht gesetzt wurde, weitermachen
		loop: while (flagge > 0) {
			//System.out.println(count);

			// Zeilen durchlaufen
			for (int i = 0; i < 3; i++) {
				// Spalten durchlaufen
				for (int j = 0; j < 10; j++) {
					// Überspringe bereits besetzte Felder
					if(!(spielfeld[i][j].getFigur() == null)) {
						continue;
					}

					if (flagge == 0) break loop;
					else if(flagge >0) {

						// Wahrscheinlichkeit
						Random rn = new Random();
						int number = rn.nextInt(250);
						if (number <= 0) {
							// Figur setzen
							Figur temp = new Major(1);
							figurInit(temp, i, j);
							flagge--;
						}
					}
				}
			}
			// Einfacher Zaehler
			count++;
		}

        // Listener fuer Buttons
		for(ButtonFigurVerkn[] i:spielfeld) {
			for(ButtonFigurVerkn j: i){
				j.getButton().addActionListener(this);
			}
		}
		// abbrechen Button hinzufuegen
		button.addActionListener(this);


        //Panels auf Frame packen (das panelButton hat ein GridLayout, dass jetzt in den WestBereich des BorderLayouts kommt)
        getContentPane().add(BorderLayout.CENTER, panelButton);
        // sichtbar machen
        setVisible(true);
		panelAktualisieren();
    }



	/*
	Setzt Spielfiguren um, reagiert auf Mausklick
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("abbrechen")) {
			// Damit count wieder auf 0 gesetzt wird
			ButtonFigurVerkn test = new ButtonFigurVerkn();
			dispose();
			new Menue();
		} else {

			int number = Integer.parseInt(e.getActionCommand());

			if (wasser.contains(number)) {
				System.out.println("das ist wasser");
			} else {
				if (firstClickPerformed) {
					if (!(number / 10 == firstClickPosition.getX() && number % 10 == firstClickPosition.getY()) || spielfeld[number/10][number%10].getFigur().getTeam() != 1) {
						// Wenn nicht der gleiche Button gedrueckt wird und keine eigene Figur dort liegt
						// Zug durchfuehren
						//spielfeld[number / 10][number % 10].setFigur(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur());
						figurSetzen(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur(), number/10, number%10);
						System.out.println(number / 10 + " und " + number % 10);

						// Alte Figur null setzen
						spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].setFigur(null);
					}
					spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getButton().setBackground(new Color(0, 153, 0));
					firstClickPerformed = false;
				} else {
					firstClickPosition = new Position(number / 10, number % 10);
					System.out.println(number);
					// Wenn das angeklickte Feld eine Figur hat
					if (!(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur() == null) && spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur().getTeam() == 1) {
						// Nur wenn eine Figur auf dem Feld liegt und diese auch eine eigene ist
						System.out.println(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur());
						spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getButton().setBackground(new Color(156, 255, 107));
						firstClickPerformed = true;
					}
				}

			}

			panelAktualisieren();
		}// ende Else vom abbrechen Button
	}


    public static void main(String[] args) {
        //@SuppressWarnings("unused")
		//Spielfeld spielfeld = new Spielfeld();
    }

	// Aktualisiert das gesamte Panel (die Buttons)
	public void panelAktualisieren() {


		for(ButtonFigurVerkn[] i: spielfeld) {
			for(ButtonFigurVerkn j : i) {
				panelButton.add(j.getButton());
			}
		}
		panelButton.add(button, BorderLayout.PAGE_END);
	}

    public void figurSetzen(Position pos, Figur figur) {
    	spielfeld[pos.getX()][pos.getY()] = new ButtonFigurVerkn(figur);
    }

    public void figurLoeschen(Figur fig) {
    	int i = fig.getPosition().getX();
    	int j = fig.getPosition().getY();
    	if(! ((i==4 || i==5) && (j==2 || j==3 || j==6 || j==7))) {
    		// Grasspielfeld setzen
    		spielfeld[i][j] = new ButtonFigurVerkn("grün");
    	} else {
    		// Wasserspielfeld setzen
    		spielfeld[i][j] = new ButtonFigurVerkn("blau");
    	}
    	// Figur auf 'ungueltig' setzen
    	fig.setId(-1);
    }


	// x und y sind Koordinaten der neuen Position
    public void figurSetzen(Figur a, int x, int y) {
		// Alte Position auf null setzen
		spielfeld[a.getPosition().getX()][a.getPosition().getY()] = null;
		// Neue Position setzen
		a.setPosition(new Position(x, y));
		spielfeld[x][y] = new ButtonFigurVerkn(a);
	}

	public void figurSetzen(Figur a, Position pos) {
		// Alte Position auf null setzen
		spielfeld[a.getPosition().getX()][a.getPosition().getY()] = null;
		// Neue Position setzen
		a.setPosition(pos);
		spielfeld[pos.getX()][pos.getY()] = new ButtonFigurVerkn(a);
	}

	// Methode zum initialisieren eines Feldes mit einer Figur
	public void figurInit(Figur a, int x, int y) {
		// Setzt eine Figur und gibt ihr direkt die richtige Nummer
		// Setzt die Position der Figur
		a.setPosition(new Position(x, y));
		spielfeld[x][y] = new ButtonFigurVerkn(a, x*10+y);
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
