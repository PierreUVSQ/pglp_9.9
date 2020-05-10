package uvsq.commande;

import uvsq.forme.DaoFactory;

public class LoadCommand implements Command {

  private String nom;
  private DrawingTui draw;

  /**
   * Commande qui charge le dessin du jdbc.
   *
   * @param nom Nom du dessin
   * @param draw Interface utilisateur
   */
  public LoadCommand(String nom, DrawingTui draw) {
    this.nom = nom;
    this.draw = draw;
  }

  /** Cherche est attache le dessin. */
  @Override
  public void execute() {
    this.draw.setDessin(DaoFactory.getDessinDao().find(this.nom));
  }
}
