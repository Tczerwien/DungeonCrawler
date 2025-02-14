import java.awt.*;
import java.io.File;

public class Sprite implements Displayable{
    private Image image;
    private double x;
    private double y;
    double height;
    double width;

    public Sprite(Image image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Image getImage() {
        return image;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }



    @Override
    public void draw(Graphics g){
        g.drawImage(image, (int) this.x, (int) this.y, (int) this.width, (int) this.height, null);
    };
}
