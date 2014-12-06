import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dennis on 25.11.2014.
 */
public class SpielStarten extends JFrame {
    private JButton neuesSpiel;
    private JButton ladeSpiel;
    private JButton zurueck;
    private JPanel panel;

    public SpielStarten() {
        super("Spiel starten");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);
        // Programm beim Schliessen des Fensters beenden
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(2, 1));

        // Buttons erzeugen
        neuesSpiel = new JButton();
        neuesSpiel.setName("neuesSpiel");
        neuesSpiel.setText("Neues Spiel");

        ladeSpiel = new JButton();
        ladeSpiel.setName("ladeSpiel");
        ladeSpiel.setText("Spiel laden");

        zurueck = new JButton();
        zurueck.setName("zurueck");
        zurueck.setText("Zurueck");

        // Panels auf BorderLayout erzeugen
        panel = new JPanel(new GridLayout(3,1));

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


