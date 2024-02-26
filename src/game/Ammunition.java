package game;

import city.cs.engine.*;

public class Ammunition extends DynamicBody {
    private static final Shape Bomb = new CircleShape(1);
    private final GhostlyFixture BulletFixture;
    private static final BodyImage image = new BodyImage("data/Loot/Seperate/tile373.png", 4f);

    public Ammunition(World w) {
        super(w, Bomb);
        BulletFixture = new GhostlyFixture(this, Bomb);
        addImage(image);
    }
}
