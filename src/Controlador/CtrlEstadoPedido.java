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
public class CtrlEstadoPedido implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmEstadoPedido Frm;
    private static CtrlEstadoPedido Single;

    private CtrlEstadoPedido(FrmEstadoPedido Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TEstadoPedido.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TEstadoPedido.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TEstadoPedido.getValueAt(C.fila, 0).toString());
                        Frm.txtEstadoPedido.setText(Frm.TEstadoPedido.getValueAt(C.fila, 1).toString());
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

    public static synchronized CtrlEstadoPedido getInstance(FrmEstadoPedido Frm){
        if(Single == null){
            Single = new CtrlEstadoPedido(Frm);
        }
        return Single;
    }
    
    public void Iniciar() {
        Frm.TEstadoPedido.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "ESTADO DE PEDIDO"});
        Frm.TEstadoPedido.getColumnModel().getColumn(1).setPreferredWidth(300);

        Tabla();
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtEstadoPedido.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA UN ESTADO DE PEDIDO");
            Frm.txtEstadoPedido.grabFocus();
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtEstadoPedido.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtEstadoPedido.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM estadopedido WHERE idEstadoPedido LIKE '" + Frm.txtBuscar.getText()
                + "%' or Estado like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }

    public void Guardar() {
        String sql = "SELECT * FROM estadopedido WHERE Estado='" + Frm.txtEstadoPedido.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                String Cod = C.GeneraCodigo(Frm.txtEstadoPedido.getText().toUpperCase(), "Color", "idColor");
                C.InsertaRegistro("INSERT INTO estadopedido VALUES('" + Cod + "','"
                        + Frm.txtEstadoPedido.getText().toUpperCase() + "')");
                C.Mensaje("ESTADO REGISTRADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("EL ESTADO YA SE ENCUENTRA REGISTRADO");
        }
    }

    public void Editar() {
        String sql = "SELECT * FROM estadopedido WHERE Estado='" + Frm.txtEstadoPedido.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                C.InsertaRegistro("UPDATE estadopedido SET Estado='" + Frm.txtEstadoPedido.getText().toUpperCase()
                        + "' WHERE idEstadoPedido='" + Frm.txtCodigo.getText() + "'");
                C.Mensaje("ESTADO ACTUALIZADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
        }
    }

    public void Eliminar() {
        C.fila = Frm.TEstadoPedido.getSelectedRow();
        if (Frm.TEstadoPedido.getSelectedRow() > -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                C.InsertaRegistro("DELETE FROM estadopedido WHERE idEstadoPedido='" + Frm.TEstadoPedido.getValueAt(C.fila, 0).toString() + "'");
                C.Mensaje("ESTADO ELIMINADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("SELECCIONA UN ESTADO PARA ELIMINAR");
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
