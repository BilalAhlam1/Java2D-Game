package game;

import city.cs.engine.*;

public class Ammunition extends DynamicBody {

    //Create a circle shape for the ammunition object
    private static final Shape shape = new CircleShape(1);

    public Ammunition(GameWorld w) {
        //create DynamicBody
        super(w, shape);

        //Set the value of the bullet attribute on this body to detect all collisions between steps
        this.setBullet(true);
    }
}
