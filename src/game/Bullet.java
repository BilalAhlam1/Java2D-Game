package game;

import city.cs.engine.*;
import city.cs.engine.Shape;

import java.awt.*;

public class Bullet extends DynamicBody {
    private static final Shape Bullet =  new CircleShape(1);
    //private final GhostlyFixture BulletFixture;
    private static final BodyImage image = new BodyImage("data/Loot/Seperate/tile373.png", 4f);

    public Bullet(World w) {
        super(w, Bullet);
        //BulletFixture = new GhostlyFixture(this, Bullet);
        addImage(image);
    }

    public static Shape getBullet() {
        return Bullet;
    }
}
