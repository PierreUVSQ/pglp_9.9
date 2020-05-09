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

  public DrawingApp() {
    Statement stmt = null;
    String createTrigger = null;
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      connect = DriverManager.getConnection("jdbc:derby:test;create=true");
      stmt = connect.createStatement();

      String createTable = "CREATE TABLE Dessin(dnom varchar(50) PRIMARY KEY NOT NULL)";
      stmt.execute(createTable);
      createTable = "CREATE TABLE Groupe (gnom varchar(50) PRIMARY KEY NOT NULL)";
      stmt.execute(createTable);
      createTable =
          "CREATE TABLE Carre( nom varchar(50) PRIMARY KEY NOT NULL, x int, y int, cote int)";
      stmt.execute(createTable);
      createTable =
          "CREATE TABLE Cercle( nom varchar(50) PRIMARY KEY NOT NULL, x int, y int, rayon int)";
      stmt.execute(createTable);
      createTable =
          "CREATE TABLE Rectangle( nom varchar(50) PRIMARY KEY NOT NULL, x int, y int, longueur int, hauteur int)";
      stmt.execute(createTable);
      createTable =
          "CREATE TABLE Triangle( nom varchar(50) PRIMARY KEY NOT NULL, ax int, ay int, bx int, bay int, cx int, cy int)";
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

     createTrigger = "CREATE TRIGGER delete_recD \n" +
              "AFTER DELETE ON RectangleDessin \n" +
              " DELETE FROM Rectangle R WHERE R.nom NOT IN (SELECT RG.nom FROM RectangleGroupe RG ) AND R.nom NOT IN (SELECT RD.nom FROM RectangleDessin RD )";
      stmt.execute(createTrigger);
      createTrigger = "CREATE TRIGGER delete_cerD \n" +
              "AFTER DELETE ON CercleDessin \n" +
              " DELETE FROM Cercle R WHERE R.nom NOT IN (SELECT RD.nom FROM CercleGroupe RD ) AND R.nom NOT IN (SELECT CD.nom FROM CercleDessin CD )";
      stmt.execute(createTrigger);
      createTrigger = "CREATE TRIGGER delete_carD \n" +
              "AFTER DELETE ON CarreDessin \n" +
              " DELETE FROM Carre R WHERE R.nom NOT IN (SELECT RD.nom FROM CarreGroupe RD ) AND R.nom NOT IN (SELECT CD.nom FROM CarreDessin CD )";
      stmt.execute(createTrigger);
      createTrigger = "CREATE TRIGGER delete_trD \n" +
              "AFTER DELETE ON TriangleDessin \n" +
             " DELETE FROM Triangle R WHERE R.nom NOT IN (SELECT RD.nom FROM TriangleGroupe RD ) AND R.nom NOT IN (SELECT TD.nom FROM TriangleDessin TD )";
      stmt.execute(createTrigger);
      createTrigger = "CREATE TRIGGER delete_grD \n" +
              "AFTER DELETE ON GroupeDessin \n" +
              " DELETE FROM Groupe R WHERE R.gnom NOT IN (SELECT RD.nom FROM GroupeGroupe RD ) AND R.gnom NOT IN (SELECT GD.nom FROM GroupeDessin GD )";
      stmt.execute(createTrigger);

      ////////////////////////////////////////////////////////////////////////////////
      createTrigger = "CREATE TRIGGER delete_recG \n" +
              "AFTER DELETE ON RectangleGroupe \n" +
              " DELETE FROM Rectangle R WHERE R.nom NOT IN (SELECT RG.nom FROM RectangleGroupe RG ) AND R.nom NOT IN (SELECT RD.nom FROM RectangleDessin RD )";
      stmt.execute(createTrigger);
      createTrigger = "CREATE TRIGGER delete_cerG \n" +
              "AFTER DELETE ON CercleGroupe \n" +
              " DELETE FROM Cercle R WHERE R.nom NOT IN (SELECT RD.nom FROM CercleGroupe RD ) AND R.nom NOT IN (SELECT CD.nom FROM CercleDessin CD )";
      stmt.execute(createTrigger);
      createTrigger = "CREATE TRIGGER delete_carG \n" +
              "AFTER DELETE ON CarreGroupe \n" +
              " DELETE FROM Carre R WHERE R.nom NOT IN (SELECT RD.nom FROM CarreGroupe RD ) AND R.nom NOT IN (SELECT CD.nom FROM CarreDessin CD )";
      stmt.execute(createTrigger);
      createTrigger = "CREATE TRIGGER delete_trG \n" +
              "AFTER DELETE ON TriangleGroupe \n" +
              " DELETE FROM Triangle R WHERE R.nom NOT IN (SELECT RD.nom FROM TriangleGroupe RD ) AND R.nom NOT IN (SELECT TD.nom FROM TriangleDessin TD )";
      stmt.execute(createTrigger);
      createTrigger = "CREATE TRIGGER delete_grG \n" +
              "AFTER DELETE ON GroupeGroupe \n" +
              " DELETE FROM Groupe R WHERE R.gnom NOT IN (SELECT RD.nom FROM GroupeGroupe RD ) AND R.gnom NOT IN (SELECT GD.nom FROM GroupeDessin GD )";
      stmt.execute(createTrigger);
      /////////////////////////////////////////////////////////////////////////////
      connect.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      System.out.println("Chargement de la base de données");
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
        System.out.println("Que souhaitez-vous ?");
      }
      this.draw.afficherDessin();
    }
  }
}
