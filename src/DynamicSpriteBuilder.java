import java.awt.*;

public class DynamicSpriteBuilder {
    Image image;
    double x;
    double y;
    double width;
    double height;
    Direction direction;
    double speed = 5.0;
    int timeBetweenFrame = 200;

    public DynamicSpriteBuilder(Image image) {
        this.image = image;
    }

    public DynamicSpriteBuilder setSpeed(double speed) {
        this.speed = speed;
        return this;
    }
    public DynamicSpriteBuilder setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }
    public DynamicSpriteBuilder setHeight(double height) {
        this.height = height;
        return this;
    }
    public DynamicSpriteBuilder setWidth(double width) {
        this.width = width;
        return this;
    }
    public DynamicSpriteBuilder setY(double y) {
        this.y = y;
        return this;
    }
    public DynamicSpriteBuilder setX(double x) {
        this.x = x;
        return this;
    }

    public DynamicSprite build(){
        return new DynamicSprite(this);
    }
}
