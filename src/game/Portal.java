package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Initialise Portal Of Type Static Body
 */
public class Portal extends StaticBody{
    private static final Shape Portal =  new CircleShape(2);
    //Arrow Shape
    private static final BodyImage PortalImage = new BodyImage("data/Loot/Seperate/tile259.png", 4f);
    //Arrow Image

    /**
     * Constructor For Portal Object
     * @param w World where body exists
     * @param staticBody Position for body to spawn
     */
    public Portal(World w, Vec2 staticBody) {
        //Creates Dynamic Body
        super(w, Portal);

        //adds image to the dynamic body
        addImage(PortalImage);

        //set the position of the body to the given platform
        setPosition(new Vec2(staticBody.x, staticBody.y + 2));
    }
}
