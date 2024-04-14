package game;

import city.cs.engine.*;

/**
 * Provides a DynamicBody and Shape for ammunition's
 */
public class Ammunition extends DynamicBody {

    private static final Shape shape = new CircleShape(1);
    //Creates a circle shape for the ammunition object

    /**
     * Constructor Initialises object of type Ammunition
     * @param w GameWorld Class Where Body Exists
     */
    public Ammunition(GameWorld w) {
        //create DynamicBody
        super(w, shape);

        //Set the value of the bullet attribute on this body to detect all collisions between steps
        this.setBullet(true);
    }
}
