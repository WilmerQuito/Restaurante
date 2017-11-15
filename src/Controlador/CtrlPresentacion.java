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
public class CtrlPresentacion implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmPresentacion Frm;
    private static CtrlPresentacion Single;

    private CtrlPresentacion(FrmPresentacion Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TPresentacion.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TPresentacion.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TPresentacion.getValueAt(C.fila, 0).toString());
                        Frm.txtPresentacion.setText(Frm.TPresentacion.getValueAt(C.fila, 1).toString());
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

    public static synchronized CtrlPresentacion getInstance(FrmPresentacion Frm){
        if(Single == null){
            Single = new CtrlPresentacion(Frm);
        }
        return Single;
    }
    
    public void Iniciar() {
        Frm.TPresentacion.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "PRESENTACION DEL INGREDIENTE"});
        Frm.TPresentacion.getColumnModel().getColumn(1).setPreferredWidth(300);

        Tabla();
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtPresentacion.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA UNA PRESENTACION DEL INGREDIENTE");
            Frm.txtPresentacion.grabFocus();
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtPresentacion.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtPresentacion.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM Presentacion WHERE idpresentacion LIKE '" + Frm.txtBuscar.getText()
                + "%' or presentacion like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }

    public void Guardar() {
        String sql = "SELECT * FROM presentacion WHERE presentacion='" + Frm.txtPresentacion.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                String Cod = C.GeneraCodigo(Frm.txtPresentacion.getText().toUpperCase(), "Presentacion", "idPresentacion");
                C.InsertaRegistro("INSERT INTO Presentacion VALUES('" + Cod + "','"
                        + Frm.txtPresentacion.getText().toUpperCase() + "')");
                C.Mensaje("PRESENTACION REGISTRADA");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("LA PRESENTACION YA SE ENCUENTRA REGISTRADA");
        }
    }

    public void Editar() {
        String sql = "SELECT * FROM presentacion WHERE presentacion='" + Frm.txtPresentacion.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                C.InsertaRegistro("UPDATE Presentacion SET Presentacion='" + Frm.txtPresentacion.getText().toUpperCase()
                        + "' WHERE idPresentacion='" + Frm.txtCodigo.getText() + "'");
                C.Mensaje("PRESENTACION ACTUALIZADA");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
        }
    }

    public void Eliminar() {
        C.fila = Frm.TPresentacion.getSelectedRow();
        if (Frm.TPresentacion.getSelectedRow() > -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                C.InsertaRegistro("DELETE FROM Presentacion WHERE idPresentacion='" + Frm.TPresentacion.getValueAt(C.fila, 0).toString() + "'");
                C.Mensaje("PRESENTACION ELIMINADA");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("SELECCIONA UNA PRESENTACION PARA ELIMINAR");
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
