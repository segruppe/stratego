import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dennis on 19.11.2014.
 */
public class Figurenkampf extends JFrame{

    // TODO: Graphische Oberflaeche
    Figur figur1;
    Figur figur2;
    private JButton okButton;
    private JLabel ausgabeFigur;
    private JPanel panel;
    private JLabel bild1;
    private JLabel bild2;
    private Spielfeld spielfeld;

    public Figurenkampf(Figur figurA, Figur figurB, Spielfeld spielfeld) {
        super("Figurenkampf");
        spielfeld.figurenkampfOffen = true;
        figur1 = figurA;
        figur2 = figurB;
        this.spielfeld = spielfeld;
        // Groesse des Fensters
        setSize(300, 300);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);

        getContentPane().setLayout(new BorderLayout(1,1));

        // Bilder erzeugen...Figur vom Spieler soll immer links stehen
        if (figur1.getTeam()==1) {
            bild1 = new JLabel(new ImageIcon(figur1.getBild()));
            bild2 = new JLabel(new ImageIcon(figur2.getBild()));
        } else {
            bild1 = new JLabel(new ImageIcon(figur2.getBild()));
            bild2 = new JLabel(new ImageIcon(figur1.getBild()));
        }
        //Text festlegen
        int ergebnis=this.vergleicheStaerke();
        String ausgabe;
        if(ergebnis==0){
            ausgabe="<html>Unentschieden. <br> Beide Figuren werden entfernt!</html>";
        } else if(ergebnis==1){ // Figur1 gewint
            if (figur1.getTeam()==1) {
                ausgabe = "<html> Deine Figur hat gewonnen </html>";
                bild1.setBorder(new LineBorder(Color.red));
            } else {
                ausgabe = "<html>Die Figur der KI war leider besser </html> ";
                bild2.setBorder(new LineBorder(Color.red));
            }
        } else { // Figur2 gewinnt
            if (figur2.getTeam()==2) {
                ausgabe="<html> Die Figur der KI war leider besser </html>";
                bild2.setBorder(new LineBorder(Color.red));
            } else {
                ausgabe = "<html> Deine Figur hat gewonnen </html>";
                bild1.setBorder(new LineBorder(Color.red));
            }
        }

        //Textfeld erzeugen
        ausgabeFigur=new JLabel(ausgabe);


        //Button erzeugen
        okButton = new JButton();
        okButton.setName("okay");
        okButton.setText("OK, weiter");
        okButton.setPreferredSize(new Dimension(75,50));

        addButtonListener(okButton);

        // Panel erzeugen und Elemente hinzufuegen
        panel=new JPanel(new BorderLayout(5,1));
        panel.add(ausgabeFigur, BorderLayout.CENTER);
        panel.add(okButton, BorderLayout.SOUTH);
        panel.add(bild1, BorderLayout.WEST);
        panel.add(bild2, BorderLayout.EAST);

        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addButtonListener(JButton b) {
        if(b.getName().equals("okay")){
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    spielfeld.setEnabled(true);
                    dispose();
                    spielfeld.figurenkampfOffen = false;
                    if(figur1.getTeam() == 1) {
                        // KI zieht nachdem das Figurenkampf-Fenster geschlossen wurde wenn der Spieler angegriffen hat
                        Spielablauf.gegner.macheZug();
                    }
                }
            });
        }
    }


    // Vergleicht die Staerken der beiden Figuren
    // 0: Unentschieden, 1: Figur1 gewinnt, 2: Figur2 gewinnt

    // Figur 1 MUSS die angreifende Figur sein
    int vergleicheStaerke() {
        if (figur2.getStaerke()==1) {
            dispose();
            new SpielBeendet(figur1.getTeam()); // Spieler von Figur1 hat gewonnen
        }
        if (figur1.getStaerke() == figur2.getStaerke()) {
            return 0;
        } else if (figur1.getStaerke() == 12 && figur2.getStaerke() == 4) {
            // Mineur schlaegt Bombe
            return 2;
        } else if(figur2.getStaerke() == 12 && figur1.getStaerke() == 4) {
            // Mineur schlaegt Bombe
            return 1;
        } else if (figur1.getStaerke() == 2 && figur2.getStaerke() == 11) {
            // Ninja schlaegt Feldmarschall, wenn er angreift
            return 1;
        } else if(figur1.getStaerke() > figur2.getStaerke()) {
            return 1;
        } else {
            return 2;
        }
    }
}
