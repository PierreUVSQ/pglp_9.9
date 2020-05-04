package uvsq.Commande;

import uvsq.Forme.Element;

import java.util.List;

public class SupprimerElementCommand implements Command {

  private String aSupprimer;
  private List<Element> elementList;

  public SupprimerElementCommand(List<Element> elementList, String aSupprimer) {
    this.elementList = elementList;
    this.aSupprimer = aSupprimer;
  }

  @Override
  public void execute() {
    for (Element elem : elementList) {
      if (elem.getNom().contentEquals("aSupprimer")) {
        this.elementList.remove(elem);
      }
    }
  }
}
