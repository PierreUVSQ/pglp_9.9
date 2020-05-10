package uvsq.commande;

import java.util.List;
import uvsq.forme.Dessin;
import uvsq.forme.Element;
import uvsq.forme.Groupe;


public class CreationGroupeCommand extends CreationFormeCommand {

  private String[] groupElement;
  private String nom;

  /**
   * Commande qui crée un groupe.
   *
   * @param nom Nom du groupe
   * @param groupElement Liste des noms des éléments du groupe
   * @param dessin Dessin en cours
   */
  public CreationGroupeCommand(String nom, String[] groupElement, Dessin dessin) {
    super(dessin);
    this.groupElement = groupElement;
    this.nom = nom;
  }

  /** Ajoute le groupe au dessin. */
  @Override
  public void execute() {
    List<Element> list = this.dessin.getListe();
    Groupe groupe = new Groupe(this.nom);

    for (int i = 0; i < groupElement.length; i++) {
      if (!this.dessin.exists(groupElement[i])) {
        System.out.println("Les éléments formant le groupe n'existent pas.");
        return;
      }
    }

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
