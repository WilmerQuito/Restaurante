package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    
    public static Connection conn = null;
    public Statement st = null;
    public ResultSet rs = null;
    public static String host, db, url, usu, psw;
    public CallableStatement cs = null;

    public Conexion(String UsuServer, String PswServer, String DataBase, String HostServer) {
        Conectar(UsuServer, PswServer, DataBase, HostServer);
    }

    public void Conectar(String UsuServer, String PswServer, String DataBase, String HostServer) {
        try {
            usu = UsuServer;
            psw = PswServer;
            host = HostServer;
            db = DataBase;
            url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, usu, psw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXION \n"+e.getMessage());
            System.exit(0);
        }
    }
}
