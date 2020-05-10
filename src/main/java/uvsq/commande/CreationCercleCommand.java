package uvsq.commande;

import uvsq.forme.Cercle;
import uvsq.forme.Dessin;
import uvsq.forme.Point;

public class CreationCercleCommand extends CreationFormeCommand {

  private String nom;
  private Point centre;
  private int rayon;

  /**
   * Commande de création de cercle.
   *
   * @param nom Nom du cercle
   * @param centre Position du centre
   * @param rayon Rayon du cercle
   * @param dessin Dessin en cours
   */
  public CreationCercleCommand(String nom, Point centre, int rayon, Dessin dessin) {
    super(dessin);
    this.nom = nom;
    this.centre = centre;
    this.rayon = rayon;
  }

  /** Ajoute le cercle au dessin. */
  public void execute() {
    if (!this.exist(this.nom)) {
      super.dessin.ajoutElement(new Cercle(nom, centre, rayon));
    }
  }
}
