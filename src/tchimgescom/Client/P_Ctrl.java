/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom.Client;

/**
 *
 * @author NOMI Ph.D
 */
import tchimgescom.Res;
public class P_Ctrl {
    private final String MachineServeur="localhost";
    private final int port=6000;
    
    public P_Ctrl()
    {
        SRC src=new SRC(MachineServeur, port);
        if (!src.getOk())
        {
            Res.arret(src.getMessage());
        }
        else
        {
            P_Vue MVCP = new P_Vue(this);
            MVCP.setVisible(true);  
        }
    }
    
}
