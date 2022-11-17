/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom.Client;

/**
 *
 * @author NOMI Ph.D
 */
import java.io.IOException;
import tchimgescom.Res;
public class SRC { ///SRC=Service Reseau Client
    private int Port=6000;
    private String Host_Serveur="localhost";
    private boolean Ok;
    private String Message;
    SC LeClient;
    public SRC()
    {
        DermarrerService();
    }
    
    public SRC(String Host_Serveur, int port)
    {
        this.Host_Serveur= Host_Serveur;
        this.Port= port;
        DermarrerService();
    }
    public String getMessage(){
        return Message;
    }

    public boolean getOk(){
        return Ok;
    }
    
    private void DermarrerService()
    {
        try 
        {
            LeClient = new SC(Host_Serveur, Port);
            if (LeClient.connect()) 
            {
                Ok=true;
                Res.affiche("Client connecté au serveur");
            } 
            else 
            {
                Ok=false;
                Res.affiche("Client non connecté au serveur");
            }
        } catch (IOException ex) {
            Ok=false;
            Message=ex.getMessage();
        }
    }
    
}
