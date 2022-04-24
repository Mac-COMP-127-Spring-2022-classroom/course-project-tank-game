package TankGameFiles;

import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class TankManager {
    private CanvasWindow canvas;
    private List<Tank> tanks;
    private List<Cannon> cannons;
    private Tank redTank;
    private Tank blueTank;
    private Terrain terrain;
    private Point redTankPoint, blueTankPoint, redCannonPoint, blueCannonPoint;

    public TankManager(CanvasWindow canvas, Terrain terrain) {
        tanks = new ArrayList<>();
        cannons = new ArrayList<>();
        this.canvas = canvas;
        this.terrain = terrain;
    }

    public void generateTanks() {
        redTankPoint = new Point(terrain.getTerrainPoint(5).getX(), terrain.getTerrainPoint(5).getY()-50);
        blueTankPoint =new Point(terrain.getTerrainPoint(110).getX(), terrain.getTerrainPoint(110).getY()-50);
        redCannonPoint = new Point(redTankPoint.getX()+40, redTankPoint.getY()+1);
        blueCannonPoint = new Point(blueTankPoint.getX()-10, blueTankPoint.getY()+1);
        for (int i = 0; i < 2; i++) {
            if (i < 1) {
                Tank redTank = new Tank(redTankPoint, "RedTank.png",  redCannonPoint.getX(), redCannonPoint.getY(),  "RedCannon.png");
                redTank.setMaxHeight(50);
                redTank.getCannon().setMaxWidth(50);
                canvas.add(redTank.getCannon());
                canvas.add(redTank);
                tanks.add(redTank);
                System.out.println(redTank.getHP());
            } 
            else {
                Tank blueTank = new Tank(blueTankPoint, "BlueTank.png", blueCannonPoint.getX() , blueCannonPoint.getY()  ,"BlueCannon.png");
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
                getWorkingTank().setPoint(terrain.getTerrainMovePoint(getWorkingTank().getPoint(), -5));
                // getWorkingTank().rotateBy(tankAngleCalc(-5));   
                getWorkingTank().setPosition(getWorkingTank().getPoint());
                centerCannonToTank();
                // getWorkingCannon().setPosition(getWorkingCannon().getPoint());   
            }
        }

        if(getWorkingTank().getCenterX() + getWorkingTank().getWidth()/2 < canvas.getWidth()){
            if (key.getKey().equals(Key.valueOf("RIGHT_ARROW"))){ 
                getWorkingTank().setPoint(terrain.getTerrainMovePoint(getWorkingTank().getPoint(), 5));
                // getWorkingTank().rotateBy(tankAngleCalc(5));  
                getWorkingTank().setPosition(getWorkingTank().getPoint());   
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
        // cannon.setRotation(cannon.getAngle());
    }

    public void fireCannon(KeyboardEvent key, double initialSpeed) {
        Cannonball ball = new Cannonball((25+(getWorkingCannon().getImageWidth()/2)) * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),   (25+(getWorkingTank().getImageWidth()/2)) * -Math.sin(Math.toRadians(getWorkingCannon().getAngle()))+getWorkingTank().getY() +7.5, initialSpeed, getWorkingCannon().getAngle(), canvas.getWidth(), canvas.getHeight());
        if (key.getKey().equals(Key.valueOf("SPACE"))) {
            ball.addToCanvas(canvas);
            while (ball.updatePosition(0.1)) {
                if (intersectsWithBottomPoint(ball)||intersectsWithLeftOrRightPoint(ball)||intersectsWithTopPoint(ball)){
                    notWorkingTank().reduceHP();
                    System.out.println(notWorkingTank().getHP() + "\t" + notWorkingTank());
                    if (checkLives()) {
                        System.out.println(tanks.get(0) + " Wins!");
                        canvas.closeWindow();
                    };
                    break;
                    
                }
                canvas.draw();
            }
            ball.removeFromCanvas(canvas);
            switchWorkingTank(); 
        }
    }



    // public boolean hitsObject(Cannonball ball) {
    //     if (intersectsWithBottomPoint(ball)||intersectsWithLeftOrRightPoint(ball)||intersectsWithTopPoint(ball)){
    //         ball.removeFromCanvas(canvas);
    //         getWorkingTank().reduceHP();
    //         System.out.println(getWorkingTank().getHP() + "\t" + getWorkingTank());
    //         if (checkLives()) {
    //             System.out.println(tanks.get(0) + " Wins!");
    //             canvas.closeWindow();
    //     }
    //     return false;
    // }

    /**
     * Centers cannon to tank.
     */
    private void centerCannonToTank(){
       getWorkingCannon().setCenter(25 * Math.cos(Math.toRadians(getWorkingCannon().getAngle())) + getWorkingTank().getCenterX(),   - 25 * Math.sin(Math.toRadians(getWorkingCannon().getAngle()))+getWorkingTank().getY() + 7.5);
    }


    public double tankAngleCalc(int move) {
        double a = 0;
        double b = 0;
        double c = 0;

        if (move == -5) {
            a = -5;
            b = getWorkingTank().getPoint().distance(terrain.getTerrainMovePoint(getWorkingTank().getPoint(), move));
            c = terrain.getTerrainMovePoint(getWorkingTank().getPoint(), move).getY()-getWorkingTank().getPoint().getY();
            if (c < 0) {
                c = getWorkingTank().getPoint().getY()-terrain.getTerrainMovePoint(getWorkingTank().getPoint(), move).getY();
            }
        }
        if (move == 5) {
            a = 5;
            b = getWorkingTank().getPoint().distance(terrain.getTerrainMovePoint(getWorkingTank().getPoint(), move));
            c = terrain.getTerrainMovePoint(getWorkingTank().getPoint(), move).getY()-getWorkingTank().getPoint().getY();
            if (c < 0) {
                c = getWorkingTank().getPoint().getY()-terrain.getTerrainMovePoint(getWorkingTank().getPoint(), move).getY();
            }
        }
        return Math.toDegrees(Math.acos(Math.cos((a*a+b*b-c*c)/(2*a*b))));
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
    

    public void switchWorkingTank(){
        for (Tank t : tanks) {
            t.switchWorking();
        }
    }

     /**
     * Checks if tank intersects with the top point. 
     */
    public boolean intersectsWithTopPoint(Cannonball ball) {
        if (canvas.getElementAt(ball.getTopPoint()) instanceof Tank) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if tank intersects with the Left or Right point. 
     */
    public boolean intersectsWithLeftOrRightPoint(Cannonball ball) {
        if (canvas.getElementAt(ball.getRightPoint()) instanceof Tank) {
            return true;
        }
        if (canvas.getElementAt(ball.getLeftPoint()) instanceof Tank) {
            return true;
        }
        return false;
    }

    /**
     * Checks if tank intersects with the bottom point of the ball. 
     */
    public boolean intersectsWithBottomPoint(Cannonball ball) {
        if (canvas.getElementAt(ball.getBottomPoint()) instanceof ) {
            return true;
        } else {
            return false;
        }
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
