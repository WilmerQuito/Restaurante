package Modelo;

import java.util.*;
import javax.swing.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class ImprimeReporte {
    
    public String ruta="";
    public int ctdpgns=0;
    
    public void ImprimirConParametro(String nomrep, String Parametro, String Dato, String Titulo) {
        try {
            Map parametro = new HashMap();
            ruta = System.getProperties().getProperty("user.dir") + "/Reportes/" + nomrep + ".jasper";
            parametro.put(Parametro,Dato);
            JasperPrint prt = JasperFillManager.fillReport(ruta, parametro, Conexion.conn);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "NO HAY DATOS");
            }
        } catch (Exception E) {
        }
    }
    
    public void ImprimirParametros(String nomrep, String Parametro1, String Dato1, String Parametro2, String Dato2, String Titulo) {
        try {
            Map parametro = new HashMap();
            ruta = System.getProperties().getProperty("user.dir") + "/Reportes/" + nomrep + ".jasper";
            parametro.put(Parametro1,Dato1);
            parametro.put(Parametro2,Dato2);
            JasperPrint prt = JasperFillManager.fillReport(ruta, parametro, Conexion.conn);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "NO HAY DATOS");
            }
        } catch (Exception E) {
        }
    }
    
    
    public void Imprimir(String NomRepor, String Titulo) {
        try {
            Map parametro = new HashMap();
            ruta = System.getProperties().getProperty("user.dir") + "/Reportes/"+ NomRepor +".jasper";
            parametro.put("", "");
            JasperPrint prt = JasperFillManager.fillReport(ruta, parametro, Conexion.conn);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "NO HAY DATOS");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
