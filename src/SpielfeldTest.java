import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpielfeldTest {

    Spielfeld spielfeldTest;
    Figur figur;
    Figur figur2;
    Figur figurKi1;
    Figur figurKi2;

    @Before
    public void setUp() {
        spielfeldTest=new Spielfeld();
        figur = new Feldmarschall(1);
        figur2 = new Leutnant(1);
        figurKi1 = new Feldmarschall(2);
        figurKi2 = new Leutnant(2);
    }

    public final int X = 3;
    public final int Y = 0;

    @Test
    public void testFigurInit() throws Exception {
        spielfeldTest.figurInit(figur, X, Y);
        assertEquals("Name:", "Feldmarschall", spielfeldTest.spielfeld[X][Y].getFigur().toString());
        assertEquals("X:", 3, spielfeldTest.spielfeld[X][Y].getFigur().getPosition().getX());
        assertEquals("Y:", 0, spielfeldTest.spielfeld[X][Y].getFigur().getPosition().getY());
    }

    @Test
    public void testZweiFelderRegel() throws Exception {
        // Spieler
        Assert.assertFalse("Regel nicht verletzt S1: ", spielfeldTest.zweiFelderRegel(figur,0));
        Assert.assertFalse("Regel nicht verletzt S2: ", spielfeldTest.zweiFelderRegel(figur,2));
        Assert.assertFalse("Regel nicht verletzt S3: ", spielfeldTest.zweiFelderRegel(figur,0));
        Assert.assertTrue("Regel verletzt S: ", spielfeldTest.zweiFelderRegel(figur, 2));
        Assert.assertFalse("Regel nicht verletzt S4: ", spielfeldTest.zweiFelderRegel(figur,1));
        Assert.assertFalse("Regel nicht verletzt S5: ", spielfeldTest.zweiFelderRegel(figur2,3));

        // KI
        Assert.assertFalse("Regel nicht verletzt K1: ", spielfeldTest.zweiFelderRegel(figurKi1,0));
        Assert.assertFalse("Regel nicht verletzt K2: ", spielfeldTest.zweiFelderRegel(figurKi1,2));
        Assert.assertFalse("Regel nicht verletzt K3: ", spielfeldTest.zweiFelderRegel(figurKi1,0));
        Assert.assertTrue("Regel verletzt K: ", spielfeldTest.zweiFelderRegel(figurKi1, 2));
        Assert.assertFalse("Regel nicht verletzt K4: ", spielfeldTest.zweiFelderRegel(figurKi1,1));
        Assert.assertFalse("Regel nicht verletzt K5: ", spielfeldTest.zweiFelderRegel(figurKi2,3));
    }
}