/**
 * Abstrakte Klasse von der alle Figuren erben
 */
public abstract class Figur {
    /** Zaehlt die Anzahl der Figuren vom Spieler hoch */
	public static int zaehler1=0;
    /** Zaehlt die Anzahl der Figuren der KI hoch */
    public static int zaehler2=40; // Damit die id insgesammt eindeutig ist

    private int id;
	private Position position;
	private int team;
	private int staerke;
	private String bild;
	private String name;
	private boolean istBewegbar; // Fahnen und Bomben nicht bewegbar

    /* Stärken der Figuren
        12: Bombe  (wingdings M)
        11: Feldmarschall
        10: General
        9: Oberst
        8: Major
        7: Hauptmann
        6: Leutnant
        5: Unteroffizier
        4: Mineur
        3: Aufklaerer (Mehr als ein Feld bewegbar) TODO: Bei Position setzen beachten
        2: Ninja
        1: Fahne
	*/

    /**
     * @return id der Figur
     */
	public int getId() {
		return id;
	}
    /**
     * @param id id der Figur
     */
	public void setId(int id) {
		this.id = id;
	}

    /**
     * @return Position der Figur
     */
	public Position getPosition() {
		return position;
	}

    /**
     * @param position Position der Figur
     */
	public void setPosition(Position position) {
		this.position = position;
	}

    /**
     * @return Team der Figur
     */
	public int getTeam() {
		return team;
	}

    /**
     * @param team Team der Figur
     */
	public void setTeam(int team) {
		this.team = team;
	}

    /**
     * @return Staerke der Figur
     */
	public int getStaerke() {
		return staerke;
	}

    /**
     * @param staerke Staerke der Figur
     */
	public void setStaerke(int staerke) {
		this.staerke = staerke;
	}

    /**
     * @return Bild zur Figur
     */
	public String getBild() {
		return bild;
	}

    /**
     * @param bild Bild der Figur
     */
	public void setBild(String bild) {
		this.bild = bild;
	}

    /**
     * @return ist die Figur bewegbar
     */
    public boolean getIstBewegbar() { return istBewegbar; }

    /**
     * @param bewegbar ist die Figur bewegbar
     */
    public void setIstBewegbar(boolean bewegbar) { this.istBewegbar = bewegbar; }

    /**
     * @return Name der Figur
     */
	public String toString() { return name; };
}

