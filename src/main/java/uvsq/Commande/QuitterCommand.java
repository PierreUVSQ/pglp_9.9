package uvsq.Commande;

import static java.lang.System.exit;

public class QuitterCommand implements Command {

    public QuitterCommand() {

    }


    @Override
    public void execute() {
        exit(0);
    }
}
