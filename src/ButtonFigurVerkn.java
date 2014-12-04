import javax.swing.*;
import java.awt.*;

public class ButtonFigurVerkn {
    private JButton button;
    private Figur figur;
    private ImageIcon bild;
    private static int count = 0;
    protected Color blue = new Color(0,154,205);
    protected Color green = new Color(0,153,0);
    protected Color red = new Color(207, 4, 0);

    // Noetig, damit bei erneutem Spielstart innerhalb einer Session die Felder wieder ab 0 nummeriert werden
    public ButtonFigurVerkn(){
        count=0;
    };

    // Standard Konstruktor für Figur auf dem Spielfeld
    public ButtonFigurVerkn(Figur figur){
        if(count > 99){
            this.button = new JButton(new ImageIcon(figur.getBild()));
            this.button.setActionCommand(Integer.toString(figur.getPosition().getX() * 10 + figur.getPosition().getY()));
        } else {
            this.button = new JButton(new ImageIcon(figur.getBild()));
            this.button.setActionCommand(Integer.toString(count));
        }
        this.figur = figur;
        count++;
    }

    // Konstruktor für Grass oder Wasser
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

    public ButtonFigurVerkn(Figur a, String farbe) {
        System.out.println(a + "  Team: " +a.getTeam());
        this.button = new JButton(Integer.toString(a.getPosition().getX()*10+a.getPosition().getY()));

        if (farbe.contains("blau") || farbe.contains("blue")) {
            this.button.setBackground(blue);
            // Test Felder für den User unsichtbar zu machen
            //            this.button.setVisible(false);
        } else if (farbe.contains("grün") || farbe.contains("green")) {
            this.button.setBackground(green);
        } else if (farbe.contains("red") || farbe.contains("rot")) {
            this.button.setBackground(red);
            //this.button = new JButton(new ImageIcon(a.getBild()));
        }
        this.figur = a;
    }

    /*
    Setzt eine Figur neu und gibt ihr die richtige Nummer (x*10+y)
     */
    public ButtonFigurVerkn(Figur figur, int count) {
        this.button = new JButton(new ImageIcon(figur.getBild()));
        this.button.setActionCommand(Integer.toString(count));
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
        this.button = new JButton(new ImageIcon(figur.getBild()));
        this.button.setActionCommand(Integer.toString(figur.getPosition().getX() * 10 + figur.getPosition().getY()));
        this.figur=figur;
    }
}
