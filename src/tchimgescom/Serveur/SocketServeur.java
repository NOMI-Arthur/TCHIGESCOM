/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom.Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import tchimgescom.Paquet;
import tchimgescom.Res;

/**
 *
 * @author NOMI Ph.D
 */
public class SocketServeur {
    
    
    InputStream is;
    ThreadGroup TGSERV = new ThreadGroup("MVCGESCOM");
    private ServerSocket LeSocketServeur;
    private int port=6000;
    
    public SocketServeur(int Leport) {
        port = Leport;
    }
    
    
    
    public boolean SocketDemarreService(){
        try {
            LeSocketServeur = new ServerSocket(port);
            if (!LeSocketServeur.isClosed()){
                Thread TH;
                TH = new Thread (TGSERV, new MVCGESCOM_THREAD(), "SERV");
                TH.start();
                return true;
            }            
        } catch (IOException ex) {
            Res.MessageDlg(ex.getMessage());
        }
        return false;
   }
    public void start() throws IOException {
       SocketDemarreService();
    }
    
   class MVCGESCOM_THREAD implements Runnable{
        @Override
      public void run()  {
        while (true) {
            try {
                Socket SocketClient;
                
                SocketClient = LeSocketServeur.accept();
                
                Thread UnClient;
                String NomThreadCli=SocketClient.toString()+"UNCLI";
                UnClient = new Thread(TGSERV,new MVCGESCOM_THREADCLIENT(SocketClient),NomThreadCli);
                UnClient.start();
                
            } catch (IOException ex) {
                Res.MessageDlg(ex.getMessage());
            }
        }
       }
    }
    
 class MVCGESCOM_THREADCLIENT implements Runnable{
        Socket MSocketClient;
        
        public MVCGESCOM_THREADCLIENT(Socket SocketC){
            MSocketClient=SocketC;
        }
        @Override
      public void run()  {
            Paquet Pack_Sortie; 
            try {
                
                while (true) {
                    //Début de la déséréalisation
                    Paquet Pack_Entree=Res.NetLit(MSocketClient);
                    //Fin désérialisation
                                
                    if (Pack_Entree.getNomTraitement().equals("stop")){
                            Pack_Sortie=new Paquet("",true);
                            Res.NetEcrit(Pack_Sortie, MSocketClient);
                            break;
                    }
                    else if (Pack_Entree.getNomTraitement().equals("erreur")){               
                        break;
                    }
                    else{
                        try {     
                              //etape 1 : recuperation de la methode
                                Class maClass = Class.forName(Pack_Entree.getNomClasse());
                                Class mesParamsTypes[] = new Class[1];
                                mesParamsTypes[0]=Paquet.class;
                                Method maMethode;
                                maMethode = maClass.getMethod(Pack_Entree.getNomTraitement(), mesParamsTypes);

                                //etape 2 : appel
                                Object[] mesParams = new Object[1];
                                mesParams[0]=Pack_Entree;
                                Object InstanceObjet = maClass.newInstance();                             
                                Pack_Sortie=(Paquet)maMethode.invoke(InstanceObjet, mesParams);                             
                                Res.NetEcrit(Pack_Sortie, MSocketClient);
                        } catch (IOException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException ex) {
                            
                            Pack_Sortie=new Paquet(ex.getMessage(),false);
                            Res.NetEcrit(Pack_Sortie, MSocketClient); 
                        }                
                     }
                } 
                MSocketClient.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketServeur.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    } 
}
