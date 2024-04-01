package game;

import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Zombie extends Enemy{

    private static final BodyImage enemySprite = new BodyImage("data/Loot/Seperate/tile387.png", 4f);
    public Zombie(GameWorld w, StaticBody platform, Character character) {
        super(w, platform, character);
        addImage(enemySprite);

        //sets the primary position of the enemy before walking
        setPosition(new Vec2(platform.getPosition().x, platform.getPosition().y + 2));

        // Move the character in alternating speeds
        int speed = 8;
        move(this, platform, speed);
        this.startWalking(speed);
    }
}
