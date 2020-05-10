package uvsq.forme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dessin {

  private List<Element> elementList;
  private String nom;

  public Dessin(String nom) {

    this.nom = nom;
    this.elementList = new ArrayList<>();
  }

  public List<Element> getElementList() {
    return Collections.unmodifiableList(elementList);
  }

  public String getNom() {
    return new String(this.nom);
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setElementList(List<Element> list) {
    this.elementList = list;
  }

  public void ajoutElement(Element elem) {
    this.elementList.add(elem);
  }

  public List<Element> getListe() {
    return this.elementList;
  }

  public boolean exists(String nom) {
    for (int i = 0; i < this.elementList.size(); i++) {
      if (this.elementList.get(i).getNom().matches(nom)) {

        return true;
      }
    }
    return false;
  }
}
