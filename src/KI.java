import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
  * @date: 30.10.14
  * @author: merten
  */
public class KI {
  // Grenzen für das Gebiet der KI
  private int grenzeX = 10;
  private int grenzeY = 3;
  private String schwierigkeit;

  public KI(String schwierigkeit) {
    this.schwierigkeit = schwierigkeit;
  }

  /*
  Vereinfacht das nutzen der KI
   */
  public Position macheZug() {
    if(this.schwierigkeit == "leicht") {
      return this.leichteKI();
    } else if(this.schwierigkeit == "mittel") {
      return this.mittlereKI();
    } else if(this.schwierigkeit == "schwer") {
      return this.schwereKI();
    } else {
      System.out.println("Keine KI ausgewaehlt!");
      return new Position(0, 0);
    }
  }

  private Position leichteKI() {
    // ich mache den Zug einer leichten KI
    return new Position(0, 0);
  }

  private Position mittlereKI() {
    // ich mache den Zug einer mittleren KI
    return new Position(0, 0);
  }

  private Position schwereKI() {
    // ich mache den Zug einer schweren KI
    return new Position(0, 0);
  }

}