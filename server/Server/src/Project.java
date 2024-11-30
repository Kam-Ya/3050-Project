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
    private int ID;

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

    public void deleteFromDB() {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes the project from the database
            this.getID();
            String input = String.format("DELETE FROM project WHERE projectID = %d;", this.ID);
            state.executeUpdate(input);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return;
        }
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
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get task and project ID's from database
            String input = String.format("SELECT emp FROM projassign WHERE proj = %d;", this.ID);
            ResultSet rs = state.executeQuery(input);

            while(rs.next()) {
                this.employees.add(rs.getInt("emp"));
            }

            // close the connection
            rs.close();
            state.close();
            con.close();
            return this.employees;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return null;
        }
    }

    public ArrayList<Integer> addEmployee(Integer employee) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            ArrayList<Integer> possible = new ArrayList<Integer>();
            // get task and project ID's from database
            String input = String.format("SELECT employee FROM inORG WHERE org = %d;", employee);
            ResultSet rs = state.executeQuery(input);

            while(rs.next()) {
                possible.add(rs.getInt("emp"));
            }

            // close the connection
            rs.close();
            state.close();
            con.close();
            return possible;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return null;
        }
    }

    public void insertEmp(Integer hid, ArrayList<Integer>selected) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {

            // insert the ID's into the connection table
            String input = String.format("INSERT INTO projassign VALUES (%d, %d);", this.ID, hid);
            state.executeUpdate(input);

            // insert the rest of the employees into the list
            for (Integer i : selected) {
                input = String.format("INSERT INTO projassign VALUES (%d, %d);", this.ID, i);
                state.executeUpdate(input);
            }

            // close the connection
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
        }
    }

    public void addTask(String name, Date due, String desc, int prio) {
        Task t = new Task(name, due, desc, prio);
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

    public void addReport(String title, String details, int ID) {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT name FROM users WHERE employeeID = %d;", ID);
            ResultSet rs = state.executeQuery(input);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date today = new Date();

            ProgressReport rep = new ProgressReport(title, details, rs.getString("name"), today);

            // insert the report in to the database
            input = String.format("INSERT INTO report (content, date, user, proj, title) VALUES('%s', '%s', '%s', %d, '%s');",
                    rep.getReportDetails(), df.format(rep.getTimestamp()), rep.getUser(), this.ID, rep.getTitle());
            state.executeUpdate(input);

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch(SQLException sqle) {
            return;
        }
    }

    public void deleteFromDatabase() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes project from database
            String input = String.format("DELETE FROM project WHERE name = '%s' AND description = '%s';", this.projectName, this.Desc);
            state.executeUpdate(input);

            // close the connection
            state.close();
            con.close();
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void getID() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT projectID FROM project WHERE description = '%s';", this.Desc);
            ResultSet rs = state.executeQuery(input);

            this.ID = rs.getInt("projectID");

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch(SQLException sqle) {
            return;
        }
    }

    public ArrayList<Integer> getTaskList() {
        ArrayList<Integer> tasks = new ArrayList<Integer>();
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT task FROM forProject WHERE proj = %d;", this.ID);
            ResultSet rs = state.executeQuery(input);

            while(rs.next()) {
                tasks.add(rs.getInt("task"));
            }

            // close the connection
            rs.close();
            state.close();
            con.close();

            return tasks;
        } catch(SQLException sqle) {
            return null;
        }
    }

    public ArrayList<ProgressReport> listReports() {
        ArrayList<ProgressReport> reports = new ArrayList<ProgressReport>();
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT title, date, user FROM report WHERE proj = %d;", this.ID);
            ResultSet rs = state.executeQuery(input);

            for(int i = 0; rs.next(); i++) {
                reports.add(new ProgressReport(rs.getString("title"), "", rs.getString("user"), rs.getDate("date")));
            }

            // close the connection
            rs.close();
            state.close();
            con.close();

            return reports;
        } catch(SQLException sqle) {
            return null;
        }
    }
}
