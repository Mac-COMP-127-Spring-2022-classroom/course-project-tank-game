package TankGameFiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.events.MouseMotionEvent;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import java.util.Timer;

public class TankManager {
    private CanvasWindow canvas;
    private List<Tank> tanks;
    private List<Cannon> cannons;
    private Tank redTank;
    private Tank blueTank;
    private Terrain terrain;
    private Point redTankPoint, blueTankPoint, redCannonPoint, blueCannonPoint;
    private String blueCannonPath, redCannonPath;
    private double force;
    private Rectangle currentForceMeter;
    private double tankAngle;
    private ForceMeter forceMeter;
    private Timer timer;

    public TankManager(CanvasWindow canvas, Terrain terrain) {
        tanks = new ArrayList<>();
        cannons = new ArrayList<>();
        redCannonPath="RedCannon.png";
        blueCannonPath="BlueCannon.png";
        this.canvas = canvas;
        this.terrain = terrain;
        redTankPoint = new Point(terrain.getTerrainPoint(10).getX(), terrain.getTerrainPoint(5).getY()-50);
        blueTankPoint = new Point(terrain.getTerrainPoint(115).getX(), terrain.getTerrainPoint(110).getY()-50);
        redCannonPoint = new Point(redTankPoint.getX()+40, redTankPoint.getY()+1);
        blueCannonPoint = new Point(blueTankPoint.getX()-10, blueTankPoint.getY()+1);
        redTank = new Tank(redTankPoint, "RedTank.png",  redCannonPoint.getX(), redCannonPoint.getY(), redCannonPath);
        blueTank = new Tank(blueTankPoint, "BlueTank.png", blueCannonPoint.getX(), blueCannonPoint.getY(), blueCannonPath);
        timer = new Timer();
        force = 0;
        forceMeter = new ForceMeter(canvas);
        forceMeter.addToCanvas(canvas);
        // Still need to cap the force meter and reset the force meter.
    }

    public void generateTanks() {
        for (int i = 0; i < 2; i++) {
            if (i < 1) {
                redTank.setMaxHeight(50);
                redTank.getCannon().setMaxWidth(50);
                System.out.println(redTank.getCannon().getImageHeight());
                canvas.add(redTank.getCannon());
                redTank.setCenter(redTankPoint);
                canvas.add(redTank);
                tanks.add(redTank);
            }
            else {
                blueTank.getCannon().setAngle(180);
                blueTank.setMaxHeight(50);
                blueTank.getCannon().setMaxWidth(50);
                canvas.add(blueTank.getCannon());
                canvas.add(blueTank);
                tanks.add(blueTank);
                blueTank.switchWorking();
            }
        }
    }
       /**
     * Moves Tank
     * @param key
     */
    public void moveTank(KeyboardEvent key) {
        if(getWorkingTank().getCenterX()-getWorkingTank().getWidth()/2 > 0 ){ 
            if (key.getKey().equals(Key.valueOf("LEFT_ARROW"))){
                getWorkingTank().setPoint(terrain.getTerrainMovePoint(workingTankPoint(), -5));
                getWorkingTank().setRotation(tankAngleCalc(-5));   
                getWorkingTank().setPosition(workingTankPoint());
                getWorkingTank().setCenter(getWorkingTank().getPosition());
                centerCannonToTank();
                // getWorkingCannon().setPosition(getWorkingCannon().getPoint());   
            }
        }

        if(getWorkingTank().getCenterX() + getWorkingTank().getWidth()/2 < canvas.getWidth()){
            if (key.getKey().equals(Key.valueOf("RIGHT_ARROW"))){ 
                getWorkingTank().setPoint(terrain.getTerrainMovePoint(workingTankPoint(), 5));
                getWorkingTank().setRotation(tankAngleCalc(5));  
                getWorkingTank().setPosition(workingTankPoint());  
                getWorkingTank().setCenter(getWorkingTank().getPosition()); 
                centerCannonToTank();
                // getWorkingCannon().setCenter(25 * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),  - 25 * Math.sin(Math.toRadians(getWorkingCannon().getAngle())) +getWorkingTank().getY() +7.5);
            }
        }
    }

    /**
    * Changes cannon angle
    * @param key
    * @param cannon
    */
    public void setCannonAngle(KeyboardEvent key) {
        if (key.getKey().equals(Key.valueOf("DOWN_ARROW")) && getWorkingCannon().getAngle()>0){
            
            getWorkingCannon().rotateBy(5);
            getWorkingCannon().setAngle(getWorkingCannon().getAngle() - 5);
            centerCannonToTank();
        }
        
            if (key.getKey().equals(Key.valueOf("UP_ARROW")) && getWorkingCannon().getAngle()<180){
            getWorkingCannon().rotateBy(-5);
            getWorkingCannon().setAngle(getWorkingCannon().getAngle() + 5);
            centerCannonToTank();
            }
        
    }

        /**
     * Centers cannon to tank.
     */
    private void centerCannonToTank(){
        getWorkingCannon().setCenter(25 * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),   - 25 * Math.sin(Math.toRadians(getWorkingCannon().getAngle()))+getWorkingTank().getY() + 7.5);
     }
 

    public void fireCannon(KeyboardEvent key) {
        Cannonball ball = new Cannonball((25+(getWorkingCannon().getImageWidth()/2)) * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),   (25+(getWorkingTank().getImageWidth()/2)) * -Math.sin(Math.toRadians(getWorkingCannon().getAngle()))+getWorkingTank().getY() +7.5, getForce(), getWorkingCannon().getAngle(), canvas.getWidth(), canvas.getHeight());
        if (key.getKey().equals(Key.valueOf("SPACE"))) {
            ball.addToCanvas(canvas);
            
            while (ball.updatePosition(0.1)) {
                if (intersects(ball) == 0){
                    if (canvas.getElementAt(ball.getBottomPoint()) == redTank || canvas.getElementAt(ball.getLeftPoint()) == redTank || canvas.getElementAt(ball.getRightPoint()) == redTank|| canvas.getElementAt(ball.getTopPoint()) == redTank) {
                        redTank.reduceHP();
                        redTank.getHP();
                    }
                    if (canvas.getElementAt(ball.getBottomPoint()) == blueTank || canvas.getElementAt(ball.getLeftPoint()) == blueTank || canvas.getElementAt(ball.getRightPoint()) == blueTank || canvas.getElementAt(ball.getTopPoint()) == blueTank) {
                        blueTank.reduceHP();
                        blueTank.getHP();
                    }
                    // System.out.println(getWorkingTank().getHP() + "\t" + getWorkingTank());
                    // System.out.println(notWorkingTank().getHP() + "\t" + notWorkingTank());
                    if (checkLives()) {
                        // System.out.println(tanks.get(0) + " Wins!");
                        canvas.closeWindow();
                    };
                    break;
                }
                if (intersects(ball) == 1){
                    break;
                }
                canvas.draw();
            }
            ball.removeFromCanvas(canvas);
            resetForce();
            forceMeter.resetForceMeter();
            switchWorkingTank(); // comment out when animating
        }
    }


    public void setForce(KeyboardEvent k){
        if (k.getKey().equals(Key.valueOf("SPACE"))) {
            force += 2;
            forceMeter.setForce(force);
            forceMeter.updateForceMeter();
            System.out.println(force);
        }
    }

    public double getForce() {
        return force;
    }

    public double resetForce() {
        force = 10;
        return force;
    }


    public boolean hitsObject(Cannonball ball) {
        if (intersects(ball)==0){
            ball.removeFromCanvas(canvas);
            getWorkingTank().reduceHP();
            // System.out.println(getWorkingTank().getHP() + "\t" + getWorkingTank());
            if (checkLives()) {
                // System.out.println(tanks.get(0) + " Wins!");
                canvas.closeWindow();
            }
        }
        if (intersects(ball)==1){
            ball.removeFromCanvas(canvas);
        }
        return false;
    }



    public double tankAngleCalc(int move) {
        double a = 0;
        double b = 0;
        double c = 0;

        if (move == -5) {
            a = 5;
            b = findDistance(terrain.getTerrainMovePoint(workingTankPoint(), move));
            c = terrain.getTerrainMovePoint(workingTankPoint(), move).getY()-workingTankPoint().getY();
            if (c < 0) {
                c = Math.abs(terrain.getTerrainMovePoint(workingTankPoint(), move).getY()-workingTankPoint().getY());
                tankAngle= Math.toDegrees(Math.acos(Math.cos((a*a+b*b-c*c)/(2*a*b))));
                // System.out.println(tankAngle);
                return tankAngle;
            }
            if (c == 0) {
                // System.out.println(tankAngle);
                return 0;
            }
            c = Math.abs(c);
            tankAngle= Math.toDegrees(Math.acos(Math.cos((a*a+b*b-c*c)/(2*a*b))))+270;
            // System.out.println(tankAngle);
            return tankAngle;
        }
        else {
            a = 5;
            b = findDistance(terrain.getTerrainMovePoint(workingTankPoint(), move));
            c = terrain.getTerrainMovePoint(workingTankPoint(), move).getY()-workingTankPoint().getY();
            if (c < 0) {
                c = Math.abs(terrain.getTerrainMovePoint(workingTankPoint(), move).getY()-workingTankPoint().getY());
                tankAngle=360-Math.toDegrees(Math.acos(Math.cos((a*a+b*b-c*c)/(2*a*b))));
                // System.out.println(tankAngle);
                return tankAngle;
            }
            if (c == 0) {
                tankAngle=0;
                // System.out.println(tankAngle);
                return tankAngle;
            }
            c = Math.abs(c);
            tankAngle=Math.toDegrees(Math.acos(Math.cos((a*a+b*b-c*c)/(2*a*b))));
            // System.out.println(tankAngle);
            return tankAngle;
        }
    }

    public double findDistance(Point terrainPoint){
        return getWorkingTank().getPoint().distance(terrainPoint);
    }

    public Point workingTankPoint(){
        return getWorkingTank().getPoint();
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public Tank getWorkingTank() {
        for (Tank t : tanks) {
            if (t.isWorking()) {
                return t;
            }
        }
        return null;
    }

    public Tank notWorkingTank(){
        for (Tank t : tanks) {
            if (t.notWorking()) {
                return t;
            }
        }
        return null;
    }
    
    public void animateCannonFrame1()  {
            if(getWorkingTank()==redTank){
                redTank.getCannon().setImagePath("RedFireFrame1.png");
                redTank.getCannon().setMaxWidth(65);
                

            }
            if(getWorkingTank()==blueTank){
                blueTank.getCannon().setImagePath("BlueFireFrame1.png");
                blueTank.getCannon().setMaxWidth(65);
            }
    }

    public void animateCannonFrame2(){
            if(getWorkingTank()==redTank){
            blueTank.getCannon().setImagePath("RedFireFrame2.png");
            blueTank.getCannon().setMaxWidth(75);
            }
            if(getWorkingTank()==blueTank){
                redTank.getCannon().setImagePath("BlueFireFrame2.png");
                redTank.getCannon().setMaxWidth(75);
                }
    }

    public void animateCannonFrame3(){
            if(getWorkingTank()==redTank){
                redTank.getCannon().setImagePath("RedFireFrame3.png");
                redTank.getCannon().setMaxWidth(75);
            }
            if(getWorkingTank()==blueTank){
             blueTank.getCannon().setImagePath("BlueFireFrame3.png");
             blueTank.getCannon().setMaxWidth(75);
            }
    }
    public void resetToOriginCannon(){
            if(getWorkingTank()==redTank){
                redTank.getCannon().setImagePath("RedCannon.png");
                redTank.getCannon().setMaxWidth(50);
                }
        
            if(getWorkingTank()==blueTank){
            blueTank.getCannon().setImagePath("BlueCannon.png");
            blueTank.getCannon().setMaxWidth(50);
            }
    }

    public void switchWorkingTank(){
        for (Tank t : tanks) {
            t.switchWorking();
        }
    }

     /**
     * Checks if tank intersects with the top point. 
     */
    public int intersects(Cannonball ball) {
        if (canvas.getElementAt(ball.getCenterX(), ball.getCenterY()) instanceof Tank) {
            return 0;
        }
        if (canvas.getElementAt(ball.getCenterX(), ball.getCenterY()) instanceof Path) {
            return 1;
        }
        return 2;
    }

    public Cannon getWorkingCannon(){
        return getWorkingTank().getCannon();
    } 

    /**
     * Check how many lives a tank has, if 0 removes the tank.
     */
    public boolean checkLives() {
        for (Tank t : tanks) {
            if (t.getHP() == 0) {
                tanks.remove(t);
                return true;
            }
        }
        return false;
    }
}
