package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Portal extends StaticBody{
    //Arrow shape
    private static final Shape Portal =  new CircleShape(2);

    //ArrowImage
    private static final BodyImage PortalImage = new BodyImage("data/Loot/Seperate/tile259.png", 4f);

    public Portal(World w, StaticBody staticBody) {
        //Creates Dynamic Body
        super(w, Portal);

        //Create a ghostlyFixture for the portal
        this.getFixtureList().removeFirst().destroy();
        GhostlyFixture ghostlyFixture = new GhostlyFixture(this, Portal);

        //adds image to the dynamic body
        addImage(PortalImage);

        //set the position of the body to the given platform
        setPosition(new Vec2(staticBody.getPosition().x, staticBody.getPosition().y + 2));
    }
}
