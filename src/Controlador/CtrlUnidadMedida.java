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
public class CtrlUnidadMedida implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmUnidadMedida Frm;
    private static CtrlUnidadMedida Single;

    private CtrlUnidadMedida(FrmUnidadMedida Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TUnidadMedida.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TUnidadMedida.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TUnidadMedida.getValueAt(C.fila, 0).toString());
                        Frm.txtUnidadMedida.setText(Frm.TUnidadMedida.getValueAt(C.fila, 1).toString());
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

    public static synchronized CtrlUnidadMedida getInstance(FrmUnidadMedida Frm){
        if(Single == null){
            Single = new CtrlUnidadMedida(Frm);
        }
        return Single;
    }
    
    public void Iniciar() {
        Frm.TUnidadMedida.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "UNIDAD DE MEDIDA"});
        Frm.TUnidadMedida.getColumnModel().getColumn(1).setPreferredWidth(300);

        Tabla();
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtUnidadMedida.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA UNA UNIDAD DE MEDIDA");
            Frm.txtUnidadMedida.grabFocus();
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtUnidadMedida.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtUnidadMedida.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM unidades_medida WHERE idUnidades_Medida LIKE '" + Frm.txtBuscar.getText()
                + "%' or Unidades_Medidacol like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 2);
    }

    public void Guardar() {
        String sql = "SELECT * FROM Unidades_Medida WHERE Unidades_Medidacol='" + Frm.txtUnidadMedida.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                String Cod = C.GeneraCodigo(Frm.txtUnidadMedida.getText().toUpperCase(), "Unidades_Medida", "idUnidades_Medida");
                C.InsertaRegistro("INSERT INTO Unidades_Medida VALUES('" + Cod + "','"
                        + Frm.txtUnidadMedida.getText().toUpperCase() + "')");
                C.Mensaje("UNIDAD DE MEDIDA REGISTRADA");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("LA UNIDAD DE MEDIDA YA SE ENCUENTRA REGISTRADA");
        }
    }

    public void Editar() {
        String sql = "SELECT * FROM Unidades_Medida WHERE Unidades_Medidacol='" + Frm.txtUnidadMedida.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                C.InsertaRegistro("UPDATE Unidades_Medida SET Unidades_Medidacol='" + Frm.txtUnidadMedida.getText().toUpperCase()
                        + "' WHERE idUnidades_Medida='" + Frm.txtCodigo.getText() + "'");
                C.Mensaje("UNIDAD DE MEDIDA ACTUALIZADA");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
        }
    }

    public void Eliminar() {
        C.fila = Frm.TUnidadMedida.getSelectedRow();
        if (Frm.TUnidadMedida.getSelectedRow() > -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                C.InsertaRegistro("DELETE FROM Unidades_Medida WHERE idUnidades_Medida='" + Frm.TUnidadMedida.getValueAt(C.fila, 0).toString() + "'");
                C.Mensaje("UNIDAD DE MEDIDA ELIMINADA");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("SELECCIONA UNA UNIDAD DE MEDIDA PARA ELIMINAR");
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
