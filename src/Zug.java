/**
 * Klasse um den Zug einer Figur abzubilden
 */
public class Zug {
    private Position position;
    private Figur figur;

    /**
     * Konstruktor, um Zug zu speichern
     * @param position Position der Figur
     * @param figur Figur, die bewegt wird
     */
    public Zug(Position position, Figur figur){
        this.position=position;
        this.figur=figur;
    }

    /**
     * Setzen der neuen Position
     * @param position neue Position der Figur
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Getter fuer die Position
     * @return Position der Figur
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Getter fuer die Figur
     * @return Figur
     */
    public Figur getFigur(){
        return figur;
    }

    /**
     * Setter fuer die Figur
     * @param figur Figur
     */
    public void setFigur(Figur figur){
        this.figur=figur;
    }
}
