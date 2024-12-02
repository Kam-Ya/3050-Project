package main.client;
import java.util.ArrayList;
import main.java.*;
import main.objects.*;
import main.java.CredentialsController;

public class clientController {
    CredentialsController credentials;
  static CTMClient clientProcess;
    public static void sendMSG(Object obj, String operation, Integer ID){

        switch(operation){
            case "createOrg":
            break;
            case "createUser":
            break;
            case "loginRequest":
            break;
            case "createProject":
            break;
            case "createTask":
            break;
            case  "assignTask":
            break;
            case  "commentTask":
            break;
            case "assignPriority":
            break;
            case "deleteObject":
            break;
            case "writeReport":
            break;
            case "viewReports":
            break;
            case "readReport":
            break;
            case "getCalendar":
            break;
            case "getTasks":
            break;
            case "getProjects":
            break;
            case "completeTask":
            break;
            case "completeProject":
            break;
            case "messageString":
            break;
            case "Error":
            break;
        }
        msgFormat message=new msgFormat(operation,obj,ID);
        try{
            clientProcess.sendToServer(message);
        } catch(java.io.IOException e){
            System.out.println("Failed to send message:" + e);
        }
        }

        public static void handleLogin(Object obj){
            Integer userID=(Integer) obj;
            
            Main.userID=(Integer) obj;

            
        }

        public static void projectInfo(Object obj){
            ArrayList <Project> projects=(ArrayList <Project>)obj;
            ListOfProjectsController.listofProjects=projects;

        }
        public static void taskInfo(){

        }
        public static void reportList(){}
        public static void readReport(){}


        }


