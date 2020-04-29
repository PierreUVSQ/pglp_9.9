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

  @Override
  public void deplacer(int x, int y) {
    this.centre.deplacer(x, y);
  }
}
