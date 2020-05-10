package uvsq.commande;

import uvsq.forme.Groupe;

public class DeplacerGroupeCommand implements Command {

  private Groupe groupe;
  private int abscisse;
  private int ordonne;

  /**
   * Commande pour déplacer un groupe.
   *
   * @param groupe Groupe à déplacer
   * @param x Distance verticale
   * @param y Distance horizontale
   */
  public DeplacerGroupeCommand(Groupe groupe, int x, int y) {
    this.groupe = groupe;
    this.abscisse = x;
    this.ordonne = y;
  }

  /** Déplace le groupe. */
  @Override
  public void execute() {

    this.groupe.deplacerDirection(abscisse, ordonne);
  }
}
