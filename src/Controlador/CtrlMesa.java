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
public class CtrlMesa implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private static FrmMesa Frm;
    private static CtrlMesa Single;

    private CtrlMesa(FrmMesa Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TMesa.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TMesa.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TMesa.getValueAt(C.fila, 0).toString());
                        C.MostrarenCombo(Frm.cboRest, Frm.TMesa.getValueAt(C.fila, 1).toString());
                        Frm.txtNumMesa.setText(Frm.TMesa.getValueAt(C.fila, 2).toString());
                        Frm.txtCantPer.setText(Frm.TMesa.getValueAt(C.fila, 3).toString());
                        C.MostrarenCombo(Frm.cboFumador, Frm.TMesa.getValueAt(C.fila, 4).toString());
                        C.MostrarenCombo(Frm.cboUbicacion, Frm.TMesa.getValueAt(C.fila, 5).toString());
                        C.MostrarenCombo(Frm.cboEstado, Frm.TMesa.getValueAt(C.fila, 6).toString());
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

    public static synchronized CtrlMesa getInstance(FrmMesa Frm) {
        if (Single == null) {
            Single = new CtrlMesa(Frm);
        }
        return Single;
    }

    public void Iniciar() {

        Frm.TMesa.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "RESTAURANTE", "NUM. MESA", "CANT. PERSONAS", "FUMADOR", "UBICACION", "ESTADO"});

        Tabla();
        Restaurante();
        Ubicacion();
        Estado();
        Fumador();
    }

    public void Restaurante() {
        C.sql = "SELECT * FROM restaurante";
        C.LlenarCombo(Frm.cboRest, C.sql, "<SELECCIONE>", 2);
    }

    public void Ubicacion() {
        C.sql = "SELECT * FROM ubicacion";
        C.LlenarCombo(Frm.cboUbicacion, C.sql, "<SELECCIONE>", 2);
    }

    public void Estado() {
        C.sql = "SELECT * FROM estado";
        C.LlenarCombo(Frm.cboEstado, C.sql, "<SELECCIONE>", 2);
    }

    public void Fumador() {
        Frm.cboFumador.removeAllItems();
        String Datos[] = {"<SELECCIONE>", "SI", "NO"};
        for (int i = 0; i < 3; i++) {
            Frm.cboFumador.addItem(Datos[i]);
        }
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.cboEstado.getSelectedIndex() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "SELECCIONA UN ESTADO");
            Frm.cboEstado.grabFocus();
        } else {
            if (Frm.cboFumador.getSelectedIndex() == 0) {
                C.flag = false;
                JOptionPane.showMessageDialog(null, "SELECCIONA FUMADOR");
                Frm.cboFumador.grabFocus();
            } else {
                if (Frm.cboRest.getSelectedIndex() == 0) {
                    C.flag = false;
                    JOptionPane.showMessageDialog(null, "SELECCIONA RESTAURANTE");
                    Frm.cboRest.grabFocus();
                } else {
                    if (Frm.cboUbicacion.getSelectedIndex() == 0) {
                        C.flag = false;
                        JOptionPane.showMessageDialog(null, "SELECCIONA UBICACION");
                        Frm.cboUbicacion.grabFocus();
                    } else {
                        if (Frm.txtCantPer.getText().trim().length() == 0) {
                            C.flag = false;
                            JOptionPane.showMessageDialog(null, "INGRESA CANTIDAD DE PERSONAS");
                            Frm.txtCantPer.grabFocus();
                        } else {
                            if (Frm.txtNumMesa.getText().trim().length() == 0) {
                                C.flag = false;
                                JOptionPane.showMessageDialog(null, "INGRESA NUMERO DE MESA");
                                Frm.txtCantPer.grabFocus();
                            } else {
                                if (Frm.txtCantPer.getText().equals("0")) {
                                    C.flag = false;
                                    JOptionPane.showMessageDialog(null, "INGRESA CANTIDAD DE PERSONAS MAYOR A 0");
                                    Frm.txtCantPer.grabFocus();
                                } else {
                                    if (Frm.txtNumMesa.getText().equals("0")) {
                                        C.flag = false;
                                        JOptionPane.showMessageDialog(null, "INGRESA NUMERO DE MESA MAYOR A 0");
                                        Frm.txtCantPer.grabFocus();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtCantPer.setText(null);
        Frm.txtNumMesa.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.cboEstado.setSelectedIndex(0);
        Frm.cboFumador.setSelectedIndex(0);
        Frm.cboRest.setSelectedIndex(0);
        Frm.cboUbicacion.setSelectedIndex(0);
        Frm.cboRest.grabFocus();
        Frm.TMesa.clearSelection();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM vtamesa WHERE cod_mesa LIKE '" + Frm.txtBuscar.getText()
                + "%' or nombre like '" + Frm.txtBuscar.getText()
                + "%' or num_mesa like '" + Frm.txtBuscar.getText()
                + "%' or cant_personas like '" + Frm.txtBuscar.getText()
                + "%' or fumador like '" + Frm.txtBuscar.getText()
                + "%' or ubicacion like '" + Frm.txtBuscar.getText()
                + "%' or estado like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 7);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Rest = C.DatoCombo("SELECT * FROM restaurante WHERE nombre='" + Frm.cboRest.getSelectedItem().toString() + "'", 1);
        String Ubica = C.DatoCombo("SELECT * FROM ubicacion WHERE ubicacion='" + Frm.cboUbicacion.getSelectedItem().toString() + "'", 1);
        String Esta = C.DatoCombo("SELECT * FROM estado WHERE estado='" + Frm.cboEstado.getSelectedItem().toString() + "'", 1);

        if (e.getSource() == Frm.btnguardar) {
            String sql = "SELECT * FROM mesa WHERE num_mesa='" + Frm.txtNumMesa.getText() + "' AND cod_restaurante='" + Rest + "';";
            if (!C.VerificarConsulta(sql)) {
                if (Validar()) {
                    String Cod = C.GeneraCodigo("M", "mesa", "cod_mesa");
                    C.InsertaRegistro("INSERT INTO mesa VALUES('" + Cod + "','"
                            + Frm.txtCantPer.getText().toUpperCase() + "','"
                            + Frm.txtNumMesa.getText().toUpperCase() + "','"
                            + Frm.cboFumador.getSelectedItem().toString().toUpperCase() + "','"
                            + Esta + "','"
                            + Ubica + "','"
                            + Rest + "')");
                    C.Mensaje("MESA REGISTRADA");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("LA MESA YA SE ENCUENTRA REGISTRADA");
            }
        }

        if (e.getSource() == Frm.btneditar) {
            String sql = "SELECT * FROM mesa WHERE num_mesa='" + Frm.txtNumMesa.getText() + "' AND cod_restaurante='" + Rest + "';";
            if (!C.VerificarConsulta(sql)) {
                if (Validar()) {
                    C.InsertaRegistro("UPDATE mesa SET cant_personas='" + Frm.txtCantPer.getText().toUpperCase()
                            + "', num_mesa='" + Frm.txtNumMesa.getText().toUpperCase()
                            + "', fumador='" + Frm.cboFumador.getSelectedItem().toString().toUpperCase()
                            + "', cod_estado='" + Esta
                            + "', cod_ubicacion='" + Ubica
                            + "', cod_restaurante='" + Rest
                            + "' WHERE cod_mesa='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("MESA ACTUALIZADA");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }

        if (e.getSource() == Frm.btneliminar) {
            C.fila = Frm.TMesa.getSelectedRow();
            if (Frm.TMesa.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM mesa WHERE cod_mesa='" + Frm.TMesa.getValueAt(C.fila, 0).toString() + "'");
                    C.Mensaje("MESA ELIMINADA");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("SELECCIONA MESA PARA ELIMINAR");
            }
        }

        if (e.getSource() == Frm.btnlimpiar) {
            Limpiar();
        }
    }

}
