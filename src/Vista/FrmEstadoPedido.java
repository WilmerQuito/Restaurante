/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author Wilmer Quito
 */
public class FrmEstadoPedido extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmEstadoPedido
     */
    public FrmEstadoPedido() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btneliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btneditar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnlimpiar = new javax.swing.JButton();
        txtEstadoPedido = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TEstadoPedido = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("ESTADO DE MESAS");

        jLabel3.setText("ESTADO:");

        btneliminar.setText("ELIMINAR");
        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setText("CODIGO:");

        txtCodigo.setEditable(false);
        txtCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btneditar.setText("EDITAR");
        btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ESTADO DE MESAS");

        btnlimpiar.setText("LIMPIAR");
        btnlimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtEstadoPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtEstadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoPedidoActionPerformed(evt);
            }
        });
        txtEstadoPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoPedidoKeyTyped(evt);
            }
        });

        btnguardar.setText("GUARDAR");
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSCAR:"));
        jPanel4.setToolTipText("");

        txtBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        TEstadoPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TEstadoPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TEstadoPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TEstadoPedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TEstadoPedido);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtBuscar))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstadoPedido)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnguardar)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar)
                        .addGap(18, 18, 18)
                        .addComponent(btneliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnlimpiar))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar)
                    .addComponent(btneditar)
                    .addComponent(btneliminar)
                    .addComponent(btnlimpiar))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEstadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoPedidoActionPerformed

    private void txtEstadoPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoPedidoKeyTyped
        if(txtEstadoPedido.getText().length()>=45){
            evt.consume();
        }
        if(Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEstadoPedidoKeyTyped

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void TEstadoPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TEstadoPedidoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TEstadoPedidoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TEstadoPedido;
    public javax.swing.JButton btneditar;
    public javax.swing.JButton btneliminar;
    public javax.swing.JButton btnguardar;
    public javax.swing.JButton btnlimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtCodigo;
    public javax.swing.JTextField txtEstadoPedido;
    // End of variables declaration//GEN-END:variables
}