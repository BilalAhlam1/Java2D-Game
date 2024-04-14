package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Initialise Power up
 */
public class PowerUps extends StaticBody {
    private static final Shape shape =  new CircleShape(2);
    //Arrow Shape
    private final Vec2 staticBody;
    //Spawn Position

    /**
     * Constructor initialises Power Up
     * <p>Creates Object and Position</p>
     * @param w World body exists in
     * @param staticBody Position of the body
     */
    public PowerUps(World w, Vec2 staticBody) {
        //Creates Dynamic Body
        super(w, shape);
        this.staticBody = staticBody;

        //set the position of the body to the given platform
        setPosition(new Vec2(staticBody.x, staticBody.y + 2));
    }

    /**
     * Getter for the position of the object
     * @return Position Vec2
     */
    public Vec2 getStaticBody() {
        return staticBody;
    }
}
