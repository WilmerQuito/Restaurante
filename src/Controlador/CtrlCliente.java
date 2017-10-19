/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlCliente implements ActionListener{
    
    private DefaultTableModel DTM=new DefaultTableModel();
    private Controlador C=new Controlador();
    private FrmCliente Frm;
    
    public CtrlCliente (FrmCliente Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        Frm.btnsalir.addActionListener(this);
        
        Frm.TCliente.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            JTable table =(JTable) e.getSource();
            Point point = e.getPoint();
            int row = table.rowAtPoint(point);
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
      
    }
    
    public void IniciarCliente(){
        Frm.setTitle("REGISTRO DE CLIENTES");
        Frm.setLocationRelativeTo(null);
        
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
                JOptionPane.showMessageDialog(null, "INGRESA UN NOMBRE");
                Frm.txtDNI.grabFocus();
            } else {
                if (Frm.txtCelular.getText().trim().length() == 0) {
                    C.flag = false;
                    JOptionPane.showMessageDialog(null, "INGRESA UN APELLIDO");
                    Frm.txtCelular.grabFocus();
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
            if (Validar()) {
                String Cod="10"+Frm.txtDNI.getText().toUpperCase();
                C.InsertaRegistro("INSERT INTO cliente VALUES('"+Cod+"','"
                        +Frm.txtNombre.getText().toUpperCase()+"','"
                        +Frm.txtDNI.getText().toUpperCase()+"','"
                        +Frm.txtCelular.getText().toUpperCase()+"')");
                
                C.Mensage("CLIENTE REGISTRADO");
                Tabla();
                Limpiar();
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            if (Validar()) {
                C.InsertaRegistro("UPDATE cliente SET Nombre='"+Frm.txtNombre.getText().toUpperCase()
                        +"', DNI='"+Frm.txtDNI.getText().toUpperCase()
                        +"', Celular='"+Frm.txtCelular.getText().toUpperCase()
                        +"' WHERE Cod_Cliente='"+Frm.txtCodigo.getText()+"'");
                C.Mensage("CLIENTE ACTUALIZADO");
                Tabla();
                Limpiar();
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TCliente.getSelectedRow();
            if (Frm.TCliente.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM cliente WHERE Cod_Cliente='"+Frm.TCliente.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensage("CLIENTE ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            }
        }
        
        if(e.getSource() == Frm.btnsalir){
            System.exit(0);
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }  
}
