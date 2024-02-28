package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class XP extends StaticBody {
    private static final Shape XP =  new CircleShape(1);
    private int XPCount;
    private static final BodyImage xp = new BodyImage("data/Loot/Seperate/tile444.png", 4f);
    private static final BodyImage newLevelXP = new BodyImage("data/Loot/Seperate/tile447.png", 4f);
    public XP(World w, StaticBody staticBody, boolean isNewLevel) {
        super(w, XP);
        if (isNewLevel){
            addImage(newLevelXP);
        } else {
            addImage(xp);
        }
        setPosition(new Vec2(staticBody.getPosition().x, staticBody.getPosition().y + 2));
    }

    public static Shape getXp() {
        return XP;
    }
}
