/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.*;
import Modelo.Controlador;
import Vista.*;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Quito
 */
public class FrmPrincipal extends javax.swing.JFrame {
    Controlador C=new Controlador();

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        this.setUndecorated(true);
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
        mArchivo = new javax.swing.JMenu();
        jmSalir = new javax.swing.JMenuItem();
        mRestaurante = new javax.swing.JMenu();
        jmRestaurante = new javax.swing.JMenuItem();
        jmAlmacen = new javax.swing.JMenuItem();
        mMesas = new javax.swing.JMenu();
        jmMesas = new javax.swing.JMenuItem();
        jmEstadoMesa = new javax.swing.JMenuItem();
        jmUbicacion = new javax.swing.JMenuItem();
        mCliente = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mUsuario = new javax.swing.JMenu();
        jmRoles = new javax.swing.JMenuItem();
        jmEmpleado = new javax.swing.JMenuItem();
        jmUsuario = new javax.swing.JMenuItem();
        mComidas = new javax.swing.JMenu();
        jmOrigen = new javax.swing.JMenuItem();
        jmComida = new javax.swing.JMenuItem();
        mIngredientes = new javax.swing.JMenu();
        jmPresentacion = new javax.swing.JMenuItem();
        jmColor = new javax.swing.JMenuItem();
        jmTipoIngrediente = new javax.swing.JMenuItem();
        jmIngrediente = new javax.swing.JMenuItem();
        mBebidas = new javax.swing.JMenu();
        jmSabor = new javax.swing.JMenuItem();
        jmTipoBebida = new javax.swing.JMenuItem();
        jmMarca = new javax.swing.JMenuItem();
        jmBebida = new javax.swing.JMenuItem();
        mPedidos = new javax.swing.JMenu();
        jmEstadoPedido = new javax.swing.JMenuItem();
        jmPedido = new javax.swing.JMenuItem();
        jmConsumicion = new javax.swing.JMenuItem();
        mMedida = new javax.swing.JMenu();
        jmMedida = new javax.swing.JMenuItem();
        jmReportes = new javax.swing.JMenu();
        rptEmpleados = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("BIENVENIDO:");

        lblnombre.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(0, 0, 0));
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

        mArchivo.setText("ARCHIVO");
        mArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mArchivoActionPerformed(evt);
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
        mArchivo.add(jmSalir);

        jMenuBar1.add(mArchivo);

        mRestaurante.setText("RESTAURANTE");
        mRestaurante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jmRestaurante.setText("RESTAURANTE");
        jmRestaurante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmRestaurante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRestauranteActionPerformed(evt);
            }
        });
        mRestaurante.add(jmRestaurante);

        jmAlmacen.setText("ALMACEN");
        jmAlmacen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAlmacenActionPerformed(evt);
            }
        });
        mRestaurante.add(jmAlmacen);

        jMenuBar1.add(mRestaurante);

        mMesas.setText("MESAS");
        mMesas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jmMesas.setText("MESAS");
        jmMesas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMesasActionPerformed(evt);
            }
        });
        mMesas.add(jmMesas);

        jmEstadoMesa.setText("ESTADO");
        jmEstadoMesa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmEstadoMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEstadoMesaActionPerformed(evt);
            }
        });
        mMesas.add(jmEstadoMesa);

        jmUbicacion.setText("UBICACION");
        jmUbicacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUbicacionActionPerformed(evt);
            }
        });
        mMesas.add(jmUbicacion);

        jMenuBar1.add(mMesas);

        mCliente.setText("CLIENTE");
        mCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem1.setText("CLIENTE");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mCliente.add(jMenuItem1);

        jMenuBar1.add(mCliente);

        mUsuario.setText("USUARIO");
        mUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jmRoles.setText("ROLES");
        jmRoles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRolesActionPerformed(evt);
            }
        });
        mUsuario.add(jmRoles);

        jmEmpleado.setText("EMPLEADOS");
        jmEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEmpleadoActionPerformed(evt);
            }
        });
        mUsuario.add(jmEmpleado);

        jmUsuario.setText("USUARIO");
        jmUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUsuarioActionPerformed(evt);
            }
        });
        mUsuario.add(jmUsuario);

        jMenuBar1.add(mUsuario);

        mComidas.setText("COMIDAS");

        jmOrigen.setText("ORIGEN DE COMIDAS");
        jmOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmOrigenActionPerformed(evt);
            }
        });
        mComidas.add(jmOrigen);

        jmComida.setText("COMIDA");
        jmComida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmComidaActionPerformed(evt);
            }
        });
        mComidas.add(jmComida);

        jMenuBar1.add(mComidas);

        mIngredientes.setText("INGREDIENTES");

        jmPresentacion.setText("PRESENTACION");
        jmPresentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPresentacionActionPerformed(evt);
            }
        });
        mIngredientes.add(jmPresentacion);

        jmColor.setText("COLOR");
        jmColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmColorActionPerformed(evt);
            }
        });
        mIngredientes.add(jmColor);

        jmTipoIngrediente.setText("TIPO");
        jmTipoIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmTipoIngredienteActionPerformed(evt);
            }
        });
        mIngredientes.add(jmTipoIngrediente);

        jmIngrediente.setText("INGREDIENTES");
        jmIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmIngredienteActionPerformed(evt);
            }
        });
        mIngredientes.add(jmIngrediente);

        jMenuBar1.add(mIngredientes);

        mBebidas.setText("BEBIDAS");

        jmSabor.setText("SABOR");
        jmSabor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSaborActionPerformed(evt);
            }
        });
        mBebidas.add(jmSabor);

        jmTipoBebida.setText("TIPO");
        jmTipoBebida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmTipoBebidaActionPerformed(evt);
            }
        });
        mBebidas.add(jmTipoBebida);

        jmMarca.setText("MARCA");
        jmMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMarcaActionPerformed(evt);
            }
        });
        mBebidas.add(jmMarca);

        jmBebida.setText("BEBIDA");
        jmBebida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmBebidaActionPerformed(evt);
            }
        });
        mBebidas.add(jmBebida);

        jMenuBar1.add(mBebidas);

        mPedidos.setText("PEDIDOS");

        jmEstadoPedido.setText("ESTADO");
        jmEstadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEstadoPedidoActionPerformed(evt);
            }
        });
        mPedidos.add(jmEstadoPedido);

        jmPedido.setText("PEDIDO");
        jmPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPedidoActionPerformed(evt);
            }
        });
        mPedidos.add(jmPedido);

        jmConsumicion.setText("CONSUMICION");
        jmConsumicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmConsumicionActionPerformed(evt);
            }
        });
        mPedidos.add(jmConsumicion);

        jMenuBar1.add(mPedidos);

        mMedida.setText("MEDIDA");

        jmMedida.setText("UNIDADES MEDIDA");
        jmMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMedidaActionPerformed(evt);
            }
        });
        mMedida.add(jmMedida);

        jMenuBar1.add(mMedida);

        jmReportes.setText("REPORTES");

        rptEmpleados.setText("EMPLEADOS");
        rptEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptEmpleadosActionPerformed(evt);
            }
        });
        jmReportes.add(rptEmpleados);

        jMenuBar1.add(jmReportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mArchivoActionPerformed
        
    }//GEN-LAST:event_mArchivoActionPerformed

    private void jmSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSalirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "¿QUIERES CERRAR EL SISTEMA?", "SALIR", 0) == 0) {
            FrmLoginPersonal Frm2 = new FrmLoginPersonal();
            CtrlLoginPersonal Ctl = new CtrlLoginPersonal(Frm2);
            Ctl.Iniciar();
            Frm2.setVisible(true);
            this.setVisible(false);
        }
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

    private void jmEstadoMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEstadoMesaActionPerformed
        FrmEstadoMesas Frm=new FrmEstadoMesas();
        CtrlEstadoMesas Ctl= new CtrlEstadoMesas(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmEstadoMesaActionPerformed

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

    private void jmEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEmpleadoActionPerformed
        FrmEmpleado Frm=new FrmEmpleado();
        CtrlEmpleado Ctl=new CtrlEmpleado(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmEmpleadoActionPerformed

    private void jmOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmOrigenActionPerformed
        FrmOrigenComida Frm=new FrmOrigenComida();
        CtrlOrigenComida Ctl=new CtrlOrigenComida(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmOrigenActionPerformed
        
    private void jmComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmComidaActionPerformed
        FrmComida Frm=new FrmComida();
        CtrlComida Ctl=new CtrlComida(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmComidaActionPerformed

    private void jmPresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPresentacionActionPerformed
        FrmPresentacion Frm=new FrmPresentacion();
        CtrlPresentacion Ctl=new CtrlPresentacion(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmPresentacionActionPerformed

    private void jmColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmColorActionPerformed
        FrmColor Frm=new FrmColor();
        CtrlColor Ctl=new CtrlColor(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmColorActionPerformed

    private void jmTipoIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmTipoIngredienteActionPerformed
        FrmTipoIngrediente Frm=new FrmTipoIngrediente();
        CtrlTipoIngrediente Ctl=new CtrlTipoIngrediente(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmTipoIngredienteActionPerformed

    private void jmIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmIngredienteActionPerformed
        FrmIngredientes Frm=new FrmIngredientes();
        CtrlIngredientes Ctl=new CtrlIngredientes(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmIngredienteActionPerformed

    private void jmSaborActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSaborActionPerformed
        FrmSabor Frm=new FrmSabor();
        CtrlSabor Ctl=new CtrlSabor(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmSaborActionPerformed

    private void jmTipoBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmTipoBebidaActionPerformed
        FrmTipoBebida Frm=new FrmTipoBebida();
        CtrlTipoBebida Ctl=new CtrlTipoBebida(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmTipoBebidaActionPerformed

    private void jmMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMarcaActionPerformed
        FrmMarca Frm=new FrmMarca();
        CtrlMarca Ctl=new CtrlMarca(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmMarcaActionPerformed

    private void jmBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmBebidaActionPerformed
        FrmBebida Frm=new FrmBebida();
        CtrlBebida Ctl=new CtrlBebida(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmBebidaActionPerformed

    private void jmEstadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEstadoPedidoActionPerformed
        FrmEstadoPedido Frm=new FrmEstadoPedido();
        CtrlEstadoPedido Ctl=new CtrlEstadoPedido(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmEstadoPedidoActionPerformed

    private void jmPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPedidoActionPerformed
        /*FrmPedido Frm=new FrmPedido();
        CtrlPedido Ctl=new CtrlPedido(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();*/
    }//GEN-LAST:event_jmPedidoActionPerformed

    private void jmConsumicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmConsumicionActionPerformed
        /*FrmConsumicion Frm=new FrmConsumicion();
        CtrlConsumicion Ctl=new CtrlConsumicion(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();*/
    }//GEN-LAST:event_jmConsumicionActionPerformed

    private void jmMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMedidaActionPerformed
        FrmUnidadMedida Frm=new FrmUnidadMedida();
        CtrlUnidadMedida Ctl=new CtrlUnidadMedida(Frm);
        jdpPrincipal.add(Frm);
        Dimension Tam = jdpPrincipal.getSize();
        Dimension FrameSize = Frm.getSize();
        Frm.setLocation((Tam.width - FrameSize.width)/2, (Tam.height- FrameSize.height)/2);
        Ctl.Iniciar();
        Frm.show();
    }//GEN-LAST:event_jmMedidaActionPerformed

    private void rptEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptEmpleadosActionPerformed
        C.impri.Imprimir("RptEmpleado");
    }//GEN-LAST:event_rptEmpleadosActionPerformed

    
    
    
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
    public javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenuItem jmAlmacen;
    private javax.swing.JMenuItem jmBebida;
    private javax.swing.JMenuItem jmColor;
    private javax.swing.JMenuItem jmComida;
    private javax.swing.JMenuItem jmConsumicion;
    private javax.swing.JMenuItem jmEmpleado;
    private javax.swing.JMenuItem jmEstadoMesa;
    private javax.swing.JMenuItem jmEstadoPedido;
    private javax.swing.JMenuItem jmIngrediente;
    private javax.swing.JMenuItem jmMarca;
    private javax.swing.JMenuItem jmMedida;
    private javax.swing.JMenuItem jmMesas;
    private javax.swing.JMenuItem jmOrigen;
    private javax.swing.JMenuItem jmPedido;
    private javax.swing.JMenuItem jmPresentacion;
    public static javax.swing.JMenu jmReportes;
    private javax.swing.JMenuItem jmRestaurante;
    private javax.swing.JMenuItem jmRoles;
    private javax.swing.JMenuItem jmSabor;
    public javax.swing.JMenuItem jmSalir;
    private javax.swing.JMenuItem jmTipoBebida;
    private javax.swing.JMenuItem jmTipoIngrediente;
    private javax.swing.JMenuItem jmUbicacion;
    private javax.swing.JMenuItem jmUsuario;
    public static javax.swing.JLabel lblnombre;
    public static javax.swing.JMenu mArchivo;
    public static javax.swing.JMenu mBebidas;
    public static javax.swing.JMenu mCliente;
    public static javax.swing.JMenu mComidas;
    public static javax.swing.JMenu mIngredientes;
    public static javax.swing.JMenu mMedida;
    public static javax.swing.JMenu mMesas;
    public static javax.swing.JMenu mPedidos;
    public static javax.swing.JMenu mRestaurante;
    public static javax.swing.JMenu mUsuario;
    private javax.swing.JMenuItem rptEmpleados;
    // End of variables declaration//GEN-END:variables
}
