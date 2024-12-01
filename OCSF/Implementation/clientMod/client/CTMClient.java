package client;
import com.form.msgFormat;


public class CTMClient extends AbstractClient{
    CTMClient(String h, int p) {
  
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
        Object msgObj = (Object) message.obj;
        switch (message.type) {
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
            break;
          case "Success":
            break;
          default:
            break;
      }

}
