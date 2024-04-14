package game;

import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

/**
 * Initialise Zombie of type Enemy
 */
public class Zombie extends Enemy{
    private final Vec2 platformPosition;
    //Platform Position
    private static final BodyImage enemySprite = new BodyImage("data/Loot/Seperate/tile387.png", 4f);
    //Enemy image

    /**
     * Constructor Initialise Zombie
     * <p>Sets body image, position, speed, and call type of movement</p>
     */
    public Zombie(GameWorld w, Vec2 platform, Character character) {
        super(w, character);
        addImage(enemySprite);

        this.platformPosition = platform;

        //sets the primary position of the enemy before walking
        setPosition(new Vec2(platform.x, platform.y + 2));

        // Move the character in alternating speeds
        int speed = 8;
        move(this, platform, speed);

        //start walking
        this.startWalking(speed);
    }

    /**
     * Getter to return enemy position
     * @return Default enemy position
     */
    @Override
    public Vec2 getPlatformPosition() {
        return platformPosition;
    }
}
