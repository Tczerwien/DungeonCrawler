import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;

    public RenderEngine(){
        renderList = new ArrayList<>();
    }

    public void setRenderEngine(ArrayList<Displayable> renderList){
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable){
        renderList.add(displayable);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (Displayable element : renderList){
            element.draw(g);
        }
    }

    @Override
    public void update(){
        repaint();
    }

}
