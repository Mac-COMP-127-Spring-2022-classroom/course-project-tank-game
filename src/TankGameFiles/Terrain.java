package TankGameFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;

public class Terrain extends GraphicsGroup{
    private List<Point> points;
    private CanvasWindow canvas;
    private Random random;


    public Terrain(CanvasWindow canvas, double bounds, double yAxis) {
        this.canvas = canvas;
        points = new ArrayList<>();
        random = new Random();
        // WE WILL BE RANDOMIZING TERRAIN HERE
        for (double xCoord = 0; xCoord < bounds; xCoord += 5) {
            double stuff = random.nextInt(150);
            Point point = new Point(xCoord, yAxis + stuff);
            points.add(point);
        }
    }

    public void setTerrainPoint(Point initialPoint, Point newPoint) {
        points.set(points.indexOf(initialPoint), newPoint);
    }

    public Point getTerrainPoint(int index) {
        return points.get(index);
    }

    public Point getTerrainMovePoint(Point point, double x) {
        Point newPoint = new Point(point.getX() + x, point.getY());
        for (Point p : points) {
            if (Math.round(p.getX()) == Math.round(newPoint.getX())) {
                newPoint = new Point(p.getX(), p.getY()-50);
            }
        }
        return newPoint;
    }

    public void generateTerrain() {
        Path path = new Path(points, false);
        canvas.add(path);
        canvas.draw();
    }

    public void terrainListDebug() {
        System.out.println(points.size());
    }
}
