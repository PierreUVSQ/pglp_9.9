package uvsq.Forme;

public abstract class Forme extends Element {

  public Forme(String nom) {
    super(nom);
  }

  /**
   * Déplace la forme en ajoutant la direction plutôt que de la de la bouger précisément.
   * @param x Abscisse
   * @param y Ordonnée
   */
  public abstract void deplacerDirection(int x, int y);

}
