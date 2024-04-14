package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Initialise Quiver of type static body
 */
public class Quiver extends StaticBody{
    private static final Shape Arrows =  new CircleShape(1);
    //Shape
    private static final BodyImage ArrowImage = new BodyImage("data/Loot/Seperate/tile135.png", 4f);
    //Arrow Image

    /**
     * Constructor Initialise Quiver To The World And Position
     * @param w World Body Exists
     * @param staticBody Position of the object
     */
    public Quiver(World w, Vec2 staticBody) {
        //Makes a static body 'quiver' at the position of the platform
        super(w, Arrows);
        addImage(ArrowImage);
        setPosition(new Vec2(staticBody.x, staticBody.y + 2));
    }
}
