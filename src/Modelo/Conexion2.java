package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion2 {

    public static Connection conn = null;
    public Statement st = null;
    public ResultSet rs = null;
    public static String usu, psw, host, db, url;

    public Conexion2() {
        Conectar();
    }

    public void Conectar() {
        //String ruta = "C://restosist/Conexion.txt";
        String ruta = System.getProperties().getProperty("user.dir") + "/Complementos/Conexion.txt";
        File archivo = new File(ruta);
        String linea = null;
        
        try {

            int numLineas = 8;
            int contador = 0;

            String datos[] = new String[numLineas];

            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            linea = reader.readLine();

            while (linea != null && contador < numLineas) {
                datos[contador] = linea;
                linea = reader.readLine();
                contador++;
            }
            
            try {
                usu = datos[1];
                psw = datos[3];
                host = datos[5];
                db = datos[7];
                url = "jdbc:mysql://" + host + "/" + db;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, usu, psw);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.exit(0);
            }
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "NO ENCUENTRA ARCHIVO");
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL ABRIR");
            System.exit(0);
        }
    }
}
