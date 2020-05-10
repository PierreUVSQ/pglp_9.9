package uvsq.forme;

public class Point {

  protected int abscisse;
  protected int ordonne;

  public Point(int x, int y) {
    this.abscisse = x;
    this.ordonne = y;
  }

  public void deplacer(int x, int y) {
    this.abscisse = x;
    this.ordonne = y;
  }

  public String getPosition() {
    return "(" + abscisse + ", " + ordonne + ")";
  }
}
