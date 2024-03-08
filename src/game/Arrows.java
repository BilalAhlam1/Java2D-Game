package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Arrows extends DynamicBody {

    //Arrow shape
    private static final Shape Arrows =  new CircleShape(1);

    //ArrowImage
    private static final BodyImage ArrowImage = new BodyImage("data/Loot/Seperate/tile132.png", 4f);

    public Arrows(World w, StaticBody staticBody) {
        //Creates Dynamic Body
        super(w, Arrows);

        //adds image to the dynamic body
        addImage(ArrowImage);

        //set the position of the body to the given platform
        setPosition(new Vec2(staticBody.getPosition().x, staticBody.getPosition().y + 2));
    }
}
