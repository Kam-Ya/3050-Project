package main.objects;
import java.io.*;
//msgFormat implements serializable
//Provides a standard way to exchange objects between the client and the server
public class msgFormat implements Serializable{
    private static final long serialVersionUID=20241130;
    //This dictates what type of operation is needed based on standardized message strings
    public String type;

    //This is the object that will be exchanged
    public Object obj;

    //This passes the userID for any necessary permissions checking
    public Integer genericId;

    public msgFormat(String type, Object obj, Integer genericId){
        

        this.type=type;
        this.obj=obj;
        this.genericId=genericId;
    }
}
