package game;
import city.cs.engine.*;
public class Character extends Walker {
    private static final Shape CharacterShape =  new BoxShape(1, 2);
    private int XPCount;
    private final GhostlyFixture CharacterFixture;
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);
    public Character(World w) {
        super(w, CharacterShape);
        CharacterFixture = new GhostlyFixture(this, getCharacterShape());
        addImage(image);
    }

    public static Shape getCharacterShape() {
        return CharacterShape;
    }

    public void setXPCount(int XPCount) {
        this.XPCount = XPCount;
        System.out.println("XP:" + XPCount);
    }

    public int getXPCount() {
        return XPCount;
    }

    public GhostlyFixture getCharacterFixture() {
        return CharacterFixture;
    }
}
