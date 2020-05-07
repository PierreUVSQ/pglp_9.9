package uvsq.Commande;

import uvsq.Forme.Carre;
import uvsq.Forme.Dessin;
import uvsq.Forme.Point;

public class CreationCarreCommand extends CreationFormeCommand {

  private String nom;
  private Point c;
  private int cote;

  public CreationCarreCommand(String nom, Point c, int cote, Dessin dessin) {

    super(dessin);
    this.nom = nom;
    this.c = c;
    this.cote = cote;
  }

  @Override
  public void execute() {
    if (!this.exist(this.nom)) {
      super.dessin.ajoutElement(new Carre(nom, c, cote));
    }
  }
}
