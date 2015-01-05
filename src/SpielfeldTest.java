import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpielfeldTest {

    Spielfeld spielfeldTest;

    @Before
    public void setUp() { spielfeldTest=new Spielfeld();}

    public final int X = 3;
    public final int Y = 0;
    @Test
    public void testFigurInit() throws Exception {
        spielfeldTest.figurInit(new Fahne(1), X, Y);
        assertEquals("Name:", "Fahne", spielfeldTest.spielfeld[X][Y].getFigur().toString());
        assertEquals("X:", 3, spielfeldTest.spielfeld[X][Y].getFigur().getPosition().getX());
        assertEquals("Y:", 0, spielfeldTest.spielfeld[X][Y].getFigur().getPosition().getY());
    }
}