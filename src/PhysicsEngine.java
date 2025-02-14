import java.util.ArrayList;

public class PhysicsEngine implements Engine {
    private ArrayList<Sprite> environment = new ArrayList<>();
    private ArrayList<DynamicSprite> movingSpriteList = new ArrayList<>();

    public void addToMovingSpriteList(DynamicSprite sprite) {
        movingSpriteList.add(sprite);
    }
    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    public PhysicsEngine(){

    }

    public void addToEnvironmentList(){

    }


    @Override
    public void update() {
        for (DynamicSprite sprite : movingSpriteList) {
            sprite.moveIfPossible(environment);
        }
    }
}
