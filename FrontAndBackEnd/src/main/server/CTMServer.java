package main.server;
import main.com.format.msgFormat;




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
        //Checks for what operation is required to send to the correct CTMController call
        switch(message.type){
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
        CTMController.messageString(message.obj,client);
        break;
        default:
        }
      }
    }
