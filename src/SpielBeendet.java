import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpielBeendet extends JFrame{
    Figur sieger;
    private JLabel ausgabeSieger;
    private JButton beenden;
    private JPanel panel;

    public SpielBeendet(Figur sieger){
        super("Spiel Beendet");
        this.sieger = sieger;
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);
        // Programm beim Schliessen des Fensters beenden
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(4, 1));

        String ausgabe;
        if (sieger.getTeam()==1) {
            ausgabe = "<html><h1>Glückwunsch du hast gewonnen</h1></html>";
        } else {
            ausgabe = "<html><h1>Schade! Du hast leider verloren</h1></html>";
        }

        // textfeld erstellen und fuellen
        ausgabeSieger = new JLabel(ausgabe);

        // Button erzeugen
        beenden = new JButton();
        beenden.setName("beenden");
        beenden.setText("Spiel beenden");
        beenden.setPreferredSize(new Dimension(100,50));

        // Panels erzeugen
        panel = new JPanel(new BorderLayout(4,1));

        // Button und Textfeld auf panel packen
        panel.add(ausgabeSieger,BorderLayout.PAGE_START);
        panel.add(beenden, BorderLayout.PAGE_END);

        // Listener
        addButtonListener(beenden);

        //Panel auf Frame packen
        getContentPane().add(BorderLayout.CENTER, panel);
        // sichtbar machen
        setVisible(true);
    }

    private void addButtonListener(JButton b) {
        if (b.getName().equals("beenden")) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Spiel beenden
                    System.exit(0);
                }
            });
        }
    }
}
