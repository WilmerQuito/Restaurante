/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlRol implements ActionListener{
    private DefaultTableModel DTM=new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmRol Frm;
    private static CtrlRol Single;
    
    private CtrlRol (FrmRol Frm){
        this.Frm = Frm;
        
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        
        Frm.TRol.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TRol.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TRol.getValueAt(C.fila, 0).toString());
                    Frm.txtRol.setText(Frm.TRol.getValueAt(C.fila, 1).toString());
                }
            }    
            }
        } );
        
        Frm.txtBuscar.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {Tabla();}

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}
        });
      
    }
    
    public static synchronized CtrlRol getInstance(FrmRol Frm){
        if(Single == null){
            Single = new CtrlRol(Frm);
        }
        return Single;
    }
    
    public void Iniciar(){
        Frm.TRol.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","ROL"});
        Frm.TRol.getColumnModel().getColumn(1).setPreferredWidth(300);
        
        Tabla();
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtRol.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA EL ROL");
            Frm.txtRol.grabFocus();
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtBuscar.setText(null);
        Frm.txtRol.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtRol.grabFocus();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM Rol WHERE Cod_Rol LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Nombre like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM Rol WHERE Nombre='"+Frm.txtRol.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod=C.GeneraCodigo(Frm.txtRol.getText().toUpperCase(), "Rol", "Cod_Rol");
                    C.InsertaRegistro("INSERT INTO Rol VALUES('"+Cod+"','"
                            +Frm.txtRol.getText().toUpperCase()+"')");
                    C.Mensaje("ROL REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("EL ROL YA SE ENCUENTRA REGISTRADO");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            String sql="SELECT * FROM Rol WHERE Nombre='"+Frm.txtRol.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE Rol SET Nombre='"+Frm.txtRol.getText().toUpperCase()
                            +"' WHERE Cod_Rol='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("ROL ACTUALIZADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TRol.getSelectedRow();
            if (Frm.TRol.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM rol WHERE Cod_Rol='"+Frm.TRol.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("ROL ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA ROL PARA ELIMINAR");
            }
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }

}
