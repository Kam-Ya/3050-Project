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
    public ArrayList<Task> tasks;
    public ArrayList<ProgressReport> reports;
    public String Desc;
    public String manager;
    private int ID;

    public Project(String name, Date dueDate, String desc, Integer ID) {
        this.Desc = desc;
        this.projectName = name;
        this.projectDueDate = dueDate;
        this.ID = ID;
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

    public void insertEmp(ArrayList<Integer>selected, Integer ID) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // insert the rest of the employees into the list
            for (Integer i : selected) {
                String input = String.format("INSERT INTO projassign VALUES (%d, %d);", ID, i);
                state.executeUpdate(input);
            }

            // close the connection
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
        }
    }

    public void addManage(Integer id) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // insert the rest of the employees into the list
            String input = String.format("INSERT INTO projassign VALUES (%d, %d);", this.ID, id);
            state.executeUpdate(input);

            // close the connection
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
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

    public void listtasks() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT taskID, name, priority, description, due FROM task WHERE projectID = " +
                    "(SELECT task FROM forProject WHERE proj = %d;)", this.ID);
            ResultSet rs = state.executeQuery(input);

            while(rs.next()) {
                this.tasks.add(new Task(rs.getString("name"), rs.getDate("due"), rs.getString("description"), rs.getInt("priority"), rs.getInt("taskID")));
            }

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch(SQLException sqle) {
            return;
        }
    }

    public ArrayList<ProgressReport> listReports() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT ID, title, date, user FROM report WHERE proj = %d;", this.ID);
            ResultSet rs = state.executeQuery(input);

            for(int i = 0; rs.next(); i++) {
                reports.add(new ProgressReport(rs.getString("title"), "", rs.getString("user"), rs.getInt("ID")));
            }

            // close the connection
            rs.close();
            state.close();
            con.close();

            return reports;
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
            return null;
        }
    }

    public void removeEmp(int ID) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes the project from the database
            this.getID();
            String input = String.format("DELETE FROM projassign WHERE emp = %d;", ID);
            state.executeUpdate(input);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return;
        }
    }

    public ArrayList<Task> getProjs() {
        return this.tasks;
    }
}
