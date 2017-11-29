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
public class CtrlOrigenComida implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmOrigenComida Frm;
    private static CtrlOrigenComida Single;

    private CtrlOrigenComida(FrmOrigenComida Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TOrigen.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TOrigen.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TOrigen.getValueAt(C.fila, 0).toString());
                        Frm.txtOrigen.setText(Frm.TOrigen.getValueAt(C.fila, 1).toString());
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

    public static synchronized CtrlOrigenComida getInstance(FrmOrigenComida Frm) {
        if (Single == null) {
            Single = new CtrlOrigenComida(Frm);
        }
        return Single;
    }

    public void Iniciar() {

        Frm.TOrigen.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "ORIGEN DE COMIDA"});
        Frm.TOrigen.getColumnModel().getColumn(1).setPreferredWidth(300);

        Tabla();
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtOrigen.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA EL ORIGEN DE LA COMIDA");
            Frm.txtOrigen.grabFocus();
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtOrigen.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtOrigen.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM origen WHERE idorigen LIKE '" + Frm.txtBuscar.getText()
                + "%' or origencol like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Frm.btnguardar) {
            String sql = "SELECT * FROM origen WHERE origencol='" + Frm.txtOrigen.getText() + "';";
            if (!C.VerificarConsulta(sql)) {
                if (Validar()) {
                    String Cod = C.GeneraCodigo(Frm.txtOrigen.getText().toUpperCase(), "origen", "idorigen");
                    C.InsertaRegistro("INSERT INTO origen VALUES('" + Cod + "','"
                            + Frm.txtOrigen.getText().toUpperCase() + "')");
                    C.Mensaje("ORIGEN REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("EL ORIGEN YA SE ENCUENTRA REGISTRADO");
            }
        }

        if (e.getSource() == Frm.btneditar) {
            String sql = "SELECT * FROM origen WHERE origencol='" + Frm.txtOrigen.getText() + "';";
            if (!C.VerificarConsulta(sql)) {
                if (Validar()) {
                    C.InsertaRegistro("UPDATE origen SET origencol='" + Frm.txtOrigen.getText().toUpperCase()
                            + "' WHERE idorigen='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("ORIGEN ACTUALIZADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }

        if (e.getSource() == Frm.btneliminar) {
            C.fila = Frm.TOrigen.getSelectedRow();
            if (Frm.TOrigen.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM origen WHERE idorigen='" + Frm.TOrigen.getValueAt(C.fila, 0).toString() + "'");
                    C.Mensaje("ORIGEN ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("SELECCIONA UN ORIGEN PARA ELIMINAR");
            }
        }

        if (e.getSource() == Frm.btnlimpiar) {
            Limpiar();
        }
    }

}
