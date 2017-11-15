/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlReservaCliente implements ActionListener {

    private DefaultTableModel DTM = new DefaultTableModel();
    Controlador C = Controlador.getInstance();
    private FrmReservaCliente Frm;
    Date dat = new Date();
    SpinnerNumberModel nm = new SpinnerNumberModel();
    String CodMesa, CodCli, NumMesa;
    Integer Cant;

    public CtrlReservaCliente(FrmReservaCliente Frm) {
        this.Frm = Frm;
        Frm.cboRestaurante.addActionListener(this);
        Frm.btnCancelar.addActionListener(this);
        Frm.btnReservar.addActionListener(this);
        Frm.btnLimpiar.addActionListener(this);
        Frm.btnManual.addActionListener(this);

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

        Frm.setTitle("RESERVA TU MESA...!!!");
        Frm.setLocationRelativeTo(null);

        Frm.TMesasLibres.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO", "RESTAURANTE", "NUM. MESA", "CAPACIDAD", "FUMADOR", "UBICACION"});

        Restaurante();
        Limpiar();
        FechaActual();
        Asistentes();
    }

    public void Asistentes() {
        nm.setValue(1);
        nm.setMaximum(20);
        nm.setMinimum(1);
        Frm.jspAsistentes.setModel(nm);
        Frm.jspAsistentes.setEditor(new JSpinner.DefaultEditor(Frm.jspAsistentes));

    }

    public void Restaurante() {
        C.sql = "SELECT * FROM restaurante";
        C.LlenarCombo(Frm.cboRestaurante, C.sql, "<SELECCIONE>", 2);
    }

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
                            }else {
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
    
        public void MesasRestaurante() {
        C.sql = "SELECT * FROM VtaMesa WHERE Nombre='"+Frm.cboRestaurante.getSelectedItem().toString()+"' ";
        C.MostrarenJTable(DTM, C.sql, 6);
    }

    public void Tabla() {
        C.sql = "SELECT * FROM VtaMesa WHERE Cod_Mesa LIKE '" + Frm.txtBuscar.getText()
                + "%' or Nombre like '" + Frm.txtBuscar.getText()
                + "%' or Num_Mesa like '" + Frm.txtBuscar.getText()
                + "%' or Cant_Personas like '" + Frm.txtBuscar.getText()
                + "%' or Fumador like '" + Frm.txtBuscar.getText()
                + "%' or Ubicacion like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 6);
    }

    public void Visualizar() {
        if (Frm.cboRestaurante.getSelectedIndex() > 0) {
            String RS = Frm.cboRestaurante.getSelectedItem().toString();
            String Rest = C.DatoCombo("SELECT * FROM restaurante WHERE Nombre='" + RS + "'", 2);
            String Dire = C.DatoCombo("SELECT * FROM restaurante WHERE Nombre='" + RS + "'", 3);
            String Telef = C.DatoCombo("SELECT * FROM restaurante WHERE Nombre='" + RS + "'", 4);

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
    public String Fecha() {
        String dia = Integer.toString(Frm.jdcFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(Frm.jdcFecha.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(Frm.jdcFecha.getCalendar().get(Calendar.YEAR));
        String fecha = (year + "-" + mes + "-" + dia);
        return fecha;
    }

    public void Reservar() {
        CodCli = C.DevolverDatoString("SELECT * FROM cliente WHERE Nombre='" + Frm.lblCliente.getText() + "'", 1);
        
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(Frm.jspHora, "HH:mm");
        JSpinner.DateEditor de2 = new JSpinner.DateEditor(Frm.jspHora, "hh:mm a");

        if (Validar()) {
            C.sql = "SELECT * FROM VtaReserva WHERE Fecha='"+Fecha()
                    +"' AND Hora<='"+de1.getFormat().format(Frm.jspHora.getValue())
                    +"' AND Num_mesa='"+NumMesa
                    +"' AND Resta='"+Frm.lblRestaurante.getText().toUpperCase()+"';";
            
            if (!C.VerificarConsulta(C.sql)) {
                if (JOptionPane.showConfirmDialog(null, "¿LA RESERVA ES CORRECTA?"
                        + "\n\nCLIENTE: " + Frm.lblCliente.getText()
                        + "\nRESTAURANTE: " + Frm.lblRestaurante.getText().toUpperCase()
                        + "\nMESA: " + NumMesa
                        + "\nHORA: " + de2.getFormat().format(Frm.jspHora.getValue())
                        + "\nFECHA: " + Fecha()
                        + "\nASISTENTES: " + Frm.jspAsistentes.getValue(), "CONSULTA", 0) == 0) {

                    String Cod = C.GeneraCodigo("R", "Reserva", "Cod_Reserva");
                    C.InsertaRegistro("INSERT INTO Reserva VALUES('" + Cod + "','"
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
                    C.InsertaRegistro("UPDATE mesa SET Cod_Estado='R' WHERE Cod_Mesa='" + CodMesa + "'");
                }
            }else{
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
            try {
                File path = new File("C://restosist/Manual.pdf");
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
