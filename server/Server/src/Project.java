package Server.src;

import java.io.Serializable;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;

public class Project implements Serializable {
    private String projectName;
    public Date projectDueDate;
    public ArrayList<Integer> employees;
    public String Desc;
    public String manager;

    public Project(String name, Date dueDate, String desc) {
        this.Desc = desc;
        this.projectName = name;
        this.projectDueDate = dueDate;
    }

    public void addToDB() {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // adds the project to the database
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String input = String.format("INSERT INTO project (name, date, description) VALUES('%s', '%s', '%s');",
                    this.projectName, df.format(this.projectDueDate), this.Desc);
            state.executeUpdate(input);

            // close connection
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
        }
    }

    public int deleteFromDB() {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes the project from the database
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String input = String.format("DELETE FROM project WHERE description = '%s';", this.Desc);
            state.executeUpdate(input);
        } catch (SQLException sqle) {
            // TODO
        }
        return 1;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void updateProjectName(String Name) {
        this.projectName = Name;
    }

    public Date getProjectDueDate() {
        return this.projectDueDate;
    }

    public void updateProjectDueDate(Date newDate) {
        this.projectDueDate = newDate;
    }

    public ArrayList<Integer> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Integer employee) {
        this.employees.add(employee);
    }

    public void addTask(String name, Date due, String desc) {
        Task t = new Task(name, due, desc);
        t.addToDB();
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get task and project ID's from database
            String input = String.format("SELECT taskID from task WHERE description = '%s';", t.getDesc());
            ResultSet rs = state.executeQuery(input);

            input = String.format("SELECT projectID FROM project WHERE description = '%s';", this.Desc);
            ResultSet r = state.executeQuery(input);

            // insert the ID's into the connection table
            input = String.format("INSERT INTO forProject (proj, task) VALUES (%d, %d);", rs.getInt("taskID"), r.getInt("projectID"));
            state.executeUpdate(input);

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
        }
    }

    public void addReport() {
        ProgressReport report = new ProgressReport();
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT projectID FROM project WHERE description = '%s';", this.Desc);
            ResultSet rs = state.executeQuery(input);

            // insert the report in to the database
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            input = String.format("INSERT INTO report (content, date, user, proj) VALUES('%s', '%s', '%s', %d);",
                    report.getReportDetails(), df.format(report.getTimestamp()), report.getUser(), rs.getInt("projectID"));
            state.executeUpdate(input);

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch(SQLException sqle) {
            return;
        }
    }
}
