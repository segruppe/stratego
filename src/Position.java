/**
 * Klasse um die Position einer Figur festzulegen
 */
public class Position {
	private int x;
	private int y;

    /**
     * Konstruktor, der die Position festlegt
     *
     * @param xKoordinate Zeile, in der die Figur liegt
     * @param yKoordinate Spalte, in der die Figur liegt
     */
	public Position(int xKoordinate, int yKoordinate){
		this.setX(xKoordinate);
		this.setY(yKoordinate);
	}

    /**
     * Getter fuer die xKoordinate
     *
     * @return Zeile der Figur
     */
	public int getX() {
		return x;
	}

    /**
     * Setter fuer die xKoordinate
     *
     * @param x Zeile der Figur
     */
	public void setX(int x) {
		this.x = x;
	}

    /**
     * Getter fuer die yKoordinate
     *
     * @return Spalte der Figur
     */
	public int getY() {
		return y;
	}

    /**
     * Setter fuer die yKoordinate
     *
     * @param y Spalte der Figur
     */
	public void setY(int y) {
		this.y = y;
	}
}
