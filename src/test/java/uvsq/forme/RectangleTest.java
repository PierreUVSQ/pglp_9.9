package uvsq.forme;

import junit.framework.TestCase;

public class RectangleTest extends TestCase {

  public void testAfficher() {}

  public void testDeplacer() {

    Rectangle r1 = new Rectangle("r1", new Point(0, 20), 20, 10);
    r1.deplacer(5, 5);
    assertEquals(r1.bg.getPosition(), "(5, 5)");
  }
}
