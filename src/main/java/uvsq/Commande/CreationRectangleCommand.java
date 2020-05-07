package uvsq.Commande;

import uvsq.Forme.Dessin;
import uvsq.Forme.Point;
import uvsq.Forme.Rectangle;

public class CreationRectangleCommand extends CreationFormeCommand {

  private Point p;
  private int longueur;
  private int hauteur;
  private String nom;

  public CreationRectangleCommand(String nom, Point p, int longueur, int hauteur, Dessin dessin) {
    super(dessin);
    this.p = p;
    this.longueur = longueur;
    this.hauteur = hauteur;
    this.nom = nom;
  }

  @Override
  public void execute() {
    if (!this.exist(this.nom)) {
      super.dessin.ajoutElement(new Rectangle(nom, p, longueur, hauteur));
    }
  }
}
