/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tchimgescom.Serveur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import tchimgescom.Paquet;

/**
 *
 * @author hp
 */
public class P_Modele {
    private static Connection  con;
    private static Statement stmtReadOnly;
    private static Statement stmtReadWrite;
    private static String User="root";
    private static String passw="MotDePasse";
    private static String MachineHote="localhost";
    private static String Port="3306";
    private static String BD="NomBD";
    
    private static Paquet PackBD;
    
    
    
    public P_Modele(){
       PackBD=ConnexionBD_MySQL(); 
    }
    
    public static void Connexion(String MachineHote2, String Port2, String BD2, String User2, String passw2 ){
        MachineHote=MachineHote2;
        Port=Port2;
        BD=BD2;
        passw=passw2;
        User=User2;
        PackBD=ConnexionBD_MySQL(); 
    }
        
    private static Paquet ConnexionBD_MySQL() {
        String Message="";
        try // chargement du pilote
        {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            System.out.println("instance crée");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            Message="Impossible de charger le pilote jdbc pour mySQL \n" + e.getMessage();
            return new Paquet(Message,false);
        }
        try // connexion à la BD
        {
            String DBurl = "jdbc:mysql://"+MachineHote+":"+Port+"/"+BD;
            con = DriverManager.getConnection(DBurl,User,passw);
            stmtReadOnly = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
            stmtReadWrite = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_UPDATABLE);
            System.out.println("Connexion établie");      

        } catch (SQLException e) {
            Message=" Connection à la base de données impossible " + e.getMessage();
            return new Paquet(Message,false);
        }
        return new Paquet("",true);
    }
    public static Paquet getPackBD(){
        return PackBD;
    }
    
    public static Paquet ExecuterSansResultat(Paquet PackEntree, String StrSQl) {
        try 
        {
           stmtReadWrite.execute(StrSQl); //Exécution de la requete
        } 
        catch (SQLException e) {
            return new Paquet(e.getMessage(),false);
        }
        return new Paquet("",true);
    }
        
    public static Paquet ExecuterAvecResultat(Paquet PackEntree,String StrSQl) {
        try 
        {            
            ResultSet QueryS = stmtReadOnly.executeQuery(StrSQl);
            if (QueryS != null) {              
                ResultSetMetaData rsmd;
                rsmd = QueryS.getMetaData();
                int nbCols = rsmd.getColumnCount(); //Nombre de colonne              
                //On va à la dernière ligne;
                QueryS.last();
                //on récupère le numéro de la ligne
                int nombreLignes = QueryS.getRow();
                //on repace le curseur avant la première ligne
                QueryS.beforeFirst();
                Object MesDonnees[][] = new Object[nombreLignes][nbCols];           
                boolean encore;
                encore = QueryS.next(); //Parcours de récupération des données
                int i=0;
                while (encore) {
                    int j;
                    for(j=0;j<nbCols;j++){
                        MesDonnees[i][j]=QueryS.getString(j+1);
                    }
                    i=i+1;
                    encore = QueryS.next();
                }
                QueryS.close();
                return new Paquet("",true,MesDonnees,nombreLignes,nbCols);
            }
        } 
        catch (SQLException e) {
            return new Paquet(e.getMessage(),false);
        }
        return new Paquet("",true); //Aucune donnée trouvée
    }
    
    public static Paquet NombreDeligne(Paquet PackEntree,String StrSQl) {
        int nombreLignes=-1;
        try 
        { 
            ResultSet QueryS = stmtReadOnly.executeQuery(StrSQl);
            if (QueryS != null) {
                //On va à la dernière ligne;
                QueryS.last();
                //on récupère le numéro de la ligne
                 nombreLignes = QueryS.getRow();
                 QueryS.close();
            }
        } catch (SQLException e) {
            return new Paquet(e.getMessage(),false);
        }
        return new Paquet("",true,null,nombreLignes,0); //On recupère le nombre de ligne dans la variable nbLigne
    }

}
