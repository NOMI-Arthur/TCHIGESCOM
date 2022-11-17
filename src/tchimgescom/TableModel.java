/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tchimgescom;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NOMI Ph.D
 */
public class TableModel extends AbstractTableModel {
        
    Object MesDonnees[][]; // represente les données à afficher dans le JTable
    String Titres[]; // represente les titres des colonnes à afficher dans le JTable
    
    public TableModel(Object VMesDonnees[][],String VTitres[]){
        this.MesDonnees=VMesDonnees;
        this.Titres=VTitres;
    }
    
    @Override
    //retourne le nom de la colonne
    public String getColumnName(int col){
        return this.Titres[col];
     }
    
    @Override
    //Retourne le nombre de colonnes du tableau
     public int getColumnCount(){
        return this.Titres.length;
     }
    
    @Override
    //Retourne le nombre de lignes du tableau
    public int getRowCount(){
        return this.MesDonnees.length;
     }
    
    @Override
    //retourne la valeur de la données se trouvant en ligne "row" et en colonne "col"
    public Object getValueAt(int row,int col){
        return this.MesDonnees[row][col];
     } 
    
        
    @Override
    //Affecte la valeur à la cellule se trouvant en ligne "row" et en colonne "col"
    public void setValueAt(Object value, int row,int col){
         this.MesDonnees[row][col]=value;
     }
    
    @Override
    //retourne la classe de la données se trouvant en colonne "col"
    public Class getColumnClass(int col){
        return this.MesDonnees[0][col].getClass();
    }
    @Override
    //Dis si la cellule se trouvant en ligne "row" et en colonne "col" est modifiable
    public boolean isCellEditable(int row, int col){
        return false;
    }
}
