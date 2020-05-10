package uvsq.forme;

public abstract class Element {

  public final String nom;

  public Element(String nom) {
    this.nom = nom;
  }

  public abstract void afficher();

  public abstract void deplacerDirection(int x, int y);

  public String getNom() {
    return nom;
  }
}
