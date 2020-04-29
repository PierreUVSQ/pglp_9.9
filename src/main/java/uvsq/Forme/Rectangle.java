package uvsq.Forme;

public class Rectangle extends Forme {

  protected Point bg;
  protected int longueur;
  protected int hauteur;

  public Rectangle(String nom, Point bg, int longueur, int hauteur) {
    super(nom);
    this.bg = bg;
    this.longueur = longueur;
    this.hauteur = hauteur;
  }

  @Override
  public void afficher() {

    System.out.println();
  }

  @Override
  public void deplacer(int x, int y) {
    this.bg.deplacer(x, y);
  }

  @Override
  public void deplacerDirection(int x, int y) {
    this.bg.deplacer(this.bg.x + x, this.bg.y + y);
  }
}
