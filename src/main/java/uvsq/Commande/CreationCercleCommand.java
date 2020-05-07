package uvsq.Commande;

import uvsq.Forme.Cercle;
import uvsq.Forme.Dessin;
import uvsq.Forme.Point;

public class CreationCercleCommand extends CreationFormeCommand {

  private String nom;
  private Point centre;
  private int rayon;

  public CreationCercleCommand(String nom, Point centre, int rayon, Dessin dessin) {
    super(dessin);
    this.nom = nom;
    this.centre = centre;
    this.rayon = rayon;
  }

  public void execute() {
    if (!this.exist(this.nom)) {
      super.dessin.ajoutElement(new Cercle(nom, centre, rayon));
    }
  }
}
