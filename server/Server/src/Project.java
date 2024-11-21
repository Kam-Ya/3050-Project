import java.util.ArrayList;
import java.util.Date;

public class Project {
    private String projectName;
    public Date projectDueDate;
    public ArrayList<Integer> employees;
    public String Desc;
    public String manager;

    public int addTask() {
        try(
                Connection con = DriverManager.getConnection("jdbc:oracle:thin@localhost", root, 123);
                Statement state = con.createStatement;
        ) {
            DateFormat df = new SimpleDateFormat(pattern);
            String input = String.format("INSERT INTO project VALUES(%s, %s, %s)", this.name, df.format(this.projectDueDate), this.Desc);
            state.executeUpdate(input);
        } catch(SQLException sqle) {
            // TODO
        }
        return 1;
    }

    public int deleteTask() {
        // TODO
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
}
