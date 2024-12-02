package main.objects;

import com.mysql.cj.log.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class testDriver {
    public static void main(String[] args) throws SQLException {


        System.out.println("Testing adding a project to the database: ");
        User u = new User("tester", new Manager(), "leap", "leap");
        u.createUser();

        Login log = new Login("leap", "leap");

        if (log.authenticate() == -1) {
            System.out.println("bad login");
        }

        Login real = new Login("leap", "leap");
        int id = real.authenticate();
        if (id != -1) {
            System.out.println("Valid login");
        }
        User use = new User(id);

        Date due = new Date();
        Project proj = new Project("test", due, "test project", 1);
        Project proj2 = new Project("test2", due, "2nd test project", 2);
        proj.addToDB(use.getUserID());
        proj2.addToDB(use.getUserID());

        use.listProj();
        ArrayList<Project> projs = new ArrayList<Project>(use.getProjs());

        System.out.println("Showing projects associated with user 'tester'");

        for (Project i: projs) {
            System.out.println(i.getProjectName());
        }


        System.out.println("Testing deleting a project");

        proj2.deleteFromDB();

        use.listProj();
        projs = use.getProjs();

        System.out.println("Showing projects associated with user 'tester'");

        for (Project i: projs) {
            System.out.println(i.getProjectName());
        }

        id = proj.showID();

        ProgressReport rep = new ProgressReport("report-tester", "Test information", "tester", 1);
        rep.addReport(id);

        proj.listReports();
        ArrayList<ProgressReport> reps = new ArrayList<ProgressReport>(proj.getReports());

        for (ProgressReport i : reps) {
            System.out.println(i.getTitle());
        }


   }
}
