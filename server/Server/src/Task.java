import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String taskName;
    private Date taskDueDate;
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

    public void addComment(Comment newComment) {
        this.comments.add(newComment);
    }
}
