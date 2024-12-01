package main.client;
import main.com.format.msgFormat;
public class clientController {
  static CTMClient clientProcess;
    public static void sendMSG(Object obj, String operation, Integer ID){

        switch(operation){
            case "createOrg":
            break;
            case "createAccount":
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

            if (message.obj instanceof User) {
                User user = (User) message.obj;
                System.out.println("Login successful: " + user.getName());
  
                // Transition to the ListOfProjectsScreen
                Platform.runLater(() -> { // ensure UI updates are done on the JavaFX Application Thread
                  try {
                    CredentialsController credentialsController = Main.getCredentialsController();
                    credentialsController.openListOfProjectsScreen(user);
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                });
              }
        }

        public static void projectInfo(){}
        public static void taskInfo(){}
        public static void reportList(){}
        public static void readReport(){}


        }


