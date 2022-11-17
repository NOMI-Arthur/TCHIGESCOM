/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom.Serveur;

/**
 *
 * @author NOMI Ph.D
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tchimgescom.Res;

public class ServiceReseauServeur {
        private int port=6000;
    private boolean Ok;
    private String Message;
    
    public ServiceReseauServeur()
    {
        DermarrerService();
    }
    
    public ServiceReseauServeur(int port)
    {
        this.port= port;
        DermarrerService();
    }
    public String getMessage(){
        return Message;
    }

    public boolean getOk(){
        return Ok;
    }
    
    private void DermarrerService(){
    try {
            SocketServeur socketServer = new SocketServeur(port);
            socketServer.start();
            Res.affiche("Service réseau démarré");
            Ok=true;
        } catch (IOException ex) {
            Ok=false;
            Message=ex.getMessage();
            Logger.getLogger(ServiceReseauServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
