package main.client;


import java.util.Date;

import main.objects.*;


public class runnableClient extends Thread {

    public static void main(String[] args) {
        
      Thread test= new runnableClient();
      test.start();
    }
    CTMClient client;
    
    
    public runnableClient() {
      client= new CTMClient("172.20.10.5",12345);
      clientController.clientProcess=client;
    }
  
    public void run() {
    
      try {
        (new ClientThread(client)).start();
        sleep(5000);

        //Project proj=new Project("test",new Date(),"ddd",2);
        //clientController.sendMSG(proj,"createProject",1);
       
       
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
