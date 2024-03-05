package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.*;

public class GameWorld extends World {

    //Load platform image
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    public Character Character = null;
    public GameWorld(){
        super(60);

        //make a starting platform
        Shape shape = new BoxShape(6, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, 0));
        ground.addImage(Cloud);

        //sets the arrow and health label
        JLabel score = new JLabel("Arrows = 0");
        JLabel Health = new JLabel("Health = 100");

        //Creates Character
        Character = new Character(this, score, Health);
        Character.setPosition(new Vec2(0, 3));

        //Displays score with collisions
        characterCollisions Collisions = new characterCollisions(Character, this);
        Character.addCollisionListener(Collisions);

        Quiver quiver = new Quiver(Character.getWorld(), ground);

    }

    public Character getCharacter() {
        return Character;
    }
}
