package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Chapter2 extends GameWorld{
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    public Chapter2(Game game){
        super();

        getCharacter().setPosition(new Vec2(0, 3));

        //Displays score with collisions
        CharacterCollisions Collisions = new CharacterCollisions(getCharacter(), game);
        getCharacter().addCollisionListener(Collisions);

        //make a starting platform
        Shape shape1 = new BoxShape(6, 0.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(0f, 0));
        platform1.addImage(Cloud);

    }
}
