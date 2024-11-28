package Server.src;

import java.io.Serializable;
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


}
