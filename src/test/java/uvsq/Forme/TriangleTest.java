package uvsq.Forme;

import junit.framework.TestCase;

public class TriangleTest extends TestCase {

  public void testDeplacer() {

    Triangle t1 = new Triangle("t1", new Point(10, 10), new Point(12, 14), new Point(14, 10));
    t1.deplacer(0, 0);
    assertEquals(t1.a.x, 0);
    assertEquals(t1.a.y, 0);
    assertEquals(t1.b.x, 2);
    assertEquals(t1.b.y, 4);
    assertEquals(t1.c.x, 4);
    assertEquals(t1.c.y, 0);
  }
}
