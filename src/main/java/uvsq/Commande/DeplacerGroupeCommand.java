package uvsq.Commande;

import uvsq.Forme.Groupe;

public class DeplacerGroupeCommand implements Command {

  private Groupe groupe;
  private int x;
  private int y;

  public DeplacerGroupeCommand(Groupe groupe, int x, int y) {
    this.groupe = groupe;
    this.x = x;
    this.y = y;
  }

  @Override
  public void execute() {

    this.groupe.deplacerDirection(x, y);
  }
}
