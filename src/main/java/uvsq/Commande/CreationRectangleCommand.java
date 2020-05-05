package uvsq.Commande;

import uvsq.Forme.Element;
import uvsq.Forme.Point;
import uvsq.Forme.Rectangle;

import java.util.List;

public class CreationRectangleCommand extends CreationFormeCommand {

    private Point p;
    private int longueur;
    private int hauteur;
    private String nom;

    public CreationRectangleCommand(String nom, Point p, int longueur, int hauteur, List<Element> elementList) {
        super(elementList);
        this.p = p;
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.nom = nom;
    }


    @Override
    public void execute() {
        if(! this.exist(this.nom)) {
            super.elementList.add(new Rectangle(nom, p, longueur, hauteur));
        }

    }
}
