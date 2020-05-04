package uvsq.Commande;

import uvsq.Forme.Cercle;
import uvsq.Forme.Element;
import uvsq.Forme.Point;
import uvsq.Forme.Triangle;

import java.util.List;

public class CreationCercleCommand extends CreationFormeCommande{


    private String nom;
    private Point centre;
    private int rayon;

    public CreationCercleCommand(String nom,  Point centre, int rayon, List<Element> elementList) {
        super(elementList);
        this.nom = nom;
        this.centre = centre;
        this.rayon = rayon;
    }

    public void execute() {

        super.elementList.add(new Cercle(nom, centre, rayon));

    }

}
