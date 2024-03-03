package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Quiver extends StaticBody{
    private static final Shape Arrows =  new CircleShape(1);
    private static final BodyImage ArrowImage = new BodyImage("data/Loot/Seperate/tile135.png", 4f);
    public Quiver(World w, StaticBody staticBody) {
        super(w, Arrows);
        addImage(ArrowImage);
        setPosition(new Vec2(staticBody.getPosition().x, staticBody.getPosition().y + 2));
    }
}
