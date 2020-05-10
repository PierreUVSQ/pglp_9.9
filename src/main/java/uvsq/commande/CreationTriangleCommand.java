package uvsq.commande;

import uvsq.forme.Dessin;
import uvsq.forme.Point;
import uvsq.forme.Triangle;

public class CreationTriangleCommand extends CreationFormeCommand {

  private String nom;
  private Point pointa;
  private Point pointb;
  private Point pointc;

  /**
   * Commande de création de triangle.
   *
   * @param nom Nom du triangle
   * @param a Position du point a
   * @param b Position du point b
   * @param c Position du point c
   * @param dessin Dessin à envoyer
   */
  public CreationTriangleCommand(String nom, Point a, Point b, Point c, Dessin dessin) {
    super(dessin);
    this.nom = nom;
    this.pointa = a;
    this.pointb = b;
    this.pointc = c;
  }

  /** Ajoute le triangle au dessin. */
  @Override
  public void execute() {
    if (!this.exist(this.nom)) {
      super.dessin.ajoutElement(new Triangle(nom, pointa, pointb, pointc));
    }
  }
}
