package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;

public class GameWorld extends World {

    //Load platform image
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    private final Character Character;
    public GameWorld(){
        super(60);

        //make a starting platform
        Shape shape = new BoxShape(6, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, 0));
        ground.addImage(Cloud);

        //sets the arrow and health label
        JLabel Arrows = new JLabel("Arrows = 0");
        JLabel Health = new JLabel("Health = 100");
        JLabel lives = new JLabel("lives = 3");
        JLabel score = new JLabel("Score = 0");

        //Creates Character
        Character = new Character(this, Arrows, Health, lives, score);
        Character.setPosition(new Vec2(0, 3));

        //Displays score with collisions
        CharacterCollisions Collisions = new CharacterCollisions(Character, this);
        Character.addCollisionListener(Collisions);

        //Spawn arrows at spawn
        Quiver quiver = new Quiver(Character.getWorld(), ground);

    }

    public Character getCharacter() {
        return Character;
    }
}
