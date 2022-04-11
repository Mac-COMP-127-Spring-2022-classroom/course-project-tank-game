package TankGameFiles;

import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;

public class Tank {
    private double centerX;
    private double centerY;

    public Tank(double centerX, double centerY, double width, double height) {
        this.centerX = centerX;
        this.centerY = centerY;
        GraphicsObject body = new Rectangle(centerX, centerY, );
        GraphicsObject cannon = new Rectangle();
    }

    public void setCannonAngle(double angle) {

    }
}
