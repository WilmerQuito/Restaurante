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
public class CtrlBebida implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private FrmBebida Frm;

    public CtrlBebida(FrmBebida Frm) {
        this.Frm = Frm;

        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);

        Frm.TBebidas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TBebidas.getSelectedRow();
                    if (C.fila > -1) {
                        Frm.txtCodigo.setText(Frm.TBebidas.getValueAt(C.fila, 0).toString());
                        Frm.txtCosto.setText(Frm.TBebidas.getValueAt(C.fila, 4).toString());
                        C.MostrarenCombo(Frm.cboTipo, Frm.TBebidas.getValueAt(C.fila, 1).toString());
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
        Frm.TBebidas.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "TIPO", "MARCA", "SABOR", "COSTO"});

        Tabla();
        Tipo();
        Marca();
        Sabor();
    }

    public void Tipo() {
        C.sql = "SELECT * FROM tipoBebida";
        C.LlenarCombo(Frm.cboTipo, C.sql, "<SELECCIONE>", 2);
    }

    public void Marca() {
        C.sql = "SELECT * FROM Marca";
        C.LlenarCombo(Frm.cboMarca, C.sql, "<SELECCIONE>", 2);
    }

    public void Sabor() {
        C.sql = "SELECT * FROM Sabor";
        C.LlenarCombo(Frm.cboSabor, C.sql, "<SELECCIONE>", 2);
    }

    public boolean Validar() {
        C.flag = true;
        if (Frm.txtCosto.getText().trim().length() == 0) {
            C.flag = false;
            JOptionPane.showMessageDialog(null, "INGRESA EL COSTO DE BEBIDA");
            Frm.txtCosto.grabFocus();
        } else {
            if (Frm.cboTipo.getSelectedIndex() == 0) {
                C.flag = false;
                JOptionPane.showMessageDialog(null, "SELECCIONA EL TIPO DE BEBIDA");
                Frm.cboTipo.grabFocus();
            }
        }
        return C.flag;
    }

    public void Limpiar() {
        Frm.txtBuscar.setText(null);
        Frm.txtCosto.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.cboTipo.setSelectedIndex(0);
        Frm.cboMarca.setSelectedIndex(0);
        Frm.cboSabor.setSelectedIndex(0);
        Frm.cboTipo.grabFocus();
        Tabla();
    }

    public void Tabla() {
        C.sql = "SELECT * FROM VtaBebida WHERE idBebidas LIKE '" + Frm.txtBuscar.getText()
                + "%' or Tipo like '" + Frm.txtBuscar.getText()
                + "%' or Marca like '" + Frm.txtBuscar.getText()
                + "%' or Sabor like '" + Frm.txtBuscar.getText()
                + "%' or Costo like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 5);
    }
    
    public void Guardar() {
        String Tipo = C.DatoCombo("SELECT * FROM tipoBebida WHERE nomTipo='" + Frm.cboTipo.getSelectedItem().toString() + "'", 1);
        String Marc = C.DatoCombo("SELECT * FROM marca WHERE marcacol='" + Frm.cboMarca.getSelectedItem().toString() + "'", 1);
        String Sabo = C.DatoCombo("SELECT * FROM sabor WHERE saborcol='" + Frm.cboSabor.getSelectedItem().toString() + "'", 1);
        
        String sql = "SELECT * FROM VtaBebida WHERE Tipo='" + Frm.cboTipo.getSelectedItem().toString()
                + "' AND Marca='" + Frm.cboMarca.getSelectedItem().toString()
                + "' AND Sabor='" + Frm.cboSabor.getSelectedItem().toString()
                + "' AND Costo='" + Frm.txtCosto.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                String Cod = C.GeneraCodigo("B", "bebidas", "idbebidas");
                
                if (Frm.cboMarca.getSelectedIndex() == 0 && Frm.cboSabor.getSelectedIndex() > 0) {
                    C.InsertaRegistro("INSERT INTO bebidas"
                            + " (idbebidas, tipoBebida_idtipoBebida, sabor_idsabor, costo) VALUES('"
                            + Cod + "','"
                            + Tipo + "','"
                            + Sabo + "','"
                            + Frm.txtCosto.getText() + "')");
                    C.Mensaje("BEBIDA REGISTRADA");
                }
                
                if (Frm.cboSabor.getSelectedIndex() == 0 && Frm.cboMarca.getSelectedIndex() > 0) {
                    C.InsertaRegistro("INSERT INTO bebidas"
                            + " (idbebidas, tipoBebida_idtipoBebida, marca_idmarca, costo) VALUES('"
                            + Cod + "','"
                            + Tipo + "','"
                            + Marc + "','"
                            + Frm.txtCosto.getText() + "')");
                    C.Mensaje("BEBIDA REGISTRADA");
                }
                
                if (Frm.cboSabor.getSelectedIndex() == 0 && Frm.cboMarca.getSelectedIndex() == 0) {
                    C.InsertaRegistro("INSERT INTO bebidas"
                            + " (idbebidas, tipoBebida_idtipoBebida, costo) VALUES('"
                            + Cod + "','"
                            + Tipo + "','"
                            + Frm.txtCosto.getText() + "')");
                    C.Mensaje("BEBIDA REGISTRADA");
                }
                
                if (Frm.cboSabor.getSelectedIndex() > 0 && Frm.cboMarca.getSelectedIndex() > 0) {
                    C.InsertaRegistro("INSERT INTO bebidas VALUES('" + Cod + "','"
                            + Tipo + "','"
                            + Marc + "','"
                            + Sabo + "','"
                            + Frm.txtCosto.getText() + "')");
                    C.Mensaje("BEBIDA REGISTRADA");
                }
                
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("LA BEBIDA YA SE ENCUENTRA REGISTRADA");
        }
    }

    public void Editar() {
        String Tipo = C.DatoCombo("SELECT * FROM tipoBebida WHERE nomTipo='" + Frm.cboTipo.getSelectedItem().toString() + "'", 1);
        String Marc = C.DatoCombo("SELECT * FROM marca WHERE marcacol='" + Frm.cboMarca.getSelectedItem().toString() + "'", 1);
        String Sabo = C.DatoCombo("SELECT * FROM sabor WHERE saborcol='" + Frm.cboSabor.getSelectedItem().toString() + "'", 1);

        String sql = "SELECT * FROM VtaBebida WHERE Tipo='" + Frm.cboTipo.getSelectedItem().toString()
                + "' AND Marca='" + Frm.cboMarca.getSelectedItem().toString()
                + "' AND Sabor='" + Frm.cboSabor.getSelectedItem().toString()
                + "' AND Costo='" + Frm.txtCosto.getText() + "';";
        if (!C.VerificarConsulta(sql)) {
            if (Validar()) {
                if (Frm.cboMarca.getSelectedIndex() == 0 && Frm.cboSabor.getSelectedIndex() > 0) {
                    C.InsertaRegistro("UPDATE bebidas SET tipoBebida_idtipoBebida='" + Tipo
                            + "', marca_idmarca=NULL"
                            + ", sabor_idsabor='" + Sabo
                            + "', Costo='" + Frm.txtCosto.getText()
                            + "' WHERE idbebidas='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("BEBIDA ACTUALIZADA");
                }
                
                if (Frm.cboSabor.getSelectedIndex() == 0 && Frm.cboMarca.getSelectedIndex() > 0) {
                    C.InsertaRegistro("UPDATE bebidas SET tipoBebida_idtipoBebida='" + Tipo
                            + "', marca_idmarca='" + Marc
                            + "', sabor_idsabor=NULL"
                            + ", Costo='" + Frm.txtCosto.getText()
                            + "' WHERE idbebidas='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("BEBIDA ACTUALIZADA");
                }
                
                if (Frm.cboSabor.getSelectedIndex() == 0 && Frm.cboMarca.getSelectedIndex() == 0) {
                    C.InsertaRegistro("UPDATE bebidas SET tipoBebida_idtipoBebida='" + Tipo
                            + "', marca_idmarca=NULL"
                            + ", sabor_idsabor=NULL"
                            + ", Costo='" + Frm.txtCosto.getText()
                            + "' WHERE idbebidas='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("BEBIDA ACTUALIZADA");
                }
                
                if (Frm.cboSabor.getSelectedIndex() > 0 && Frm.cboMarca.getSelectedIndex() > 0) {
                    C.InsertaRegistro("UPDATE bebidas SET tipoBebida_idtipoBebida='" + Tipo
                            + "', marca_idmarca='" + Marc
                            + "', sabor_idsabor='" + Sabo
                            + "', Costo='" + Frm.txtCosto.getText()
                            + "' WHERE idbebidas='" + Frm.txtCodigo.getText() + "'");
                    C.Mensaje("BEBIDA ACTUALIZADA");
                }
                
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
        }
    }

    public void Eliminar() {
        C.fila = Frm.TBebidas.getSelectedRow();
        if (Frm.TBebidas.getSelectedRow() > -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                C.InsertaRegistro("DELETE FROM bebidas WHERE idbebidas='" + Frm.TBebidas.getValueAt(C.fila, 0).toString() + "'");
                C.Mensaje("BEBIDA ELIMINADA");
                Tabla();
                Limpiar();
            }
        } else {
            C.Mensaje("SELECCIONA UNA BEBIDA PARA ELIMINAR");
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
