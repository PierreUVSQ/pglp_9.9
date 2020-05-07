package uvsq.Commande;

import uvsq.Forme.Dessin;
import uvsq.Forme.Element;

public abstract class CreationFormeCommand implements Command {

  protected Dessin dessin;

  public CreationFormeCommand(Dessin dessin) {
    this.dessin = dessin;
  }

  public boolean exist(String nom) {

    boolean res = false;

    for (Element elem : dessin.getElementList()) {

      if (elem.getNom().matches(nom)) {
        return true;
      }
    }

    return false;
  }
}
