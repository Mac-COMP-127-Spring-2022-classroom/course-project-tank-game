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
        for (int i = 0; i<bounds; i+=5) {
            Point point = new Point(xCord, yAxis);
            points.add(point);
            xCord += 5;
        }
    }

    public void setTerrainPoint(Point point, Point newPoint) {
        points.set(points.indexOf(point), newPoint);
    }

    public Point getTerrainPoint(int listPoint) {
        return points.get(listPoint);
    }

    public Point getTerrainMovePoint(Point point, double x) {
        Point newPoint = new Point(point.getX()+x, point.getY());
        for (Point p:points) {
            if (Math.round(p.getX())==Math.round(newPoint.getX())) {
                newPoint = new Point(p.getX(), point.getY());
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
