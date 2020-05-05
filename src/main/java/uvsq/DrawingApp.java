package uvsq;

import uvsq.Commande.Command;
import uvsq.Commande.DrawingTUI;
import uvsq.Forme.Element;

import java.util.ArrayList;
import java.util.List;

public class DrawingApp {

    private DrawingTUI draw;
    private Command command;

    public DrawingApp(){

        this.draw = new DrawingTUI();
    }

  public void run() {

    while (true) {
      command = this.draw.nextCommand();
      try {
        command.execute();
      } catch (NullPointerException ne) {
        System.out.println("Que souhaitez-vous ?");
      }
      this.draw.afficherDessin();
    }
    }

}
