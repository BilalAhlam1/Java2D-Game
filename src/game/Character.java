package game;
import city.cs.engine.*;
public class Character extends Walker {
    private static final Shape CharacterShape =  new BoxShape(1, 2);
    private int XPCount;
    private int health = 100;
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);
    public Character(World w) {
        super(w, CharacterShape);
        SolidFixture characterFixture = new SolidFixture(this, CharacterShape);
        addImage(image);
    }

    public void setXPCount(int XPCount) {
        this.XPCount = XPCount;
        System.out.println("XP:" + XPCount);
    }

    public void reduceHealth(){
        health = health - 10;
    }

    public int getHealth() {
        return health;
    }

    public int getXPCount() {
        return XPCount;
    }

}
