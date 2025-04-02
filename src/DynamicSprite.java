import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private boolean isWalking = true;
    private double speed;
    private final int spriteSheetNumberOfColumn = 10;
    private int timeBetweenFrame = 200;
    private Direction direction;

    //Private constructor for builder
    DynamicSprite(DynamicSpriteBuilder builder) {
        super(builder.image, (int) builder.x, (int) builder.y, (int) builder.width, (int) builder.height);
        this.direction = builder.direction;
        this.speed = builder.speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        //current animation frame index
        long currentTime = System.currentTimeMillis();
        int frameIndex = (int) ((currentTime / timeBetweenFrame) % spriteSheetNumberOfColumn);

        // Get the row
        int row = direction.getFrameLineNumber();

        // Coordinates from spritesheet
        int frameWidth = this.getImage().getWidth(null) / spriteSheetNumberOfColumn;
        int frameHeight = this.getImage().getHeight(null) / 4;

        int srcX = frameIndex * frameWidth;
        int srcY = row * frameHeight;

        // Destination coordinates from Sprite
        int destX = (int) this.getX();
        int destY = (int) this.getY();
        int destWidth = (int) width;
        int destHeight = (int) height;

        // Draw
        g.drawImage(
                this.getImage(),
                destX, destY, destX + destWidth, destY + destHeight, // Destination rectangle
                srcX, srcY, srcX + frameWidth, srcY + frameHeight,    // Source rectangle
                null
        );
    }

    private void move(){
        switch(direction){
            case NORTH -> this.setY(getY() - speed);
            case SOUTH -> this.setY(getY() + speed);
            case EAST -> this.setX(getX() + speed);
            case WEST -> this.setX(getX() - speed);
        }
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment){
        double newX = this.getX();
        double newY = this.getY();

        switch(direction){
            case NORTH -> newY -= speed;
            case SOUTH -> newY += speed;
            case EAST -> newX += speed;
            case WEST -> newX -= speed;
        }

        Rectangle2D.Double newHitbox = new Rectangle2D.Double(newX, newY, width, height);

        for(Sprite s : environment){
            if(s instanceof SolidSprite && s != this){
                Rectangle2D.Double otherHitbox = new Rectangle2D.Double(s.getX(), s.getY(), s.getWidth(), s.getHeight());

                if(newHitbox.intersects(otherHitbox)){
                    return false;
                }
            }
        }
        return true;
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if(isMovingPossible(environment)){
            move();
        }
    }


}