/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlAlmacen implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmAlmacen Frm;
    private static CtrlAlmacen Single;

    private CtrlAlmacen(FrmAlmacen Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TAlmacen.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TAlmacen.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TAlmacen.getValueAt(C.fila, 0).toString());
                        C.MostrarenCombo(Frm.cboRest, Frm.TAlmacen.getValueAt(C.fila, 1).toString());
                        Frm.txtDescri.setText(Frm.TAlmacen.getValueAt(C.fila, 2).toString());
                    }
                }
            }
        });

        Frm.txtBuscar.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                Tabla();
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });

    }

    public static synchronized CtrlAlmacen getInstance(FrmAlmacen Frm) {
        if (Single == null) {
            Single = new CtrlAlmacen(Frm);
        }
        return Single;
    }

    public void Iniciar() {
        Frm.TAlmacen.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "RESTAURANTE", "DESCRIPCION"});
        Frm.TAlmacen.getColumnModel().getColumn(1).setPreferredWidth(300);
        Frm.TAlmacen.getColumnModel().getColumn(2).setPreferredWidth(300);

        Tabla();
        Rol();
    }

    public void Rol() {
        C.sql = "SELECT * FROM restaurante";
        C.LlenarCombo(Frm.cboRest, C.sql, "<SELECCIONE>", 2);
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtDescri.getText().trim().length() == 0) {
            C.flag = false;
            C.Mensaje("INGRESA DESCRIPCION DE ALMACEN");
            Frm.txtDescri.grabFocus();
        } else {
            if (Frm.cboRest.getSelectedIndex() == 0) {
                C.flag = false;
                C.Mensaje("SELECCIONA RESTAURANTE");
                Frm.cboRest.grabFocus();
            } else {

            }
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtDescri.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.cboRest.setSelectedIndex(0);
        Frm.cboRest.grabFocus();
        Frm.TAlmacen.clearSelection();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM vtaalmacen WHERE cod_almacen LIKE '" + Frm.txtBuscar.getText()
                + "%' or nombre like '" + Frm.txtBuscar.getText()
                + "%' or descripcion like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String Res = C.DatoCombo("SELECT * FROM restaurante WHERE nombre='" + Frm.cboRest.getSelectedItem().toString() + "'", 1);
        String sql = "SELECT * FROM almacen WHERE descripcion='" + Frm.txtDescri.getText() + "' AND cod_restaurante='" + Res + "';";

        if (e.getSource() == Frm.btnguardar) {
            if (!C.VerificarConsulta(sql)) {
                if (Validar()) {
                    String Cod = C.GeneraCodigo("A", "almacen", "cod_almacen");
                    C.InsertaRegistro("INSERT INTO almacen VALUES('" + Cod + "','" + Frm.txtDescri.getText().toUpperCase() + "','" + Res + "')");
                    C.Mensaje("ALMACEN REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("EL ALMACEN YA SE ENCUENTRA REGISTRADO");
            }
        }

        if (e.getSource() == Frm.btneditar) {
            if (!C.VerificarConsulta(sql)) {
                if (Validar()) {
                    C.InsertaRegistro("UPDATE almacen SET descripcion='" + Frm.txtDescri.getText().toUpperCase()
                            + "', cod_restaurante='" + Res
                            + "' WHERE cod_almacen='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("ALMACEN ACTUALIZADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }

        if (e.getSource() == Frm.btneliminar) {
            C.fila = Frm.TAlmacen.getSelectedRow();
            if (Frm.TAlmacen.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM almacen WHERE cod_almacen='" + Frm.TAlmacen.getValueAt(C.fila, 0).toString() + "'");
                    C.Mensaje("ALMACEN ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("SELECCIONA ALMACEN PARA ELIMINAR");
            }
        }

        if (e.getSource() == Frm.btnlimpiar) {
            Limpiar();
        }
    }
}
