public class Zug {
    private Position position;
    private Figur figur;

    public Zug(Position position, Figur figur){
        this.position=position;
        this.figur=figur;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    public Figur getFigur(){
        return figur;
    }
    public void setFigur(Figur figur){
        this.figur=figur;
    }
}
