/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wilmer Quito
 */
public class ArchivoTXT {

    private static ArchivoTXT Single = null;

    private ArchivoTXT() {
    }

    private synchronized static void Instancia() {
        if (Single == null) {
            Single = new ArchivoTXT();
        }
    }

    public static ArchivoTXT getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }

    public void CrearArchivo(String ruta, String Datos) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(ruta);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(Datos); //CREA NUEVA PALABRA
            bfwriter.newLine(); //AGREGA SALTO DE LINEA
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList LeerArchivo(String ruta) {
        File file = new File(ruta);
        ArrayList Ticket = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                //se usa una expresión regular
                //que valida que antes o despues de una coma (,) exista cualquier cosa
                //parte la cadena recibida cada vez que encuentre una coma				
                //delimitar.useDelimiter("\\s*,\\s*");
                Ticket.add(delimitar.next());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Ticket;
    }

    //añadir más estudiantes al archivo
    public void AñadirDatos(String ruta, String Datos) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(ruta, true);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(Datos); //ESCRIBE NUEVA LINEA
            bfwriter.newLine(); //AGREGA SALTO DE LINEA
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Imprimir(String ruta) {     
        try {
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }

            desktop.print(new File(ruta));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
