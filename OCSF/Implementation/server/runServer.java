public class runServer extends Thread {

    public static void main(String[] args) {
      
      System.out.println("*** Testing of exception throwing inside message handling ***\n");
  
      Thread test= new runServer();
      test.start();
    }
  
    CTMServer server;
    
    public runServer() {
      server= new CTMServer(12345);
    }

    public void run() {
  
        try {
    
         // Server listens    
         System.out.println("-- Server listens --");
         server.listen();
         Thread.sleep(1000);
         System.out.println("\n");
         
         // Server closes
         System.out.println("-- Server closes --");
         server.close();
         
        } catch (Exception ex) {
        
          System.out.println("Server exception: " + ex);
        }
      }
    }