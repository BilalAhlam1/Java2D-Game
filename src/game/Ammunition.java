package game;

import city.cs.engine.*;

public class Ammunition extends DynamicBody {
    private static final Shape Bomb = new CircleShape(1);

    public Ammunition(World w) {
        super(w, Bomb);
        SolidFixture solidFixture = new SolidFixture(this, Bomb);
        solidFixture.setFriction(1);
    }

    public static Shape getBomb() {
        return Bomb;
    }
}
