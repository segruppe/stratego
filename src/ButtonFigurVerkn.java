import javax.swing.*;
import java.awt.*;

/**
 * Created by Merten on 19.11.2014.
 */
public class ButtonFigurVerkn {
    private JButton button;
    private Figur figur;
    private ImageIcon bild;
    Color blue = new Color(0,154,205);
    Color green = new Color(0,153,0);

    // Standard Konstruktor für Figur auf dem Spielfeld
    public ButtonFigurVerkn(Figur figur){
        this.button = new JButton(new ImageIcon(figur.getBild()));
        this.figur = figur;

    }

    // Konstruktor für Grass oder Wasser
    public ButtonFigurVerkn(String farbe) {
        if(farbe.contains("blau") || farbe.contains("blue")){
            this.button = new JButton();
            this.button.setBackground(green);
            this.figur = null;
        } else if(farbe.contains("grün") || farbe.contains("green")) {
            this.button = new JButton();
            this.button.setBackground(blue);
            this.figur = null;
        }
    }

    // Getter für den Button
    public JButton getButton() {
        return this.button;
    }

}
