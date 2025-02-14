import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Main {
    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicsEngine physicsEngine;

    public Main() throws Exception{
       displayZoneFrame = new JFrame("Java Labs");
       displayZoneFrame.setSize(400, 600);
       displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 200, 300, 48, 50, Direction.SOUTH);

       renderEngine = new RenderEngine();
       gameEngine = new GameEngine(hero);
       physicsEngine = new PhysicsEngine();

       Playground playground = new Playground("./img/level1.txt");

       renderEngine.setRenderEngine(playground.getSpriteList());
       renderEngine.addToRenderList(hero);

       physicsEngine.setEnvironment(playground.getSolidSpriteList());
       physicsEngine.addToMovingSpriteList(hero);

       displayZoneFrame.addKeyListener(gameEngine);
       displayZoneFrame.setVisible(true);

       Timer renderTimer = new Timer(50, (time)->renderEngine.update());
       Timer gameTimer = new Timer(50, (time)->gameEngine.update());
       Timer physicsTimer = new Timer(50, (time)->physicsEngine.update());
       renderTimer.start();
       gameTimer.start();
       physicsTimer.start();

       displayZoneFrame.getContentPane().add(renderEngine);
       displayZoneFrame.setVisible(true);

    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}