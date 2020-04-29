package uvsq.Forme;

import junit.framework.TestCase;
import uvsq.Forme.Cercle;
import uvsq.Forme.Groupe;
import uvsq.Forme.Point;
import uvsq.Forme.Rectangle;

public class GroupeTest extends TestCase {

  public void testDeplacer() {

    Groupe g1 = new Groupe("g1");
    g1.ajoutForme(new Rectangle("r1", new Point(10, 10), 5, 5));
    g1.ajoutForme(new Cercle("c1",new Point(20, 14), 2));
    g1.deplacer(20, 20);
  }
}