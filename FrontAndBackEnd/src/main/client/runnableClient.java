package main.client;
public class runnableClient extends Thread {

    public static void main(String[] args) {
        
      Thread test= new runnableClient();
      test.start();
    }
    CTMClient client;
    
    public runnableClient() {
      client= new CTMClient("localhost",12345);
    }
  
    public void run() {
    
      try {
        (new ClientThread(client)).start();
        sleep(5000);
       
       
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
