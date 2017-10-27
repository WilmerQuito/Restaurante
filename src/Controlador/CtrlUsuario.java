/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import Vista.FrmUsuario;
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
public class CtrlUsuario implements ActionListener{
    
    private DefaultTableModel DTM=new DefaultTableModel();
    private Controlador C=new Controlador();
    private FrmUsuario Frm;
    
    public CtrlUsuario(FrmUsuario Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        
        Frm.TUsuario.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TUsuario.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TUsuario.getValueAt(C.fila, 0).toString());
                    Frm.txtUsuario.setText(Frm.TUsuario.getValueAt(C.fila, 1).toString());
                    C.MostrarenCombo(Frm.cboRol, Frm.TUsuario.getValueAt(C.fila,2).toString());
                }
            }    
            }
        } );
        
        Frm.txtBuscar.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {Tabla();}

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}
        });
      
    }
    
    public void Iniciar(){
        Frm.setTitle("REGISTRO DE USUARIOS");
        Frm.TUsuario.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","USUARIO","ROL"});
        Frm.TUsuario.getColumnModel().getColumn(1).setPreferredWidth(300);
        Frm.TUsuario.getColumnModel().getColumn(2).setPreferredWidth(300);

        Tabla();
        Rol();
    }
    
    public void Rol(){
        C.sql= "SELECT * FROM Rol";
        C.LlenarCombo(Frm.cboRol, C.sql, "<SELECCIONE>",2);
    }
    
    public boolean Validar(){
        C.flag = true;
        if(!C.ComparaContra(Frm.txtPsw1.getPassword(), Frm.txtPsw2.getPassword())){
            C.flag = false;
            C.Mensaje("LAS CONTRASEÑAS NO COINCIDEN");
            Frm.txtPsw2.grabFocus();
        }else{
            if(Frm.txtPsw1.getText().trim().length() != Frm.txtPsw2.getText().trim().length()){
                C.flag = false;
                C.Mensaje("LAS CONTRASEÑAS NO COINCIDEN2");
                Frm.txtPsw2.grabFocus();
            }else{
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
                        }else{
                            if (Frm.txtPsw1.getText().trim().length() <= 6 || Frm.txtPsw2.getText().trim().length() <= 6) {
                                C.flag = false;
                                C.Mensaje("TAMAÑO DE CONTRASEÑA MINIMO");
                                Frm.txtPsw2.grabFocus();
                            }else{
                                if(Frm.cboRol.getSelectedIndex() == 0){
                                    C.flag = false;
                                    C.Mensaje("SELECCIONA UN ROL");
                                    Frm.txtPsw2.grabFocus();
                                }
                            }
                        }
                    }
                }
            }
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtBuscar.setText(null);
        Frm.txtPsw1.setText(null);
        Frm.txtPsw2.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtUsuario.setText(null);
        Frm.txtUsuario.grabFocus();
        Frm.cboRol.setSelectedIndex(0);
        Frm.TUsuario.clearSelection();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM VtaSesion WHERE Cod_Usuario LIKE '" + Frm.txtBuscar.getText() 
                + "%' or Usuario like '" + Frm.txtBuscar.getText()
                + "%' or Nombre like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 3);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM Usuario WHERE Usuario='"+Frm.txtUsuario.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod=C.GeneraCodigo(Frm.txtUsuario.getText().toUpperCase(), "Usuario", "Cod_Usuario");
                    C.sql=C.DatoCombo("SELECT * FROM Rol WHERE Nombre='"+Frm.cboRol.getSelectedItem().toString()+"'",1);
                    C.InsertaRegistro("INSERT INTO Usuario VALUES('"+Cod+"','"
                            +Frm.txtUsuario.getText().toUpperCase()+"','"
                            +Frm.txtPsw1.getText().toUpperCase()+"','"
                            +C.sql+"')");
                    C.Mensaje("USUARIO REGISTRADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("USUARIO YA SE ENCUENTRA REGISTRADO");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            C.sql=C.DatoCombo("SELECT * FROM Rol WHERE Nombre='"+Frm.cboRol.getSelectedItem().toString()+"'",1);
            String sql="SELECT * FROM Usuario WHERE Usuario='"+Frm.txtUsuario.getText()+"' AND Cod_Rol='"+C.sql+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE Usuario SET Usuario='"+Frm.txtUsuario.getText().toUpperCase()
                            +"', Clave='"+Frm.txtPsw1.getText().toUpperCase()
                            +"', Cod_Rol='"+C.sql
                            +"' WHERE Cod_Usuario='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("USUARIO ACTUALIZADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TUsuario.getSelectedRow();
            if (Frm.TUsuario.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM Usuario WHERE Cod_Usuario='"+Frm.TUsuario.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("USUARIO ELIMINADO");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA USUARIO PARA ELIMINAR");
            }
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }
}
