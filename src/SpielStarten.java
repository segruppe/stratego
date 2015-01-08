import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI-Klasse zum Starten eines Spiels. Der Spieler hat die Auswahl ein neues Spiel zu beginnen, ein altes zu laden
 * oder zum Menue zurueck zu kehren
 *
 * @see SpielLaden
 * @see SchwierigkeitWaehlen
 * @see Menue
 */
public class SpielStarten extends JFrame {
    /**
     * Konstruktor, um das Fenster zu erzeugen, ob der Spieler ein Spiel starten/ laden will oder zum Menue zurueckkehren will
     */
    public SpielStarten() {
        super("Spiel starten");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);
        // Programm beim Schliessen des Fensters beenden
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(2, 1));

        // Buttons erzeugen
        JButton neuesSpiel = new JButton();
        neuesSpiel.setName("neuesSpiel");
        neuesSpiel.setText("Neues Spiel");

        JButton ladeSpiel = new JButton();
        ladeSpiel.setName("ladeSpiel");
        ladeSpiel.setText("Spiel laden");

        JButton zurueck = new JButton();
        zurueck.setName("zurueck");
        zurueck.setText("Zurueck");

        // Panels auf BorderLayout erzeugen
        JPanel panel = new JPanel(new GridLayout(3,1));

        // Button auf Panel packen
        panel.add(neuesSpiel);
        panel.add(ladeSpiel);
        panel.add(zurueck);

        // Listener
        addButtonListener(neuesSpiel);
        addButtonListener(ladeSpiel);
        addButtonListener(zurueck);

        // Panels auf Frame packen
        getContentPane().add(BorderLayout.CENTER, panel);

        // sichtbar machen
        setVisible(true);
    }

    private void addButtonListener(JButton b) {
        if (b.getName().equals("neuesSpiel")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    // Schwierigkeit auswaehlen lassen
                    new SchwierigkeitWaehlen();
                }
            });
        } else if (b.getName().equals("ladeSpiel")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    // Spiel laden
                    new SpielLaden();
                }
            });
        } else if (b.getName().equals("zurueck")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    // Menue aufrufen
                    new Menue();
                }
            });
        }
    }

}


