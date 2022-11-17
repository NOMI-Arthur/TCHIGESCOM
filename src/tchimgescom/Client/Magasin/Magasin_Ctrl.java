/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom.Client.Magasin;

import javax.swing.JDesktopPane;
import javax.swing.JTable;
import tchimgescom.Client.SC;
import tchimgescom.Paquet;
import tchimgescom.Res;

/**
 *
 * @author NOMI Ph.D
 */
public class Magasin_Ctrl extends Res{
    
    private final Magasin_Vue View;
    private final String LaClasse = tchimgescom.Serveur.Produit.Categorie.class.getName();
    
    
    public Magasin_Ctrl(){
         View=new Magasin_Vue(this);
    }
    
    public void Call_View(JDesktopPane desktopPane){
        Call_View(View, desktopPane);
    }
        
    private boolean test(Object Nom)
    {   
        if (((String)Nom).equals("")){
            MessageDlg("Indiquer le code de la Magasin des produits");
            return false;
        }
        return true;
    }
    
    public Paquet ajouter(Object[] Parametre){
        if (test(Parametre[0])) {
            
            Paquet result=SC.executerTraitement(Parametre,LaClasse, Res.getAjouter());
            if (!result.getOk()){
                MessageDlg(result.getMessage());
            }
            return result;
        }
        return new Paquet("",false);
    }
    
    public Paquet modifier(Object[] Parametre){
       if (test(Parametre[0])) {
            Paquet result=SC.executerTraitement(Parametre,LaClasse, Res.getModifier());
            if (!result.getOk()){
                MessageDlg(result.getMessage());
            }
            return result;
        }
        return new Paquet("",false);
    }
    
    public Paquet supprimer(Object[] Parametre){
        if (MessageDlgQ("Voulez-vous supprimer ce pays ?")==0)
        {
            Paquet result=SC.executerTraitement(Parametre,LaClasse, Res.getSupprimer());
            if (!result.getOk()){
                MessageDlg(result.getMessage());
            }
            return result;
        }
        return new Paquet("",false);
    }
    
    public Paquet rafraichir(String[] Titres, JTable TableDonnee){
        String[] Parametre=null;
        Paquet result=SC.executerTraitement(Parametre,LaClasse, Res.getActualiser());
        if (result.getOk()){
            ChargerTableModel(result.getTDonnees(), Titres, TableDonnee);
        }
        else
        {
            MessageDlg(result.getMessage());
        }
        return result;
    } 
    
    public Paquet rafraichir(){
        String[] Parametre=null;
        Paquet result=SC.executerTraitement(Parametre,LaClasse, Res.getActualiser());
        return result;
    } 
}
