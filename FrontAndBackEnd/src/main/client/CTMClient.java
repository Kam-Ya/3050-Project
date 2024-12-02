package main.client;
import main.objects.*;
import main.java.*;


public class CTMClient extends AbstractClient{
    public CTMClient(String h, int p) {
  
        super(h,p);
      }
      
      protected void connectionClosed() {
    
        System.out.println("Client: Closed");
        System.out.println("Client.isConnected()="+isConnected());
      }
      
      protected void connectionException(Exception exception) {
      
        System.out.println("Client exception: " + exception);
      }
      
      protected void connectionEstablished() {
    
        System.out.println("Client: Connected");
        System.out.println("Client.isConnected()="+isConnected());
      }
    
      protected void handleMessageFromServer(Object msg){
        msgFormat message= (msgFormat) msg;
        Object msgObj=message.obj;

        switch (message.type){
          case "userAuth":
          clientController.handleLogin(msgObj);
          case "projectList":
          clientController.projectInfo(msgObj);
          break;
          case "sendTaskInfo":
          clientController.taskInfo();
          break;
          case "operationConfirmation":
          break;
          case "sendReportList":
          clientController.reportList();
          break;
          case "sendReport":
          clientController.readReport();
          break;
          default:
            System.err.println("Unknown message type: " + message.type);
        }
      }

}
