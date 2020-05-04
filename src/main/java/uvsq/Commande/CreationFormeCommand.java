package uvsq.Commande;

import uvsq.Forme.Element;

import java.util.List;

public abstract class CreationFormeCommand implements Command{

    protected List<Element> elementList;

    public CreationFormeCommand(List<Element> elementList){
        this.elementList = elementList;

    }


}
