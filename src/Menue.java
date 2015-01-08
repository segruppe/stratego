import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI-Klasse die das Menue darstellt. Stellt den Start des Spiels dar
 *
 * @see HilfeAnzeigen
 * @see SpielStarten
 */
public class Menue extends JFrame {
    /**
     * Erzeugen des Menue Fensters
     */
    public Menue() {
        super("Stratego");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);
        // Programm beim Schliessen des Fensters beenden
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(5, 5));

        // Buttons erzeugen
        JButton spielStartenButton = new JButton("Spiel starten");
        spielStartenButton.setName("spielStartenButton");
        JButton hilfeButton  = new JButton("Spielanleitung");
        hilfeButton.setName("hilfeButton");
        JButton beendenButton  = new JButton("Beenden");
        beendenButton.setName("beendenButton");

        // Panels auf GridLayout erzeugen
        JPanel panelButton = new JPanel(new GridLayout(3, 1));

        // Buttons auf panel packen
        panelButton.add(spielStartenButton );
        panelButton.add(hilfeButton );
        panelButton.add(beendenButton );

        // Listener fuer Buttons
        addButtonListener(spielStartenButton );
        addButtonListener(hilfeButton );
        addButtonListener(beendenButton );

        //Panels auf Frame packen (das panelButton hat ein GridLayout, dass jetzt in den WestBereich des BorderLayouts kommt)
        getContentPane().add(BorderLayout.CENTER, panelButton);
        // sichtbar machen
        setVisible(true);
    }

    /**
     * Startpunkt des Spiels
     *
     * @param args keine Uebergabe
     */
    public static void main(String[] args) {
        new Menue();
    }

    private void addButtonListener(JButton b) {
        // Funktionalitaet des Beenden Buttons
        if (b.getName().equals("beendenButton")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        } else if(b.getName().equals("hilfeButton")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Menue schliessen, wenn Hilfe aufgerufen wird
                    setVisible(false);
                    new HilfeAnzeigen();
                }
            });
        } else if(b.getName().equals("spielStartenButton")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Menue schliessen
                    setVisible(false);
                    new SpielStarten();
                }
            });
        }
    }

}