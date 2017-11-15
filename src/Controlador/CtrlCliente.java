/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.FrmCliente;
import Modelo.*;
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
public class CtrlCliente implements ActionListener{
    
    private DefaultTableModel DTM=new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmCliente Frm;
    private static CtrlCliente Single;
    
    private CtrlCliente (FrmCliente Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        
        Frm.TCliente.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TCliente.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TCliente.getValueAt(C.fila, 0).toString());
                    Frm.txtNombre.setText(Frm.TCliente.getValueAt(C.fila, 1).toString());
                    Frm.txtDNI.setText(Frm.TCliente.getValueAt(C.fila, 2).toString());
                    Frm.txtCelular.setText(Frm.TCliente.getValueAt(C.fila, 3).toString());
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
    
    public static synchronized CtrlCliente getInstance(FrmCliente Frm){
        if(Single == null){
            Single = new CtrlCliente(Frm);
        }
        return Single;
    }
    
    public void Iniciar(){
        Frm.TCliente.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","NOMBRE","DNI","CELULAR"});
        
        Tabla();
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtNombre.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA EL NOMBRE");
            Frm.txtNombre.grabFocus();
        } else {
            if (Frm.txtDNI.getText().trim().length() == 0) {
                C.flag = false;
                JOptionPane.showMessageDialog(null, "INGRESA UN DNI");
                Frm.txtDNI.grabFocus();
            } else {
                if (Frm.txtCelular.getText().trim().length() == 0) {
                    C.flag = false;
                    JOptionPane.showMessageDialog(null, "INGRESA UN CELULAR");
                    Frm.txtCelular.grabFocus();
                }else{
                    if(Frm.txtDNI.getText().trim().length() < 8){
                        C.flag = false;
                        JOptionPane.showMessageDialog(null, "LONGITUD DE DNI INCORRECTO");
                        Frm.txtDNI.grabFocus();
                    }else{
                        if(Frm.txtCelular.getText().trim().length() < 9) {
                            C.flag = false;
                            JOptionPane.showMessageDialog(null, "LONGITUD DE CELULAR INCORRECTO");
                            Frm.txtCelular.grabFocus();
                        }
                    }
                }
            }
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtBuscar.setText(null);
        Frm.txtCelular.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtDNI.setText(null);
        Frm.txtNombre.setText(null);
        Frm.txtNombre.grabFocus();
        Frm.TCliente.clearSelection();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM cliente WHERE Cod_Cliente LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Nombre like '" + Frm.txtBuscar.getText()
                + "%' or DNI like '" + Frm.txtBuscar.getText()
                + "%' or Celular like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 4);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM Cliente WHERE DNI='"+Frm.txtDNI.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod="10"+Frm.txtDNI.getText().toUpperCase();
                    C.InsertaRegistro("INSERT INTO cliente VALUES('"+Cod+"','"
                            +Frm.txtNombre.getText().toUpperCase()+"','"
                            +Frm.txtDNI.getText().toUpperCase()+"','"
                            +Frm.txtCelular.getText().toUpperCase()+"')");
                    C.Mensaje("CLIENTE REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("CLIENTE YA SE ENCUENTRA REGISTRADO");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            String sql="SELECT * FROM Cliente WHERE Nombre='"+Frm.txtNombre.getText()+"' AND DNI='"+Frm.txtDNI.getText()+"' AND Celular='"+Frm.txtCelular.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE cliente SET Nombre='"+Frm.txtNombre.getText().toUpperCase()
                            +"', DNI='"+Frm.txtDNI.getText().toUpperCase()
                            +"', Celular='"+Frm.txtCelular.getText().toUpperCase()
                            +"' WHERE Cod_Cliente='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("CLIENTE ACTUALIZADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TCliente.getSelectedRow();
            if (Frm.TCliente.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM cliente WHERE Cod_Cliente='"+Frm.TCliente.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("CLIENTE ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA CLIENTE PARA ELIMINAR");
            }
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }

}
