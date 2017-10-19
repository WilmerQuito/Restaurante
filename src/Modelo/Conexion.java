package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {

    public static Connection conn = null;
    public Statement st = null;
    public ResultSet rs = null;
    public static String usu, psw, host, db, url;

    public Conexion() {
        Conectar();
    }

    public void Conectar() {
        try {
            usu = "root";
            psw = "root123456";
            host = "localhost";
            db = "db_restaurante";
            url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, usu, psw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
