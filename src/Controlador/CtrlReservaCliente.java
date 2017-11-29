/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlReservaCliente implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    ArchivoTXT A = ArchivoTXT.getInstance();
    private static FrmReservaCliente Frm;
    private static CtrlReservaCliente Single;
    private static String ruta = System.getProperties().getProperty("user.dir") + "/Complementos/Ticket.txt";
    Date dat = new Date();
    SpinnerNumberModel nm = new SpinnerNumberModel();
    String CodMesa, CodCli, NumMesa;
    Integer Cant;

    //IMPLEMENTACION DE SINGLETON
    private CtrlReservaCliente(FrmReservaCliente Frm) {
        this.Frm = Frm;
        Frm.cboRestaurante.addActionListener(this);
        Frm.btnCancelar.addActionListener(this);
        Frm.btnReservar.addActionListener(this);
        Frm.btnLimpiar.addActionListener(this);
        Frm.btnManual.addActionListener(this);

        //OBTENCION DE DATOS DE LA TABLA
        Frm.TMesasLibres.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    C.fila = Frm.TMesasLibres.getSelectedRow();
                    if (C.fila > -1) {
                        CodMesa = Frm.TMesasLibres.getValueAt(C.fila, 0).toString();
                        NumMesa = Frm.TMesasLibres.getValueAt(C.fila, 2).toString();
                        Cant = Integer.parseInt(Frm.TMesasLibres.getValueAt(C.fila, 3).toString());
                    }
                }
            }
        });

        //BUSCA DATOS EN LA TABLA
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

    public static synchronized CtrlReservaCliente getInstance(FrmReservaCliente Frm) {
        if (Single == null) {
            Single = new CtrlReservaCliente(Frm);
        }
        return Single;
    }

    //INICIA LOS DATOS EN EL FORMULARIO
    public void Iniciar() {

        Frm.setTitle("RESERVA TU MESA...!!!");
        Frm.setLocationRelativeTo(null);

        Frm.TMesasLibres.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "RESTAURANTE", "NUM. MESA", "CAPACIDAD", "FUMADOR", "UBICACION"});

        Restaurante();
        Limpiar();
        FechaActual();
        Asistentes();
    }

    //LIMITA LOS SPINNER
    public void Asistentes() {
        nm.setValue(1);
        nm.setMaximum(20);
        nm.setMinimum(1);
        Frm.jspAsistentes.setModel(nm);
        Frm.jspAsistentes.setEditor(new JSpinner.DefaultEditor(Frm.jspAsistentes));

    }

    //CARGA CON DATOS DE LA TABLA RESTAURANTE AL COMBO
    public void Restaurante() {
        C.sql = "SELECT * FROM restaurante";
        C.LlenarCombo(Frm.cboRestaurante, C.sql, "<SELECCIONE>", 2);
    }

    //LIMPIA LOS DATOS DEL FORMULARIO
    public void Limpiar() {
        Frm.lblDireccion.setText(null);
        Frm.lblRestaurante.setText(null);
        Frm.lblTelefono.setText(null);
        Frm.cboRestaurante.setSelectedIndex(0);
        Frm.TMesasLibres.clearSelection();
        Frm.cboRestaurante.grabFocus();
        FechaActual();
        C.LimpiarTabla(DTM);
        nm.setValue(1);
        Frm.txtComentario.setText(null);
        Frm.txtBuscar.setText(null);
    }

    //VALIDA LOS DATOS INGRESADOS AL FORMULARIO
    public boolean Validar() {
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(Frm.jspHora, "HH:mm.ss");
        DateFormat HoraFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat FechaFormat = new SimpleDateFormat("yyyy-MM-dd");

        C.flag = true;
        if (Frm.cboRestaurante.getSelectedIndex() == 0) {
            C.flag = false;
            C.Mensaje("SELECCIONA UN RESTAURANTE");
            Frm.cboRestaurante.grabFocus();
        } else {
            if (Frm.TMesasLibres.getSelectedRow() <= -1) {
                C.flag = false;
                C.Mensaje("SELECCIONA MESA PARA RESERVAR");
                Frm.TMesasLibres.grabFocus();
            } else {
                if (Frm.jdcFecha.getDate() == null) {
                    C.flag = false;
                    C.Mensaje("SELECCIONA UNA FECHA VALIDA");
                    Frm.jdcFecha.grabFocus();
                } else {
                    if (Frm.jdcFecha.getDate().getTime() <= dat.getTime()) {
                        C.flag = false;
                        C.Mensaje("SELECCIONA UNA FECHA VALIDA");
                        Frm.jdcFecha.grabFocus();
                    } else {
                        if (Frm.jspHora.getValue() == null) {
                            C.flag = false;
                            C.Mensaje("INGRESA UNA HORA VALIDA");
                            Frm.jdcFecha.grabFocus();
                        } else {
                            if (Integer.parseInt(String.valueOf(Frm.jspAsistentes.getValue())) > Cant) {
                                C.flag = false;
                                C.Mensaje("PERSONAS ASISTENTES SUPERA LA CAPACIDAD DE MESA");
                                Frm.jdcFecha.grabFocus();
                            } else {
                                if (Fecha().equals(FechaFormat.format(dat))) {
                                    if (de1.getFormat().format(Frm.jspHora.getValue()).compareTo(HoraFormat.format(dat)) <= 0) {
                                        C.flag = false;
                                        C.Mensaje("LA HORA NO ES ACEPTABLE");
                                        Frm.jspHora.grabFocus();
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

    //VISUALIZA DATOS EN LA TABLA SEGUN RESTAURANTE
    public void MesasRestaurante() {
        C.InsertaRegistro("CREATE OR REPLACE VIEW vtamesaresta AS SELECT * FROM vtamesa WHERE nombre='" + Frm.cboRestaurante.getSelectedItem().toString() + "'");
        C.sql = "SELECT * FROM vtamesaresta";
        C.MostrarenJTable(DTM, C.sql, 6);
    }

    //BUSQUEDA DE DATOS EN LA TABLA
    public void Tabla() {
        C.sql = "SELECT * FROM vtamesaresta WHERE cod_mesa LIKE '" + Frm.txtBuscar.getText()
                + "%' or nombre like '" + Frm.txtBuscar.getText()
                + "%' or num_mesa like '" + Frm.txtBuscar.getText()
                + "%' or cant_personas like '" + Frm.txtBuscar.getText()
                + "%' or fumador like '" + Frm.txtBuscar.getText()
                + "%' or ubicacion like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 6);
    }

    //VISUALIZA LAS MESAS SEGUN EL RESTAURANTE SELECCIONADO
    public void Visualizar() {
        if (Frm.cboRestaurante.getSelectedIndex() > 0) {
            String RS = Frm.cboRestaurante.getSelectedItem().toString();
            String Rest = C.DatoCombo("SELECT * FROM restaurante WHERE nombre='" + RS + "'", 2);
            String Dire = C.DatoCombo("SELECT * FROM restaurante WHERE nombre='" + RS + "'", 3);
            String Telef = C.DatoCombo("SELECT * FROM restaurante WHERE nombre='" + RS + "'", 4);

            Frm.lblRestaurante.setText(Rest);
            Frm.lblDireccion.setText(Dire);
            Frm.lblTelefono.setText(Telef);

            MesasRestaurante();

            if (DTM.getRowCount() <= 0) {
                C.Mensaje("NO HAY MESAS LIBRES, PRUEBE EN OTRO RESTAURANTE");
            }
        }
        if (Frm.cboRestaurante.getSelectedIndex() == 0) {
            Limpiar();
            C.LimpiarTabla(DTM);
        }
    }

    //OBTIENE FECHA ACTUAL
    public void FechaActual() {
        Calendar FC = new GregorianCalendar();
        Frm.jdcFecha.setCalendar(FC);
    }

    /*public String FC(String fech) {
        String d = fech.substring(8, 10);
        String m = fech.substring(5, 7);
        String y = fech.substring(0, 4);
        return d + "/" + m + "/" + y;
    }

    public static Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }*/
    //CONVIERTE LA FECHA PARA INSERCION
    public String Fecha() {
        String dia = Integer.toString(Frm.jdcFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(Frm.jdcFecha.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(Frm.jdcFecha.getCalendar().get(Calendar.YEAR));
        String fecha = (year + "-" + mes + "-" + dia);
        return fecha;
    }

    //REALIZA LA INSERCION DE DATOS A LA TABLA RESERVA
    public void Reservar() {
        //OBTIENE EL CODIGO DEL CLIENTE
        CodCli = C.DevolverDatoString("SELECT * FROM cliente WHERE nombre='" + Frm.lblCliente.getText() + "'", 1);

        //FORMATEA LA HORA
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(Frm.jspHora, "HH:mm");
        JSpinner.DateEditor de2 = new JSpinner.DateEditor(Frm.jspHora, "hh:mm a");

        if (Validar()) {
            C.sql = "SELECT * FROM vtareserva WHERE fecha='" + Fecha()
                    + "' AND hora<='" + de1.getFormat().format(Frm.jspHora.getValue())
                    + "' AND num_mesa='" + NumMesa
                    + "' AND resta='" + Frm.lblRestaurante.getText().toUpperCase() + "';";

            if (!C.VerificarConsulta(C.sql)) {
                if (JOptionPane.showConfirmDialog(null, "¿LA RESERVA ES CORRECTA?"
                        + "\n\nCLIENTE: " + Frm.lblCliente.getText()
                        + "\nRESTAURANTE: " + Frm.lblRestaurante.getText().toUpperCase()
                        + "\nMESA: " + NumMesa
                        + "\nHORA: " + de2.getFormat().format(Frm.jspHora.getValue())
                        + "\nFECHA: " + Fecha()
                        + "\nASISTENTES: " + Frm.jspAsistentes.getValue(), "CONSULTA", 0) == 0) {

                    //IMPRIME TICKET
                    A.CrearArchivo(ruta, "TICKET DE RESERVA");
                    A.AñadirDatos(ruta, " ");
                    A.AñadirDatos(ruta, "Cliente: " + Frm.lblCliente.getText());
                    A.AñadirDatos(ruta, "Restaurante: " + Frm.lblRestaurante.getText().toUpperCase());
                    A.AñadirDatos(ruta, "Mesa: " + NumMesa);
                    A.AñadirDatos(ruta, "Hora: " + de2.getFormat().format(Frm.jspHora.getValue()));
                    A.AñadirDatos(ruta, "Fecha: " + Fecha());
                    A.AñadirDatos(ruta, "Asistentes: " + Frm.jspAsistentes.getValue());
                    A.AñadirDatos(ruta, " ");
                    A.AñadirDatos(ruta, "GRACIAS POR SU RESERVA...!!");
                    A.Imprimir(ruta);

                    //REGISTRA RESERVA
                    String Cod = C.GeneraCodigo("R", "reserva", "cod_reserva");
                    C.InsertaRegistro("INSERT INTO reserva VALUES('" + Cod + "','"
                            + de1.getFormat().format(Frm.jspHora.getValue()) + "','"
                            + Fecha() + "','"
                            + Frm.jspAsistentes.getValue() + "','"
                            + Frm.txtComentario.getText().toString().toUpperCase() + "','"
                            + CodCli + "','"
                            + CodMesa + "')");
                    C.Mensaje("RESERVA REALIZADA");
                    Tabla();
                    Limpiar();
                    C.BuscarCliente(Frm);
                    //C.InsertaRegistro("UPDATE mesa SET Cod_Estado='R' WHERE Cod_Mesa='" + CodMesa + "'");
                }
            } else {
                C.Mensaje("LA MESA ESTA RESERVADA PARA ESE DIA Y HORA");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Frm.cboRestaurante) {
            Visualizar();
        }

        if (e.getSource() == Frm.btnCancelar) {
            if (JOptionPane.showConfirmDialog(null, "¿QUIERES CANCELAR LA RESERVA?", "CONSULTA", 0) == 0) {
                C.BuscarCliente(Frm);
            }
        }

        if (e.getSource() == Frm.btnReservar) {
            Reservar();
        }

        if (e.getSource() == Frm.btnLimpiar) {
            Limpiar();
        }

        if (e.getSource() == Frm.btnManual) {
            C.Video();
        }

    }

}
