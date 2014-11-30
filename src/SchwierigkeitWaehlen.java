import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dennis on 27.11.2014.
 */
public class SchwierigkeitWaehlen extends JFrame {
    private JLabel anweisung;
    private JButton einfachButton;
    private JButton mittelButton;
    private JButton schwerButton;
    private JPanel panel;

    public SchwierigkeitWaehlen() {
        super("Schwierigkeit auswaehlen");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);
        // Programm beim Schliessen des Fensters beenden
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(4, 1));

        String anweisungText="";
        // Damit Text in Mitte steht
        for (int i=0; i<65; i++) {
            anweisungText += " ";
        }
        anweisungText += "Waehle die Schwierigkeit der KI aus";

        // Textfeld erstellen und fuellen
        anweisung = new JLabel(anweisungText);

        // Buttons erzeugen
        einfachButton = new JButton();
        einfachButton.setName("einfach");
        einfachButton.setText("Einfach");
        einfachButton.setPreferredSize(new Dimension(100,50));

        mittelButton = new JButton();
        mittelButton.setName("mittel");
        mittelButton.setText("Mittel");
        mittelButton.setPreferredSize(new Dimension(100,50));

        schwerButton = new JButton();
        schwerButton.setName("schwer");
        schwerButton.setText("Schwer");
        schwerButton.setPreferredSize(new Dimension(100,50));

        // Panel erzeugen
        panel = new JPanel(new GridLayout(4,1));

        // Panel bepacken
        panel.add(anweisung, BorderLayout.PAGE_START);
        panel.add(einfachButton);
        panel.add(mittelButton);
        panel.add(schwerButton);

        // Listener
        addButtonListener(einfachButton);
        addButtonListener(mittelButton);
        addButtonListener(schwerButton);

        // Panel auf Frame packen
        getContentPane().add(BorderLayout.CENTER, panel);
        setVisible(true);
    }

    private void addButtonListener(JButton b) {
        if (b.getName().equals("einfach")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Spielablauf("einfach");
                }
            });
        } else if (b.getName().equals("mittel")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Spielablauf("mittel");
                }
            });
        } else if (b.getName().equals("schwer")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Spielablauf("schwer");
                }
            });
        }
    }

}
