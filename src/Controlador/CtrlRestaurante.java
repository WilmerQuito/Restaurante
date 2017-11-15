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
public class CtrlRestaurante implements ActionListener{
    
    private DefaultTableModel DTM=new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmRestaurante Frm;
    private static CtrlRestaurante Single;
    
    private CtrlRestaurante (FrmRestaurante Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        
        Frm.TRestaurante.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TRestaurante.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TRestaurante.getValueAt(C.fila, 0).toString());
                    Frm.txtNombre.setText(Frm.TRestaurante.getValueAt(C.fila, 1).toString());
                    Frm.txtDireccion.setText(Frm.TRestaurante.getValueAt(C.fila, 2).toString());
                    Frm.txtTelefono.setText(Frm.TRestaurante.getValueAt(C.fila, 3).toString());
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
    
    public static synchronized CtrlRestaurante getInstance(FrmRestaurante Frm){
        if(Single == null){
            Single = new CtrlRestaurante(Frm);
        }
        return Single;
    }
    
    public void Iniciar(){
        Frm.TRestaurante.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","NOMBRE","DIRECCION","TELEFONO"});
        
        Tabla();
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtNombre.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA EL NOMBRE");
            Frm.txtNombre.grabFocus();
        } else {
            if (Frm.txtDireccion.getText().trim().length() == 0) {
                C.flag = false;
                JOptionPane.showMessageDialog(null, "INGRESA UN DIRECCION");
                Frm.txtDireccion.grabFocus();
            } else {
                if (Frm.txtTelefono.getText().trim().length() == 0) {
                    C.flag = false;
                    JOptionPane.showMessageDialog(null, "INGRESA UN TELEFONO");
                    Frm.txtTelefono.grabFocus();
                }else{
                    if(Frm.txtTelefono.getText().trim().length() < 9){
                        C.flag = false;
                        JOptionPane.showMessageDialog(null, "LONGITUD DE TELEFONO INCORRECTO");
                        Frm.txtTelefono.grabFocus();
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
        Frm.txtDireccion.setText(null);
        Frm.txtNombre.setText(null);
        Frm.txtNombre.grabFocus();
        Frm.TRestaurante.clearSelection();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM restaurante WHERE Cod_Restaurante LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Nombre like '" + Frm.txtBuscar.getText()
                + "%' or Direccion like '" + Frm.txtBuscar.getText()
                + "%' or Telefono like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 4);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM restaurante WHERE Nombre='"+Frm.txtNombre.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod=C.GeneraCodigo(Frm.txtNombre.getText().toUpperCase(), "Restaurante", "Cod_Restaurante");
                    C.InsertaRegistro("INSERT INTO restaurante VALUES('"+Cod+"','"
                            +Frm.txtNombre.getText().toUpperCase()+"','"
                            +Frm.txtDireccion.getText().toUpperCase()+"','"
                            +Frm.txtTelefono.getText().toUpperCase()+"')");
                    C.Mensaje("RESTAURANTE REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("RESTAURANTE YA SE ENCUENTRA REGISTRADO");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            String sql="SELECT * FROM Restaurante WHERE Nombre='"+Frm.txtNombre.getText()+"' AND Direccion='"+Frm.txtDireccion.getText()+"' AND Telefono='"+Frm.txtTelefono.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE Restaurante SET Nombre='"+Frm.txtNombre.getText().toUpperCase()
                            +"', Direccion='"+Frm.txtDireccion.getText().toUpperCase()
                            +"', Telefono='"+Frm.txtTelefono.getText().toUpperCase()
                            +"' WHERE Cod_Restaurante='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("RESTAURANTE ACTUALIZADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TRestaurante.getSelectedRow();
            if (Frm.TRestaurante.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM Restaurante WHERE Cod_Restaurante='"+Frm.TRestaurante.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("RESTAURANTE ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA UN RESTAURANTE PARA ELIMINAR");
            }
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }

}
