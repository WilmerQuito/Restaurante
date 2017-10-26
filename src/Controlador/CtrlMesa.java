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
public class CtrlMesa implements ActionListener{
    
    private DefaultTableModel DTM=new DefaultTableModel();
    private Controlador C=new Controlador();
    private FrmMesa Frm;
    
    public CtrlMesa (FrmMesa Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        Frm.btnsalir.addActionListener(this);
        
        Frm.TMesa.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TMesa.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TMesa.getValueAt(C.fila, 0).toString());
                    C.MostrarenCombo(Frm.cboRest, Frm.TMesa.getValueAt(C.fila,1).toString());
                    Frm.txtNumMesa.setText(Frm.TMesa.getValueAt(C.fila, 2).toString());
                    Frm.txtCantPer.setText(Frm.TMesa.getValueAt(C.fila, 3).toString());
                    C.MostrarenCombo(Frm.cboFumador, Frm.TMesa.getValueAt(C.fila,4).toString());
                    C.MostrarenCombo(Frm.cboUbicacion, Frm.TMesa.getValueAt(C.fila,5).toString());
                    C.MostrarenCombo(Frm.cboEstado, Frm.TMesa.getValueAt(C.fila,6).toString());
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
        Frm.setTitle("REGISTRO DE MESAS");
        Frm.setLocationRelativeTo(null);
        
        Frm.TMesa.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","RESTAURANTE","NUM. MESA","CANT. PERSONAS", "FUMADOR", "UBICACION", "ESTADO"});
        
        Tabla();
        Restaurante();
        Ubicacion();
        Estado();
        Fumador();
    }
    
    public void Restaurante(){
        C.sql= "SELECT * FROM restaurante";
        C.LlenarCombo(Frm.cboRest, C.sql, "<SELECCIONE>",2);
    }
    
    public void Ubicacion(){
        C.sql= "SELECT * FROM ubicacion";
        C.LlenarCombo(Frm.cboUbicacion, C.sql, "<SELECCIONE>",2);
    }
    
    public void Estado(){
        C.sql= "SELECT * FROM estado";
        C.LlenarCombo(Frm.cboEstado, C.sql, "<SELECCIONE>",2);
    }
    
    public void Fumador(){
        Frm.cboFumador.removeAllItems();
        String Datos []={"<SELECCIONE>","SI","NO"};
        for(int i=0; i<3; i++){
            Frm.cboFumador.addItem(Datos[i]);
        }
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.cboEstado.getSelectedIndex() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "SELECCIONA UN ESTADO");
            Frm.cboEstado.grabFocus();
        } else {
            if (Frm.cboFumador.getSelectedIndex() == 0) {
                C.flag = false;
                JOptionPane.showMessageDialog(null, "SELECCIONA FUMADOR");
                Frm.cboFumador.grabFocus();
            } else {
                if (Frm.cboRest.getSelectedIndex() == 0) {
                    C.flag = false;
                    JOptionPane.showMessageDialog(null, "SELECCIONA RESTAURANTE");
                    Frm.cboRest.grabFocus();
                }else{
                    if(Frm.cboUbicacion.getSelectedIndex() == 0){
                        C.flag = false;
                        JOptionPane.showMessageDialog(null, "SELECCIONA UBICACION");
                        Frm.cboUbicacion.grabFocus();
                    }else{
                        if(Frm.txtCantPer.getText().trim().length() == 0) {
                            C.flag = false;
                            JOptionPane.showMessageDialog(null, "INGRESA CANTIDAD DE PERSONAS");
                            Frm.txtCantPer.grabFocus();
                        }else{
                            if(Frm.txtNumMesa.getText().trim().length() == 0){
                                C.flag = false;
                                JOptionPane.showMessageDialog(null, "INGRESA NUMERO DE MESA");
                                Frm.txtCantPer.grabFocus();
                            }else{
                                if(Frm.txtCantPer.getText().equals("0")) {
                                    C.flag = false;
                                    JOptionPane.showMessageDialog(null, "INGRESA CANTIDAD DE PERSONAS MAYOR A 0");
                                    Frm.txtCantPer.grabFocus();
                                }else{
                                    if(Frm.txtNumMesa.getText().equals("0")){
                                        C.flag = false;
                                        JOptionPane.showMessageDialog(null, "INGRESA NUMERO DE MESA MAYOR A 0");
                                        Frm.txtCantPer.grabFocus();
                                    }
                                }
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
        Frm.txtCantPer.setText(null);
        Frm.txtNumMesa.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.cboEstado.setSelectedIndex(0);
        Frm.cboFumador.setSelectedIndex(0);
        Frm.cboRest.setSelectedIndex(0);
        Frm.cboUbicacion.setSelectedIndex(0);
        Frm.cboRest.grabFocus();
        Frm.TMesa.clearSelection();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM VtaMesa WHERE Cod_Mesa LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Nombre like '" + Frm.txtBuscar.getText()
                + "%' or Num_Mesa like '" + Frm.txtBuscar.getText()
                + "%' or Cant_Personas like '" + Frm.txtBuscar.getText()
                + "%' or Fumador like '" + Frm.txtBuscar.getText()
                + "%' or Ubicacion like '" + Frm.txtBuscar.getText()
                + "%' or Estado like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 7);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String Rest=C.DatoCombo("SELECT * FROM restaurante WHERE Nombre='"+Frm.cboRest.getSelectedItem().toString()+"'",1);
        String Ubica=C.DatoCombo("SELECT * FROM ubicacion WHERE Ubicacion='"+Frm.cboUbicacion.getSelectedItem().toString()+"'",1);
        String Esta=C.DatoCombo("SELECT * FROM estado WHERE Estado='"+Frm.cboEstado.getSelectedItem().toString()+"'",1);
            
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM mesa WHERE Num_Mesa='"+Frm.txtNumMesa.getText()+"' AND Cod_Restaurante='"+Rest+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod=C.GeneraCodigo("M", "Mesa", "Cod_Mesa");
                    C.InsertaRegistro("INSERT INTO mesa VALUES('"+Cod+"','"
                            +Frm.txtCantPer.getText().toUpperCase()+"','"
                            +Frm.txtNumMesa.getText().toUpperCase()+"','"
                            +Frm.cboFumador.getSelectedItem().toString().toUpperCase()+"','"
                            +Esta+"','"
                            +Ubica+"','"
                            +Rest+"')");
                    C.Mensaje("MESA REGISTRADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("LA MESA YA SE ENCUENTRA REGISTRADA");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            String sql="SELECT * FROM mesa WHERE Num_Mesa='"+Frm.txtNumMesa.getText()+"' AND Cod_Restaurante='"+Rest+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE mesa SET Cant_Personas='"+Frm.txtCantPer.getText().toUpperCase()
                            +"', Num_Mesa='"+Frm.txtNumMesa.getText().toUpperCase()
                            +"', Fumador='"+Frm.cboFumador.getSelectedItem().toString().toUpperCase()
                            +"', Cod_Estado='"+Esta
                            +"', Cod_Ubicacion='"+Ubica
                            +"', Cod_Restaurante='"+Rest
                            +"' WHERE Cod_Mesa='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("MESA ACTUALIZADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TMesa.getSelectedRow();
            if (Frm.TMesa.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM mesa WHERE Cod_Mesa='"+Frm.TMesa.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("MESA ELIMINADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA MESA PARA ELIMINAR");
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
