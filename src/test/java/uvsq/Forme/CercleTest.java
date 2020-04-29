package uvsq.Forme;

import junit.framework.TestCase;

public class CercleTest extends TestCase {

  public void testAfficher() {
    Cercle c1 = new Cercle("c1", new Point(10, 10), 10);
    c1.afficher();
  }

  public void testDeplacer() {
    Cercle c1 = new Cercle("c1", new Point(10, 10), 10);
    c1.deplacer(0, 0);
    assertEquals(0, c1.centre.x);
    assertEquals(0, c1.centre.y);
  }
}
