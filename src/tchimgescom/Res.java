/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tchimgescom;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class Res {
    private static final String NomApp="MVC Gescom";
    private static final String Actualiser = "Actualiser";
    private static final String Ajouter = "Ajouter";
    private static final String Modifier = "Modifier";
    private static final String Supprimer = "Supprimer";
    
    public static String getNomApp(){  return NomApp;   }
    
    public static final String getActualiser(){  return Actualiser;   }
    public static final String getAjouter(){  return Ajouter;   }
    public static final String getModifier(){  return Modifier;   }
    public static final String getSupprimer(){  return Supprimer;   }
    
    //Remplacer une ' (quote) par '' (quote quote) au cas ou la chaine n,est pas vide
    public static String QuotedStr(String MaChaine) {
        if ((MaChaine == null)||(MaChaine.trim().equals(""))) 
        {
            return "''";
        } 
        else 
        {
            return "'" + MaChaine.replaceAll("'", "''") + "'"; //'ETO''O'
        }
    }
    
    public static void affiche (String message){
        System.out.println(message);
    }
    public static void arret(String message) {
         JOptionPane.showMessageDialog(null, "L'application va s'arrêter : \n " 
                 + message, getNomApp(),JOptionPane.INFORMATION_MESSAGE);
        System.exit(99);
    }
    public static void MessageDlg(Object Msg) {
        JOptionPane.showMessageDialog(null, Msg, getNomApp(),
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    public static int MessageDlgQ(Object Msg) {
        return JOptionPane.showConfirmDialog(null, Msg, getNomApp(),
                                             JOptionPane.YES_NO_OPTION,
                                             JOptionPane.QUESTION_MESSAGE);
    }
    
    public static void Fermer(JComponent Compo) {
        if (MessageDlgQ("Voulez-vous vraiment fermer cette fenetre?") ==
            JOptionPane.YES_OPTION) {
            Compo.setVisible(false);
        } 
    } 

 
    ///////////////////// Charge les données dans des listes ou tableaux
    public static void ChargerComboBox(Object[][] data, JComboBox ComboBoxDonne, int numCol) {
        ComboBoxDonne.removeAllItems();
        int nbdelignes=data.length;
        int i =0;
        while (i<nbdelignes)
        {
            ComboBoxDonne.addItem(data[i][numCol]);
            i++;
        }
    }
    
    public static void ChargerTable (Object data[][], String[] Titres, JTable TableDonnee){
        DefaultTableModel d= new DefaultTableModel();
        d=(DefaultTableModel)TableDonnee.getModel();
        d.setDataVector(data, Titres);
        TableDonnee.repaint();
    }
    
    public static void ChargerTableModel (Object data[][], String[] Titres, JTable TableDonnee){     
        TableModel model;
        model=new TableModel(data, Titres);
        TableDonnee.setModel(model);     
        TableDonnee.repaint();
    }
        
    public static boolean NetEcrit(Paquet Paq_Entree, Socket LaSocket) throws IOException{
        if (LaSocket.isConnected()){            
            ObjectOutputStream oos = new ObjectOutputStream(LaSocket.getOutputStream()); 
            oos.writeObject(Paq_Entree);
            return true;
        }    
        else
        {
            return false;
        }     
    } 

    public static Paquet NetLit(Socket LaSocket)
    {
        Paquet Paq_Entree;
        if (LaSocket.isConnected()){
            try {
                ObjectInputStream ois = new ObjectInputStream(LaSocket.getInputStream());
                Object MonP=ois.readObject();
                 Paq_Entree = (Paquet)MonP;
              return  Paq_Entree;            
            } catch (IOException | ClassNotFoundException ex) {
                  Paq_Entree=new Paquet(ex.getMessage(),false);
                  return  Paq_Entree;
            } 
        }
        else
        {
            Paq_Entree=new Paquet("Erreur de connexion", false);
            return  Paq_Entree;
        }    
    }
    
    public static void Call_Cltr(String NomCtlr, JDesktopPane desktopPane){
        Class maClass;
        try 
        {
            maClass = Class.forName(NomCtlr);
            Class mesParamsTypes[] = new Class[1];
            Object[] mesParams = new Object[1];
            mesParamsTypes[0]=JDesktopPane.class;
            mesParams[0]=desktopPane;
            Method maMethode;
            maMethode = maClass.getMethod("Call_View", mesParamsTypes);                                     
            Object InstanceObjet = maClass.newInstance();
            maMethode.invoke(InstanceObjet, mesParams);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            MessageDlg(ex);
        }
    }
    
    public static void Call_View(JInternalFrame Fen, JDesktopPane desktopPane){
        try {
            desktopPane.add(Fen);
            Fen.setMaximum(true);
            Fen.setSelected(true);
            Fen.setVisible(true);
        } catch (PropertyVetoException ex) {
            MessageDlg(ex);
        }
    }
    
    // Les Convertisseurs
    
    public static int ft_Entier(Object LaValeur)
    {
        if (LaValeur == null) return 0;
        if (((String)LaValeur).equals("")) return 0;
        return (int)LaValeur;
    }

    public static double ft_Nombre(Object LaValeur)
    {
        if (LaValeur == null) return 0;
        if (((String)LaValeur).equals("")) return 0;
        return (double)LaValeur;
    }

    public static String ft_Chaine(Object LaValeur)
    {
        if (LaValeur == null) return "";
        return (String)LaValeur;
    }
    
    public static Object[] Selectionner(JTable LaTable, int ligne, int nbcolsup)
    {
        int totalColonne = LaTable.getColumnCount();
        Object[] donnees = null;
        if (ligne>=0){
            donnees = new Object[totalColonne + nbcolsup];
            for (int i = 0; i < totalColonne; i++)
            {
                donnees[i] = LaTable.getValueAt(ligne,i);
            }
        }
        return donnees; 
    }
       
}
