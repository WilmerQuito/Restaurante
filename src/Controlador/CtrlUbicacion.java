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
public class CtrlUbicacion implements ActionListener{
    private DefaultTableModel DTM=new DefaultTableModel();
    private Controlador C=new Controlador();
    private FrmUbicacion Frm;
    
    public CtrlUbicacion (FrmUbicacion Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        
        Frm.TUbicacion.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TUbicacion.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TUbicacion.getValueAt(C.fila, 0).toString());
                    Frm.txtUbicacion.setText(Frm.TUbicacion.getValueAt(C.fila, 1).toString());
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
    
    public void Iniciar(){
        Frm.setTitle("REGISTRO DE UBICACIONES");
        
        Frm.TUbicacion.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","UBICACION"});
        Frm.TUbicacion.getColumnModel().getColumn(1).setPreferredWidth(300);
        
        Tabla();
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtUbicacion.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA LA UBICACION");
            Frm.txtUbicacion.grabFocus();
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtBuscar.setText(null);
        Frm.txtUbicacion.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtUbicacion.grabFocus();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM ubicacion WHERE Cod_Ubicacion LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Ubicacion like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM ubicacion WHERE Ubicacion='"+Frm.txtUbicacion.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod=C.GeneraCodigo(Frm.txtUbicacion.getText().toUpperCase(), "Ubicacion", "Cod_Ubicacion");
                    C.InsertaRegistro("INSERT INTO ubicacion VALUES('"+Cod+"','"
                            +Frm.txtUbicacion.getText().toUpperCase()+"')");
                    C.Mensaje("UBICACION REGISTRADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("LA UBICACION YA SE ENCUENTRA REGISTRADA");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            String sql="SELECT * FROM ubicacion WHERE Ubicacion='"+Frm.txtUbicacion.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE ubicacion SET Ubicacion='"+Frm.txtUbicacion.getText().toUpperCase()
                            +"' WHERE Cod_Ubicacion='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("UBICACION ACTUALIZADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TUbicacion.getSelectedRow();
            if (Frm.TUbicacion.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM ubicacion WHERE Cod_Ubicacion='"+Frm.TUbicacion.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("UBICACION ELIMINADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA UBICACION PARA ELIMINAR");
            }
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }

}
