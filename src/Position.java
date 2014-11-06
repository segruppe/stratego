
public class Position {
	private int x;
	private int y;
	
	public Position(int xKoordinate, int yKoordinate){
		this.setX(xKoordinate);
		this.setY(yKoordinate);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
