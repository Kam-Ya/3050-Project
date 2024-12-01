package main.com.format;
import java.io.*;
//msgFormat implements serializable
//Provides a standard way to exchange objects between the client and the server
public class msgFormat implements Serializable{
    //This dictates what type of operation is needed based on standardized message strings
    public String type;

    //This is the object that will be exchanged
    public Object obj;

    //This object allows passing of generic ids
    public Integer genericID;

    public msgFormat(String type, Object obj, Integer genericID){
        this.type=type;
        this.obj=obj;
        this.genericID=genericID;
    }
}
