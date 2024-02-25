package game;
import city.cs.engine.*;
public class Student extends Walker {
    private static final Shape studentShape =  new BoxShape(1, 2);
    private int XPCount;
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idle-01.png", 4f);
    public Student(World w) {
        super(w, studentShape);
        addImage(image);
    }

    public static Shape getStudentShape() {
        return studentShape;
    }

    public void setXPCount(int XPCount) {
        this.XPCount = XPCount;
    }

    public int getXPCount() {
        return XPCount;
    }
}
