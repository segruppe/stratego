
public class Spielfeld {
	public int[10][10] spielfeld;
	
	
	public void figurSetzen(Figur a, int x, int y) {
		// Alte Position auf null setzen
		spielfeld[a.getPosition().getX()][a.getPosition().getY()] = null;
		// Neue Position setzen
		spielfeld[x][y] = a;
	}
	
	public void figurSetzen(Figur a, Position pos) {
		// Alte Position auf null setzen
		spielfeld[a.getPosition().getX()][a.getPosition().getY()] = null;
		// Neue Position setzen
		spielfeld[pos.getX()][pos.getY()] = a;
	}
	
	public boolean spielfeldVergleichen(Spielfeld feld2) {
		int length = this.spielfeld.lenth;
		for(int i=0; i<length; i++){
			for(int j=0; j<length; j++){
				// Wenn ein Feld unterschiedlich ist, gebe false zurueck
				if(this.spielfeld[i][j] != feld2[i][j])
					return false;
			}
				
		}
		// Ansonsten true
		return true;
	}
	
	public void spielfeldSpeichern() {
		// Noch uberlegen wie wir das speichern
		/*	
		 * [3][3] - so ungefaehr vielleicht? 
		 * typ:team typ:team null
		 *  null typ:team null
		 *  typ:team null typ:team
		 * 
		 */
	}
	
	public void spielfeldLaden(File datei) {
		// haengt von Speicherung ab
	}
      
}