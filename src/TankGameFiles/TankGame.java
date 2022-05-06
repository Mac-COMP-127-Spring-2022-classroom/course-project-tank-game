package TankGameFiles;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

public class TankGame {
    private final int CANVAS_HEIGHT = 600;
    private final int CANVAS_WIDTH = 1200;
    private TankManager tankManager;
    private CanvasWindow canvas;
    private Cannonball cannonBall; 
    private Terrain terrain;

    public TankGame() {
        canvas = new CanvasWindow("Tanks!", CANVAS_WIDTH, CANVAS_HEIGHT);
        Button gameButton = new Button("Start Game!");
        gameButton.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        GraphicsText text = new GraphicsText("Tanks!");
        text.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2 - 50);
        Image redTankImage = new Image("RedTank.png");
        Image redTankCannonImage = new Image("RedCannon.png");
        Image blueTankImage = new Image("BlueTank.png");
        Image blueTankCannonImage = new Image("BlueCannon.png");
        // System.out.println(redTankCannonImage.getWidth());
        // System.out.println(redTankCannonImage.getHeight());
        redTankImage.setCenter(CANVAS_WIDTH/3, CANVAS_HEIGHT/3);
        redTankCannonImage.setX(redTankImage.getX() + 80);
        redTankCannonImage.setY(redTankImage.getY() +  7);

        blueTankImage.setCenter(CANVAS_WIDTH * 0.9, CANVAS_HEIGHT/2);
        blueTankCannonImage.setX(blueTankImage.getX());
        blueTankCannonImage.setY(blueTankImage.getY());

        canvas.add(blueTankCannonImage);
        canvas.add(blueTankImage);
        canvas.add(redTankCannonImage);
        canvas.add(redTankImage);
        canvas.add(text);
        terrain = new Terrain(canvas, CANVAS_WIDTH, 400);
        canvas.setBackground(Color.RED);
        gameButton.onClick(() -> {
            canvas.removeAll();
            canvas.setBackground(Color.WHITE);
            tankManager = new TankManager(canvas, terrain);
            terrain.generateTerrain();
            terrain.terrainListDebug();
            canvas.draw();
            tankControls(tankManager.getWorkingTank(), tankManager.getWorkingCannon());
            cannonControls(tankManager.getWorkingTank(), tankManager.getWorkingCannon());
        });
        canvas.add(gameButton);   
    }

    public void tankControls(Tank tank, Cannon cannon) {
        canvas.onKeyDown(event -> tankManager.moveTank(event));
    }
 
    public void cannonControls(Tank tank, Cannon cannon) {
        canvas.onKeyDown(event -> tankManager.setCannonAngle(event));
        canvas.onKeyDown(event -> tankManager.setForce(event));
        canvas.onKeyUp(event -> tankManager.fireCannon(event));
        
    }

    public void checkLives() {
        System.out.println("Red Wins!");
        System.out.println("Blue Wins!");
    }

    
    // We will bring back the run method later. We just don't need it at this time.
    public static void run() {

    }

    public static void main(String[] args) {
        new TankGame();
    }
}
