package uvsq;

import uvsq.Commande.Command;
import uvsq.Commande.DrawingTUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DrawingApp {

    private DrawingTUI draw;
    private Command command;
    private Connection connect;

    public DrawingApp(){
        Statement stmt = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connect = DriverManager.getConnection("jdbc:derby:test;create=true");
            stmt = connect.createStatement();

            String createTable =
                    "CREATE TABLE Dessin(dnom varchar(50) PRIMARY KEY NOT NULL)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE Groupe (gnom varchar(50) PRIMARY KEY NOT NULL)";
            stmt.execute(createTable);
            createTable = "CREATE TABLE Carre( nom varchar(50) PRIMARY KEY NOT NULL, x int, y int, cote int)";
            stmt.execute(createTable);
            createTable = "CREATE TABLE Cercle( nom varchar(50) PRIMARY KEY NOT NULL, x int, y int, rayon int)";
            stmt.execute(createTable);
            createTable = "CREATE TABLE Rectangle( nom varchar(50) PRIMARY KEY NOT NULL, x int, y int, longueur int, hauteur int)";
            stmt.execute(createTable);
            createTable = "CREATE TABLE Triangle( nom varchar(50) PRIMARY KEY NOT NULL, ax int, ay int, bx int, bay int, cx int, cy int)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE CarreGroupe(gnom varchar(50), nom varchar(50), PRIMARY KEY(gnom,nom), FOREIGN KEY (gnom) REFERENCES Groupe(gnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Carre(nom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE CercleGroupe(gnom varchar(50), nom varchar(50), PRIMARY KEY(gnom,nom), FOREIGN KEY (gnom) REFERENCES Groupe(gnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Cercle(nom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE RectangleGroupe(gnom varchar(50), nom varchar(50), PRIMARY KEY(gnom,nom), FOREIGN KEY (gnom) REFERENCES Groupe(gnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Rectangle(nom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE TriangleGroupe(gnom varchar(50), nom varchar(50), PRIMARY KEY(gnom,nom), FOREIGN KEY (gnom) REFERENCES Groupe(gnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Triangle(nom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE GroupeGroupe(gnom varchar(50), nom varchar(50), PRIMARY KEY(gnom,nom), FOREIGN KEY (gnom) REFERENCES Groupe(gnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Groupe(gnom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            /////////////////////////////////////////////////////////////////////////////

            createTable =
                    "CREATE TABLE CarreDessin(dnom varchar(50), nom varchar(50), PRIMARY KEY(dnom,nom), FOREIGN KEY (dnom) REFERENCES Dessin(dnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Carre(nom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE CercleDessin(dnom varchar(50), nom varchar(50), PRIMARY KEY(dnom,nom), FOREIGN KEY (dnom) REFERENCES Dessin(dnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Cercle(nom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE RectangleDessin(dnom varchar(50), nom varchar(50), PRIMARY KEY(dnom,nom), Constraint FK_RDD FOREIGN KEY (dnom) REFERENCES Dessin(dnom) ON DELETE CASCADE , Constraint FK_RDN FOREIGN KEY (nom) REFERENCES Rectangle(nom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE TriangleDessin(dnom varchar(50), nom varchar(50), PRIMARY KEY(dnom,nom), FOREIGN KEY (dnom) REFERENCES Dessin(dnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Triangle(nom) ON DELETE CASCADE)";
            stmt.execute(createTable);
            createTable =
                    "CREATE TABLE GroupeDessin(dnom varchar(50), nom varchar(50), PRIMARY KEY(dnom,nom), FOREIGN KEY (dnom) REFERENCES Dessin(dnom) ON DELETE CASCADE, FOREIGN KEY (nom) REFERENCES Groupe(gnom) ON DELETE CASCADE)";
            stmt.execute(createTable);



            /////////////////////////////////////////////////////////////////////////////
            connect.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            try {
                connect.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        this.draw = new DrawingTUI();
    }

  public void run() {

    while (true) {
      command = this.draw.nextCommand();
      try {
        command.execute();
      } catch (NullPointerException ne) {
        ne.printStackTrace();
        System.out.println("Que souhaitez-vous ?");
      }
      this.draw.afficherDessin();

    }
    }

}
