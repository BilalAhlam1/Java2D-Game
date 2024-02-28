package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Character extends Walker {
    private static final Shape CharacterShape =  new BoxShape(1, 2);
    private int XPCount;
    private int health = 100;
    private GameWorld w;
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);
    public Character(GameWorld w) {
        super(w, CharacterShape);
        this.w = w;
        SolidFixture characterFixture = new SolidFixture(this, CharacterShape);
        addImage(image);
    }

    public void setXPCount(int XPCount) {
        this.XPCount = XPCount;
        System.out.println("XP:" + XPCount);
    }

    public void reduceHealth(){
        health = health - 5;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getXPCount() {
        return XPCount;
    }

    public void reset(){
        this.XPCount = 0;
        this.health = 100;
        this.setPosition(new Vec2(0, -11));
        w.getHealth().setText("Health = 100");
    }

}
