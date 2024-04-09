package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class PowerUps extends StaticBody {
    //Arrow shape
    private static final Shape shape =  new CircleShape(2);
    private final Vec2 staticBody;
    private final Character character;

    //private static final BodyImage PortalImage = new BodyImage("data/Loot/Seperate/tile281.png", 4f);

    public PowerUps(World w, Vec2 staticBody, Character character) {
        //Creates Dynamic Body
        super(w, shape);

        this.staticBody = staticBody;
        this.character = character;

        //adds image to the dynamic body
        //addImage(PortalImage);

        //set the position of the body to the given platform
        setPosition(new Vec2(staticBody.x, staticBody.y + 2));
    }

    public Vec2 getStaticBody() {
        return staticBody;
    }

    public Character getCharacter() {
        return character;
    }
}
