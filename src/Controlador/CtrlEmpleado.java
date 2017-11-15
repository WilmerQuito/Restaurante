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
public class CtrlEmpleado implements ActionListener{
    
    private DefaultTableModel DTM=new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private FrmEmpleado Frm;
    
    public CtrlEmpleado (FrmEmpleado Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        
        Frm.TEmpleado.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TEmpleado.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TEmpleado.getValueAt(C.fila, 0).toString());
                    Frm.txtNombre.setText(Frm.TEmpleado.getValueAt(C.fila, 1).toString());
                    Frm.txtDNI.setText(Frm.TEmpleado.getValueAt(C.fila, 2).toString());
                    Frm.txtTelefono.setText(Frm.TEmpleado.getValueAt(C.fila, 3).toString());
                    Frm.txtDireccion.setText(Frm.TEmpleado.getValueAt(C.fila, 4).toString());
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
        Frm.TEmpleado.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","NOMBRE","DNI","CELULAR","DIRECCION"});
        
        Tabla();
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtNombre.getText().trim().length() == 0) {
            C.flag = false;
            C.Mensaje("INGRESA EL NOMBRE");
            Frm.txtNombre.grabFocus();
        } else {
            if (Frm.txtDNI.getText().trim().length() == 0) {
                C.flag = false;
                C.Mensaje("INGRESA UN DNI");
                Frm.txtDNI.grabFocus();
            } else {
                if (Frm.txtTelefono.getText().trim().length() == 0) {
                    C.flag = false;
                    C.Mensaje("INGRESA UN CELULAR");
                    Frm.txtTelefono.grabFocus();
                }else{
                    if(Frm.txtDNI.getText().trim().length() < 8){
                        C.flag = false;
                        C.Mensaje("LONGITUD DE DNI INCORRECTO");
                        Frm.txtDNI.grabFocus();
                    }else{
                        if(Frm.txtTelefono.getText().trim().length() < 9) {
                            C.flag = false;
                            C.Mensaje("LONGITUD DE CELULAR INCORRECTO");
                            Frm.txtTelefono.grabFocus();
                        }else{
                            if(Frm.txtDireccion.getText().trim().length() == 0){
                                C.flag = false;
                                C.Mensaje("INGRESA UNA DIRECCION");
                                Frm.txtDireccion.grabFocus();
                            }
                        }
                    }
                }
            }
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtBuscar.setText(null);
        Frm.txtTelefono.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtDNI.setText(null);
        Frm.txtDireccion.setText(null);
        Frm.txtNombre.setText(null);
        Frm.txtNombre.grabFocus();
        Frm.TEmpleado.clearSelection();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM empleado WHERE idempleado LIKE '" + Frm.txtBuscar.getText() 
                + "%' or nombre like '" + Frm.txtBuscar.getText()
                + "%' or dni like '" + Frm.txtBuscar.getText()
                + "%' or telefono like '" + Frm.txtBuscar.getText()
                + "%' or direccion like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 5);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM empleado WHERE dni='"+Frm.txtDNI.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod="10"+Frm.txtDNI.getText().toUpperCase();
                    C.InsertaRegistro("INSERT INTO empleado VALUES('"+Cod+"','"
                            +Frm.txtNombre.getText().toUpperCase()+"','"
                            +Frm.txtDNI.getText().toUpperCase()+"','"
                            +Frm.txtTelefono.getText().toUpperCase()+"','"
                            +Frm.txtDireccion.getText().toUpperCase()+"')");
                    C.Mensaje("EMPLEADO REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("EL EMPLEADO YA SE ENCUENTRA REGISTRADO");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            String sql="SELECT * FROM empleado WHERE Nombre='"+Frm.txtNombre.getText()
                    +"' AND dni='"+Frm.txtDNI.getText()
                    +"' AND telefono='"+Frm.txtTelefono.getText()
                    +"' AND direccion='"+Frm.txtDireccion.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE empleado SET nombre='"+Frm.txtNombre.getText().toUpperCase()
                            +"', dni='"+Frm.txtDNI.getText().toUpperCase()
                            +"', telefono='"+Frm.txtTelefono.getText().toUpperCase()
                            +"', direccion='"+Frm.txtDireccion.getText().toUpperCase()
                            +"' WHERE idempleado='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("EMPLEADO ACTUALIZADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TEmpleado.getSelectedRow();
            if (Frm.TEmpleado.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM empleado WHERE idempleado='"+Frm.TEmpleado.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("EMPLEADO ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA EMPLEADO PARA ELIMINAR");
            }
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }

}
