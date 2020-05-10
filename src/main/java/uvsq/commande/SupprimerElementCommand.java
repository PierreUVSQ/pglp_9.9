package uvsq.commande;

import java.util.List;
import uvsq.forme.Dessin;
import uvsq.forme.Element;

public class SupprimerElementCommand implements Command {

  private String asupprimer;
  private Dessin dessin;

  /**
   * Supprime un élément du dessin.
   *
   * @param dessin Dessin en cours
   * @param asupprimer Nom de l'élément à supprimer
   */
  public SupprimerElementCommand(Dessin dessin, String asupprimer) {
    this.dessin = dessin;
    this.asupprimer = asupprimer;
  }

  /** Supprime l'élément. */
  @Override
  public void execute() {
    List<Element> liste = this.dessin.getListe();
    for (int i = 0; i < liste.size(); i++) {
      if (liste.get(i).getNom().matches(asupprimer)) {
        liste.remove(i);
      }
    }
  }
}
