import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Spielfeld extends JFrame implements ActionListener {
    /**
	 * Achtung, das stimmt so noch nicht, laesst sich aber ausfuehren und zeigt bei mir auch Bilder an 
	 * (wenn das bei euch nicht der Fall ist, liegts an den Pfaden zu den Bildern :/)
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelButton;
    private ButtonFigurVerkn spielfeld[][];
	private static ArrayList<Integer> wasser = new ArrayList<Integer>(){{add(42); add(43); add(46); add(47); add(52); add(53); add(56); add(57);}}; //Wasserfelder zum einfachen ueberpruefen
	private static boolean firstClickPerformed = false; //wenn erster Klick getaetigt wurde
	private static Position firstClickPosition; //temporaerer Speicher fuer die zuerst angeklickte Position

    public Spielfeld() {
        super("Stratego - Spiel");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters
        setLocation(400, 100);
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
        panelButton = new JPanel(new GridLayout(10, 10));
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
							Figur temp = new Fahne();
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

		int number = Integer.parseInt(e.getActionCommand());

		if(wasser.contains(number)) {
			System.out.println("das ist wasser");
		} else {
			if(firstClickPerformed) {
				if(number/10 == firstClickPosition.getX() && number%10 == firstClickPosition.getY() ) {
					// Gleichen Button gedrückt
					// Wieder Farbe zuruecksetzen
					spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getButton().setBackground(new Color(0,153,0));
				} else {
					spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getButton().setBackground(new Color(0, 153, 0));
					spielfeld[number/10][number%10].setFigur(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur());
					System.out.println(number/10+" und "+number%10);
					//spielfeld[number/10][number%10] = new ButtonFigurVerkn(new Fahne()); // bekommt noch nullPointer, kp warum


					// Alte Stelle auf Grün setzen - Die ist immer grün
					spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].setFigur(null);
				}
				firstClickPerformed = false;
			} else {
				firstClickPosition = new Position(number/10, number%10);
				System.out.println(number);
				if(!(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur() == null)) {
					// Wenn das angeklickte Feld eine Figur hat
					System.out.println(spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getFigur());
					spielfeld[firstClickPosition.getX()][firstClickPosition.getY()].getButton().setBackground(new Color(156, 255, 107));
					firstClickPerformed = true;
				}
			}

		}

		panelAktualisieren();
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
	}

    // TODO: Neue Klasse aufrufen
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
		spielfeld[x][y] = new ButtonFigurVerkn(a);
	}
	
	public void figurSetzen(Figur a, Position pos) {
		// Alte Position auf null setzen
		spielfeld[a.getPosition().getX()][a.getPosition().getY()] = null;
		// Neue Position setzen
		spielfeld[pos.getX()][pos.getY()] = new ButtonFigurVerkn(a);
	}

	// Methode zum initialisieren eines Feldes mit einer Figur
	public void figurInit(Figur a, int x, int y) {
		// Setzt eine Figur und gibt ihr direkt die richtige Nummer
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
