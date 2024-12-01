package client;
import com.form.msgFormat;
import objects.CTMController;

public class clientController {
    public static void sendMSG(Object obj, Integer genID, String operation, CTMClient client){

        switch(operation){
            case "createUser":
                break;
            case "deleteUser":
                break;
            case "loginRequest":
                break;
            case "createProject":
                break;
            case "deleteProject":
                break;
            case "assignEmpProj":
                break;
            case  "createTask":
                break;
            case "deleteTask":
                break;
            case "assignEmpTask":
                break;
            case "commentTask":
                break;
            case "createReport":
                break;
            case "viewReport":
                break;
            case "messageString":
                break;
            default:
        }
        msgFormat message=new msgFormat(operation,obj,genID);
        try{
            client.sendToServer(message);
        } catch(java.io.IOException e){
            System.out.println("Failed to send message:" + e);
        }
        }


}
