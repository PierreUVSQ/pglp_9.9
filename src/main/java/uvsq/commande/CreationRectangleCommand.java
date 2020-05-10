package uvsq.commande;

import uvsq.forme.Dessin;
import uvsq.forme.Point;
import uvsq.forme.Rectangle;

public class CreationRectangleCommand extends CreationFormeCommand {

  private Point point;
  private int longueur;
  private int hauteur;
  private String nom;

  /**
   * Commande de création de rectangle.
   *
   * @param nom Nom du rectangle
   * @param p Point en bas à gauche
   * @param longueur Longueur
   * @param hauteur Hauteur
   * @param dessin Dessin en cours
   */
  public CreationRectangleCommand(String nom, Point p, int longueur, int hauteur, Dessin dessin) {
    super(dessin);
    this.point = p;
    this.longueur = longueur;
    this.hauteur = hauteur;
    this.nom = nom;
  }

  /** Ajoute le rectangle au dessin. */
  @Override
  public void execute() {
    if (!this.exist(this.nom)) {
      super.dessin.ajoutElement(new Rectangle(nom, point, longueur, hauteur));
    }
  }
}
