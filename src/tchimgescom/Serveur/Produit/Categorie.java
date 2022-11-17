/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom.Serveur.Produit;

/**
 *
 * @author NOMI Ph.D
 */
import tchimgescom.Paquet;
import tchimgescom.Res;
import tchimgescom.Serveur.P_Modele;
public class Categorie {
    //private MonPacket Lepaket;
    //public FamilleProduit(MonPacket Lepack){
    //    Lepaket=Lepack;
    //}
    
    public Paquet Ajouter(Paquet Pack_Entree){
        String Code=Res.ft_Chaine(Pack_Entree.getparametre()[0]);
        String Nom=Res.ft_Chaine(Pack_Entree.getparametre()[1]);
        String SourceSQL;
        SourceSQL="INSERT INTO categorie(Id_categorie,lib_categorie)Values ("+
                Res.QuotedStr(Code)+","+
                Res.QuotedStr(Nom)+")";
        Paquet Pack_Sortie=P_Modele.ExecuterSansResultat(Pack_Entree, SourceSQL);
        return Pack_Sortie;  
    }
   
    public Paquet Modifier(Paquet Pack_Entree){
        String Code=Res.ft_Chaine(Pack_Entree.getparametre()[0]);
        String Nom=Res.ft_Chaine(Pack_Entree.getparametre()[1]);
        String AncienCodeFamille=Res.ft_Chaine(Pack_Entree.getparametre()[1]);

        String SourceSQL;
        SourceSQL="UPDATE categorie SET Id_categorie="+Res.QuotedStr(Code)+","+
                     " lib_categorie="+Res.QuotedStr(Nom)+
                      " WHERE Id_categorie="+Res.QuotedStr(AncienCodeFamille);
        Paquet Pack_Sortie=P_Modele.ExecuterSansResultat(Pack_Entree, SourceSQL);
        return Pack_Sortie;  
    }
    public Paquet Supprimer(Paquet Pack_Entree){
        String Code=Res.ft_Chaine(Pack_Entree.getparametre()[0]);      
        String SourceSQL;
        SourceSQL="DELETE FROM categorie WHERE Id_categorie="+Res.QuotedStr(Code);        
        Paquet Pack_Sortie=P_Modele.ExecuterSansResultat(Pack_Entree, SourceSQL);
        return Pack_Sortie;  
    }
    
    public Paquet Actualiser(Paquet Pack_Entree){
        String SourceSQL="Select Id_categorie, lib_categorie from categorie Order by Id_categorie";  
        Paquet Pack_Sortie=P_Modele.ExecuterAvecResultat(Pack_Entree, SourceSQL);        
        return Pack_Sortie;  
    }
    
}
