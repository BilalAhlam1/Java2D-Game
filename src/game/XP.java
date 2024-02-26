package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class XP extends DynamicBody {
    private static final Shape XP =  new BoxShape(1, 2);
    private int XPCount;
    private static final BodyImage image = new BodyImage("data/Loot/Seperate/tile444.png", 4f);
    public XP(World w, StaticBody staticBody) {
        super(w, XP);
        addImage(image);
        setPosition(new Vec2(staticBody.getPosition().x, staticBody.getPosition().y + 2));
    }

    public static Shape getXp() {
        return XP;
    }
}
