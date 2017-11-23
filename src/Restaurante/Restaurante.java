/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import Controlador.*;
import PatronVistas.*;
import Vista.*;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Quito
 */
public class Restaurante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JUnique.acquireLock("krupf");
                    try {
                        AccesoServer Frm = PtAccesoServer.getInstance();
                        CtrlAccesoServer Ctl = CtrlAccesoServer.getInstance(Frm);
                        Ctl.Iniciar();
                        Frm.setVisible(true);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, String.valueOf(e));
                    }
                } catch (AlreadyLockedException ex) {
                    JOptionPane.showMessageDialog(null,"EL SISTEMA YA SE ESTA EJECUTANDO");
                    System.exit(0);
                }

            }
        });
    }
}
