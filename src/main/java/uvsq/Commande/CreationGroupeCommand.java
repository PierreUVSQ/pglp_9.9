package uvsq.Commande;

import uvsq.Forme.Dessin;
import uvsq.Forme.Element;
import uvsq.Forme.Groupe;

import java.util.List;

public class CreationGroupeCommand extends CreationFormeCommand {

  private String[] groupElement;
  private String nom;

  public CreationGroupeCommand(String nom, String[] groupElement, Dessin dessin) {
    super(dessin);
    this.groupElement = groupElement;
    this.nom = nom;
  }

  @Override
  public void execute() {
    List<Element> list = this.dessin.getListe();
    Groupe groupe = new Groupe(this.nom);

    for (int i = 0; i < groupElement.length; i++) {
      for (int j = 0; j < list.size(); j++) {
        Element elem = list.get(j);
        if (this.groupElement[i].matches(elem.getNom())) {
          groupe.ajoutForme(elem);
          list.remove(j);
        }
      }
    }
    list.add(groupe);
  }
}
