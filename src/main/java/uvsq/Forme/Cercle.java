package uvsq.Forme;

public class Cercle extends Forme {

  protected Point centre;
  protected int rayon;

  public Cercle(String nom, Point centre, int rayon) {
    super(nom);
    this.rayon = rayon;
    this.centre = centre;
  }

  @Override
  public void afficher() {
    System.out.println(
        this.nom + "(centre=" + this.centre.getPosition() + ",rayon=" + this.rayon + ")");
  }

  public void deplacer(int x, int y) {
    this.centre.deplacer(x, y);
  }

  @Override
  public void deplacerDirection(int x, int y) {
    this.deplacer(this.centre.x + x, this.centre.y + y);
  }
}
