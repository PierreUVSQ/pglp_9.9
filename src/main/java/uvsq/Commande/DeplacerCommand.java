package uvsq.Commande;

import uvsq.Forme.Forme;

public class DeplacerCommand implements Command {

  private Forme forme;
  private int x;
  private int y;

  public DeplacerCommand(Forme forme, int x, int y) {
    this.forme = forme;
    this.x = x;
    this.y = y;
  }

  @Override
  public void execute() {

    this.forme.deplacer(x, y);
  }
}
