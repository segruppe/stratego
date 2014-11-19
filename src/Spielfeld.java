import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class Spielfeld extends JFrame {
    /**
	 * Achtung, das stimmt so noch nicht, laesst sich aber ausfuehren und zeigt bei mir auch Bilder an 
	 * (wenn das bei euch nicht der Fall ist, liegts an den Pfaden zu den Bildern :/)
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelButton;
    private ButtonFigurVerkn spielfeld[][];
    private ImageIcon grass;
    private ImageIcon water;

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

        // Buttons auf panel packen
        for(int i=0; i<spielfeld.length; i++) {
        	for(int j=0; j<spielfeld[0].length; j++) {
        		panelButton.add(spielfeld[i][j].getButton());
        	}
        }

        // Listener fuer Buttons
//        addButtonListener(beendenButton );

        //Panels auf Frame packen (das panelButton hat ein GridLayout, dass jetzt in den WestBereich des BorderLayouts kommt)
        getContentPane().add(BorderLayout.CENTER, panelButton);
        // sichtbar machen
        setVisible(true);
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
		Spielfeld spielfeld = new Spielfeld();
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
//		spielfeld[x][y] = a;
	}
	
	public void figurSetzen(Figur a, Position pos) {
		// Alte Position auf null setzen
		spielfeld[a.getPosition().getX()][a.getPosition().getY()] = null;
		// Neue Position setzen
		spielfeld[pos.getX()][pos.getY()] = new ButtonFigurVerkn(a);
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
