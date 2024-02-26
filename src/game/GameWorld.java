package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;

public class GameWorld extends World {
    private static final BodyImage Cloud = new BodyImage("data/Cloud Platform.png", 7f);
    public Character studentWalker = null;
    public GameWorld(){
        super(60);

        //make a ground platform
        Shape shape = new BoxShape(3, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));
        ground.addImage(Cloud);

        // make a suspended platform
        Shape platformShape = new BoxShape(6, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8, -4f));
        platform1.addImage(Cloud);

        //Creates Character
        studentWalker = new Character(this);
        studentWalker.setPosition(new Vec2(0, -11));

        //sets the score
        JLabel score = new JLabel("Score = 0");

        //Displays score with collisions
        XPpickup Collisions = new XPpickup(studentWalker, score);
        studentWalker.addCollisionListener(Collisions);

    }

    public Character getStudentWalker() {
        return studentWalker;
    }
}
