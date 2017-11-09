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
public class CtrlLoginPersonal implements ActionListener {

    private Controlador C = new Controlador();
    private FrmLoginPersonal Frm;
    private FrmPrincipal FrmP;

    public CtrlLoginPersonal(FrmLoginPersonal Frm) {
        this.Frm = Frm;

        Frm.btnAcceder.addActionListener(this);
        Frm.btnLimpiar.addActionListener(this);
        Frm.btnSalir.addActionListener(this);
    }

    public void Iniciar() {
        Frm.setTitle("INICIAR SESION");
        Frm.setLocationRelativeTo(null);
        Rol();
    }

    public void Rol() {
        C.sql = "SELECT * FROM Rol;";
        C.LlenarCombo(Frm.cbRol, C.sql, "<SELECCIONE>", 2);
    }

    public boolean Validar() {
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

    public void Limpiar() {
        Frm.txtUsu.setText(null);
        Frm.txtPsw.setText(null);
        Frm.cbRol.setSelectedIndex(0);
        Frm.cbRol.grabFocus();
    }

    public void Privilegios() {
        String Rol = C.DevolverDatoString("SELECT * FROM VtaSesion WHERE Usuario='" + Frm.txtUsu.getText() + "'", 3).toUpperCase();

        if (Rol.equalsIgnoreCase("ALMACENERO")) {
            FrmP.mArchivo.setVisible(true);
            FrmP.mBebidas.setVisible(true);
            FrmP.mCliente.setVisible(false);
            FrmP.mComidas.setVisible(false);
            FrmP.mIngredientes.setVisible(true);
            FrmP.mMedida.setVisible(false);
            FrmP.mMesas.setVisible(false);
            FrmP.mPedidos.setVisible(false);
            FrmP.mRestaurante.setVisible(false);
            FrmP.mUsuario.setVisible(false);
        } else {
            if (Rol.equalsIgnoreCase("MOZO")) {
                FrmP.mArchivo.setVisible(true);
                FrmP.mBebidas.setVisible(false);
                FrmP.mCliente.setVisible(false);
                FrmP.mComidas.setVisible(false);
                FrmP.mIngredientes.setVisible(false);
                FrmP.mMedida.setVisible(false);
                FrmP.mMesas.setVisible(false);
                FrmP.mPedidos.setVisible(true);
                FrmP.mRestaurante.setVisible(false);
                FrmP.mUsuario.setVisible(false);
            } else {
                if (Rol.equalsIgnoreCase("CAJERO")) {
                    FrmP.mArchivo.setVisible(true);
                    FrmP.mBebidas.setVisible(false);
                    FrmP.mCliente.setVisible(false);
                    FrmP.mComidas.setVisible(false);
                    FrmP.mIngredientes.setVisible(false);
                    FrmP.mMedida.setVisible(false);
                    FrmP.mMesas.setVisible(false);
                    FrmP.mPedidos.setVisible(false);
                    FrmP.mRestaurante.setVisible(false);
                    FrmP.mUsuario.setVisible(false);
                } else {
                    if (Rol.equalsIgnoreCase("ADMINISTRADOR")) {
                        FrmP.mArchivo.setVisible(true);
                        FrmP.mBebidas.setVisible(true);
                        FrmP.mCliente.setVisible(true);
                        FrmP.mComidas.setVisible(true);
                        FrmP.mIngredientes.setVisible(true);
                        FrmP.mMedida.setVisible(true);
                        FrmP.mMesas.setVisible(true);
                        FrmP.mPedidos.setVisible(true);
                        FrmP.mRestaurante.setVisible(true);
                        FrmP.mUsuario.setVisible(true);
                    }
                }
            }
        }
    }

    public void Acceso() {
        if (Validar()) {
            C.Acceso(Frm, new FrmPrincipal(), Frm.txtUsu.getText(), Frm.txtPsw.getText(), Frm.cbRol.getSelectedItem().toString());
            FrmP.lblnombre.setText(null);
            FrmP.lblnombre.setText(C.DevolverDatoString("SELECT * FROM VtaSesion WHERE Usuario='" + Frm.txtUsu.getText() + "'", 2).toUpperCase());
            Privilegios();
            Limpiar();
        } else {
            C.Mensaje("ACCEDO DENEGADO");
        }
    }

    public void BuscarCliente() {
        FrmBuscarCliente Frm2 = new FrmBuscarCliente();
        CtrlBuscarCliente Ctl = new CtrlBuscarCliente(Frm2);
        Ctl.Iniciar();
        Frm2.setVisible(true);
        Frm.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Frm.btnAcceder) {
            Acceso();
        }

        if (e.getSource() == Frm.btnLimpiar) {
            Limpiar();
        }

        if (e.getSource() == Frm.btnSalir) {
            BuscarCliente();
        }
    }
}
