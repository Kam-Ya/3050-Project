package Server.src;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String taskName;
    private Date taskDueDate;
    private String desc;
    private int priority;
    private boolean completed;
    private ArrayList<Integer> asignees;
    private ArrayList<Comment> comments;

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
        return this.comments;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void addComment(Comment newComment) {
        Comment com = new Comment();
        this.comments.add(com);
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the taskID from the database
            String input = String.format("SELECT taskID from task WHERE description = '%s';", this.desc);
            ResultSet rs = state.executeQuery(input);

            // insert the comment into the database
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            input = String.format("INSERT INTO comment (content, date, commentor, task) VALUES('%s', '%s', '%s', %d);",
                    com.getContent(), df.format(com.getDateMade()), com.getCommentor(), rs.getInt("taskID"));
            state.executeUpdate(input);

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch(SQLException sqle) {
            return;
        }
    }

    public void addToDB() {
        try(
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
        } catch(SQLException sqle) {
            return;
        }
    }
}
