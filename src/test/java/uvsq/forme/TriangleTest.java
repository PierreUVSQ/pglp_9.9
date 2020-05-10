package uvsq.forme;

import junit.framework.TestCase;

public class TriangleTest extends TestCase {

  public void testDeplacer() {

    Triangle t1 = new Triangle("t1", new Point(10, 10), new Point(12, 14), new Point(14, 10));
    t1.deplacer(0, 0);
    assertEquals(t1.pointa.abscisse, 0);
    assertEquals(t1.pointa.ordonne, 0);
    assertEquals(t1.pointb.abscisse, 2);
    assertEquals(t1.pointb.ordonne, 4);
    assertEquals(t1.pointc.abscisse, 4);
    assertEquals(t1.pointc.ordonne, 0);
  }
}
