package Modelo;
import Modelo.Conexion;
import com.placeholder.PlaceHolder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.util.Date;
import javax.swing.ImageIcon;


public class Controlador {
    public String sql="";
    public Conexion base = new Conexion();
    public boolean flag;
    public String[] Data = new String[15];
    public int fila;
    public PlaceHolder holder;
    public Imprimir impri = new Imprimir();
    
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
        LimpiarTabla(mdl);
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
    
    public void Mensaje(String Con){
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
                cod = PrmrsLetras(cdn) + "-" + ctd;
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
        
    public void Acceso(JFrame Actual, JFrame Siguiente, String Usu, String Contra, String Rol){
        try {
            sql="SELECT * FROM VtaSesion WHERE Usuario='"+Usu+"' AND Clave='"+Contra+"' AND Rol='"+Rol+"'";
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(sql);
            if(base.rs.next()){
                Actual.dispose();
                Siguiente.setVisible(true);
            } else{
                Mensaje("ERROR AL INICIAR SESION");                
            }
        } catch (Exception e) {
        }
    }
    
    public ImageIcon Ajuste(String ruta, int escala){
        ImageIcon IconAux = new ImageIcon(getClass().getResource(ruta));
        ImageIcon TemIcon = new ImageIcon(IconAux.getImage().getScaledInstance(escala, -1,Image.SCALE_DEFAULT));
        return TemIcon;
    }
    
    public boolean ComparaContra(char[] Psw1, char[] Psw2) {
        boolean valor = true;
        int puntero = 0;
        if (Psw1.length != Psw2.length) {
            valor = false;
        } else {
            while ((valor) && (puntero < Psw1.length)) {
                if (Psw1[puntero] != Psw2[puntero]) {
                    valor = false;
                }
                puntero++;
            }
        }
        return valor;
    }
    
    public void Holder(JTextField txt, String msj){
        holder = new PlaceHolder(txt, msj);
    }
    
    public String DevolverDatoString(String cnst, int posdev) {
        String dto = "";
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cnst);
            if (base.rs.next()) {
                dto = base.rs.getString(posdev);
            }
        } catch (Exception e) {
        }
        return dto;
    }
    
    public Double DevolverDatoDouble(String cnst, int posdev) {
        double dto =0;
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cnst);
            if (base.rs.next()) {
                dto = base.rs.getDouble(posdev);
            }
        } catch (Exception e) {
        }
        return dto;
    }
    
    public Double DevolverDatoInteger(String cnst, int posdev) {
        double dto =0;
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cnst);
            if (base.rs.next()) {
                dto = base.rs.getInt(posdev);
            }
        } catch (Exception e) {
        }
        return dto;
    }
    
    public Date DevolverDatoDate(String cnst, int posdev) {
        Date dto=null;
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cnst);
            if (base.rs.next()) {
                dto = base.rs.getDate(posdev);
            }
        } catch (Exception e) {
        }
        return dto;
    }
    
    public void MarcarTexto(JTextField txt) {
        txt.setSelectionStart(0);
        txt.setSelectionEnd(txt.getText().length());
        txt.requestFocus();
    }
    
}
