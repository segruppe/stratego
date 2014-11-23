
public abstract class Figur {
	public static int zaehler1=0;
    public static int zaehler2=40; // Damit die id insgesammt eindeutig ist

    private int id;
	private Position position;
	private int team;
	private int staerke;
	private String bild;
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
        1: Fahne (wingdings O)
	*/

	// Setter und Getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getStaerke() {
		return staerke;
	}
	public void setStaerke(int staerke) {
		this.staerke = staerke;
	}
	public String getBild() {
		return bild;
	}
	public void setBild(String bild) {
		this.bild = bild;
	}
    public boolean getIstBewegbar() { return istBewegbar; }
    public void setIstBewegbar(boolean bewegbar) { this.istBewegbar = bewegbar; }
}

