/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import Controlador.*;
import Modelo.Controlador;
import PatronVistas.*;
import Vista.*;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javax.swing.JOptionPane;
import java.io.*;

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
                    String ruta = System.getProperties().getProperty("user.dir") + "/Complementos/Conexion.txt";
                    File archivo = new File(ruta);
                    String linea = null;
                    JUnique.acquireLock("krupf");
                    try {

                        int numLineas = 8;
                        int contador = 0;

                        String datos[] = new String[numLineas];

                        BufferedReader reader = new BufferedReader(new FileReader(archivo));
                        linea = reader.readLine();

                        while (linea != null && contador < numLineas) {
                            datos[contador] = linea;
                            linea = reader.readLine();
                            contador++;
                        }

                        Controlador.UsuServer = datos[1];
                        Controlador.PswServer = datos[3];
                        Controlador.DataBase = datos[5];
                        Controlador.HostServer = datos[7];

                        try {
                            FrmBuscarCliente Frm = PtBuscarCliente.getInstance();
                            CtrlBuscarCliente Ctl = CtrlBuscarCliente.getInstance(Frm);
                            Ctl.Iniciar();
                            Frm.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, String.valueOf(e));
                        }
                    } catch (FileNotFoundException ex) {
                        try {
                            AccesoServer Frm = PtAccesoServer.getInstance();
                            CtrlAccesoServer Ctl = CtrlAccesoServer.getInstance(Frm);
                            Ctl.Iniciar();
                            Frm.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, String.valueOf(e));
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR AL ABRIR\n");
                    }
                } catch (AlreadyLockedException ex) {
                    JOptionPane.showMessageDialog(null, "EL SISTEMA YA SE ESTA EJECUTANDO\n");
                    System.exit(0);
                }

            }
        });
    }
}
