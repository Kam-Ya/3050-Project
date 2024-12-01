package main.client;
import main.com.format.msgFormat;


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
        switch (message.type){
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
        }
      }

}
