package TankGameFiles;

import edu.macalester.graphics.Image;



public class Tank extends Image{              
    private boolean working;
    private Cannon cannon;
    private int hp = 5;
    

    /**
     * Creates a Tank Object.
     * @param x
     * @param y
     * @param imagePath
     * @param cannonX
     * @param cannonY
     * @param cannonPath
     */
    public Tank(double x, double y, String imagePath, double cannonX, double cannonY, String cannonPath) {
        super(x, y, imagePath);
        cannon = new Cannon(cannonX, cannonY, cannonPath);
 
    }

    public int getHP() {
        return hp;
    }

    public int reduceHP(int set) {
        return hp -= set;
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
    // public Point getLeftPoint(){
    //     return new Point(body.getPosition().getX(), getCenterY());
    // }
    // public Point getRightPoint(){
    //     return new Point(getCenterX()+75, getCenterY());
    // }
    //Should fire cannon be here or in TankGame? (Marcus)
}
