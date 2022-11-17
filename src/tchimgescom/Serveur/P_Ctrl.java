/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom.Serveur;

import javax.swing.JComboBox;
import javax.swing.JTable;
import tchimgescom.Paquet;
import tchimgescom.Res;

/**
 *
 * @author NOMI Ph.D
 */
public class P_Ctrl {
        private final String MachineHote="localhost";
    private final String Port="3306";
    private final String BD="bdgestioncommerciale";
    private final String User="root";
    private final String passw="";
    private final int port=6000;
    public P_Ctrl(){
        P_Modele.Connexion(MachineHote, Port, BD, User, passw);
        if (! P_Modele.getPackBD().getOk()){
            Res.arret(P_Modele.getPackBD().getMessage());
        }
        else
        {
            ServiceReseauServeur SR=new ServiceReseauServeur(port);
            if (!SR.getOk())
            {
                Res.arret(SR.getMessage());
            }
            else
            {
                P_Vue MVCP = new P_Vue(this);
                MVCP.setVisible(true);  
            }
            
        }
    }
 
}
