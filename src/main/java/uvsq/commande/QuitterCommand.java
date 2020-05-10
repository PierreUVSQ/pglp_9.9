package uvsq.commande;

import static java.lang.System.exit;

public class QuitterCommand implements Command {

  public QuitterCommand() {}

  /** Ferme le programme. */
  @Override
  public void execute() {
    exit(0);
  }
}
