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

        getContentPane().setLayout(new BorderLayout(5, 5));

        // Buttons erzeugen
        textFieldHilfeAusgabe = new JLabel("Hier steht eine Anleitung zum Spiel");
        zurueckButton = new JButton("Zurück");
        zurueckButton.setName("zurueckButton");

        // Panels auf GridLayout erzeugen
        panel = new JPanel(new GridLayout(3, 1));

        // Buttons auf panel packen
        panel.add(textFieldHilfeAusgabe);
        panel.add(zurueckButton);

        // Listener fuer Button
        addButtonListener(zurueckButton);

        //Panels auf Frame packen (das panelButton hat ein GridLayout, dass jetzt in den WestBereich des BorderLayouts kommt)
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
