package Modelo;

import java.util.*;
import javax.swing.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class Imprimir {
    
    public String rta="";
    public int ctdpgns=0;
    
    public void ImprimirConParametro(String nomrep, String Parametro, String Dato, String Titulo) {
        try {
            Map parametro = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + nomrep + ".jasper";
            parametro.put(Parametro,Dato);
            JasperPrint prt = JasperFillManager.fillReport(rta, parametro, Conexion.conn);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }
    
    public void ImprimirParametros(String nomrep, String Parametro1, String Dato1, String Parametro2, String Dato2, String Parametro3, String Dato3, String Titulo) {
        try {
            Map parametro = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + nomrep + ".jasper";
            parametro.put(Parametro1,Dato1);
            parametro.put(Parametro2,Dato2);
            parametro.put(Parametro3,Dato3);
            JasperPrint prt = JasperFillManager.fillReport(rta, parametro, Conexion.conn);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }
    
    
    public void Imprimir2Param(String nomrep, String Parametro1, String Dato1, String Parametro2, String Dato2, String Titulo) {
        try {
            Map parametro = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + nomrep + ".jasper";
            parametro.put(Parametro1,Dato1);
            parametro.put(Parametro2,Dato2);
            JasperPrint prt = JasperFillManager.fillReport(rta, parametro, Conexion.conn);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }
    
    public void Imprimir(String nomrep) {
        try {
            Map parametro = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/"+ nomrep +".jrxml";
            parametro.put("", "");
            JasperPrint prt = JasperFillManager.fillReport(rta, parametro, Conexion.conn);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte de Empleados");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }
    
}
