import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI-Klasse um den Figurenkampf darzustellen
 *
 * @see Figur
 * @see Spielfeld
 */
public class Figurenkampf extends JFrame{

    private Figur figur1;
    private Figur figur2;
    private JButton okButton;
    private JLabel ausgabeFigur;
    private JPanel panel;
    private JLabel bild1;
    private JLabel bild2;
    private Spielfeld spielfeld;
    private int sieger;
    private boolean spielBeendet;

    /**
     * Getter fuer den Sieger
     *
     * @return Sieger des Figurenkampfs
     */
    public int getSieger() {
        return sieger;
    }

    /**
     * Konstruktor zur Erzeugung des GUI-Fensters.
     * Anzeige der beiden kaempfenden Figuren und Ausgabe welche gewonnen hat
     *
     * @param figurA angreifende Figur
     * @param figurB angegriffene Figur
     * @param spielfeld Spielfeld, auf dem das Spiel laeuft
     */
    public Figurenkampf(Figur figurA, Figur figurB, Spielfeld spielfeld) {
        super("Figurenkampf auf Feld " + figurA.getPosition().getX()+figurA.getPosition().getY());
        spielfeld.figurenkampfOffen = true;
        figur1 = figurA;
        figur2 = figurB;
        this.spielfeld = spielfeld;

        // Wenn Fahne besiegt
        if (figur1.toString().equals("Fahne")) {
            if (figur1.getTeam()==1) {
                new SpielBeendet(2);
            } else {
                new SpielBeendet(1);
            }
            spielBeendet=true;
        } else if (figur2.toString().equals("Fahne")) {
            if (figur2.getTeam()==2) {
                new SpielBeendet(1);
            } else {
                new SpielBeendet(2);
            }
            spielBeendet=true;
        }

        // Groesse des Fensters
        setSize(300, 300);
        // Position des Fensters. Wird immer in die Mitte gesetzt
        setLocationRelativeTo(null);
        // Fenstergroesse darf nicht geaendert werden
        setResizable(false);

        getContentPane().setLayout(new BorderLayout(1,1));

        // Bilder erzeugen...Figur vom Spieler soll immer links stehen
        if (figur1.getTeam()==1) {
            bild1 = new JLabel(new ImageIcon(getClass().getResource(figur1.getBild())));
            bild2 = new JLabel(new ImageIcon(getClass().getResource(figur2.getBild())));
            // Spieler Figur ist der KI bekannt
            figur1.setIstBekannt(true);
        } else {
            bild1 = new JLabel(new ImageIcon(getClass().getResource(figur2.getBild())));
            bild2 = new JLabel(new ImageIcon(getClass().getResource(figur1.getBild())));
            // Spieler Figur ist der KI bekannt
            figur2.setIstBekannt(true);
        }
        //Text festlegen
        this.vergleicheStaerke();
        int ergebnis=this.sieger;
        String ausgabe;
        if(ergebnis==0){
            ausgabe="<html>Unentschieden. <br> Beide Figuren werden entfernt!</html>";
        } else if(ergebnis==1){ // Figur1 gewinnt
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
        if (!spielBeendet) {
            setVisible(true);
        }
    }

    private void addButtonListener(JButton b) {
        if(b.getName().equals("okay")){
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    spielfeld.setEnabled(true);
                    dispose();
                    spielfeld.figurenkampfOffen = false;
//                    if(figur1.getTeam() == 1) {
//                        // KI zieht nachdem das Figurenkampf-Fenster geschlossen wurde wenn der Spieler angegriffen hat
//                        Spielablauf.gegner.macheZug();
//                    }
                }
            });
        }
    }


    // Vergleicht die Staerken der beiden Figuren
    // 0: Unentschieden, 1: Figur1 gewinnt, 2: Figur2 gewinnt

    // Figur 1 MUSS die angreifende Figur sein
    private void vergleicheStaerke() {
        if (figur2.getStaerke()==1) {
            dispose();
            spielfeld.dispose();
            //new SpielBeendet(figur1.getTeam()); // Spieler von Figur1 hat gewonnen
            this.sieger=-1;
        }
        if (figur1.getStaerke() == figur2.getStaerke()) {
            this.sieger=0;
        } else if (figur1.getStaerke() == 12 && figur2.getStaerke() == 4) {
            // Mineur schlaegt Bombe
            this.sieger=2;
        } else if(figur2.getStaerke() == 12 && figur1.getStaerke() == 4) {
            // Mineur schlaegt Bombe
            this.sieger=1;
        } else if (figur1.getStaerke() == 2 && figur2.getStaerke() == 11) {
            // Ninja schlaegt Feldmarschall, wenn er angreift
            this.sieger=1;
        } else if(figur1.getStaerke() > figur2.getStaerke()) {
            this.sieger=1;
        } else {
            this.sieger=2;
        }
    }
}
