import java.net.*;
import java.io.*;
import com.lloseng.ocsf.server.*;
import com.lloseng.ocsf.client.*;
import java.util.HashMap;
 


public class CTMController {



    //Sending
    public void sendMSG(Object obj, String operation, ConnectionToClient client){

        switch(operation){
            case "sendUserInfo":
            break;
            case "sendProjectInfo":
            break;
            case "sendTaskInfo":
            break;
            case "operationConfirmation":
            break;
            case "sendReportList":
            break;
            case "sendReport":
            break;
            default:
            operation="Error";
            break;
        }
        msgFormat message=new msgFormat(operation,obj);
            try{
                client.sendToClient(message);
            } catch(java.io.IOException e){
                System.out.println("Failed to send message:" + e);
            }
        }

    //Recieving
    public void createOrg(Object obj, ConnectionToClient client){}
    public void createAccount(Object obj, ConnectionToClient client){}
    public void loginRequest(Object obj, ConnectionToClient client){}
    public void createProject(Object obj, ConnectionToClient client){}
    public void createTask(Object obj, ConnectionToClient client){}
    public void assignTask(Object obj, ConnectionToClient client){}
    public void commentTask(Object obj, ConnectionToClient client){}
    public void assignPriority(Object obj, ConnectionToClient client){}
    public void deleteObject(Object obj, ConnectionToClient client){}
    public void writeReport(Object obj, ConnectionToClient client){}
    public void viewReports(Object obj, ConnectionToClient client){}
    public void readReport(Object obj, ConnectionToClient client){}
    public void getCalendar(Object obj, ConnectionToClient client){}
    public void getTasks(Object obj, ConnectionToClient client){}
    public void getProjects(Object obj, ConnectionToClient client){}
    public void completeTask(Object obj, ConnectionToClient client){}
    public void completeProject(Object obj, ConnectionToClient client){}

    


}
