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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlBuscarCliente implements ActionListener{
    private Controlador C=new Controlador();
    private FrmBuscarCliente Frm;
    
    public CtrlBuscarCliente (FrmBuscarCliente Frm){
        this.Frm = Frm;
        
        Frm.btnBuscar.addActionListener(this);
        Frm.btnLimpiar.addActionListener(this);
        
        Frm.lblAcceso.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                LoginPersonal();
            }
        } );
    }
    
    public void Iniciar(){
        Frm.setTitle("BIENVENIDO");
        Frm.setLocationRelativeTo(null);
    }
    
    public void Limpiar(){
        Frm.txtDni.setText(null);
        Frm.txtDni.grabFocus();
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtDni.getText().trim().length() == 0) {
            C.flag = false;
            C.Mensaje("INGRESA TU DNI"); 
            Frm.txtDni.grabFocus();
        }
        return C.flag;
    }
    
    public void ClienteNuevo(){
        FrmClienteNuevo Frm2=new FrmClienteNuevo();
        CtrlClienteNuevo Ctl= new CtrlClienteNuevo(Frm2);
        Ctl.Iniciar();
        Frm2.setVisible(true);
        Frm.setVisible(false);
    }
    
    public void Reservas(){
        /*FrmReservas Frm2=new FrmReservas();
        CtrlReservas Ctl= new CtrlReservas(Frm2);
        Ctl.Iniciar();
        Frm2.setVisible(true);
        Frm.setVisible(false);*/
    }
    
    public void LoginPersonal(){
        FrmLoginPersonal Frm2=new FrmLoginPersonal();
        CtrlLoginPersonal Ctl= new CtrlLoginPersonal(Frm2);
        Ctl.Iniciar();
        Frm2.setVisible(true);
        Frm.setVisible(false);
    }
    
    public void Acceder(){
        C.sql="SELECT * FROM cliente WHERE DNI='"+Frm.txtDni.getText()+"'";
        
        if(Validar()){
            if(!C.VerificarConsulta(C.sql)){
                if (JOptionPane.showConfirmDialog(null, "NO REGISTRADO, ¿DESEAS REGISTRARTE?", "CONSULTA", 0) == 0) {
                    ClienteNuevo();
                }else{
                    Limpiar();
                }
            }else{
                C.Mensaje("BIENVENIDO "+C.DevolverDatoString("SELECT * FROM cliente WHERE DNI='"+Frm.txtDni.getText()+"'",2).toUpperCase());
                Reservas();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnBuscar){
            Acceder();
        }
        
        if(e.getSource() == Frm.btnLimpiar){
            Limpiar();
        }
    }
}
