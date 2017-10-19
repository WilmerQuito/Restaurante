/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.FrmMesa;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlMesa implements ActionListener {
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
        
        Frm.TResta.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            JTable table =(JTable) e.getSource();
            Point point = e.getPoint();
            int row = table.rowAtPoint(point);
            if (e.getClickCount() == 1) {
                C.fila = Frm.TResta.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TResta.getValueAt(C.fila, 0).toString());
                    C.MostrarenCombo(Frm.cboEstado, Frm.TResta.getValueAt(C.fila,1).toString());
                    C.MostrarenCombo(Frm.CboUbicacion, Frm.TResta.getValueAt(C.fila,2).toString());
                    Frm.txtpersonas.setText(Frm.TResta.getValueAt(C.fila, 3).toString());
                    Frm.txtmesa.setText(Frm.TResta.getValueAt(C.fila, 4).toString());
                    C.MostrarenCombo(Frm.cboFumador, Frm.TResta.getValueAt(C.fila,5).toString());
                    C.MostrarenCombo(Frm.cboResta, Frm.TResta.getValueAt(C.fila,6).toString());
                }
            }    
            }
        } );
    }
    
    public void IniciarMesas(){
        Frm.setTitle("REGISTRO DE CLIENTES");
        Frm.setLocationRelativeTo(null);
        
        Frm.TResta.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","ESTADO","UBICACION","PERSONAS","NUM_MESA","FUMADOR","RESTAURANT"});
        
        Tabla();
        Estado();
        Ubicacion();
        Fumador();
        Restaurant();
    }
    
    public void Estado(){
        Frm.cboEstado.removeAllItems();
        String Datos []={"<SELECCIONE>","LIBRE","RESERVADO","OCUPADO","PIDIENDO","EN ESPERA","SERVIDOS","CUENTA","PAGANDO"};
        for(int i=0; i<9; i++){
            Frm.cboEstado.addItem(Datos[i]);
        }
    }
    
    public void Ubicacion(){
        Frm.CboUbicacion.removeAllItems();
        String Datos []={"<SELECCIONE>","ENTRADA","FONDO","CENTRO","IZQUIERDA","DERECHA"};
        for(int i=0; i<6; i++){
            Frm.CboUbicacion.addItem(Datos[i]);
        }
    }
    
    public void Fumador(){
        Frm.cboFumador.removeAllItems();
        String Datos []={"<SELECCIONE>","SI","NO"};
        for(int i=0; i<3; i++){
            Frm.cboFumador.addItem(Datos[i]);
        }
    }
    
    public void Restaurant(){
        C.sql= "SELECT * FROM restaurante";
        C.LlenarCombo(Frm.cboResta, C.sql, "<SELECCIONE>",1);
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.cboEstado.getSelectedIndex() == 0) {
            C.flag = false;
            C.Mensage("SELECCIONA ESTADO");
            Frm.cboEstado.grabFocus();
        }else{
            if (Frm.CboUbicacion.getSelectedIndex() == 0) {
                C.flag = false;
                C.Mensage("SELECCIONA UBICACION");
                Frm.CboUbicacion.grabFocus();
            }else{
                if (Frm.txtpersonas.getText().trim().length() == 0) {
                    C.flag = false;
                    C.Mensage("INGRESA CANTIDAD DE PERSONAS");
                    Frm.txtpersonas.grabFocus();
                }else{
                    if (Frm.txtmesa.getText().trim().length() == 0) {
                        C.flag = false;
                        C.Mensage("INGRESA NUMERO DE MESA");
                        Frm.txtmesa.grabFocus();
                    }else{
                        if (Frm.cboFumador.getSelectedIndex() == 0) {
                            C.flag = false;
                            C.Mensage("SELECCIONA SI ES ZONA DE FUMAR");
                            Frm.cboFumador.grabFocus();
                        }else{
                            if (Frm.cboResta.getSelectedIndex() == 0) {
                                C.flag = false;
                                C.Mensage("SELECCIONA RESTAURANTE");
                                Frm.cboResta.grabFocus();
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
        Frm.txtmesa.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtpersonas.setText(null);
        Frm.CboUbicacion.setSelectedIndex(0);
        Frm.cboEstado.setSelectedIndex(0);
        Frm.cboFumador.setSelectedIndex(0);
        Frm.cboResta.setSelectedIndex(0);
        Frm.TResta.clearSelection();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM mesa WHERE Cod_Mesa LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Estado like '" + Frm.txtBuscar.getText()
                + "%' or Ubicacion like '" + Frm.txtBuscar.getText()
                + "%' or Cant_Personas like '" + Frm.txtBuscar.getText()
                + "%' or Num_Mesa like '" + Frm.txtBuscar.getText()
                + "%' or Fumador like '" + Frm.txtBuscar.getText()
                + "%' or Cod_Restaurante like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 7);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnguardar){
            if (Validar()) {
                String Cod=C.GeneraCodigo(Frm.CboUbicacion.getSelectedItem().toString().toUpperCase(), "mesa", "Cod_Mesa");
                C.InsertaRegistro("INSERT INTO mesa VALUES('"+Cod+"','"
                        +Frm.cboEstado.getSelectedItem().toString().toUpperCase()+"','"
                        +Frm.CboUbicacion.getSelectedItem().toString().toUpperCase()+"','"
                        +Integer.parseInt(Frm.txtpersonas.getText().toString())+"','"
                        +Integer.parseInt(Frm.txtmesa.getText().toString())+"','"
                        +Frm.cboFumador.getSelectedItem().toString().toUpperCase()+"','"
                        +Frm.cboResta.getSelectedItem().toString().toUpperCase()+"')");
                
                C.Mensage("MESA REGISTRADA");
                Tabla();
                Limpiar();
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            if (Validar()) {
                C.InsertaRegistro("UPDATE mesa SET Estado='"+Frm.cboEstado.getSelectedItem().toString().toUpperCase()
                        +"', Ubicacion='"+Frm.CboUbicacion.getSelectedItem().toString().toUpperCase()
                        +"', Cant_Personas='"+Integer.parseInt(Frm.txtpersonas.getText().toString())
                        +"', Num_Mesa='"+Integer.parseInt(Frm.txtmesa.getText().toString())
                        +"', Fumador='"+Frm.cboFumador.getSelectedItem().toString().toUpperCase()
                        +"', Cod_Restaurante='"+Frm.cboResta.getSelectedItem().toString().toUpperCase()
                        +"' WHERE Cod_Mesa='"+Frm.txtCodigo.getText()+"'");
                C.Mensage("MESA ACTUALIZADA");
                Tabla();
                Limpiar();
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TResta.getSelectedRow();
            if (Frm.TResta.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM mesa WHERE Cod_Mesa='"+Frm.TResta.getValueAt(C.fila, 0).toString()+"'");
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
