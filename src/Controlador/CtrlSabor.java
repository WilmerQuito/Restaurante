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
public class CtrlSabor implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmSabor Frm;
    private static CtrlSabor Single;

    private CtrlSabor(FrmSabor Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TSabor.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TSabor.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TSabor.getValueAt(C.fila, 0).toString());
                        Frm.txtSabor.setText(Frm.TSabor.getValueAt(C.fila, 1).toString());
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

    public static synchronized CtrlSabor getInstance(FrmSabor Frm) {
        if (Single == null) {
            Single = new CtrlSabor(Frm);
        }
        return Single;
    }

    public void Iniciar() {
        Frm.TSabor.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "SABOR DE BEBIDA"});
        Frm.TSabor.getColumnModel().getColumn(1).setPreferredWidth(300);

        Tabla();
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtSabor.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA UN SABOR DE BEBIDA");
            Frm.txtSabor.grabFocus();
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtSabor.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtSabor.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM sabor WHERE idsabor LIKE '" + Frm.txtBuscar.getText()
                + "%' or saborcol like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }

    public void Guardar() {
        String sql = "SELECT * FROM sabor WHERE saborcol='" + Frm.txtSabor.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                String Cod = C.GeneraCodigo(Frm.txtSabor.getText().toUpperCase(), "sabor", "idsabor");
                C.InsertaRegistro("INSERT INTO sabor VALUES('" + Cod + "','"
                        + Frm.txtSabor.getText().toUpperCase() + "')");
                C.Mensaje("SABOR REGISTRADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("EL SABOR YA SE ENCUENTRA REGISTRADO");
        }
    }

    public void Editar() {
        String sql = "SELECT * FROM sabor WHERE saborcol='" + Frm.txtSabor.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                C.InsertaRegistro("UPDATE sabor SET saborcol='" + Frm.txtSabor.getText().toUpperCase()
                        + "' WHERE idsabor='" + Frm.txtCodigo.getText() + "'");
                C.Mensaje("SABOR ACTUALIZADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
        }
    }

    public void Eliminar() {
        C.fila = Frm.TSabor.getSelectedRow();
        if (Frm.TSabor.getSelectedRow() > -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                C.InsertaRegistro("DELETE FROM sabor WHERE idsabor='" + Frm.TSabor.getValueAt(C.fila, 0).toString() + "'");
                C.Mensaje("SABOR ELIMINADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("SELECCIONA UN SABOR PARA ELIMINAR");
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
