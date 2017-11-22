/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Controlador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 *
 * @author Wilmer Quito
 */
public class imprime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Controlador C= Controlador.getInstance();
        
        String ruta = System.getProperties().getProperty("user.dir") + "/Base de datos/Java.pdf";
        
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(ruta);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            C.Mensaje(String.valueOf(e));
        }
        if (inputStream == null) {
            return;
        }
 
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
 
        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
 
            } catch (Exception e) {
                e.printStackTrace();
                C.Mensaje(String.valueOf(e));
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }
 
        inputStream.close();
    }
    
}
