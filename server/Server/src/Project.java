import java.util.ArrayList;
import java.util.Date;

public class Project {
    private String projectName;
    public Date projectDueDate;
    public ArrayList<Integer> employees;
    public String manager;

    public int addTask() {
        // TODO
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
