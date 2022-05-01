package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class ForceMeter extends GraphicsGroup{
    public double force;
    public Rectangle currentForceMeter;
    public Rectangle forceMeter;
    public CanvasWindow canvas;

    public ForceMeter(CanvasWindow canvas) {
        this.canvas = canvas;
        forceMeter = new Rectangle(0, 0, 400, 20);
    }

    public void setForce(double force) {
        this.force = force;
    }

    public void updateForceMeter() {
        currentForceMeter = new Rectangle(40, 40, 2 * force, 20);
        currentForceMeter.setFillColor(Color.RED);
        canvas.add(currentForceMeter);
    }

    public void resetForceMeter() {
        currentForceMeter = new Rectangle(40, 40, 2 * force, 20);
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(forceMeter);
    }
}
