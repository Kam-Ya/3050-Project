package main.client;
import java.util.ArrayList;

import javafx.application.Platform;
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

            long startTime = System.currentTimeMillis();
            while (userID <= 0 && System.currentTimeMillis() - startTime < 10000) { // 10-second timeout
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (userID <= 0) {
                Platform.runLater(() -> System.out.println("Server timed out"));
                return;
            }
            Main.userID=(Integer) obj;
            System.out.println(Main.userID);

        }

        public static void projectInfo(Object obj){
//        if(obj.getClass().getName()=="java.lang.Integer"){}
//        else {
//
//
//            ArrayList <Project> projects=(ArrayList <Project>)obj;
//            for (Project project : projects) {
//                System.out.println("Project Name: " + project.getProjectName());
//            }
//            while (projects.isEmpty()){
//                System.out.println("No projects found");
//            }
//
//            ListOfProjectsController.listofProjects=projects;
//
//        }
            if (obj instanceof Integer) {
                // Handle Integer case (likely a status or ID)
                System.out.println("Received Integer: " + obj);
                return;
            }

            if (obj instanceof ArrayList) {
                ArrayList<Project> projects = (ArrayList<Project>) obj;

                // Wait for projects to be populated
                synchronized (projects) {
                    while (projects.isEmpty()) {
                        try {
                            System.out.println("Waiting for projects to be populated...");
                            projects.wait(); // Wait for projects to be populated
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }

                // Print project names to verify
                for (Project project : projects) {
                    System.out.println("Project Name2: " + project.getProjectName());
                }

                // Pass the projects to the controller
                ListOfProjectsController.setListOfProjects(projects);

            } else {
                System.out.println("Unexpected object type: " + obj.getClass().getName());
            }

        }
        public static void taskInfo(){

        }
        public static void reportList(){}
        public static void readReport(){}


        }


