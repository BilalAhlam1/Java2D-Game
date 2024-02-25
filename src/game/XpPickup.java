package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class XpPickup implements CollisionListener {
    private Student student;
    public XpPickup(Student s){
        this.student = s;
    }
    @Override
    public void collide(CollisionEvent e) {
    }
}
