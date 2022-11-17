/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tchimgescom;

/**
 *
 * @author hp
 */
public class Paquet implements java.io.Serializable, Cloneable{
    private String Message; // Message en cas d'erreur
    private boolean Ok; // requete bien exécutée ou non true or false
    private int nbLigne; //Nombre de lignes retournée
    private int nbColonne; //Nombre de colonnes retournée
    private Object[][] TDonnees; // Tableau de données retournées éventuellement
    
    
    private static final long serialVersionUID = 1;
    private String NomTraitement;
    private String NomClasse;
   
    private int NumErreur;
    private Object[] parametre;
    private Object[][] TableauParametre;
    
    private String CodeConnexionMachine;
    private String CodeConnexionUser;
    private String NomMachine;
    private String AdresseIpMachine;
    private String PortConnexionMachine;
    private String LoginUtilisateur;
    private String NomUtilisateur;
    
    public Paquet(){
        Message="";
        Ok=true;
        nbLigne=0;
        nbColonne=0;
        TDonnees=null; 
    }
    public Paquet(String MessageErreur, boolean OkErreur){
        this.Message=MessageErreur;
        this.Ok=OkErreur;
        nbLigne=0;
        nbColonne=0;
        TDonnees=null; 
    }
    
    public Paquet(String MessageErreur, boolean OkErreur, Object[][] TDonnees, int nbLigne, int nbColonne){
        this.Message=MessageErreur;
        this.Ok=OkErreur;
        this.nbLigne=nbLigne;
        this.nbColonne=nbColonne;
        this.TDonnees=TDonnees; 
    }
    
    //Accesseurs
    public String getMessage(){
        return Message;
    }

    public boolean getOk(){
        return Ok;
    }
    
    public int getnbLigne(){
        return nbLigne;
    }
    public int getnbColonne(){
        return nbColonne;
    }
    public Object[][] getTDonnees(){
        return TDonnees;
    }

    public String getNomTraitement(){
        return NomTraitement;
    }
    public String getNomClasse(){
        return NomClasse;
    }
   
    public int getNumErreur(){
        return NumErreur;
    }
    public Object[] getparametre(){
        return parametre;
    }
    public Object[][] getTableauParametre(){
        return TableauParametre;
    }
    public String getCodeConnexionMachine(){
        return CodeConnexionMachine;
    }
    public String getCodeConnexionUser(){
        return CodeConnexionUser;
    }
    public String getNomMachine(){
        return NomMachine;
    }
    public String getAdresseIpMachine(){
        return AdresseIpMachine;
    }
    public String getPortConnexionMachine(){
        return PortConnexionMachine;
    }
    public String getLoginUtilisateur(){
        return LoginUtilisateur;
    }
    public String getNomUtilisateur(){
        return NomUtilisateur;
    }
    
    // Mutateurs
    public void setMessage(String Message){
        this.Message=Message;
    }	
    public void setOk(boolean Ok){
        this.Ok=Ok;
    }
    
    public void setnbLigne(int nbLigne){
        this.nbLigne=nbLigne;
    }
    public void setnbColonne(int nbColonne){
        this.nbColonne=nbColonne;
    }
    public void setTDonnees(Object[][] TDonnees){
        this.TDonnees=TDonnees;
    }

    public void setNomTraitement(String NomTraitement){
        this.NomTraitement=NomTraitement;
    }
    public void setNomClasse(String NomClasse){
        this.NomClasse=NomClasse;
    }
   
    public void setNumErreur(int NumErreur){
        this.NumErreur=NumErreur;
    }
    public void setparametre(Object[] parametre){
        this.parametre=parametre;
    }
    public void setTableauParametre(Object[][] TableauParametre){
        this.TableauParametre=TableauParametre;
    }
    public void setCodeConnexionMachine(String CodeConnexionMachine){
        this.CodeConnexionMachine=CodeConnexionMachine;
    }
    public void setCodeConnexionUser(String CodeConnexionUser){
        this.CodeConnexionUser=CodeConnexionUser;
    }
    public void setNomMachine(String NomMachine){
        this.NomMachine=NomMachine;
    }
    public void setAdresseIpMachine(String AdresseIpMachine){
        this.AdresseIpMachine=AdresseIpMachine;
    }
    public void setPortConnexionMachine(String PortConnexionMachine){
        this.PortConnexionMachine=PortConnexionMachine;
    }
    public void setLoginUtilisateur(String LoginUtilisateur){
        this.LoginUtilisateur=LoginUtilisateur;
    }
    public void setNomUtilisateur(String NomUtilisateur){
        this.NomUtilisateur=NomUtilisateur;
    }
}
