package TankGameFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Line;
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
        int down = 6;
        int up = 6;
        // WE WILL BE RANDOMIZING TERRAIN HERE
        Point init = new Point(0, canvas.getHeight());
        points.add(init);
        for (double xCoord = 0; xCoord < bounds; xCoord += 5) {
            if (yAxis > 570) {
                yAxis = 570;
            }
            if (yAxis < 200) {
                yAxis = 200;
            }
            if (down<5){
                yAxis += 5;
                Point point = new Point(xCoord, yAxis);
                points.add(point);
                down++;
            }
            if (up<5){
                yAxis -= 5;
                Point point = new Point(xCoord, yAxis);
                points.add(point);
                up++;
            }
            if (xCoord%100==0){
                double randomInt = random.nextInt(20+20)-20;
                yAxis += randomInt;
                Point point = new Point(xCoord, yAxis);
                points.add(point);
                if (randomInt>0) {
                    down = 0; 
                }
                if (randomInt<0) {
                    up = 0;
                }
            }
            else{
                Point point = new Point(xCoord, yAxis);
                points.add(point);
            }
        }
        Point end = new Point(canvas.getWidth(), canvas.getHeight());
        points.add(end);
    }

    // public void setTerrainPoint(Point initialPoint, Point newPoint) {
    //     points.set(points.indexOf(initialPoint), newPoint);
    // }

    public Point getTerrainPoint(int index) {
        return points.get(index);
    }

    public Point getTerrainMovePoint(Point point, double x) {
        Point newPoint = new Point(point.getX() + x, point.getY());
        for (Point p : points) {
            if (Math.round(p.getX()) == Math.round(newPoint.getX())) {
                newPoint = new Point(p.getX(), p.getY()-25);
            }
        }
        return newPoint;
    }

    public void deformTerrain(Point point) {
        double checkPoint = 0;
        Point deformCenter = new Point(0, 0);
        for (Point p : points) {
            if (Math.abs(point.getX()-p.getX()) < checkPoint) {
                checkPoint = Math.abs(point.getX()-p.getX());
                deformCenter = p;
            }
        }
    }

    public void generateTerrain() {
        Path path = new Path(points, false);
        canvas.add(path);
        canvas.draw();
    }

    public void terrainListDebug() {
        // System.out.println(points.size());
    }
}
