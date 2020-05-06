package uvsq.Forme;

public class DaoFactory {

  public static Dao<Dessin> getDessinDao() {

    return new DessinDao();
  }

  public static Dao<Groupe> getGroupeDao() {

    return new GroupeDao();
  }

  public static Dao<Carre> getCarreDao() {

    return new CarreDao();
  }

  public static Dao<Cercle> getCercleDao() {
    return new CercleDao();
  }

  public static Dao<Rectangle> getRectangleDao() {

    return new RectangleDao();
  }

  public static Dao<Triangle> getTriangleDao() {
    return new TriangleDao();
  }
}
