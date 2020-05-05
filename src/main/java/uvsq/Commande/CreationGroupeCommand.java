package uvsq.Commande;

import uvsq.Forme.Element;
import uvsq.Forme.Groupe;
import java.util.List;

public class CreationGroupeCommand extends CreationFormeCommand {

  private String[] groupElement;
  private String nom;

  public CreationGroupeCommand(String nom, String[] groupElement, List<Element> elementList) {
    super(elementList);
    this.groupElement = groupElement;
    this.nom = nom;
  }

  @Override
  public void execute() {

    Groupe groupe = new Groupe(this.nom);
    for (int i = 0; i < groupElement.length; i++) {
      for (int j = 0; j < this.elementList.size(); j++) {
        Element elem = this.elementList.get(j);
        if (this.groupElement[i].matches(elem.getNom())) {
          groupe.ajoutForme(elem);
          this.elementList.remove(j);
        }
      }
    }
    this.elementList.add(groupe);
  }
}
