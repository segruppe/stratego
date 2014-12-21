import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI-Klasse um die Spielanleitung anzuzeigen
 *
 * @see Menue
 */
public class HilfeAnzeigen extends JFrame {
    private JLabel textFieldHilfeAusgabe;
    private JButton zurueckButton;
    private JPanel panel;

    /**
     * Erzeugen des Fensters mit der Spielanleitung
     */
    public HilfeAnzeigen() {
        super("Stratego-Anleitung");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);
        // Programm beim Schliessen des Fensters beenden
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(4, 1));

        // Anleitung erstellen
        String anleitung;
        anleitung = "<html><body><h1> Anleitung </h1><br>" +
                "<p>Der Spieler spielt gegen eine auszuwaehlende KI.<br>" +
                "Ziel des Spiels ist es die Fahne des Gegners zu zerstoeren.<br>" +
                "Beide Spieler ziehen abwechselnd (KI ueber Button-Klick) eine ihrer Figuren auf ein freies oder " +
                "vom Gegner besetztes Feld (Nur ein Feld pro Zug, Ausnahmen siehe unten).<br>" +
                "Beim zweiten Fall kommt es zum Kampf zwischen den beiden Figuren.<br>" +
                "Die Figur mit der geringeren Staerke wird vom Feld genommen (Bei Unentschieden beide).<br><br>" +
                "Die verschiedenen Figuren(hierachisch nach Staerke sortiert) sind:<br>" +
                "  6x Bombe (Unbeweglich, schlaegt jeden Gegner außer Mineure)<br>" +
                "  1x Feldmarschall (Nur durch Bomben und Spion schlagbar) <br>" +
                "  1x General<br>" +
                "  2x Oberst<br>" +
                "  3x Major<br>" +
                "  4x Hauptmann<br>" +
                "  4x Leutnant<br>" +
                "  4x Unteroffizier<br>" +
                "  5x Mineur<br>" +
                "  8x Aufklaerer (Koennen ueber mehrere Felder bewegt werden, jedoch nicht ueber andere Figuren oder Seen)<br>" +
                "  1x Ninja (Schlaegt Feldmarschall, wenn er selber angreift)<br>" +
                "  1x Fahne (Unbeweglich)</p></body></html>";

        // Textfeld erstellen und fuellen
        textFieldHilfeAusgabe = new JLabel(anleitung);

        // Buttons erzeugen
        zurueckButton = new JButton();
        zurueckButton.setName("zurueckButton");
        zurueckButton.setText("Zurueck");
        zurueckButton.setPreferredSize(new Dimension(100,50));

        // Panels auf BorderLayout erzeugen
        panel = new JPanel(new BorderLayout(4, 1));

        // Buttons auf panel packen
        panel.add(textFieldHilfeAusgabe,BorderLayout.PAGE_START);
        panel.add(zurueckButton, BorderLayout.PAGE_END);

        // Listener fuer Button
        addButtonListener(zurueckButton);

        //Panels auf Frame packen
        getContentPane().add(BorderLayout.CENTER, panel);
        // sichtbar machen
        setVisible(true);
    }

    private void addButtonListener(JButton b) {
        if (b.getName().equals("zurueckButton")) {
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
