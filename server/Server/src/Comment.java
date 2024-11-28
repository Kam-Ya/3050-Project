package Server.src;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Serializable {
    private String content;
    private Date dateMade;
    private String commentor;

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
}
