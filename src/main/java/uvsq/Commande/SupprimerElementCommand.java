package uvsq.Commande;

import uvsq.Forme.Dessin;
import uvsq.Forme.Element;

import java.util.List;

public class SupprimerElementCommand implements Command {

  private String aSupprimer;
  private Dessin dessin;

  public SupprimerElementCommand(Dessin dessin, String aSupprimer) {
    this.dessin = dessin;
    this.aSupprimer = aSupprimer;
  }

  @Override
  public void execute() {
    List <Element> liste = this.dessin.getListe();
    for (int i = 0; i < liste.size(); i++) {
      if (liste.get(i).getNom().matches(aSupprimer)) {
        liste.remove(i);
      }
    }
  }
}
