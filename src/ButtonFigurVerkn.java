import javax.swing.*;
import java.awt.*;

/**
 * Created by Merten on 19.11.2014.
 */
public class ButtonFigurVerkn {
    private JButton button;
    private Figur figur;
    private ImageIcon bild;
    private static int count = 0;
    protected Color blue = new Color(0,154,205);
    protected Color green = new Color(0,153,0);

    // Standard Konstruktor für Figur auf dem Spielfeld
    public ButtonFigurVerkn(Figur figur){
        if(count > 99){
           this.button = new JButton(Integer.toString(figur.getPosition().getX() * 10 + figur.getPosition().getY()), new ImageIcon(figur.getBild()));
        } else {
            this.button = new JButton(Integer.toString(count), new ImageIcon(figur.getBild()));
        }
        this.figur = figur;
        count++;
    }

    // Konstruktor für Grass oder Wasser
    public ButtonFigurVerkn(String farbe) {
        this.button = new JButton(Integer.toString(count));

        if(farbe.contains("blau") || farbe.contains("blue")){
            this.button.setBackground(blue);
            // Test Felder für den User unsichtbar zu machen
//            this.button.setVisible(false);
        } else if(farbe.contains("grün") || farbe.contains("green")) {
            this.button.setBackground(green);
        }
        this.figur = null;
        count++;
    }

    /*
    Setzt eine Figur neu und gibt ihr die richtige Nummer (x*10+y)
     */
    public ButtonFigurVerkn(Figur figur, int count) {
        // TODO: Zum Ende hin: Figuren der KI duerfen nicht sichtbar sein. Auf Team ueberpruefen
        this.button = new JButton(Integer.toString(count), new ImageIcon(figur.getBild()));
        this.figur = figur;
    }

    // Getter für den Button
    public JButton getButton() {
        return this.button;
    }

    // Getter für die Figur
    public Figur getFigur() { return this.figur; }
    // Setter für die Figur
    public void setFigur(Figur figur) {
        //this.button = new JButton(Integer.toString(figur.getPosition().getX() * 10 + figur.getPosition().getY()), new ImageIcon("Bilder/fahne.jpg")); //hier ist ne nullpointer
        this.figur=figur;
    }
}
