package Server.src;

import java.util.Date;

public class testDriver {
    public static void main(String[] args) {


        System.out.println("Testing adding a project to the database: ");
        Date due = new Date();
        Project proj = new Project("test", due, "test project", 1);
        proj.addToDB();

        Task t = new Task("testTask", due, "testing task", 3, 1);
        t.addToDB();
        t.addTask(1);

        proj.listtasks();
        System.out.println(proj.getTasks());


   }
}
