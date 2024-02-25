package game;

import city.cs.engine.*;

public class XP extends DynamicBody {
    private static final Shape XP =  new BoxShape(1, 2);
    private int XPCount;
    private static final BodyImage image = new BodyImage("data/Loot/Seperate/tile370.png", 4f);
    public XP(World w) {
        super(w, XP);
        addImage(image);
    }

    public static Shape getXp() {
        return XP;
    }
}
