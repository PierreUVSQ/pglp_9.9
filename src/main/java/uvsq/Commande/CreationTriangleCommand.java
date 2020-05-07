package uvsq.Commande;

import uvsq.Forme.Dessin;
import uvsq.Forme.Point;
import uvsq.Forme.Triangle;

public class CreationTriangleCommand extends CreationFormeCommand {

  private String nom;
  private Point a;
  private Point b;
  private Point c;

  public CreationTriangleCommand(String nom, Point a, Point b, Point c, Dessin dessin) {
    super(dessin);
    this.nom = nom;
    this.a = a;
    this.b = b;
    this.c = c;
  }

  @Override
  public void execute() {
    if (!this.exist(this.nom)) {
      super.dessin.ajoutElement(new Triangle(nom, a, b, c));
    }
  }
}
