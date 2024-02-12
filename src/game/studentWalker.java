package game;

import city.cs.engine.*;

public class studentWalker extends Walker {
    private static Walker studentWalker;
    private static final BodyImage image = new BodyImage("data/student.png", 4f);
    //public studentWalker(World world) {
        Student studentShape = new Student(getWorld());

    public studentWalker(World world) {
        super(world);
    }
    //super(world, studentShape);
        //studentWalker = new Walker(world,studentShape);
        //studentWalker.addImage(image);
    //}

    public static Walker getStudentWalker() {
        return studentWalker;
    }
}
