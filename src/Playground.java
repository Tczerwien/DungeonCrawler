import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();

    public Playground(String pathName) {
        try {
            // Load images for different tiles
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png"));

            // Get dimensions for each image type
            final int treeWidth = imageTree.getWidth(null);
            final int treeHeight = imageTree.getHeight(null);
            final int grassWidth = imageGrass.getWidth(null);
            final int grassHeight = imageGrass.getHeight(null);
            final int rockWidth = imageRock.getWidth(null);
            final int rockHeight = imageRock.getHeight(null);

            // Read the level file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line = bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line != null) {
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) {
                    switch (element) {
                        case 'T' -> // Tree (SolidSprite)
                                environment.add(new SolidSprite(
                                        imageTree,
                                        columnNumber * treeWidth,
                                        lineNumber * treeHeight,
                                        treeWidth,
                                        treeHeight));
                        case ' ' -> // Grass (non-solid Sprite)
                                environment.add(new Sprite(
                                        imageGrass,
                                        columnNumber * grassWidth,
                                        lineNumber * grassHeight,
                                        grassWidth,
                                        grassHeight
                                ));
                        case 'R' -> // Rock (SolidSprite)
                                environment.add(new SolidSprite(
                                        imageRock,
                                        columnNumber * rockWidth,
                                        lineNumber * rockHeight,
                                        rockWidth,
                                        rockHeight
                                ));
                    }
                    columnNumber++;
                }
                columnNumber = 0;
                lineNumber++;
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSprites = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) {
                solidSprites.add(sprite);
            }
        }
        return solidSprites;
    }

    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayables = new ArrayList<>();
        for (Sprite sprite : environment) {
            displayables.add((Displayable) sprite);
        }
        return displayables;
    }
}