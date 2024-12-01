package main.client;
import javafx.application.Platform;
import main.com.format.msgFormat;
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
        switch (message.type){
          case "sendUserInfo":
//            // Handle login success
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
            System.err.println("Unknown message type: " + message.type);
        }
      }

}
