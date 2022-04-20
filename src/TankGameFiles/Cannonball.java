package TankGameFiles;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;



/**
 * Creates a Cannonball that is an ellipse object and moves it on the canvas.
 */
public class Cannonball {
    public static final double GRAVITY = -9.8;
    public static final double RADIUS = 5; 
    private double centerX;
    private double centerY;
    // Will take the angle of the cannon. Make sure that we shoot the cannonball out of the TOP of the cannon. 
    private double dx;
    private double dy;
    private double maxX;
    private double maxY;
    private Ellipse ballShape;
    
    /**
     * Creates a cannonball ellipse object.
     * @param centerX
     * @param centerY
     * @param initialSpeed
     * @param initialAngle
     * @param maxX
     * @param maxY
     */
    public Cannonball(double centerX, double centerY, double initialSpeed, double initialAngle, double maxX, double maxY) {
        this.centerX = centerX;
        this.centerY = centerY;

        this.maxX = maxX;
        this.maxY = maxY;

        double angleInRadians = Math.toRadians(initialAngle);
        this.dx = initialSpeed * Math.cos(angleInRadians);
        this.dy = initialSpeed * -Math.sin(angleInRadians);

        ballShape = new Ellipse(centerX - RADIUS, centerY - RADIUS, 2 * RADIUS, 2* RADIUS);
        ballShape.setFillColor(Color.BLUE);
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public static double getRadius() {
        return RADIUS;
    }

    public boolean updatePosition(double dt) {
        double xCoord = getCenterX() + (dx * dt);
        double yCoord = getCenterY() + (dy * dt);
        
        if (xCoord > 0 && xCoord < maxX) {
            if (yCoord > 0 && yCoord < maxY) {
                centerX = xCoord;
                centerY = yCoord;
                ballShape.setPosition(centerX, centerY);
                dy = dy - (GRAVITY * dt);
                return true;
            }
            return false;
        }
        return false;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(ballShape);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(ballShape);
    }

    /**
     * Gets topmost point of the ball.
     */
    public Point getTopPoint() {
        return new Point(getCenterX(), getCenterY() - RADIUS);
    }

    /**
     * Gets leftmost point of the ball.
     */
    public Point getLeftPoint() {
        return new Point(getCenterX() - RADIUS, getCenterY());
    }

    /**
     * Gets rightmost point of the ball.
     */
    public Point getRightPoint() {
        return new Point(getCenterX() + RADIUS, getCenterY());
    }

    /**
     * Gets bottommost point of the ball.
     */
    public Point getBottomPoint() {
        return new Point(getCenterX(), getCenterY() + RADIUS);
    }
  
}
