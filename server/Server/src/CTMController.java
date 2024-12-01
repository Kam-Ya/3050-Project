package Server.src;
import Server.src.format.msgFormat;

import java.util.ArrayList;
import Server.src.User;
import Server.src.*;

public class CTMController {



    //Sending
    public static void sendMSG(Object obj, String operation, ConnectionToClient client) {

            switch (operation) {
                case "userAuth":
                    break;
                case "projectList":
                    break;
                case "taskList":
                    break;
                case "operationConfirmation":
                    break;
                case "reportList":
                    break;
                case "viewReport":
                    break;
                case "Error":
                    String errorMsg=(String) obj;
                    obj="Error: ".concat(errorMsg);
                    break;
                case "Success":
                    String successMsg=(String) obj;
                    obj="Success: ".concat(successMsg);
                    break;
                default:
                    operation = "Unknown Error Please Contact Support";
                    break;
            }
            msgFormat message = new msgFormat(operation, obj, -1);
            try {
                client.sendToClient(message);
            } catch (java.io.IOException e) {
                System.out.println("Failed to send message:" + e);
            }
        }

        //Recieving


    public static void createUser (Object obj, ConnectionToClient client){
        User user=(User) obj;
        sendMSG("Account created","Success",client);

    }
    public static void deleteUser (Object obj, ConnectionToClient client){
        Integer userID=(Integer) obj;
        User user=new User(userID);
        user.deleteFromDatabase();
        sendMSG("Account deleted","Success",client);

    }

    public static void loginRequest (Object obj, ConnectionToClient client){
        Login login=(Login) obj;

        int user = login.authenticate();
        if (user != -1) {
            sendMSG(user,"userAuth",client);
        } else {
            sendMSG("Login Request Failed","Error",client);

        }

    }

    public static void createProject(Object obj, Integer userID, ConnectionToClient client){
    Project project=(Project) obj;
    project.addToDB();
    project.addManage(userID);
        sendMSG("Project created","Success",client);
    }
    public static void deleteProject(Object obj, ConnectionToClient client){
        Project project=(Project) obj;
        project.deleteFromDB();
        sendMSG("Project deleted","Success",client);
    }

    public static void getProjects(Object obj, Integer userID, ConnectionToClient client){
        User user = new User(userID);
        user.listProj();
        ArrayList <Project> projects = user.getProjs();
        sendMSG(projects, "projectList", client);


    }
    public static void getTasks(Object obj, Integer userID, ConnectionToClient client){
        

    }



    public static void assignEmpProj(Object obj, Integer projID, ConnectionToClient client){
        ArrayList <Integer> users = (ArrayList<Integer>) obj;
        Project.insertEmp(users,projID);

    }
    public static void createTask(Object obj, Integer taskID, ConnectionToClient client){
        Task task=(Task) obj;
        task.addToDB();
        sendMSG("Task created","Success",client);
    }
    public static void deleteTask(Object obj, ConnectionToClient client){
        Task task=(Task) obj;
        task.deleteFromDatabase();
        sendMSG("Task deleted","Success",client);

    }

    public static void assignEmpTask(Object obj, Integer userID,ConnectionToClient client){
        Task task=(Task) obj;
        task.addAsignee(userID);

    }
    public static void completeTask(Object obj, ConnectionToClient client){
        Task task=(Task) obj;
        task.toggleCompleted();
    }
    public static void commentTask(Object obj, Integer taskID, ConnectionToClient client){
        Comment comment=(Comment) obj;
        sendMSG("Comment created","Success",client);

    }
    public static void createReport(Object obj,Integer projectID, ConnectionToClient client){
        ProgressReport report = (ProgressReport) obj;
        report.addReport(projectID);
        sendMSG("Report created","Success",client);

    }
    public static void viewReport(Object obj, ConnectionToClient client){
        Integer reportID = (Integer) obj;
        ProgressReport report = new ProgressReport("","","",reportID);
        
        report.getInfo();
        


    }
    //public void setTaskPriority(Object obj, ConnectionToClient client){

    //


    //If we have time
    //public void updateTaskName(Object obj, ConnectionToClient client){}
    //public void updateTaskDate(Object obj, ConnectionToClient client){}
    //public void updateProjectName(Object obj, ConnectionToClient client){}
    //public void updateProjectDate(Object obj, ConnectionToClient client){}














    public static void messageString(Object obj, ConnectionToClient client){
        obj=(String)obj;
        System.out.println(obj);
    }

    


}
