import javax.swing.*;
import java.awt.*;

/**
 * Klasse, um die Button mit den Figuren zu verknuepfen
 *
 * @see Figur
 */
public class ButtonFigurVerkn {
    private JButton button;
    private Figur figur;
    private static int count = 0;
    protected Color blue = new Color(0,154,205);
    protected Color green = new Color(0,153,0);
    protected Color red = new Color(207, 4, 0);

    /**
     * Setzt den Zaehler, der die Button durchnumeriert auf 0.
     * Noetig, damit bei erneutem Spielstart innerhalb einer Session die Felder wieder ab 0 nummeriert werden
     *
     */
    public ButtonFigurVerkn(){
        count=0;
    }

    /**
     * Konstruktor fuer Gras und Wasser Felder
     *
     * @param farbe Soll ein Wasser(blau) oder Gras(Gruen) Feld angelegt werden
     * @param zahl Zahl, die der Button haben soll
     */
    public ButtonFigurVerkn(String farbe, int zahl) {
        if(zahl == -1) {
            this.button = new JButton(Integer.toString(count));
        } else {
            this.button = new JButton(Integer.toString(zahl));
        }

        if (farbe.contains("blau") || farbe.contains("blue")) {
            this.button.setBackground(blue);
        } else if (farbe.contains("grün") || farbe.contains("green")) {
            this.button.setBackground(green);
        }
        this.figur = null;
        count++;
    }

    /**
     * Konstruktor, um fuer den Button einer Figur, den Hintergrund auf Gruen(Gras), Blau(Wasser) oder Rot(Figur der KI)
     * zu setzen
     *
     * @param figur Figur, deren Button betroffen ist
     * @param farbe Hintergrundfarbe fuer den Button
     */
    public ButtonFigurVerkn(Figur figur, String farbe) {
        this.button = new JButton(Integer.toString(figur.getPosition().getX()*10+figur.getPosition().getY()));

        if (farbe.contains("blau") || farbe.contains("blue")) {
            this.button.setBackground(blue);
        } else if (farbe.contains("grün") || farbe.contains("green")) {
            this.button.setBackground(green);
        } else if (farbe.contains("red") || farbe.contains("rot")) {
            // Figuren vom Gegner fuer den Spieler als rot darstellen
            this.button.setBackground(red);
        }
        this.figur = figur;
    }

    /**
     * Konstruktor, um eine Figur neu zu setzen und dem Button die richtige Nummer zu geben
     *
     * @param figur Figur, die neu gesetzt wird
     * @param count Nummer des Buttons
     */
    public ButtonFigurVerkn(Figur figur, int count) {
        this.button = new JButton(new ImageIcon(figur.getBild()));
        this.button.setActionCommand(Integer.toString(count));
        this.figur = figur;
    }

    /**
     * Getter fuer einen Button
     * @return aktuellen Button
     */
    public JButton getButton() {
        return this.button;
    }

    /**
     * Getter fuer die Figur
     * @return aktuelle Figur
     */
    public Figur getFigur() { return this.figur; }
    // Setter für die Figur

    /**
     * Setter fuer die Figur
     * @param figur Figur, die neu gesetzt wird
     */
    public void setFigur(Figur figur) {
        this.button = new JButton(new ImageIcon(figur.getBild()));
        this.button.setActionCommand(Integer.toString(figur.getPosition().getX() * 10 + figur.getPosition().getY()));
        this.figur=figur;
    }
}
