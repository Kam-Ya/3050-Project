package main.objects;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Task implements Serializable {
    private static final long serialVersionUID=20241130;
    private String taskName;
    private Date taskDueDate;
    private String desc;
    private int priority;
    private boolean completed;
    private ArrayList<Integer> asignees;
    private int ID;
    private ArrayList<Comment> comments;

    public Task(String name, Date due, String Description, Integer priority, Integer ID) {
        this.taskName = name;
        this.taskDueDate = due;
        this.desc = Description;
        this.priority = priority;
        this.ID = ID;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String newName) {
        this.taskName = newName;
    }

    public Date getTaskDueDate() {
        return this.taskDueDate;
    }

    public void setTaskDueDate(Date newDate) {
        this.taskDueDate = newDate;
    }

    public boolean checkCompleted() {
        return this.completed;
    }

    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int newPriority) {
        this.priority = newPriority;
    }

    public ArrayList<Integer> getAsignees() {
        return this.asignees;
    }

    public void addAsignee(Integer ID) {
        this.asignees.add(ID);
    }

    public ArrayList<Comment> getComments() {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT content, date, commentor FROM comment WHERE task = %d;", this.ID);
            ResultSet rs = state.executeQuery(input);

            for (int i = 0; rs.next(); i++) {
                this.comments.add(new Comment(rs.getString("content"), rs.getDate("date"), rs.getString("commentor")));
            }

            // close the connection
            rs.close();
            state.close();
            con.close();

            return this.comments;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return null;
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void addToDB() {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // insert the task into the project
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String input = String.format("INSERT INTO task (name, priority, description, due) VALUES('%s', %d, '%s', '%s');",
                    this.taskName, this.priority, this.desc, df.format(this.taskDueDate));
            state.executeUpdate(input);

            // close connection
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
        }
    }

    public void deleteFromDatabase() {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes task from database
            this.getID();
            String input = String.format("DELETE FROM task WHERE taskID = %d;", this.ID);
            state.executeUpdate(input);

            // close the connection
            state.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void getID() {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the task ID from the database
            String input = String.format("SELECT taskID FROM task WHERE description = '%s' AND name = '%s';", this.desc, this.taskName);
            ResultSet rs = state.executeQuery(input);

            this.ID = rs.getInt("taskID");

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
        }
    }

    public void addTask(Integer ID) {
        this.addToDB();
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            this.getID();

            // insert the ID's into the connection table
            String input = String.format("INSERT INTO forProject (proj, task) VALUES (%d, %d);", this.ID, ID);
            state.executeUpdate(input);

            // close the connection
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
        }
    }
}
