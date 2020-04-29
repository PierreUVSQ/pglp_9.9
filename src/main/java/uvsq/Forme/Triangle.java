package uvsq.Forme;

public class Triangle extends Forme {

  protected Point a;
  protected Point b;
  protected Point c;

  public Triangle(String nom, Point a, Point b, Point c) {
    super(nom);
    this.a = a;
    this.b = b;
    this.c = c;
  }

  @Override
  public void afficher() {
    System.out.println(this.nom + "(A=" + this.a.getPosition() +", B=" + this.b.getPosition() + "C=" + this.c.getPosition() + ")");
  }

  @Override
  public void deplacer(int x, int y) {
    int ancienX = this.a.x;
    int ancienY = this.a.y;
    this.a.deplacer(x, y);
    this.b.deplacer(this.b.x + (this.a.x - ancienX), this.b.y + (this.a.x - ancienY));
    this.c.deplacer(this.c.x + (this.a.x - ancienX), this.c.y + (this.a.x - ancienY));
  }
}
