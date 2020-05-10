package uvsq.commande;

import uvsq.forme.DaoFactory;
import uvsq.forme.Dessin;

public class SaveCommand implements Command {

  private Dessin dessin;

  /**
   * Commande qui sauvegarde le dessin
   *
   * @param dessin Dessin
   */
  public SaveCommand(Dessin dessin) {
    this.dessin = dessin;
  }

  /** Crée le dessin souhaité. */
  public void execute() {
    DaoFactory.getDessinDao().create(this.dessin);
  }
}
