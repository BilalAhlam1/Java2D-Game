package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Arrows extends DynamicBody {
    private static final Shape Arrows =  new CircleShape(1);
    private static final BodyImage ArrowImage = new BodyImage("data/Loot/Seperate/tile132.png", 4f);
    private static final BodyImage newLevel = new BodyImage("data/Loot/Seperate/tile135.png", 4f);
    public Arrows(World w, StaticBody staticBody) {
        super(w, Arrows);
        addImage(ArrowImage);
        setPosition(new Vec2(staticBody.getPosition().x, staticBody.getPosition().y + 2));
        this.isBullet();
        this.getFixtureList().remove(0);
        GhostlyFixture ghostlyFixture = new GhostlyFixture(this, Arrows);
    }
}
