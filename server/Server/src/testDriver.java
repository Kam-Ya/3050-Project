package Server.src;

import java.util.Date;

public class testDriver {
    public static void main(String[] args) {


        Date d = new Date();
        System.out.println("Testing adding a project to the database: ");
        Project pro = new Project("Test", d, "test Description");
        pro.addToDB();
    }
}
