package game;

import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

public class Zombie extends Enemy{

    private Vec2 platformPosition;

    private static final BodyImage enemySprite = new BodyImage("data/Loot/Seperate/tile387.png", 4f);
    public Zombie(GameWorld w, Vec2 platform, Character character) {
        super(w, platform, character);
        addImage(enemySprite);

        this.platformPosition = platform;

        //sets the primary position of the enemy before walking
        setPosition(new Vec2(platform.x, platform.y + 2));

        // Move the character in alternating speeds
        int speed = 8;
        move(this, platform, speed);
        this.startWalking(speed);
    }

    @Override
    public Vec2 getPlatformPosition() {
        return platformPosition;
    }
}
