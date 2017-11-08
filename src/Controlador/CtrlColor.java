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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlColor implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    private Controlador C = new Controlador();
    private FrmColor Frm;

    public CtrlColor(FrmColor Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TColor.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TColor.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TColor.getValueAt(C.fila, 0).toString());
                        Frm.txtColor.setText(Frm.TColor.getValueAt(C.fila, 1).toString());
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

    public void Iniciar() {
        Frm.TColor.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "COLOR DE INGREDIENTE"});
        Frm.TColor.getColumnModel().getColumn(1).setPreferredWidth(300);

        Tabla();
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtColor.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA UN COLOR DE INGREDIENTE");
            Frm.txtColor.grabFocus();
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtColor.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtColor.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM Color WHERE idcolor LIKE '" + Frm.txtBuscar.getText()
                + "%' or color like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }

    public void Guardar() {
        String sql = "SELECT * FROM color WHERE color='" + Frm.txtColor.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                String Cod = C.GeneraCodigo(Frm.txtColor.getText().toUpperCase(), "Color", "idColor");
                C.InsertaRegistro("INSERT INTO color VALUES('" + Cod + "','"
                        + Frm.txtColor.getText().toUpperCase() + "')");
                C.Mensaje("COLOR REGISTRADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("EL COLOR YA SE ENCUENTRA REGISTRADO");
        }
    }

    public void Editar() {
        String sql = "SELECT * FROM Color WHERE color='" + Frm.txtColor.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                C.InsertaRegistro("UPDATE color SET color='" + Frm.txtColor.getText().toUpperCase()
                        + "' WHERE idColor='" + Frm.txtCodigo.getText() + "'");
                C.Mensaje("COLOR ACTUALIZADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
        }
    }

    public void Eliminar() {
        C.fila = Frm.TColor.getSelectedRow();
        if (Frm.TColor.getSelectedRow() > -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                C.InsertaRegistro("DELETE FROM Color WHERE idColor='" + Frm.TColor.getValueAt(C.fila, 0).toString() + "'");
                C.Mensaje("COLOR ELIMINADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("SELECCIONA UN COLOR PARA ELIMINAR");
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
