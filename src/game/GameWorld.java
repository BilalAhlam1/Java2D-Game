package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;

public abstract class GameWorld extends World {

    //Load platform image
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    private final Character Character;
    public GameWorld(){
        super(60);

        //sets the arrow and health label
        JLabel Arrows = new JLabel("Arrows = 0");
        JLabel Health = new JLabel("Health = 100");
        JLabel score = new JLabel("Score = 0");

        //Creates Character
        Character = new Character(this, Arrows, Health, score);

    }

    public Character getCharacter() {
        return Character;
    }
}
