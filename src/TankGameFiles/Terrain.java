package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Line;

public class Terrain extends GraphicsGroup{
    private Line line;
    // private static final double MAX_X =1000;
    private CanvasWindow canvas;

    public Terrain(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    public void generateTerrain(double maxX){
        Line line = new Line(0, 450, maxX, 450);
        canvas.add(line);
        canvas.draw();
    }
    
}
