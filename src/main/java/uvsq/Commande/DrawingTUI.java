package uvsq.Commande;

public class DrawingTUI {

    public Command nextCommand(String ligne){

       Command res = null;
       if(ligne.split(" ")[0].contentEquals("move") ){


       }

        else if(ligne.split(" ")[0].contentEquals("delete") ){


        }

        else if(ligne.split(" ")[1].contentEquals("=") ){


        }

        else if(ligne.split(" ")[0].contentEquals("group") ){


        }
       return res;
    }


    public void afficherDessin(){
        
    }


}
