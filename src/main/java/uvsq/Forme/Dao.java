package uvsq.Forme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao<T> implements AutoCloseable {

  protected Connection connect = null;

  public Statement stmt = null;

  public abstract T create(T obj);

  public abstract T find(String id);

  public abstract void delete(String id);

  /** Etablie la connexion avec le SGBD. */
  public void connect() {

    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      connect = DriverManager.getConnection("jdbc:derby:test");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      try {
        connect.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  /** Ferme la connexion avec le SGBD. */
  public void disconnect() {

    try {
      connect.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
