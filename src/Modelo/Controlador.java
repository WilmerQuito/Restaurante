package Modelo;

import PatronVistas.*;
import Controlador.*;
import Modelo.Conexion;
import Vista.*;
import com.placeholder.PlaceHolder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.io.*;
import java.util.*;
import java.text.*;

public class Controlador {
    public static String UsuServer=null, PswServer=null, DataBase=null, HostServer=null;
    public String sql = "";
    public Conexion base = new Conexion(UsuServer, PswServer, DataBase, HostServer);
    public boolean flag;
    public String[] Data = new String[15];
    public int fila;
    public PlaceHolder holder;
    public ImprimeReporte impri = new ImprimeReporte();
    private static Controlador Single = null;
    public static boolean Cerrar = false;
    ArchivoTXT A = ArchivoTXT.getInstance();

//METODO SINGLETON
    private Controlador() {
    }

    private synchronized static void Instancia() {
        if (Single == null) {
            Single = new Controlador();
        }
    }

    public static Controlador getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }

    //MUESTRA DATOS DE UNA TABLA EN UN COMBO SEGUN LA POSICION
    public void LlenarCombo(JComboBox cb, String cons, String ini, int colum) {
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
            Mensaje(String.valueOf(e));
        }
    }
    
    //CREACION DE DATOS PARA EL ACCESO AL SERVIDOR
    public void AccesoServer(){
        String ruta = System.getProperties().getProperty("user.dir") + "/Complementos/Conexion.txt";
        A.CrearArchivo(ruta, "#USUARIO");
        A.AñadirDatos(ruta, UsuServer);
        A.AñadirDatos(ruta, "#PASSWORD");
        A.AñadirDatos(ruta, PswServer);
        A.AñadirDatos(ruta, "#BASE DE DATOS");
        A.AñadirDatos(ruta, DataBase);
        A.AñadirDatos(ruta, "#HOST DE ACCESO");
        A.AñadirDatos(ruta, HostServer);
    }
    
    //REALIZA BACKUP DE LA BASE DE DATOS
    public static void Backup(){
        Date date = new Date();
        DateFormat HoraFecha = new SimpleDateFormat("hh.mm.ss a_dd-MM-yyyy");
        try {
            Process p = Runtime.getRuntime().exec("C:\\AppServ\\MySQL\\bin\\mysqldump -e -u "+UsuServer+" -p"+PswServer+" restaurante");

            InputStream is = p.getInputStream();
            //FileOutputStream fos = new FileOutputStream("C:\\Backups\\LaDelicia_"+HoraFecha.format(date)+".sql");
            FileOutputStream fos = new FileOutputStream(System.getProperties().getProperty("user.dir")+ "/Complementos/LaDelicia_"+HoraFecha.format(date)+".sql");
             
            byte[] buffer = new byte[1000];

            int leido = is.read(buffer);
            while (leido > 0) {
                fos.write(buffer, 0, leido);
                leido = is.read(buffer);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //MUESTRA TODOS LOS DATOS EN UN JTABLE DE ACUERDO A UNA CONSULTA
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
            Mensaje(String.valueOf(e));
        }
    }

    //EJECUTA UNA SENTENCIA SQL
    public void InsertaRegistro(String cns) {
        try {
            base.st = base.conn.createStatement();
            base.st.executeUpdate(cns);
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
    }

    //CREAR UN REGISTRO EN LA BASE DE DATOS
    public String CrearRegistros(String cns) {
        String rst = "";
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cns);
            if (base.rs.next()) {
                rst = base.rs.getString(1);
            }
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
        return rst;
    }

    //MOSTRAR MENSAJES 
    public void Mensaje(String Con) {
        JOptionPane.showMessageDialog(null, Con);
    }

    //GENERA UN CODIGO CONSULTADO LA EXISTENCIA
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

    //OBTENE LAS PRIMERAS LETRAS DE UNA PALABRA
    public String PrmrsLetras(String cdn) {
        String ltr = "" + cdn.charAt(0);
        for (int p = 1; p < cdn.length(); p++) {
            if (cdn.charAt(p) == ' ') {
                ltr.concat("" + cdn.charAt(p + 1));
            }
        }
        return ltr;
    }

    //VERIFICA LA EXISTENCIA DE UN DATO CONSULTADO
    public boolean VerificarConsulta(String cns) {
        boolean bdr = false;
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cns);
            bdr = base.rs.next();
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
        return bdr;
    }
    
    //MUESTRA EL DATO DE UNA TABLA EN UN COMBO
    public void MostrarenCombo(JComboBox cbo, String dto) {
        for (int j = 0; j < cbo.getModel().getSize(); j++) {
            if (cbo.getModel().getElementAt(j).toString().equalsIgnoreCase(dto)) {
                cbo.setSelectedIndex(j);
                break;
            }
        }
    }
    
    //OBTENER UN DATO DE LA TABLA SEGUN LA POSICION DESEADA
    public String DatoCombo(String cons, int pos) {
        String dto = "";
        try {
            sql = cons;
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(sql);
            if (base.rs.next()) {
                dto = base.rs.getString(pos);
            }
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
        return dto;
    }

    //LIMPIA EL JTABLA QUE SE PASE POR PARAMETRO
    public void LimpiarTabla(DefaultTableModel modelo) {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    //CAMBIA DE UN FORMULARIO A OTRO
    public void Mostrar(JFrame actual, JFrame next) {
        next.setVisible(true);
        actual.setVisible(false);
    }

    //COMPARA LOS DATOS DE LA BASE DE DATOS CON LA INGRESADA PARA DAR ACCESO
    public void Acceso(JFrame Actual, JFrame Siguiente, String Usu, String Contra, String Rol) {
        try {
            sql = "SELECT * FROM vtasesion WHERE usuario='" + Usu + "' AND clave='" + Contra + "' AND rol='" + Rol + "'";
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(sql);
            if (base.rs.next()) {
                Actual.dispose();
                Siguiente.setVisible(true);
            } else {
                Mensaje("ERROR AL INICIAR SESION");
            }
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
    }

    //AJUSTA LA IMAGEN QUE SE REQUIERA PONER EN UN FORMULARIO
    public ImageIcon Ajuste(String ruta, int escala) {
        ImageIcon IconAux = new ImageIcon(getClass().getResource(ruta));
        ImageIcon TemIcon = new ImageIcon(IconAux.getImage().getScaledInstance(escala, -1, Image.SCALE_DEFAULT));
        return TemIcon;
    }

    //COMPARA LAS CONTRASEÑAS POR LONGITUD
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
    
    //MUESTRA MARCA DE AGUA EN LOS JTEXT
    public void Holder(JTextField txt, String msj) {
        holder = new PlaceHolder(txt, msj);
    }

    //DEVUELVE UN DATOS DE TIPO STRING DE LA BD
    public String DevolverDatoString(String cnst, int posdev) {
        String dto = "";
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cnst);
            if (base.rs.next()) {
                dto = base.rs.getString(posdev);
            }
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
        return dto;
    }

    //DEVUELVE UN DATOS DE TIPO DOUBLE DE LA BD
    public Double DevolverDatoDouble(String cnst, int posdev) {
        double dto = 0;
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cnst);
            if (base.rs.next()) {
                dto = base.rs.getDouble(posdev);
            }
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
        return dto;
    }

    //DEVUELVE UN DATOS DE TIPO ENTERO DE LA BD
    public Double DevolverDatoInteger(String cnst, int posdev) {
        double dto = 0;
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cnst);
            if (base.rs.next()) {
                dto = base.rs.getInt(posdev);
            }
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
        return dto;
    }

    //DEVUELVE UN DATOS DE TIPO DATE DE LA BD
    public Date DevolverDatoDate(String cnst, int posdev) {
        Date dto = null;
        try {
            base.st = base.conn.createStatement();
            base.rs = base.st.executeQuery(cnst);
            if (base.rs.next()) {
                dto = base.rs.getDate(posdev);
            }
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
        return dto;
    }
    
    //MARCA UN TEXTO ERRADO
    public void MarcarTexto(JTextField txt) {
        txt.setSelectionStart(0);
        txt.setSelectionEnd(txt.getText().length());
        txt.requestFocus();
    }

    //LLAMA AL FORMULARIO DE BUSQUEDA DE CLIENTE
    public void BuscarCliente(JFrame Actual) {
        try {
            FrmBuscarCliente Frm = PtBuscarCliente.getInstance();
            CtrlBuscarCliente Ctl = CtrlBuscarCliente.getInstance(Frm);
            Ctl.Iniciar();
            Frm.setVisible(true);
            Actual.setVisible(false);
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
    }
    
    //LLAMA AL FORMULARIO DE BUQUEDA DE CLIENTE INICIAL
    public void BuscarClienteInicial() {
        try {
            FrmBuscarCliente Frm = PtBuscarCliente.getInstance();
            CtrlBuscarCliente Ctl = CtrlBuscarCliente.getInstance(Frm);
            Ctl.Iniciar();
            Frm.setVisible(true);
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
    }

    //LLAMA AL FORMULARIO DE CLIENTE NUEVO
    public void ClienteNuevo(JFrame Actual) {
        try {
            FrmClienteNuevo Frm = PtClienteNuevo.getInstance();
            CtrlClienteNuevo Ctl = CtrlClienteNuevo.getInstance(Frm);
            Ctl.Iniciar();
            Frm.setVisible(true);
            Actual.setVisible(false);
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
    }
    
    //LLAMA AL FORMULARIO DE LOGIN PERSONAL
    public void LoginPersonal(JFrame Actual) {
        try {
            FrmLoginPersonal Frm = PtLoginPersonal.getInstance();
            CtrlLoginPersonal Ctl = CtrlLoginPersonal.getInstance(Frm);
            Ctl.Iniciar();
            Frm.setVisible(true);
            Actual.setVisible(false);
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
    }

    //LLAMA AL FORMULARIO DE RESERVAS
    public void Reservas(JFrame Actual) {
        try {
            FrmReservaCliente Frm = PtReservaCliente.getInstance();
            CtrlReservaCliente Ctl = CtrlReservaCliente.getInstance(Frm);
            Ctl.Iniciar();
            Frm.setVisible(true);
            Actual.setVisible(false);
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
    }
    
    //LLAMA AL FORMULARIO DE VIDEO
    public void Video() {
        try {
            FrmVideo Frm = PtVideo.getInstance();
            CtrlVideo Ctl = CtrlVideo.getInstance(Frm);
            Ctl.Iniciar();
            Frm.setVisible(true);
        } catch (Exception e) {
            Mensaje(String.valueOf(e));
        }
    }
}
