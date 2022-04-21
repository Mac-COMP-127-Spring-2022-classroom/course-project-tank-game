package TankGameFiles;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;

public class Terrain extends GraphicsGroup{
    private List<Point> points;
    // private static final double MAX_X =1000;
    private CanvasWindow canvas;

    public Terrain(CanvasWindow canvas, double bounds, double yAxis) {
        this.canvas = canvas;
        points = new ArrayList<>();
        double xCord = 0;
        do {
            Point point = new Point(xCord, yAxis);
            points.add(point);
            xCord += 5;
        }
        while (xCord != bounds);
    }

    public void generateTerrain(){
        Path path = new Path(points, false);
        canvas.add(path);
        canvas.draw();
    }
    
}
