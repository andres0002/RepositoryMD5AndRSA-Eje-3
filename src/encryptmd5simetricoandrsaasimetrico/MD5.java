package encryptmd5simetricoandrsaasimetrico;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author William Andres Ramirez Jimenez
 */
public class MD5 {
    public static void main(String[] args) {
        // TODO code application logic here
    }

    /**
     * encodeMD5: Esta función se encarga de encriptar el username con el metodo
     * MD5 Simetrico.
     *
     * @param secretKey: Llave para encriptar.
     * @param username: String a encriptar.
     * @return UsernameEncrypt: String encriptado.
     */
    public String encodeMD5(String secretKey, String username) {
        String UsernameEncrypt = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = username.getBytes("utf-8");
            byte[] buf = cifrado.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            UsernameEncrypt = new String(base64Bytes);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            JOptionPane.showMessageDialog(null, "Se presento una Exceptión y fue: " + ex);
        }
        return UsernameEncrypt;
    }

    /**
     * decodeMD5: Esta función se encarga de desencriptar el username con el
     * metodo MD5 Simetrico.
     *
     * @param secretKey: Llave para desencriptar.
     * @param usernameEncrypted: String a desencriptar.
     * @return usernameDencrypted: String desencriptada.
     */
    public String decodeMD5(String secretKey, String usernameEncrypted) {
        String usernameDencrypted = "";
        try {
            byte[] message = Base64.decodeBase64(usernameEncrypted.getBytes("utf-8"));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            usernameDencrypted = new String(plainText, "UTF-8");

        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            JOptionPane.showMessageDialog(null, "Se presento una Exceptión y fue: " + ex);
        }
        return usernameDencrypted;
    }
}
