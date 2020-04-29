package uvsq.Forme;

public class Carre extends Forme {

  protected Point p;
  protected int cote;

  public Carre(String nom, Point p, int cote) {
    super(nom);
    this.p = p;
    this. cote = cote;

  }

  @Override
  public void afficher() {
    System.out.println(this.nom + "(Point=" + this.p.getPosition() + "," + "côté=" + this.cote);
  }

  @Override
  public void deplacer(int x, int y) {
    this.p.deplacer(x, y);
  }

  @Override
  public void deplacerDirection(int x, int y) {
    this.deplacer(this.p.x + x, this.p.y + y);
  }
}
