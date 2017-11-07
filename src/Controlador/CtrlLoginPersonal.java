/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.*;
import Vista.FrmPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlLoginPersonal implements ActionListener{
    private Controlador C=new Controlador();
    private FrmLoginPersonal Frm;
    
    public CtrlLoginPersonal(FrmLoginPersonal Frm){
        this.Frm = Frm;
       
        Frm.btnAcceder.addActionListener(this);
        Frm.btnLimpiar.addActionListener(this);
        Frm.btnSalir.addActionListener(this);
    }
    
    public void Iniciar(){
        Frm.setTitle("INICIAR SESION");
        Frm.setLocationRelativeTo(null);
        Rol();
    }
    
    public void Rol(){
        C.sql= "SELECT * FROM Rol;";
        C.LlenarCombo(Frm.cbRol, C.sql, "<SELECCIONE>",2);
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtUsu.getText().trim().length() == 0) {
            C.flag = false;
            C.Mensaje("INGRESA TU USUARIO");
            Frm.txtUsu.grabFocus();
        } else {
            if (Frm.txtPsw.getText().trim().length() == 0) {
                C.flag = false;
                C.Mensaje("INGRESA UN CONSTRASEÑA");
                Frm.txtPsw.grabFocus();
            } else {
                if (Frm.cbRol.getSelectedIndex() == 0) {
                    C.flag = false;
                    C.Mensaje("SELECCIONA TU ROL");
                    Frm.cbRol.grabFocus();
                }
            }
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtUsu.setText(null);
        Frm.txtPsw.setText(null);
        Frm.cbRol.setSelectedIndex(0);
        Frm.cbRol.grabFocus();
    }
    
    public void Acceso(){
        if(Validar()){
            C.Mensaje("BIENVENIDO "+C.DevolverDatoString("SELECT * FROM VataSesion WHERE Usuario='"+Frm.txtUsu.getText()+"'",2).toUpperCase());
            C.Acceso(Frm, new FrmPrincipal(), Frm.txtUsu.getText(), Frm.txtPsw.getText(), Frm.cbRol.getSelectedItem().toString());
            FrmPrincipal.lblnombre.setText(null);
            FrmPrincipal.lblnombre.setText(Frm.txtUsu.getText().toUpperCase());
            Limpiar();
        }else{
            C.Mensaje("ACCEDO DENEGADO");
        }
    }
    
    public void BuscarCliente(){
        FrmBuscarCliente Frm2=new FrmBuscarCliente();
        CtrlBuscarCliente Ctl= new CtrlBuscarCliente(Frm2);
        Ctl.Iniciar();
        Frm2.setVisible(true);
        Frm.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnAcceder){
            Acceso();
        }
        
        if(e.getSource() == Frm.btnLimpiar){
            Limpiar();
        }
        
        if(e.getSource() == Frm.btnSalir){
            BuscarCliente();
        }
    }
}
