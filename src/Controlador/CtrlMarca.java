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
public class CtrlMarca implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmMarca Frm;
    private static CtrlMarca Single;

    private CtrlMarca(FrmMarca Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TTipo.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TTipo.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TTipo.getValueAt(C.fila, 0).toString());
                        Frm.txtTipo.setText(Frm.TTipo.getValueAt(C.fila, 1).toString());
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

    public static synchronized CtrlMarca getInstance(FrmMarca Frm) {
        if (Single == null) {
            Single = new CtrlMarca(Frm);
        }
        return Single;
    }

    public void Iniciar() {
        Frm.TTipo.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "MARCA DE BEBIDA"});
        Frm.TTipo.getColumnModel().getColumn(1).setPreferredWidth(300);

        Tabla();
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtTipo.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA UNA MARCA DE BEBIDA");
            Frm.txtTipo.grabFocus();
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtTipo.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtTipo.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM marca WHERE idmarca LIKE '" + Frm.txtBuscar.getText()
                + "%' OR marcacol LIKE '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }

    public void Guardar() {
        String sql = "SELECT * FROM marca WHERE marcacol='" + Frm.txtTipo.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                String Cod = C.GeneraCodigo(Frm.txtTipo.getText().toUpperCase(), "marca", "idmarca");
                C.InsertaRegistro("INSERT INTO marca VALUES('" + Cod + "','"
                        + Frm.txtTipo.getText().toUpperCase() + "')");
                C.Mensaje("MARCA DE BEBIDA REGISTRADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("LA MARCA DE BEBIDA YA SE ENCUENTRA REGISTRADO");
        }
    }

    public void Editar() {
        String sql = "SELECT * FROM marca WHERE marcacol='" + Frm.txtTipo.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                C.InsertaRegistro("UPDATE marca SET marcacol='" + Frm.txtTipo.getText().toUpperCase()
                        + "' WHERE idmarca='" + Frm.txtCodigo.getText() + "'");
                C.Mensaje("MARCA DE BEBIDA ACTUALIZADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
        }
    }

    public void Eliminar() {
        C.fila = Frm.TTipo.getSelectedRow();
        if (Frm.TTipo.getSelectedRow() > -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                C.InsertaRegistro("DELETE FROM marca WHERE idmarca='" + Frm.TTipo.getValueAt(C.fila, 0).toString() + "'");
                C.Mensaje("MARCA DE BEBIDA ELIMINADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("SELECCIONA UNA MARCA DE BEBIDA PARA ELIMINAR");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Frm.btnguardar) {
            Guardar();
        }

        if (e.getSource() == Frm.btneditar) {
            Editar();
        }

        if (e.getSource() == Frm.btneliminar) {
            Eliminar();
        }

        if (e.getSource() == Frm.btnlimpiar) {
            Limpiar();
        }
    }
}
