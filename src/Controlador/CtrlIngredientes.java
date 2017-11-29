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
public class CtrlIngredientes implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmIngredientes Frm;
    private static CtrlIngredientes Single;

    private CtrlIngredientes(FrmIngredientes Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TIngredientes.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TIngredientes.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TIngredientes.getValueAt(C.fila, 0).toString());
                        Frm.txtIngredientes.setText(Frm.TIngredientes.getValueAt(C.fila, 1).toString());
                        C.MostrarenCombo(Frm.cboPresentacion, Frm.TIngredientes.getValueAt(C.fila, 3).toString());
                        C.MostrarenCombo(Frm.cboTipo, Frm.TIngredientes.getValueAt(C.fila, 4).toString());
                        C.MostrarenCombo(Frm.cboColor, Frm.TIngredientes.getValueAt(C.fila, 2).toString());
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

    public static synchronized CtrlIngredientes getInstance(FrmIngredientes Frm) {
        if (Single == null) {
            Single = new CtrlIngredientes(Frm);
        }
        return Single;
    }

    public void Iniciar() {
        Frm.TIngredientes.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "INGREDIENTE", "COLOR", "PRESENTACION", "TIPO"});

        Tabla();
        Tipo();
        Presentacion();
        Color();
    }

    public void Tipo() {
        C.sql = "SELECT * FROM tipoingre";
        C.LlenarCombo(Frm.cboTipo, C.sql, "<SELECCIONE>", 2);
    }

    public void Presentacion() {
        C.sql = "SELECT * FROM presentacion";
        C.LlenarCombo(Frm.cboPresentacion, C.sql, "<SELECCIONE>", 2);
    }

    public void Color() {
        C.sql = "SELECT * FROM color";
        C.LlenarCombo(Frm.cboColor, C.sql, "<SELECCIONE>", 2);
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtIngredientes.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA UN INGREDIENTE");
            Frm.txtIngredientes.grabFocus();
        } else {
            if (Frm.cboTipo.getSelectedIndex() == 0) {
                C.flag = false;
                JOptionPane.showMessageDialog(null, "SELECCIONA TIPO DE INGREDIENTE");
                Frm.cboTipo.grabFocus();
            } else {
                if (Frm.cboPresentacion.getSelectedIndex() == 0) {
                    C.flag = false;
                    JOptionPane.showMessageDialog(null, "SELECCIONA PRESENTACION DE INGREDIENTE");
                    Frm.cboPresentacion.grabFocus();
                }
            }
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtIngredientes.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.cboColor.setSelectedIndex(0);
        Frm.cboPresentacion.setSelectedIndex(0);
        Frm.cboTipo.setSelectedIndex(0);
        Frm.txtIngredientes.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM vtaingredientes WHERE cod_ingredientes LIKE '" + Frm.txtBuscar.getText()
                + "%' or nom_ingrediente like '" + Frm.txtBuscar.getText()
                + "%' or color like '" + Frm.txtBuscar.getText()
                + "%' or presentacion like '" + Frm.txtBuscar.getText()
                + "%' or nom_tipo like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 5);
    }

    public void Guardar() {
        String Tipo = C.DatoCombo("SELECT * FROM tipoingre WHERE nom_tipo='" + Frm.cboTipo.getSelectedItem().toString() + "'", 1);
        String Pres = C.DatoCombo("SELECT * FROM presentacion WHERE presentacion='" + Frm.cboPresentacion.getSelectedItem().toString() + "'", 1);
        String Colo = C.DatoCombo("SELECT * FROM color WHERE color='" + Frm.cboColor.getSelectedItem().toString() + "'", 1);

        String sql = "SELECT * FROM vtaingredientes WHERE nom_ingredientes='" + Frm.txtIngredientes.getText()
                + "' AND presentacion='" + Frm.cboPresentacion.getSelectedItem().toString()
                + "' AND nom_tipo='" + Frm.cboTipo.getSelectedItem().toString() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                String Cod = C.GeneraCodigo(Frm.txtIngredientes.getText().toUpperCase(), "ingredientes", "cod_ingredientes");

                if (Frm.cboColor.getSelectedIndex() == 0) {
                    C.InsertaRegistro("INSERT INTO ingredientes"
                            + " (cod_ingredientes, nom_ingrediente, presentacion_idpresentacion, tipoingre_idtipoingre) VALUES('"
                            + Cod + "','"
                            + Frm.txtIngredientes.getText().toUpperCase() + "','"
                            + Pres + "','"
                            + Tipo + "')");
                    C.Mensaje("INGREDIENTE REGISTRADO");
                } else {
                    C.InsertaRegistro("INSERT INTO ingredientes VALUES('" + Cod + "','"
                            + Frm.txtIngredientes.getText().toUpperCase() + "','"
                            + Colo + "','"
                            + Pres + "','"
                            + Tipo + "')");
                    C.Mensaje("INGREDIENTE REGISTRADO");
                }

                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("EL INGREDIENTE YA SE ENCUENTRA REGISTRADO");
        }
    }

    public void Editar() {
        String Tipo = C.DatoCombo("SELECT * FROM tipoIngre WHERE nom_tipo='" + Frm.cboTipo.getSelectedItem().toString() + "'", 1);
        String Pres = C.DatoCombo("SELECT * FROM presentacion WHERE presentacion='" + Frm.cboPresentacion.getSelectedItem().toString() + "'", 1);
        String Colo = C.DatoCombo("SELECT * FROM color WHERE color='" + Frm.cboColor.getSelectedItem().toString() + "'", 1);

        String sql = "SELECT * FROM vtaingredientes WHERE nom_ingredientes='" + Frm.txtIngredientes.getText()
                + "' AND presentacion='" + Frm.cboPresentacion.getSelectedItem().toString()
                + "' AND nom_tipo='" + Frm.cboTipo.getSelectedItem().toString() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                if (Frm.cboColor.getSelectedIndex() == 0) {
                    C.InsertaRegistro("UPDATE ingredientes SET nom_ingrediente='" + Frm.txtIngredientes.getText().toUpperCase()
                            + "', color_idcolor=NULL"
                            + ", presentacion_idpresentacion='" + Pres
                            + "', tipoingre_idtipoingre='" + Tipo
                            + "' WHERE cod_ingredientes='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("INGREDIENTE ACTUALIZADO");
                } else {
                    C.InsertaRegistro("UPDATE ingredientes SET nom_ingrediente='" + Frm.txtIngredientes.getText().toUpperCase()
                            + "', color_idcolor='" + Colo
                            + "', presentacion_idpresentacion='" + Pres
                            + "', tipoingre_idtipoingre='" + Tipo
                            + "' WHERE cod_ingredientes='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("INGREDIENTE ACTUALIZADO");
                }
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
        }
    }

    public void Eliminar() {
        C.fila = Frm.TIngredientes.getSelectedRow();
        if (Frm.TIngredientes.getSelectedRow() > -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                C.InsertaRegistro("DELETE FROM ingredientes WHERE cod_ingredientes='" + Frm.TIngredientes.getValueAt(C.fila, 0).toString() + "'");
                C.Mensaje("INGREDIENTE ELIMINADO");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("SELECCIONA UN INGREDIENTE PARA ELIMINAR");
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
