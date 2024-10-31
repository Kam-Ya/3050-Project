package server;
import java.io.*;
//msgFormat implements serializable
//Provides a standard way to exchange objects between the client and the server
public class msgFormat implements Serializable{
    //This dictates what type of operation is needed based on standardized message strings
    public String type;

    //This is the object that will be exchanged
    public Object obj;

    public msgFormat(String type, Object obj){
        this.type=type;
        this.obj=obj;
    }
}
