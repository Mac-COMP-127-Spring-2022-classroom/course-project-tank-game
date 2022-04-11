package TankGameFiles;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

public class Cannonball {
    public static final double GRAVITY = -9.8;
    public static final double RADIUS = 0; 
    private double centerX;
    private double centerY;
    // Will take the angle of the cannon. Make sure that we shoot the cannonball out of the TOP of the cannon. 
    private double dx;
    private double dy;
    private double maxX;
    private double maxY;
    
    public Cannonball(double centerX, double centerY, double initialSpeed, double initialAngle, double maxX, double maxY) {
        this.centerX = centerX;
        this.centerY = centerY;

        this.maxX = maxX;
        this.maxY = maxY;

        double angleInRadians = Math.toRadians(initialAngle);
        this.dx = initialSpeed * Math.cos(angleInRadians);
        this.dy = initialSpeed * -Math.sin(angleInRadians);



        Ellipse ballShape = new Ellipse(centerX - RADIUS, centerY - RADIUS, 2 * RADIUS, 2* RADIUS);
    }
}
