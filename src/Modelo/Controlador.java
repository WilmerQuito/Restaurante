package Modelo;
import Modelo.Conexion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Controlador {
    public String sql="";
    public Conexion base = new Conexion();
    public boolean flag;
    public String[] Data = new String[15];
    public int fila;
    
    public void LlenarCombo(JComboBox cb, String cons, String ini,int colum) {
        cb.removeAllItems();
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cons);
            cb.addItem(ini);
            while (base.rs.next()) {
                cb.addItem(base.rs.getString(colum));
            }
            cb.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }
    
    public void MostrarenJTable(DefaultTableModel mdl, String cns, int ctdclmns) {
        LimpJTable(mdl);
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cns);
            while (base.rs.next()) {
                for (int i = 0; i < ctdclmns; i++) {
                    Data[i] = base.rs.getString(i + 1);
                }
                mdl.addRow(Data);
            }
        } catch (Exception e) {
        }
    }
    
    public void LimpJTable(DefaultTableModel mdtb) {
        while (mdtb.getRowCount() > 0) {
            mdtb.removeRow(0);
        }
    }
    
    public void InsertaRegistro(String cns) {
        try {
            base.st = base.conn.createStatement();
            base.st.executeUpdate(cns);
        } catch (Exception e) {
        }
    }
    
    public String CrearRegistros(String cns) {
        String rst = "";
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cns);
            if (base.rs.next()) {
                rst = base.rs.getString(1);
            }
        } catch (Exception e) {
        }
        return rst;
    }
    
    public void Mensage(String Con){
        JOptionPane.showMessageDialog(null, Con);
    }    
    
    public String GeneraCodigo(String cdn, String nomtbl, String cmpcod) {
        String cod = PrmrsLetras(cdn);
        boolean flag = false;
        int ctd = 0;
        while (flag == false) {
            sql = "select * from " + nomtbl + " where " + cmpcod + "='" + cod + "'";
            flag = VerificarConsulta(sql);
            if (flag) {
                ctd++;
                cod = PrmrsLetras(cdn) + "_" + ctd;
                flag = false;
            } else {
                flag = true;
            }
        }
        return cod;
    }
    
    public String PrmrsLetras(String cdn) {
        String ltr = "" + cdn.charAt(0);
        for (int p = 1; p < cdn.length(); p++) {
            if (cdn.charAt(p) == ' ') {
                ltr.concat("" + cdn.charAt(p + 1));
            }
        }
        return ltr;
    }
    
    public boolean VerificarConsulta(String cns) {
        boolean bdr = false;
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cns);
            bdr = base.rs.next();
        } catch (Exception e) {
        }
        return bdr;
    }
    
    public void MostrarenCombo(JComboBox cbo, String dto) {
        for (int j = 0; j < cbo.getModel().getSize(); j++) {
            if (cbo.getModel().getElementAt(j).toString().equalsIgnoreCase(dto)) {
                cbo.setSelectedIndex(j);
                break;
            }
        }
    }
    
    public String  DatoCombo(String cons, int pos){
        String dto = "";
        try {
            sql = cons;
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(sql);
            if (base.rs.next()) {
                dto = base.rs.getString(pos);
            }
        } catch (Exception e) {}
        return dto;
    }
    
    public void LimpiarTabla(DefaultTableModel modelo) {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    
    public void Mostrar(JFrame actual, JFrame next){
        next.setVisible(true);
        actual.setVisible(false);
    }
    
    public  void Acceso(JFrame Actual, JFrame Siguiente, String Usu, String Contra){
        
        try {
            sql="select * from usuario where usuario ='"+Usu+"' and clave ='"+Contra+"'";
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(sql);
            if(base.rs.next()){
                Actual.dispose();
                Siguiente.setVisible(true);
            } else{
                Mensage("LA CLAVE O EL USUARIO SON INCORRECTOS");
            }
        } catch (Exception e) {
        }
    }
    
    public ImageIcon Ajuste(String ruta, int escala){
        ImageIcon IconAux = new ImageIcon(getClass().getResource(ruta));
        ImageIcon TemIcon = new ImageIcon(IconAux.getImage().getScaledInstance(escala, -1,Image.SCALE_DEFAULT));
        return TemIcon;
    }
}
