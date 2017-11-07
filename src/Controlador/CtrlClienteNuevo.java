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
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Quito
 */
public class CtrlClienteNuevo implements ActionListener{
    private Controlador C=new Controlador();
    private FrmClienteNuevo Frm;
    
    public CtrlClienteNuevo (FrmClienteNuevo Frm){
        this.Frm = Frm;
        
        Frm.btnRegresar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
    }
    
    public void Iniciar(){
        Frm.setTitle("REGISTRATE...!!!");
        Frm.setLocationRelativeTo(null);
        
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtNombre.getText().trim().length() == 0) {
            C.flag = false;
            C.Mensaje("INGRESA EL NOMBRE");
            Frm.txtNombre.grabFocus();
        } else {
            if (Frm.txtDNI.getText().trim().length() == 0) {
                C.flag = false;
                C.Mensaje("INGRESA UN DNI");
                Frm.txtDNI.grabFocus();
            } else {
                if (Frm.txtCelular.getText().trim().length() == 0) {
                    C.flag = false;
                    C.Mensaje("INGRESA UN CELULAR");
                    Frm.txtCelular.grabFocus();
                }else{
                    if(Frm.txtDNI.getText().trim().length() < 8){
                        C.flag = false;
                        C.Mensaje("LONGITUD DE DNI INCORRECTO");
                        Frm.txtDNI.grabFocus();
                    }else{
                        if(Frm.txtCelular.getText().trim().length() < 9) {
                            C.flag = false;
                            C.Mensaje("LONGITUD DE CELULAR INCORRECTO");
                            Frm.txtCelular.grabFocus();
                        }
                    }
                }
            }
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtCelular.setText(null);
        Frm.txtDNI.setText(null);
        Frm.txtNombre.setText(null);
        Frm.txtNombre.grabFocus();
    }
    
    public void BuscarCliente(){
        FrmBuscarCliente Frm2=new FrmBuscarCliente();
        CtrlBuscarCliente Ctl= new CtrlBuscarCliente(Frm2);
        Ctl.Iniciar();
        Frm2.setVisible(true);
        Frm.setVisible(false);
    }
    
    public void Reservas(){
        /*FrmReservas Frm2=new FrmReservas();
        CtrlReservas Ctl= new CtrlReservas(Frm2);
        Ctl.Iniciar();
        Frm2.setVisible(true);
        Frm.setVisible(false);*/
    }
    
    public void Guardar(){
        String sql="SELECT * FROM Cliente WHERE DNI='"+Frm.txtDNI.getText()+"';";
        if(!C.VerificarConsulta(sql)){
            if (Validar()) {
                if (JOptionPane.showConfirmDialog(null, "¿SON CORRECTOS TUS DATOS?", "CONSULTA", 0) == 0) {
                    String Cod="10"+Frm.txtDNI.getText().toUpperCase();
                    C.InsertaRegistro("INSERT INTO cliente VALUES('"+Cod+"','"
                            +Frm.txtNombre.getText().toUpperCase()+"','"
                            +Frm.txtDNI.getText().toUpperCase()+"','"
                            +Frm.txtCelular.getText().toUpperCase()+"')");
                    C.Mensaje("YA ESTAS REGISTRADO");
                    Limpiar();
                    if(JOptionPane.showConfirmDialog(null, "¿QUIERES RESERVAR UNA MESA?", "CONSULTA", 0) == 0){
                        C.Mensaje("FORMULARIO RESERVA MESA");
                        Reservas();
                    }else{
                        BuscarCliente();
                    }
                }
            }
        }else{
            C.Mensaje("EL DNI YA ESTA REGISTRADO");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnRegresar){
            BuscarCliente();
        }
        
        if(e.getSource() == Frm.btnguardar){
            Guardar();
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }
}
