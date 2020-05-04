package uvsq.Commande;

import uvsq.Forme.Element;

import java.util.List;

public abstract class CreationFormeCommande implements Command{

    protected List<Element> elementList;

    public CreationFormeCommande(List<Element> elementList){
        this.elementList = elementList;

    }


}
