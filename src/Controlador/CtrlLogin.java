/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.FrmCliente;
import Vista.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlLogin implements ActionListener{
    
    private Controlador C=new Controlador();
    private FrmLogin Frm;
    
    public CtrlLogin (FrmLogin Frm){
        this.Frm = Frm;
       
        Frm.btnAcceder.addActionListener(this);
        Frm.btnSalir.addActionListener(this);        
        Frm.btnLimpiar.addActionListener(this); 

    }
    
    public void IniciarCliente(){
        Frm.setTitle("REGISTRO DE CLIENTES");
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
            C.Mensage("INGRESA TU USUARIO");
            Frm.txtUsu.grabFocus();
        } else {
            if (Frm.txtPsw.getText().trim().length() == 0) {
                C.flag = false;
                C.Mensage("INGRESA UN CONSTRASEÑA");
                Frm.txtPsw.grabFocus();
            } else {
                if (Frm.cbRol.getSelectedIndex() == 0) {
                    C.flag = false;
                    C.Mensage("SELECCIONA TU ROL");
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Frm.btnAcceder){
           if(Validar()){
               C.Acceso(Frm, Frm.txtUsu.getText(), Frm.txtPsw.getText(), Frm.cbRol.getSelectedItem().toString());
               Limpiar();
           }
        }
        
        if(e.getSource() == Frm.btnLimpiar){
           Limpiar();
        }
        
        if(e.getSource() == Frm.btnSalir){
           System.exit(0);
        }
    }
}
