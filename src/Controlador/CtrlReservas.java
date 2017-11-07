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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlReservas implements ActionListener{
    
    private DefaultTableModel DTM=new DefaultTableModel();
    private Controlador C=new Controlador();
    private FrmReservas Frm;
    
    public CtrlReservas (FrmReservas Frm){
        this.Frm = Frm;
        Frm.cboRestaurante.addActionListener(this);
        
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
        
        Frm.setTitle("RESERVA TU MESA...!!!");
        Frm.setLocationRelativeTo(null);
        
        Frm.TReservas.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","RESTAURANTE","NUM. MESA","CANT. PERSONAS", "FUMADOR", "UBICACION", "ESTADO"});
        
        Restaurante();
        Limpiar();
    }
    
    public void Restaurante(){
        C.sql= "SELECT * FROM restaurante";
        C.LlenarCombo(Frm.cboRestaurante, C.sql, "<SELECCIONE>",2);
    }
    
    public void Limpiar(){
        Frm.lblDireccion.setText(null);
        Frm.lblRestaurante.setText(null);
        Frm.lblTelefono.setText(null);
        Frm.cboRestaurante.setSelectedIndex(0);
        Frm.TReservas.clearSelection();
        Frm.cboRestaurante.grabFocus();
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.cboRestaurante.getSelectedIndex() == 0) {
            C.flag = false;
            C.Mensaje("SELECCIONA UN RESTAURANTE");
            Frm.cboRestaurante.grabFocus();
        }
        return C.flag;
    }
    
    public void RestLibre() {
        C.sql = "SELECT * FROM VtaMesaLibre WHERE Nombre='"+Frm.cboRestaurante.getSelectedItem().toString()+"' AND Estado='Libre'";
        C.MostrarenJTable(DTM, C.sql, 7);
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM VtaMesaLibre WHERE Cod_Mesa LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Nombre like '" + Frm.txtBuscar.getText()
                + "%' or Num_Mesa like '" + Frm.txtBuscar.getText()
                + "%' or Cant_Personas like '" + Frm.txtBuscar.getText()
                + "%' or Fumador like '" + Frm.txtBuscar.getText()
                + "%' or Ubicacion like '" + Frm.txtBuscar.getText()
                + "%' or Estado like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 7);
    }
    
    public void Visualizar(){
        if(Frm.cboRestaurante.getSelectedIndex() > 0){
            String RS=Frm.cboRestaurante.getSelectedItem().toString();
            String Rest=C.DatoCombo("SELECT * FROM restaurante WHERE Nombre='"+RS+"'",2);
            String Dire=C.DatoCombo("SELECT * FROM restaurante WHERE Nombre='"+RS+"'",3);
            String Telef=C.DatoCombo("SELECT * FROM restaurante WHERE Nombre='"+RS+"'",4);

            Frm.lblRestaurante.setText(Rest);
            Frm.lblDireccion.setText(Dire);
            Frm.lblTelefono.setText(Telef);
            
            RestLibre();
            
            if(DTM.getRowCount() <= 0){
                C.Mensaje("NO HAY MESAS LIBRES, PRUEBE EN OTRO RESTAURANTE");
            }
        }
        if(Frm.cboRestaurante.getSelectedIndex() == 0){
            Limpiar();
            C.LimpiarTabla(DTM);
        }
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.cboRestaurante){
            Visualizar();
        }
    }

}
