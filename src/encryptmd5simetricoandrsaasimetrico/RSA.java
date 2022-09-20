package encryptmd5simetricoandrsaasimetrico;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.swing.JOptionPane;

/**
 *
 * @author William Andres Ramirez Jimenez
 */
public class RSA {

    /**
     * publicKey: En esta variable se almacena la llave publica. privateKey: En
     * esta variable se almacena la llave privada.
     *
     */
    private PrivateKey privateKey;
    public PublicKey publicKey;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    /**
     * RSA: Esta función se encarga de generar las llaves Publicas y Privadas.
     *
     */
    public RSA() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "Se presento una Exceptión y fue: " + ex);
        }
    }

    /**
     * encryptRSA: Esta función se encarga de encriptar el password con el
     * metodo RSA Asimetrico.
     *
     * @param password: Password a encriptar.
     * @return: Password encriptado.
     * @throws Exception: Un error si sucede.
     */
    public String encryptRSA(String password) throws Exception {
        byte[] passwordToBytes = password.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(passwordToBytes);
        return encodeRSA(encryptedBytes);
    }

    /**
     *
     * @param data: Data a convertir.
     * @return: Data convertida.
     */
    private String encodeRSA(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * decryptRSA: Esta función se encarga de desencriptar el password con el
     * metodo RSA Asimetrico.
     *
     * @param encryptedPassword: Password encriptado a desencriptar.
     * @return: El password desencriptado.
     * @throws Exception: Un error si sucede.
     */
    public String decryptRSA(String encryptedPassword) throws Exception {
        byte[] encryptedBytes = decodeRSA(encryptedPassword);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedPassword = cipher.doFinal(encryptedBytes);
        return new String(decryptedPassword, "UTF8");
    }

    /**
     *
     * @param data: Data a convertir.
     * @return: Data convertidad.
     */
    private byte[] decodeRSA(String data) {
        return Base64.getDecoder().decode(data);
    }
}
