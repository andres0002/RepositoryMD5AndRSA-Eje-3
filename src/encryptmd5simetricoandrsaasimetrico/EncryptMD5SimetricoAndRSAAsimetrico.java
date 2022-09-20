package encryptmd5simetricoandrsaasimetrico;

import javax.swing.JOptionPane;
import encryptmd5simetricoandrsaasimetrico.MD5;
import encryptmd5simetricoandrsaasimetrico.RSA;

/**
 *
 * @author William Andres Ramirez Jimenez
 */
public class EncryptMD5SimetricoAndRSAAsimetrico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Clave (secretKey) para encriptar y desencriptar usando el metodo MD5 Sincrono.
        String secretKey = "WilliamAndresRamirezJimenez";
        MD5 md5 = new MD5();
        RSA rsa = new RSA();
        // Valores de entrada username - password.
        String username = JOptionPane.showInputDialog("Ingrese el Username");
        String password = JOptionPane.showInputDialog("Ingrese el Password");
        // Funciones de Encripción (encodeMD5) - Desencripción (decodeMD5) Simetrico.
        String usernameEncrypted = md5.encodeMD5(secretKey, username);
        String usernameDencrypted = md5.decodeMD5(secretKey, usernameEncrypted);
        // Valores en consola.
        System.out.println("Username Ingresado: " + username);
        System.out.println("Password Ingresado: " + password);
        System.out.println("Username Encriptado: " + usernameEncrypted);
        System.out.println("Username Desencriptado: " + usernameDencrypted);
        try {
            // Funciones de Encripción (encryptRSA) - Desencripción (decryptRSA) Asimetrico.
            String passwordEncrypted = rsa.encryptRSA(password);
            String passwordDencrypted = rsa.decryptRSA(passwordEncrypted);
            // Valores en consola.
            System.out.println("Password Encriptado:\n" + passwordEncrypted);
            System.out.println("Password Desencriptado: " + passwordDencrypted);
        } catch (Exception ingored) {
        }
    }
}
