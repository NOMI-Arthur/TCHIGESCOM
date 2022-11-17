/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import tchimgescom.Paquet;
import tchimgescom.Res;

/**
 *
 * @author NOMI Ph.D
 */
public class SC {//Socket Client
    private static String ServerHostName;
    private static int port;
    private static Socket LeSocketClient; //liaison avec client
    private static String NomMachine;
    private static String IPMachine;
    public  static boolean connecter=false;

    
    public static String getServerHostName(){return ServerHostName;}
    public static int getport(){return port;}
    public static Socket getLeSocketClient(){return LeSocketClient;}
    public static String getNomMachine(){return NomMachine;}
    public static String getIPMachine(){return IPMachine;}
    public boolean getconnecter(){return connecter;}
    
    public SC(String hostname, int port){
        SC.ServerHostName = hostname;
        SC.port = port;
        InetAddress iphost;
        try {
            iphost = InetAddress.getLocalHost();
            NomMachine=iphost.getHostName();
            IPMachine=iphost.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(SC.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    public static boolean connect() throws UnknownHostException, IOException{     
        LeSocketClient = new Socket(ServerHostName,port);
        connecter=LeSocketClient.isConnected();
        return connecter;
    }
    

    
    public static Paquet executerTraitement(Object[] parametre,String LaClasse,String nomTraitement) {
        Paquet Paq_Entree=new Paquet();
        Paq_Entree.setNomTraitement(nomTraitement);
        Paq_Entree.setNomClasse(LaClasse);
        Paq_Entree.setparametre(parametre);
        Paq_Entree.setAdresseIpMachine(IPMachine);
        Paq_Entree.setNomMachine(NomMachine);
        try {
            if (Res.NetEcrit(Paq_Entree, LeSocketClient)){
                Paq_Entree=Res.NetLit(LeSocketClient);
                if (! Paq_Entree.getOk()){
                    Res.MessageDlg(Paq_Entree.getMessage());
                }
            } 
        } catch (IOException ex) {
            Paq_Entree=new Paquet(ex.getMessage(), false);
        }
        return Paq_Entree;
    }

   
    

    /**
     *
     * @param StrSQl
     * @param Titres
     * @param TableDonnee
     * @param parametre
     * @param LaClasse
     * @param nomTraitement
     * @return
     * @throws IOException
     */
    public static Paquet executerRafraichir(JTable TableDonnee,String[] Titres,Object[] parametre,String LaClasse,String nomTraitement){
       Paquet Paq_Entree=executerTraitement(parametre,LaClasse,nomTraitement);
       if (Paq_Entree.getOk()){
           Res.ChargerTableModel(Paq_Entree.getTDonnees(), Titres, TableDonnee);
        }
       return Paq_Entree;
    }
    
    public static Paquet executerRafraichir(JComboBox TableDonnee,Object[] parametre,String LaClasse,String nomTraitement, int numcol)
    {
       Paquet Paq_Entree=executerTraitement(parametre,LaClasse,nomTraitement);
       if (Paq_Entree.getOk()){
           Res.ChargerComboBox(Paq_Entree.getTDonnees(), TableDonnee, numcol);
        }
       return Paq_Entree;
    }

    
}
