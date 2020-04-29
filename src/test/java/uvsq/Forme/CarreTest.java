package uvsq.Forme;

import junit.framework.TestCase;

public class CarreTest extends TestCase {

  public void testAfficher() {
    Carre car1 = new Carre("car1", new Point(0,0), 10);
    car1.afficher();
  }

  public void testDeplacer() {

    Carre car1 = new Carre("car1", new Point(0,0), 10);
    car1.deplacer(15, 15);
    assertEquals(15, car1.p.x);
    assertEquals(15, car1.p.y);

  }
}