package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;


/**
 * Initialises Guardian Of Type Enemy
 */
public class Guardian extends Enemy{
    private final Vec2 platformPosition;
    //Platform position
    private static final BodyImage enemySprite = new BodyImage("data/Loot/Seperate/tile379.png", 4f);
    //Enemy Image

    /**
     * Constructor initialises Guardian
     * <p>Sets Gravity Scale to 0, Sets density and Calls fly method</p>
     * @param w World Of Which Object Exists
     * @param platform Platform position
     * @param character Character Class
     */
    public Guardian(GameWorld w, Vec2 platform, Character character) {
        super(w, character);
        addImage(enemySprite);

        this.platformPosition = platform;

        //set gravity and density
        this.setGravityScale(0F);
        this.getFixtureList().getFirst().destroy();
        Fixture fixture = new SolidFixture(this, getEnemies(), 1000);

        //set position
        this.setPosition(new Vec2(platform.x, platform.y + 3));

        //enemy chases character
        fly(this, 1);
    }

    /**
     * Getter method to return platform/enemy position
     * @return Platform/Enemy position
     */
    @Override
    public Vec2 getPlatformPosition() {
        return platformPosition;
    }
}
