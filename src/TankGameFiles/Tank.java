package TankGameFiles;

import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

public class Tank {
    private final double BARREL_LENGTH = 10;
    private double centerX;
    private double centerY;
    Line cannon;

    public Tank(double centerX, double centerY, double width, double height) {
        this.centerX = centerX;
        this.centerY = centerY;
        GraphicsObject body = new Rectangle(centerX, centerY, );
        cannon = new Line(centerX, centerY, centerX+BARREL_LENGTH, centerY);
    }

    public void setCannonAngle(KeyboardEvent key) {
        int angle = 0;
        if (key.getKey().equals(Key.valueOf("DOWN_ARROW"))){
            angle -= 5;
        }
        if (key.getKey().equals(Key.valueOf("UP_ARROW"))){
            angle += 5;
        }
        double x2 = cannon.getX1() + BARREL_LENGTH * Math.cos(Math.toRadians(angle));
        double y2 = cannon.getY1() + BARREL_LENGTH * -Math.sin(Math.toRadians(angle));
        cannon.setEndPosition(x2, y2);
    }

    //Should fire cannon be here or in TankGame? (Marcus)
}
