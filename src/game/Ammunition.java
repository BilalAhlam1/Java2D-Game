package game;

import city.cs.engine.*;

public class Ammunition extends DynamicBody {
    private static final Shape Bomb = new CircleShape(1);


    public Ammunition(World w) {
        super(w, Bomb);
        this.isBullet();

    }

    public static Shape getBomb() {
        return Bomb;
    }
}
