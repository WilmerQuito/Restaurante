/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlUsuario implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    Encriptado E = Encriptado.getInstance();
    private static FrmUsuario Frm;
    private static CtrlUsuario Single;

    //IMPLEMENTACION DE SINGLETON
    private CtrlUsuario(FrmUsuario Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        //MOSTRAR DATOS DE LA TABLA EN LOS TEXTOS Y COMBOS
        Frm.TUsuario.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TUsuario.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TUsuario.getValueAt(C.fila, 0).toString());
                        C.MostrarenCombo(Frm.cboEmpleado, Frm.TUsuario.getValueAt(C.fila, 1).toString());
                        C.MostrarenCombo(Frm.cboRol, Frm.TUsuario.getValueAt(C.fila, 2).toString());
                        Frm.txtUsuario.setText(Frm.TUsuario.getValueAt(C.fila, 3).toString());
                        Frm.txtPsw1.setText(E.Desencriptar(Frm.TUsuario.getValueAt(C.fila, 4).toString()));
                        Frm.txtPsw2.setText(E.Desencriptar(Frm.TUsuario.getValueAt(C.fila, 4).toString()));
                    }
                }
            }
        });

        //BUSQUEDA DE DATOS EN LA TABLA
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

    public static synchronized CtrlUsuario getInstance(FrmUsuario Frm) {
        if (Single == null) {
            Single = new CtrlUsuario(Frm);
        }
        return Single;
    }

    //INICIO DEL FORMULARIO INGRESANDO TITULOS A LA TABLA Y DATOS A LOS COMBOS
    public void Iniciar() {
        Frm.TUsuario.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "EMPLEADO", "ROL", "USUARIO", "CLAVE"});

        Tabla();
        Rol();
        Empleado();
    }

    //INSERCION DATOS DE TABLA ROL AL COMBO
    public void Rol() {
        C.sql = "SELECT * FROM rol";
        C.LlenarCombo(Frm.cboRol, C.sql, "<SELECCIONE>", 2);
    }

    //INSERCION DATOS DE TABLA EMPLEADO AL COMBO
    public void Empleado() {
        C.sql = "SELECT * FROM empleado";
        C.LlenarCombo(Frm.cboEmpleado, C.sql, "<SELECCIONE>", 2);
    }

    //VALIDA LOS DATOS A INGRESAR EN EL FORMULARIO
    public boolean Validar() {
        C.flag = true;
        if (!C.ComparaContra(Frm.txtPsw1.getPassword(), Frm.txtPsw2.getPassword())) {
            C.flag = false;
            C.Mensaje("LAS CONTRASEÑAS NO COINCIDEN");
            Frm.txtPsw2.grabFocus();
        } else {
            if (Frm.txtPsw1.getText().trim().length() != Frm.txtPsw2.getText().trim().length()) {
                C.flag = false;
                C.Mensaje("LAS CONTRASEÑAS NO COINCIDEN");
                Frm.txtPsw2.grabFocus();
            } else {
                if (Frm.txtUsuario.getText().trim().length() == 0) {
                    C.flag = false;
                    C.Mensaje("INGRESA EL USUARIO");
                    Frm.txtUsuario.grabFocus();
                } else {
                    if (Frm.txtPsw1.getText().trim().length() == 0) {
                        C.flag = false;
                        C.Mensaje("INGRESA UNA CONTRASEÑA");
                        Frm.txtPsw1.grabFocus();
                    } else {
                        if (Frm.txtPsw2.getText().trim().length() == 0) {
                            C.flag = false;
                            C.Mensaje("REPITA LA CONTRASEÑA");
                            Frm.txtPsw2.grabFocus();
                        } else {
                            if (Frm.txtPsw1.getText().trim().length() <= 6 || Frm.txtPsw2.getText().trim().length() <= 6) {
                                C.flag = false;
                                C.Mensaje("TAMAÑO DE CONTRASEÑA MINIMO");
                                Frm.txtPsw2.grabFocus();
                            } else {
                                if (Frm.cboRol.getSelectedIndex() == 0) {
                                    C.flag = false;
                                    C.Mensaje("SELECCIONA UN ROL");
                                    Frm.txtPsw2.grabFocus();
                                } else {
                                    if (Frm.cboEmpleado.getSelectedIndex() == 0) {
                                        C.flag = false;
                                        C.Mensaje("SELECCIONA AL EMPLEADO");
                                        Frm.txtPsw2.grabFocus();
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

    //LIMPIAR TODOS LOS CAMPOS DEL FORMULARIO
    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtPsw1.setText(null);
        Frm.txtPsw2.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtUsuario.setText(null);
        Frm.txtUsuario.grabFocus();
        Frm.cboRol.setSelectedIndex(0);
        Frm.cboEmpleado.setSelectedIndex(0);
        Frm.TUsuario.clearSelection();
        Tabla();
    }

    //OBTENCION DE DATOS DE LA VISTA EN LA TABLA Y RALIZAR BUSQUEDAS
    public void Tabla() {
        C.sql = "SELECT * FROM vtasesion WHERE cod_usuario LIKE '" + Frm.txtBuscar.getText()
                + "%' or empleado like '" + Frm.txtBuscar.getText()
                + "%' or rol like '" + Frm.txtBuscar.getText()
                + "%' or usuario like '" + Frm.txtBuscar.getText()
                + "%' or clave like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //OBTENCION DE CODIGOS DE LOS COMBOS
        String Rol = C.DatoCombo("SELECT * FROM rol WHERE nombre='" + Frm.cboRol.getSelectedItem().toString() + "'", 1);
        String Empleado = C.DatoCombo("SELECT * FROM empleado WHERE nombre='" + Frm.cboEmpleado.getSelectedItem().toString() + "'", 1);

        //INSERCION DE DATOS A LA BD
        if (e.getSource() == Frm.btnguardar) {
            String sql = "SELECT * FROM usuario WHERE usuario='" + Frm.txtUsuario.getText() + "' OR empleado_idempleado='" + Empleado + "';";
            if (!C.VerificarConsulta(sql)) {
                if (Validar()) {
                    String Cod = C.GeneraCodigo(Frm.txtUsuario.getText().toUpperCase(), "usuario", "cod_usuario");
                    C.InsertaRegistro("INSERT INTO usuario VALUES('" + Cod + "','"
                            + Frm.txtUsuario.getText().toUpperCase() + "','"
                            + E.Encriptar(Frm.txtPsw1.getText().toUpperCase()) + "','"
                            + Empleado + "','"
                            + Rol + "')");
                    C.Mensaje("USUARIO REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("IMPOSIBLE REGISTRAR AL USUARIO");
            }
        }

        //EDICION DE DATOS EN LA BD
        if (e.getSource() == Frm.btneditar) {
            String sql = "SELECT * FROM usuario WHERE usuario='" + Frm.txtUsuario.getText() + "' AND empleado_idempleado='" + Empleado + "' AND rol_cod_rol='" + Rol + "';";
            if (!C.VerificarConsulta(sql)) {
                if (Validar()) {
                    C.InsertaRegistro("UPDATE usuario SET usuario='" + Frm.txtUsuario.getText().toUpperCase()
                            + "', clave='" + E.Encriptar(Frm.txtPsw1.getText().toUpperCase())
                            + "', empleado_idempleado='" + Empleado
                            + "', rol_cod_rol='" + Rol
                            + "' WHERE cod_usuario='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("USUARIO ACTUALIZADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }

        //ELIMINACION DE DATOS EN LA BD
        if (e.getSource() == Frm.btneliminar) {
            C.fila = Frm.TUsuario.getSelectedRow();
            if (Frm.TUsuario.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM usuario WHERE cod_usuario='" + Frm.TUsuario.getValueAt(C.fila, 0).toString() + "'");
                    C.Mensaje("USUARIO ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            } else {
                C.Mensaje("SELECCIONA USUARIO PARA ELIMINAR");
            }
        }

        if (e.getSource() == Frm.btnlimpiar) {
            Limpiar();
        }
    }
}
