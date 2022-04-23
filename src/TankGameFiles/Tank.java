package TankGameFiles;

import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

public class Tank extends Image{              
    private boolean working;
    private Cannon cannon;
    private Point point;
    private int hp = 5;
    private String imagePath;
    

    /**
     * Creates a Tank Object.
     * @param x
     * @param y
     * @param imagePath
     * @param cannonX
     * @param cannonY
     * @param cannonPath
     */
    public Tank(Point point, String imagePath, double cannonX, double cannonY, String cannonPath) {
        super(point.getX(), point.getY(), imagePath);
        this.point = point;
        this.imagePath = imagePath;
        cannon = new Cannon(cannonX, cannonY, cannonPath);
 
    }

    public int getHP() {
        return hp;
    }

    public int reduceHP() {
        return hp -= 1;
    }


    public Cannon getCannon(){
        return cannon;
    }

    public boolean isWorking() {
        return working;
    }

    public void switchWorking() {
        working = !working;
    }

    public double getCenterX(){
        return getPosition().getX() + getWidth()/2;
    }
    public double getCenterY(){
        return getPosition().getY() + getHeight()/2;
    }
    public String toString() {
        return imagePath;
    }
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point p) {
        point = p;
    }
    // public Point getLeftPoint(){
    //     return new Point(body.getPosition().getX(), getCenterY());
    // }
    // public Point getRightPoint(){
    //     return new Point(getCenterX()+75, getCenterY());
    // }
    //Should fire cannon be here or in TankGame? (Marcus)
}
