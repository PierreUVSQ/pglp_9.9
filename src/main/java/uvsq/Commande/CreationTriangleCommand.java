package uvsq.Commande;

import uvsq.Forme.Element;
import uvsq.Forme.Point;
import uvsq.Forme.Triangle;

import java.util.List;

public class CreationTriangleCommand extends CreationFormeCommand {

    private String nom;
    private Point a;
    private Point b;
    private Point c;

    public CreationTriangleCommand(String nom, Point a, Point b, Point c, List<Element> elementList){
        super(elementList);
        this.nom = nom;
        this.a = a;
        this.b = b;
        this.c = c;
    }


    @Override
    public void execute() {
        if(! this.exist(this.nom)) {
            super.elementList.add(new Triangle(nom, a, b,c));
        }
    }
}
