package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Initialises Explosion To Type Dynamic Body
 */
public class Explosion extends DynamicBody {
    private static final Shape explosion =  new CircleShape(1);
    //Explosion Shape
    private static final BodyImage explosionImage = new BodyImage("data/Loot/Seperate/tile373broken.png", 4f);
    //Explosion Image

    /**
     * Constructor Initialises Explosion
     * <p>Sets The Body For The Explosion</p>
     * @param w World Class Body Exists In
     * @param ammunition Ammunition Position To Set Explosion
     */
    public Explosion(World w, Ammunition ammunition) {
        //makes an explosion object for the bomb and sets the position to the position of the bomb at its last point
        super(w, explosion);
        addImage(explosionImage);
        setPosition(new Vec2(ammunition.getPosition().x, ammunition.getPosition().y));
        this.isBullet();
    }
}
