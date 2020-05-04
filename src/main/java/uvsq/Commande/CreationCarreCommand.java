package uvsq.Commande;

import uvsq.Forme.Carre;
import uvsq.Forme.Element;
import uvsq.Forme.Point;
import uvsq.Forme.Rectangle;

import java.util.List;

public class CreationCarreCommand extends CreationFormeCommand {

    private String nom;
    private Point c;
    private int cote;


    public CreationCarreCommand(String nom, Point c, int cote, List<Element> elementList) {

        super(elementList);
        this.nom = nom;
        this.c = c;
        this.cote = cote;

    }

    @Override
    public void execute() {
        super.elementList.add(new Carre(nom, c, cote));
    }
}
