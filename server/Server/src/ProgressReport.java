package Server.src;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgressReport implements Serializable {
    private String reportDetails;
    private String User;
    private Date timestamp;

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


}
