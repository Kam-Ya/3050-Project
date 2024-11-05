package client;
public class runnableClient extends Thread {

    public static void main(String[] args) {
      
      System.out.println("*** Testing of exception throwing inside message handling ***\n");
  
      Thread test= new runnableClient();
      test.start();
    }
    CTMClient client;
    
    public runnableClient() {
      client= new CTMClient("10.100.25.190",12345);  
      clientController.sendMSG("Test Message It Works!","messageString",client);

    }
  
    public void run() {
    
      try {
  
       
       
      } catch (Exception ex) {
      
        System.out.println("Server exception: " + ex);
      }
    }
}