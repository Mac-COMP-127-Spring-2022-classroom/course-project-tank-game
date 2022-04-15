package TankGameFiles;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;


public class Cannon extends GraphicsGroup{
    private Image cannon;
    private int angle = 0; // Moved angle here so that we can always move the cannon from the last position noted.
    private double centerX;
    private double centerY;
    

    Cannon(double centerX, double centerY, String imagePath){
        this.centerX = centerX;
        this.centerY = centerY;  
        cannon.setMaxHeight(50);
        cannon.setMaxWidth(50);
        cannon = new Image(centerX, centerY, imagePath);
        add(cannon);
    }


    // /**
    //  * 
    //  * @param key 
    //  */
    public void setCannonAngle(KeyboardEvent key) {
        double cannonHalfLength=cannon.getWidth()/2;
        if (key.getKey().equals(Key.valueOf("DOWN_ARROW")) && angle>0){
            angle -= 5;
        }
        if (key.getKey().equals(Key.valueOf("UP_ARROW")) && angle<180){
            angle += 5;
        }
        double x2 = cannon.getX() + cannonHalfLength * Math.cos(Math.toRadians(angle));
        System.out.println(cannon.getX());
        double y2 = cannon.getY() +cannonHalfLength* -Math.sin(Math.toRadians(angle));
        System.out.println(cannon.getY());
    }
    
    // public Point getLeftcannon(){
    //     return new Point(getCenterX(), getCenterY());
    // }
    
}
