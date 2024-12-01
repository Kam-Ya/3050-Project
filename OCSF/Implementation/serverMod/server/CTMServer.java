package server;
import com.format.msgFormat;
import objects.CTMController;


public class CTMServer extends AbstractServer {
    CTMServer(int p) {
    
        super(p);
      }
      
      protected void serverStarted() {
    
        System.out.println("Server: Started");
        System.out.println("Server.isListening()="+isListening());
        System.out.println("Server.isClosed()="+isClosed());
        System.out.println("Server.getNumberOfClients()="+getNumberOfClients());
      }
      
      protected void serverStopped() {
      
        System.out.println("Server: Stopped");
        System.out.println("Server.isListening()="+isListening());
        System.out.println("Server.isClosed()="+isClosed());
        System.out.println("Server.getNumberOfClients()="+getNumberOfClients());
      }
    
      protected void serverClosed() {
      
        System.out.println("Server: Closed");
        System.out.println("Server.isListening()="+isListening());
        System.out.println("Server.isClosed()="+isClosed());
        System.out.println("Server.getNumberOfClients()="+getNumberOfClients());
      }
      
      protected void clientConnected(ConnectionToClient client) {
      
        System.out.println("Server: client #" + getNumberOfClients() + " connected");
      }
    
      synchronized protected void clientDisconnected(
        ConnectionToClient client) {  
    
        System.out.println("Server: client disconnected");    
      }
    
      synchronized protected void clientException(
        ConnectionToClient client, Throwable exception) {
      
        System.out.println("Connection to client exception: " + exception);    
      }
      
      protected void listeningException(Throwable exception) {
      
        System.out.println("Listening exception: " + exception);    
      }
      
      protected void handleMessageFromClient(Object msg, ConnectionToClient client){
        //Creates a message object and casts msg to it for further processing
        msgFormat message= (msgFormat) msg;
        Object msgObj=message.obj;
        Integer genID=message.genericId;
        //Checks for what operation is required to send to the correct CTMController call
        switch(message.type){

        case "createUser":
          CTMController.createUser(msgObj,client);
        break;
        case "deleteUser":
          CTMController.deleteUser(msgObj,client);
        break;
        case "loginRequest":
          CTMController.loginRequest(msgObj,client);
        break;
        case "createProject":
          CTMController.createProject(msgObj,genID,client);
        break;
        case "deleteProject":
          CTMController.deleteProject(msgObj,client);
        break;
        case  "assignEmpProj":
          CTMController.assignEmpProj(msgObj,genID,client);
        break;
        case  "createTask":
          CTMController.createTask(msgObj,genID,client);
        break;
        case "deleteTask":
          CTMController.deleteTask(msgObj,client);
        break;
        case "assignEmpTask":
          CTMController.assignEmpTask(msgObj,genID,client);
        break;
        case "commentTask":
          CTMController.commentTask(msgObj,genID,client);
        break;
        case "createReport":
          CTMController.createReport(msgObj,genID,client);
        break;
        case "viewReport":
          CTMController.viewReport(msgObj,client);
        break;
        case "messageString":
        CTMController.messageString(message.obj,client);
        break;
        default:
        }
      }
    }
