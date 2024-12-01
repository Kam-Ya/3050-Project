package client;
public class runnableClient extends Thread {

    public static void main(String[] args) {
      
      System.out.println("*** Testing of exception throwing inside message handling ***\n");
  
      Thread test= new runnableClient();
      test.start();
    }
    CTMClient client;
    
    public runnableClient() {
      client= new CTMClient("10.100.148.220",12345);
    }
  
    public void run() {
    
      try {
        (new ClientThread(client)).start();
        sleep(5000);
        clientController.sendMSG("Test Message It Works!","messageString",client);
       
       
      } catch (Exception ex) {
      
        System.out.println("Server exception: " + ex);
      }
    }
}

class ClientThread extends Thread {

  CTMClient client;
  
  public ClientThread(CTMClient client) {
  
    this.client= client;  
  }

  public void run() {

    try {
      client.openConnection();  
    } catch (Exception ex) {
    
      System.out.println("Server exception: " + ex);
    }
  }
}  
