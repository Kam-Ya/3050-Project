package main.objects;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Serializable {
    private static final long serialVersionUID=20241130;

    private String content;
    private Date dateMade;
    private String commentor;
    private int ID;

    public Comment(String cont, Date made, String com) {
        this.content = cont;
        this.dateMade = made;
        this.commentor = com;
    }

    public void addComment() {

    }

    public void setContent(String newContent) {
        this.content = newContent;
    }

    public String getContent() {
        return this.content;
    }

    public Date getDateMade() {
        return this.dateMade;
    }

    public void setDateMade(Date dateMade) {
        this.dateMade = dateMade;
    }

    public String getCommentor() {
        return this.commentor;
    }

    public void setCommentor(String commentor) {
        this.commentor = commentor;
    }

    public void deleteFromDatabase() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes comment from database
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String input = String.format("DELETE FROM task WHERE content = '%s' AND date = '%s';", this.content, df.format(this.dateMade));
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
            // get the comment ID from the database
            String input = String.format("SELECT ID FROM comment WHERE content = '%s';", this.content);
            ResultSet rs = state.executeQuery(input);

            this.ID = rs.getInt("ID");

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch(SQLException sqle) {
            return;
        }
    }

    public void addComment(Integer ID) {
        Date made = new Date();
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {

            // insert the comment into the database
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String input = String.format("INSERT INTO comment (content, date, commentor, task) VALUES('%s', '%s', '%s', %d);",
                    this.getContent(), df.format(this.getDateMade()), this.getCommentor(), ID);
            state.executeUpdate(input);

            // close the connection
            state.close();
            con.close();
        } catch (SQLException sqle) {
            return;
        }
    }
}
