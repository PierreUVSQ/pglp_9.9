package uvsq.Commande;

import uvsq.Forme.Cercle;
import uvsq.Forme.Element;
import uvsq.Forme.Point;

import java.util.List;

public class CreationCercleCommand extends CreationFormeCommand {


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
        if(! this.exist(this.nom)) {
            super.elementList.add(new Cercle(nom, centre, rayon));
        }

    }

}
