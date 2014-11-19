import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dennis on 08.11.2014.
 */
public class HilfeAnzeigen extends JFrame {
    private JLabel textFieldHilfeAusgabe;
    private JButton zurueckButton;
    private JPanel panel;

    public HilfeAnzeigen() {
        super("Stratego-Anleitung");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters
        setLocation(400, 100);
        // Programm beim Schliessen des Fensters beenden
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(4, 1));

        // Anleitung erstellen
        String anleitung;
        anleitung = "<html><body><h1> Anleitung </h1><br>" +
                "<p>Der Spieler spielt gegen eine auszuwaehlende KI.<br>" +
                "Ziel des Spiels ist es die Fahne des Gegners zu zerstoeren.<br>" +
                "Beide Spieler ziehen abwechselnd eine ihrer Figuren auf ein freies oder" +
                "vom Gegner besetztes Feld(Nur ein Feld pro Zug, Ausnahmen siehe unten).<br>" +
                "Beim zweiten Fall kommt es zum Kampf zwischen den beiden Figuren.<br>" +
                "Die Figur mit der geringeren Staerke wird vom Feld genommen (Bei Unentschieden beide).<br><br>" +
                "Die verschiedenen Figuren(hierachisch nach Staerke sortiert) sind:<br>" +
                "  12: Bombe (Unbeweglich, schlaegt jeden Gegner außer Mineure)<br>" +
                "  11: Feldmarschall (Nur durch Bomben und Spion schlagbar) <br>" +
                "  10: General<br>" +
                "  9: Oberst<br>" +
                "  8: Major<br>" +
                "  7: Hauptmann<br>" +
                "  6: Leutnant<br>" +
                "  5: Unteroffizier<br>" +
                "  4: Mineur<br>" +
                "  3: Aufklaerer (Koennen ueber mehrere Felder bewegt werden, jedoch nicht ueber andere Figuren oder Seen)<br>" +
                "  2: Ninja (Schlaegt Feldmarschall, wenn er selber angreift<br>" +
                "  1: Fahne (Unbeweglich)</p></body></html>";

        // Buttons erzeugen
        textFieldHilfeAusgabe = new JLabel(anleitung);
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
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
    }

}
