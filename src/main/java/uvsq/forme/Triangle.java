package uvsq.forme;

public class Triangle extends Forme {

  protected Point pointa;
  protected Point pointb;
  protected Point pointc;

  public Triangle(String nom, Point a, Point b, Point c) {
    super(nom);
    this.pointa = a;
    this.pointb = b;
    this.pointc = c;
  }

  @Override
  public void afficher() {
    System.out.println(
        this.nom
            + "(A="
            + this.pointa.getPosition()
            + ", B="
            + this.pointb.getPosition()
            + ", C="
            + this.pointc.getPosition()
            + ")");
  }

  public void deplacer(int x, int y) {
    int ancienX = this.pointa.abscisse;
    int ancienY = this.pointa.ordonne;
    this.pointa.deplacer(x, y);
    this.pointb.deplacer(
        this.pointb.abscisse + (this.pointa.abscisse - ancienX),
        this.pointb.ordonne + (this.pointa.abscisse - ancienY));
    this.pointc.deplacer(
        this.pointc.abscisse + (this.pointa.abscisse - ancienX),
        this.pointc.ordonne + (this.pointa.abscisse - ancienY));
  }

  @Override
  public void deplacerDirection(int x, int y) {
    this.deplacer(this.pointa.abscisse + x, this.pointa.ordonne + y);
  }
}
