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
public class CtrlEstadoMesas implements ActionListener{
    private DefaultTableModel DTM=new DefaultTableModel();
    private Controlador C=new Controlador();
    private FrmEstadoMesas Frm;
    
    public CtrlEstadoMesas(FrmEstadoMesas Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        
        Frm.TEstado.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TEstado.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TEstado.getValueAt(C.fila, 0).toString());
                    Frm.txtEstado.setText(Frm.TEstado.getValueAt(C.fila, 1).toString());
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
        Frm.TEstado.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","ESTADO"});
        Frm.TEstado.getColumnModel().getColumn(1).setPreferredWidth(300);
        
        Tabla();
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtEstado.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA EL ESTADO");
            Frm.txtEstado.grabFocus();
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtBuscar.setText(null);
        Frm.txtEstado.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtEstado.grabFocus();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM estado WHERE Cod_Estado LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Estado like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM estado WHERE Estado='"+Frm.txtEstado.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod=C.GeneraCodigo(Frm.txtEstado.getText().toUpperCase(), "Estado", "Cod_Estado");
                    C.InsertaRegistro("INSERT INTO estado VALUES('"+Cod+"','"
                            +Frm.txtEstado.getText().toUpperCase()+"')");
                    C.Mensaje("ESTADO REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("EL ESTADO YA SE ENCUENTRA REGISTRADO");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            String sql="SELECT * FROM estado WHERE Estado='"+Frm.txtEstado.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE estado SET Estado='"+Frm.txtEstado.getText().toUpperCase()
                            +"' WHERE Cod_Estado='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("ESTADO ACTUALIZADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TEstado.getSelectedRow();
            if (Frm.TEstado.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM estado WHERE Cod_Estado='"+Frm.TEstado.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("ESTADO ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA UN ESTADO PARA ELIMINAR");
            }
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }
}
