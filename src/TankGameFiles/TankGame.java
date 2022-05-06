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
    private Image redTankImage;
    private Image redTankCannonImage;
    private Image blueTankImage;
    private Image blueTankCannonImage;
    private Image tankLogo;

    public TankGame() {
        canvas = new CanvasWindow("Tanks!", CANVAS_WIDTH, CANVAS_HEIGHT);
        Button gameButton = new Button("Start Game!");
        gameButton.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        // System.out.println(redTankCannonImage.getWidth());
        // System.out.println(redTankCannonImage.getHeight());
        openingScreen();
        terrain = new Terrain(canvas, CANVAS_WIDTH, 400);
        gameButton.onClick(() -> {
            canvas.removeAll();
            canvas.setBackground(Color.WHITE);
            createBackground();
            tankManager = new TankManager(canvas, terrain);
            terrain.generateTerrain();
            terrain.terrainListDebug();
            canvas.draw();
            tankControls(tankManager.getWorkingTank(), tankManager.getWorkingCannon());
            cannonControls(tankManager.getWorkingTank(), tankManager.getWorkingCannon());
        });
        canvas.add(gameButton);   
    }
    /**
     * Creates background for the game.
     */
    private void createBackground() {
        Image sky = new Image(0, -300, "background.jpg");
        sky.setMaxHeight(CANVAS_HEIGHT);
        sky.setMaxWidth(CANVAS_WIDTH);
        sky.setScale(3);
        canvas.add(sky);

        // Rectangle ground = new Rectangle(0, canvas.getHeight() - WINDOW_PADDING, canvas.getWidth(), WINDOW_PADDING);
        // ground.setFilled(true);
        // ground.setFillColor(GROUND_COLOR);
        // ground.setStroked(false);
        // canvas.add(ground);
    }
    public void tankControls(Tank tank, Cannon cannon) {
        canvas.onKeyDown(event -> tankManager.moveTank(event));
    }

    public void cannonControls(Tank tank, Cannon cannon) {
        canvas.onKeyDown(event -> tankManager.setCannonAngle(event));
        canvas.onKeyDown(event -> tankManager.setForce(event));
        canvas.onKeyUp(event -> tankManager.fireCannon(event));
    }

    /**
     * Creates the Opening Screen.
     */
    public void openingScreen(){
        Image redTankImage = new Image("RedTank.png");
        Image redTankCannonImage = new Image("RedCannon.png");
        Image blueTankImage = new Image("BlueTank.png");
        Image blueTankCannonImage = new Image("BlueCannon.png");
        Image tankLogo= new Image("tankLogo.png");
        createBackground();
        redTankImage.setCenter(CANVAS_WIDTH/6, CANVAS_HEIGHT/2);
        redTankCannonImage.setX(redTankImage.getX() + 80);
        redTankCannonImage.setY(redTankImage.getY() +  7);

        blueTankImage.setCenter(CANVAS_WIDTH * 0.9, CANVAS_HEIGHT/2);
        blueTankCannonImage.setX(blueTankImage.getX());
        blueTankCannonImage.setY(blueTankImage.getY());

        tankLogo.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2 - 50);
        canvas.add(blueTankCannonImage);
        canvas.add(blueTankImage);
        canvas.add(redTankCannonImage);
        canvas.add(redTankImage);
        canvas.add(tankLogo);
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
