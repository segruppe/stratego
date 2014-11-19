import javax.swing.*;

/**
 * Created by Merten on 19.11.2014.
 */
public class ButtonFigurVerkn {
    private JButton button;
    private Figur figur;
    private ImageIcon bild;

    public ButtonFigurVerkn(Figur figur){
        this.button = new JButton(new ImageIcon(figur.getBild()));
        this.figur = figur;
    }


}
