package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Guardian extends Enemy{
    private Vec2 platformPosition;
    private static final BodyImage enemySprite = new BodyImage("data/Loot/Seperate/tile379.png", 4f);
    public Guardian(GameWorld w, Vec2 platform, Character character) {
        super(w, platform, character);
        addImage(enemySprite);

        this.platformPosition = platform;
        this.setGravityScale(0F);
        this.getFixtureList().getFirst().destroy();
        Fixture fixture = new SolidFixture(this, getEnemies(), 1000);

        this.setPosition(new Vec2(platform.x, platform.y + 3));

        fly(this, 1);
    }

    @Override
    public Vec2 getPlatformPosition() {
        return platformPosition;
    }
}
