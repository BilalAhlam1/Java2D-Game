package game;

import city.cs.engine.*;

public class Ammunition extends DynamicBody {
    private static final Shape shape = new CircleShape(1);


    public Ammunition(World w) {
        super(w, shape);
        this.isBullet();
    }
}
