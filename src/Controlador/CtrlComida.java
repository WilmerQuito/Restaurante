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
public class CtrlComida implements ActionListener{
    
    private DefaultTableModel DTM=new DefaultTableModel();
    private Controlador C=new Controlador();
    private FrmComida Frm;
    
    public CtrlComida (FrmComida Frm){
        this.Frm = Frm;
       
        Frm.btneditar.addActionListener(this);
        Frm.btneliminar.addActionListener(this);
        Frm.btnguardar.addActionListener(this);
        Frm.btnlimpiar.addActionListener(this);
        
        Frm.TComida.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                C.fila = Frm.TComida.getSelectedRow();
                if (C.fila > -1) {
                    Frm.txtCodigo.setText(Frm.TComida.getValueAt(C.fila, 0).toString());
                    C.MostrarenCombo(Frm.cboOrigen, Frm.TComida.getValueAt(C.fila,1).toString());
                    Frm.txtNombre.setText(Frm.TComida.getValueAt(C.fila, 2).toString());
                    Frm.txtPrecio.setText(Frm.TComida.getValueAt(C.fila, 3).toString());
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
        Frm.TComida.setModel(DTM);
        DTM.setColumnIdentifiers(new String[]{"CODIGO","ORIGEN","COMIDA","PRECIO"});
        
        Tabla();
        Origen();
    }
    
    public void Origen(){
        C.sql= "SELECT * FROM Origen";
        C.LlenarCombo(Frm.cboOrigen, C.sql, "<SELECCIONE>",2);
    }
    
    public boolean Validar(){
        C.flag = true;
        if (Frm.txtNombre.getText().trim().length() == 0) {
            C.flag = false;
            C.Mensaje("INGRESA EL NOMBRE DE LA COMIDA");
            Frm.txtNombre.grabFocus();
        } else {
            if (Frm.cboOrigen.getSelectedIndex() == 0) {
                C.flag = false;
                C.Mensaje("SELECCIONA EL ORIGEN DE LA COMIDA");
                Frm.cboOrigen.grabFocus();
            } else {
                if (Frm.txtPrecio.getText().trim().length() == 0) {
                    C.flag = false;
                    C.Mensaje("INGRESA EL PRECIO DE COMIDA");
                    Frm.txtPrecio.grabFocus();
                }
            }
        }
        return C.flag;
    }
    
    public void Limpiar(){
        Frm.txtBuscar.setText(null);
        Frm.txtCodigo.setText(null);
        Frm.txtNombre.setText(null);
        Frm.txtPrecio.setText(null);
        Frm.cboOrigen.setSelectedIndex(0);
        Frm.txtNombre.grabFocus();
        Frm.TComida.clearSelection();
        Tabla();
    }
    
    public void Tabla() {
        C.sql = "SELECT * FROM VtaComida WHERE idcomida LIKE '" + Frm.txtBuscar.getText() 
                + "%' or origencol like '" + Frm.txtBuscar.getText()
                + "%' or nombre_comida like '" + Frm.txtBuscar.getText()
                + "%' or costo like '" + Frm.txtBuscar.getText()
                + "%'";
        C.MostrarenJTable(DTM, C.sql, 4);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String Origen=C.DatoCombo("SELECT * FROM origen WHERE origencol='"+Frm.cboOrigen.getSelectedItem().toString()+"'",1);
        
        if(e.getSource() == Frm.btnguardar){
            String sql="SELECT * FROM vtacomida WHERE nombre_comida='"+Frm.txtNombre.getText()+"' AND origencol='"+Frm.cboOrigen.getSelectedItem().toString()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    String Cod=C.GeneraCodigo(Frm.txtNombre.getText().toUpperCase(), "comida", "idcomida");
                    C.InsertaRegistro("INSERT INTO comida VALUES('"+Cod+"','"
                            +Frm.txtNombre.getText().toUpperCase()+"','"
                            +Frm.txtPrecio.getText().toUpperCase()+"','"
                            +Origen+"')");
                    C.Mensaje("COMIDA REGISTRADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("LA COMIDA YA SE ENCUENTRA REGISTRADO");
            }
        }
        
        if(e.getSource() == Frm.btneditar){
            String sql="SELECT * FROM vtacomida WHERE nombre_comida='"+Frm.txtNombre.getText()
                    +"' AND origencol='"+Frm.cboOrigen.getSelectedItem().toString()
                    +"' AND costo='"+Frm.txtPrecio.getText()+"';";
            if(!C.VerificarConsulta(sql)){
                if (Validar()) {
                    C.InsertaRegistro("UPDATE comida SET nombre_comida='"+Frm.txtNombre.getText().toUpperCase()
                            +"', costo='"+Frm.txtPrecio.getText().toUpperCase()
                            +"', origen_idorigen='"+Origen
                            +"' WHERE idcomida='"+Frm.txtCodigo.getText()+"'");
                    C.Mensaje("COMIDA ACTUALIZADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("ERROR AL MODIFICAR: DATOS REPETIDOS");
            }
        }
        
        if(e.getSource() == Frm.btneliminar){
            C.fila = Frm.TComida.getSelectedRow();
            if (Frm.TComida.getSelectedRow() > -1) {
                if (JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", 0) == 0) {
                    C.InsertaRegistro("DELETE FROM comida WHERE idcomida='"+Frm.TComida.getValueAt(C.fila, 0).toString()+"'");
                    C.Mensaje("COMIDA ELIMINADA");
                    Tabla();
                    Limpiar();
                }
            }else{
                C.Mensaje("SELECCIONA COMIDA PARA ELIMINAR");
            }
        }
        
        if(e.getSource() == Frm.btnlimpiar){
            Limpiar();
        }
    }

}
