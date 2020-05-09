package uvsq.Commande;
import uvsq.Forme.DaoFactory;

public class SupprimerDessinCommand implements Command {

    private String nom;


    public SupprimerDessinCommand(String nom) {
        this.nom = nom;
    }

    @Override
    public void execute() {
        DaoFactory.getDessinDao().delete(nom);

    }
}
