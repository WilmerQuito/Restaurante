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
    Controlador C = Controlador.getInstance();
    private static FrmBuscarCliente Frm;
    private static CtrlBuscarCliente Single;
    
    private CtrlBuscarCliente (FrmBuscarCliente Frm){
        this.Frm = Frm;
        
        Frm.btnBuscar.addActionListener(this);
        Frm.btnLimpiar.addActionListener(this);
        
        Frm.lblAcceso.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                C.LoginPersonal(Frm);
            }
        } );
    }

    public static synchronized CtrlBuscarCliente getInstance(FrmBuscarCliente Frm) {
        if (Single == null) {
            Single = new CtrlBuscarCliente(Frm);
        }
        return Single;
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
    
    public void Acceder(){
        C.sql="SELECT * FROM cliente WHERE DNI='"+Frm.txtDni.getText()+"'";
        
        if(Validar()){
            if(!C.VerificarConsulta(C.sql)){
                if (JOptionPane.showConfirmDialog(null, "NO ESTAS REGISTRADO, ¿DESEAS REGISTRARTE?", "CONSULTA", 0) == 0) {
                    C.ClienteNuevo(Frm);
                    FrmClienteNuevo.txtDNI.setText(Frm.txtDni.getText());
                    FrmClienteNuevo.txtDNI.setEditable(false);
                }else{
                    Limpiar();
                }
            }else{
                C.Reservas(Frm);
                FrmReservaCliente.lblCliente.setText(null);
                FrmReservaCliente.lblCliente.setText(C.DevolverDatoString("SELECT * FROM cliente WHERE DNI='"+Frm.txtDni.getText()+"'",2).toUpperCase());
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
