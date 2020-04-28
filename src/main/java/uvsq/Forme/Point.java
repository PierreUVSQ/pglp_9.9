package uvsq.Forme;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;

    }

    public void deplacer(int x, int y){
        this.x = x;
        this.y = y;

    }

    public String getPosition(){
        return "(" + x + ", " + y+ ")";
    }

}
