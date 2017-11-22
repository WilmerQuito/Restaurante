/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.AccesoServer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlAccesoServer implements ActionListener {

    private static AccesoServer Frm;
    private static CtrlAccesoServer Single;
    public boolean flag;

    private CtrlAccesoServer(AccesoServer Frm) {
        this.Frm = Frm;

        Frm.btnAcceder.addActionListener(this);
        Frm.btnCancelar.addActionListener(this);
    }

    public static synchronized CtrlAccesoServer getInstance(AccesoServer Frm){
        if(Single == null){
            Single = new CtrlAccesoServer(Frm);
        }
        return Single;
    }
    
    public void Iniciar() {
        Frm.setLocationRelativeTo(null);
    }

    public boolean Validar() {
        flag = true;
        if (Frm.txtUsu.getText().trim().length() == 0) {
            flag = false;
            JOptionPane.showMessageDialog(null,"INGRESA TU USUARIO");
            Frm.txtUsu.grabFocus();
        } else {
            if (Frm.txtPsw.getText().trim().length() == 0) {
                flag = false;
                JOptionPane.showMessageDialog(null,"INGRESA UN CONSTRASEÑA");
                Frm.txtPsw.grabFocus();
            }
        }
        return flag;
    }

    public void Limpiar() {
        Frm.txtUsu.setText(null);
        Frm.txtPsw.setText(null);
        Frm.txtUsu.grabFocus();
    }

    public void Acceso() {
        if (Validar()) {
            Controlador.UsuServer=Frm.txtUsu.getText().toString();
            Controlador.PswServer=Frm.txtPsw.getText().toString();
            Controlador C = Controlador.getInstance();
            C.Mensaje("CONEXION EXITOSA");
            C.InsertaRegistro("INSERT INTO Server VALUES('1','"+Frm.txtUsu.getText().toString()+"','"+Frm.txtPsw.getText().toString()+"')");
            C.BuscarCliente(Frm);
            Limpiar();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Frm.btnAcceder) {
            Acceso();
        }

        if (e.getSource() == Frm.btnCancelar) {
            Limpiar();
        }   
    }
}
