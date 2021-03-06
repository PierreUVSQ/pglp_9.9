package uvsq.forme;

public class Carre extends Forme {

  protected Point point;
  protected int cote;

  /**
   * Forme du carré.
   * @param nom Nom du carré
   * @param p Point en bas à gauche
   * @param cote Distance d'un côté
   */
  public Carre(String nom, Point p, int cote) {
    super(nom);
    this.point = p;
    this.cote = cote;
  }

  @Override
  public void afficher() {
    System.out.println(
        this.nom + "(Point = " + this.point.getPosition() + ", côté=" + this.cote + ")");
  }

  public void deplacer(int x, int y) {
    this.point.deplacer(x, y);
  }

  @Override
  public void deplacerDirection(int x, int y) {
    this.deplacer(this.point.abscisse + x, this.point.ordonne + y);
  }
}
