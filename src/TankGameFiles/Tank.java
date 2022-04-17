package TankGameFiles;

import edu.macalester.graphics.Image;



public class Tank extends Image{              
    private boolean working;
    private Cannon cannon;

    public Tank(double x, double y, String imagePath, double cannonY, String cannonPath) {
        super(x, y, imagePath);
        cannon = new Cannon(getCenterX(), cannonY, cannonPath);
 
    }

    /**
     * Moves Tank
     * @param key
     */
    // public void moveTank(KeyboardEvent key) {
    //     // if(body.getLeftPoint())
    //         if (key.getKey().equals(Key.valueOf("LEFT_ARROW"))){
    //             moveBy(-5, 0);
    //         }
    //         if (key.getKey().equals(Key.valueOf("RIGHT_ARROW"))){
    //             moveBy(5, 0);
    //         }
    // }

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
