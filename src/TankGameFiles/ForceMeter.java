package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class ForceMeter extends GraphicsGroup{
    private double force;
    private Rectangle currentForceMeter;
    private Rectangle forceMeter;
    private CanvasWindow canvas;
    private double xCoord;
    private double yCoord;
    private final double SCALE = 3;
    private final double BAR_WIDTH = 400;
    private final double BAR_HEIGHT = 20;

    public ForceMeter(CanvasWindow canvas, double xCoord, double yCoord) {
        this.canvas = canvas;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        forceMeter = new Rectangle(xCoord, yCoord, 400, 20);
        currentForceMeter = new Rectangle(xCoord, yCoord, 0, 20);
    }

    public double getBarWidth() {
        return BAR_WIDTH;
    }

    public double getProgressBarScale() {
        return SCALE;
    }

    public void setForce(double force) {
        this.force = force;
        if (SCALE * this.force >= 400) {
            this.force = 400/SCALE;
        } 
    }

    public double getForce() {
        return force;
    }

    public void updateRedForceMeter() {
        currentForceMeter.setSize(SCALE * force, 20);
        currentForceMeter.setFillColor(Color.RED);
        canvas.add(currentForceMeter);
    }


    public void updateBlueForceMeter() {
        currentForceMeter.setSize(SCALE * force, 20); // Blue Force Meter should expand from Right-to-Left, not Left-to-Right
        currentForceMeter.setFillColor(Color.BLUE);
        canvas.add(currentForceMeter);
    }

    public void resetForceMeter() {
        currentForceMeter.setSize(0, 0);
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(forceMeter);
    }
}
