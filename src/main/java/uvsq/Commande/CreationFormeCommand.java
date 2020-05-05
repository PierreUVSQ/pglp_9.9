package uvsq.Commande;

import uvsq.Forme.Element;

import java.util.List;

public abstract class CreationFormeCommand implements Command{

    protected List<Element> elementList;

    public CreationFormeCommand(List<Element> elementList){
        this.elementList = elementList;

    }

    public boolean exist(String nom) {

        boolean res = false;

        for(Element elem : elementList) {

            if(elem.getNom().matches(nom)){
                return true;
            }

        }


        return false;
    }


}
