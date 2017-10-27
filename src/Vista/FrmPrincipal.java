/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.*;
import Vista.*;
import java.awt.Dimension;

/**
 *
 * @author Wilmer Quito
 */
public class FrmPrincipal extends javax.swing.JFrame {
    private FrmLogin Frm;

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("MENU PRINCIPAL");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpPrincipal = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmSalir = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmRestaurante = new javax.swing.JMenuItem();
        jmAlmacen = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmMesas = new javax.swing.JMenuItem();
        jmEstado = new javax.swing.JMenuItem();
        jmUbicacion = new javax.swing.JMenuItem();
        jmCliente = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmRoles = new javax.swing.JMenuItem();
        jmUsuario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BIENVENIDO:");

        lblnombre.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(255, 255, 255));
        lblnombre.setText("BBBBBBBBBBBBBBBBBBBBBBBBB");

        jdpPrincipal.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpPrincipal.setLayer(lblnombre, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpPrincipalLayout = new javax.swing.GroupLayout(jdpPrincipal);
        jdpPrincipal.setLayout(jdpPrincipalLayout);
        jdpPrincipalLayout.setHorizontalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpPrincipalLayout.createSequentialGroup()
                .addContainerGap(933, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblnombre)
                .addContainerGap())
        );
        jdpPrincipalLayout.setVerticalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpPrincipalLayout.createSequentialGroup()
                .addContainerGap(694, Short.MAX_VALUE)
                .addGroup(jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblnombre))
                .addContainerGap())
        );

        jMenu1.setText("ARCHIVO");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jmSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jmSalir.setText("SALIR");
        jmSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSalirActionPerformed(evt);
            }
        });
        jMenu1.add(jmSalir);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("RESTAURANTE");
        jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jmRestaurante.setText("RESTAURANTE");
        jmRestaurante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmRestaurante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRestauranteActionPerformed(evt);
            }
        });
        jMenu4.add(jmRestaurante);

        jmAlmacen.setText("ALMACEN");
        jmAlmacen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAlmacenActionPerformed(evt);
            }
        });
        jMenu4.add(jmAlmacen);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("MESAS");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jmMesas.setText("MESAS");
        jmMesas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMesasActionPerformed(evt);
            }
        });
        jMenu3.add(jmMesas);

        jmEstado.setText("ESTADO");
        jmEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEstadoActionPerformed(evt);
            }
        });
        jMenu3.add(jmEstado);

        jmUbicacion.setText("UBICACION");
        jmUbicacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUbicacionActionPerformed(evt);
            }
        });
        jMenu3.add(jmUbicacion);

        jMenuBar1.add(jMenu3);

        jmCliente.setText("CLIENTE");
        jmCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem1.setText("CLIENTE");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmCliente.add(jMenuItem1);

        jMenuBar1.add(jmCliente);

        jMenu2.setText("USUARIO");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jmRoles.setText("ROLES");
        jmRoles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRolesActionPerformed(evt);
            }
        });
        jMenu2.add(jmRoles);

        jmUsuario.setText("USUARIO");
        jmUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUsuarioActionPerformed(evt);
            }
        });
        jMenu2.add(jmUsuario);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jmSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmSalirActionPerformed

    private void jmRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRolesActionPerformed
        FrmRol Frm=new FrmRol();
        CtrlRol Ctl= new CtrlRol(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmRolesActionPerformed

    private void jmUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUsuarioActionPerformed
        FrmUsuario Frm=new FrmUsuario();
        CtrlUsuario Ctl= new CtrlUsuario(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmUsuarioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrmCliente Frm=new FrmCliente();
        CtrlCliente Ctl= new CtrlCliente(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmRestauranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRestauranteActionPerformed
        FrmRestaurante Frm=new FrmRestaurante();
        CtrlRestaurante Ctl= new CtrlRestaurante(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmRestauranteActionPerformed

    private void jmAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlmacenActionPerformed
        /*FrmAlmacen Frm=new FrmAlmacen();
        CtrlAlmacen Ctl= new CtrlAlmacen(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();*/
    }//GEN-LAST:event_jmAlmacenActionPerformed

    private void jmMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMesasActionPerformed
        FrmMesa Frm=new FrmMesa();
        CtrlMesa Ctl= new CtrlMesa(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmMesasActionPerformed

    private void jmEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEstadoActionPerformed
        FrmEstado Frm=new FrmEstado();
        CtrlEstado Ctl= new CtrlEstado(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmEstadoActionPerformed

    private void jmUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUbicacionActionPerformed
        FrmUbicacion Frm=new FrmUbicacion();
        CtrlUbicacion Ctl= new CtrlUbicacion(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmUbicacionActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenuItem jmAlmacen;
    private javax.swing.JMenu jmCliente;
    private javax.swing.JMenuItem jmEstado;
    private javax.swing.JMenuItem jmMesas;
    private javax.swing.JMenuItem jmRestaurante;
    private javax.swing.JMenuItem jmRoles;
    private javax.swing.JMenuItem jmSalir;
    private javax.swing.JMenuItem jmUbicacion;
    private javax.swing.JMenuItem jmUsuario;
    public static javax.swing.JLabel lblnombre;
    // End of variables declaration//GEN-END:variables
}
