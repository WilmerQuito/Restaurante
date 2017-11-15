/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Wilmer Quito
 */
public class Encriptado {
    private static Encriptado Single = null;
    
    private Encriptado() {
    }

    private synchronized static void Instancia() {
        if (Single == null) {
            Single = new Encriptado();
        }
    }

    public static Encriptado getInstance() {
        if (Single == null) {
            Instancia();
        }
        return Single;
    }
    
    //ENCRIPTAR DATOS
    public static String Encriptar(String texto) {
 
        String secretKey = "SsGh"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey Llave = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, Llave);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,String.valueOf(ex.getMessage()));
        }
        return base64EncryptedString;
    }
    
    //DESENCRIPTAR DATOS 
    public static String Desencriptar(String textoEncriptado){
 
        String secretKey = "SsGh"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey Llave = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, Llave);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,String.valueOf(ex.getMessage()));
        }
        return base64EncryptedString;
    }
}
