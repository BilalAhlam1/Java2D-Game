package game;
import city.cs.engine.*;
import game.Student;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    private static final BodyImage Cloud = new BodyImage("data/Cloud Platform.png", 7f);
    public GameWorld(){
        super(60);

        //make a ground platform
        Shape shape = new BoxShape(30, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // make a suspended platform
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8, -4f));
        platform1.addImage(Cloud);

        //Shape platformShape2 = new BoxShape(3, 0.5f);
        //StaticBody platform2 = new StaticBody(this, platformShape);
        //platform2.setPosition(new Vec2(0, 4f));

        //make a character (with an overlaid image)
        //Student studentShape = new Student();
        //Student studentWalker = new Student(this);


        //Football
        Shape Circle = new CircleShape(1);
        DynamicBody CircleBody = new DynamicBody(this, Circle);
        CircleBody.setPosition(new Vec2(0,0));
        CircleBody.addImage(new BodyImage("data/football.png", 4));
        SolidFixture CircleFixture = new SolidFixture(CircleBody, Circle);
        CircleFixture.setDensity(2);
    }
}
