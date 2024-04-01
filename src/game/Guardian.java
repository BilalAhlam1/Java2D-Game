package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Guardian extends Enemy{
    private static final BodyImage enemySprite = new BodyImage("data/Loot/Seperate/tile379.png", 4f);
    public Guardian(GameWorld w, StaticBody platform, Character character) {
        super(w, platform, character);
        addImage(enemySprite);
        this.setGravityScale(0F);
        this.getFixtureList().getFirst().destroy();
        Fixture fixture = new SolidFixture(this, getEnemies(), 1000);

        this.setPosition(new Vec2(platform.getPosition().x, platform.getPosition().y + 3));
        fly(this, 1);
    }
}
