package uvsq.commande;

import uvsq.forme.Forme;

public class DeplacerCommand implements Command {

  private Forme forme;
  private int abscisse;
  private int ordonne;

  /**
   * Commande qui déplace une forme à la position souhaité.
   *
   * @param forme Forme à déplacer
   * @param x abscicsse
   * @param y ordonnée
   */
  public DeplacerCommand(Forme forme, int x, int y) {
    this.forme = forme;
    this.abscisse = x;
    this.ordonne = y;
  }

  /** Déplace la forme. */
  @Override
  public void execute() {

    this.forme.deplacer(abscisse, ordonne);
  }
}
