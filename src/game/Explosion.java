package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Explosion extends DynamicBody {
    private static final Shape Bomb =  new CircleShape(1);
    private static final BodyImage explosionImage = new BodyImage("data/Loot/Seperate/tile373broken.png", 4f);
    public Explosion(World w, Ammunition ammunition) {
        super(w, Bomb);
        addImage(explosionImage);
        setPosition(new Vec2(ammunition.getPosition().x, ammunition.getPosition().y));
        this.isBullet();
    }
}
