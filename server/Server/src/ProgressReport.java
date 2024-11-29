package Server.src;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgressReport implements Serializable {
    private String title;
    private String reportDetails;
    private String User;
    private Date timestamp;
    private int ID;

    public ProgressReport(String title, String content, String user, Date made) {
        this.title = title;
        this.reportDetails = content;
        this.User = user;
        this.timestamp = made;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getReportDetails() {
        return this.reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public String getUser() {
        return this.User;
    }

    public void setUser(String user) {
        this.User = user;
    }

    public String getTitle() {
        return title;
    }

    public void deleteFromDatabase() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes comment from database
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String input = String.format("DELETE FROM task WHERE content = '%s' AND date = '%s';", this.reportDetails, df.format(this.timestamp));
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
            // get the report ID from the database
            String input = String.format("SELECT ID FROM report WHERE content = '%s';", this.reportDetails);
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
}