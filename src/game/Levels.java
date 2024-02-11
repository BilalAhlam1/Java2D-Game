package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Levels {
    public float NewyPos = 0;
    public float PreyPos = 0;

    Walker studentWalker;

    World world;
    public Levels(float yPos, World world, Walker studentWalker){
        if (yPos > NewyPos) {
            this.PreyPos = NewyPos;
            this.NewyPos = yPos;
            this.world = world;
            this.studentWalker = studentWalker;
            MakeLevel();
        }
    }

    public void MakeLevel(){
        Shape platform = new BoxShape(30, 0.5f);
        StaticBody ground = new StaticBody(world, platform);
        ground.setPosition(new Vec2(studentWalker.getPosition()));

    }
}
