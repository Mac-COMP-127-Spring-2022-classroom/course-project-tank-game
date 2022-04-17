package TankGameFiles;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Line;

public class Terrain extends GraphicsGroup{
    private Line line;
    private static final double MAX_X =1000;
    private CanvasWindow canvas;

    public Terrain(CanvasWindow canvas) {
        Line line = new Line(0, 400, MAX_X, 400);
    }

    // public void generateTerrain(){
    //     Terrain land = new Terrain();
    //     canvas.add(land);
    // }
    
}
