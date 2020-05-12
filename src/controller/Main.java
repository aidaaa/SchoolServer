package controller;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Main {
    private static final byte[]SALT = {3, (byte) 253, (byte) 245, (byte) 149,86, (byte) 148, (byte) 148,43};

    private static final byte[]IV = {(byte) 139, (byte) 214,102,1, (byte) 150, (byte) 134, (byte) 236, (byte) 182,89,110,20,55, (byte) 243,120,76, (byte) 182};
    private static final String salt = "32532451";
    private static final String cryptPassword = "1133";
    private static final String fileToBeCrypted = "D:\\encrypt\\encrypt.mpg";
    private static final String fileToBeDecrypted = "D:\\encrypt\\encrypt.mpg.crypt";
    private static final String fileDecryptedOutput = "D:\\encrypt\\encrypt.mpg.decrypted";

    public static void main(String[] args) {

            try {
                encryptfile(fileToBeCrypted, cryptPassword);
                decrypt(fileToBeDecrypted, cryptPassword, fileDecryptedOutput);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

    }
    public static void encryptfile(String path,String password) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos = new FileOutputStream(path.concat(".crypt"));
        //byte[] key = {1,6,59,96,43,33,55,80,30,29,71,12,49,98,103,72};

        MessageDigest sha = MessageDigest.getInstance("SHA-256");

        sha.update(SALT);

        sha.update(password.getBytes(StandardCharsets.US_ASCII));


        byte[] key=sha.digest();

       // int a=sha.getDigestLength();
        //key = sha.digest(key);
       // key = Arrays.copyOf(key,16);

     /*   SecretKeyFactory factory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec=new PBEKeySpec(password.toCharArray(),SALT,32768,256);
        SecretKey tmp=factory.generateSecret(keySpec);*/


        SecretKeySpec sks = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //cipher.init(Cipher.ENCRYPT_MODE, sks);


        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE,sks,ivParameterSpec);

        CipherOutputStream cos = new CipherOutputStream(fos, cipher);
        int b;
        byte[] d = new byte[8];
        while((b = fis.read(d)) != -1) {
            cos.write(d, 0, b);
        }
        cos.flush();
        cos.close();
        fis.close();
    }

    public static void decrypt(String path,String password, String outPath) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos = new FileOutputStream(outPath);
       // byte[] key = {1,6,59,96,43,33,55,80,30,29,71,12,49,98,103,72};


        MessageDigest sha = MessageDigest.getInstance("SHA-256");

        sha.update(SALT);

        sha.update(password.getBytes(StandardCharsets.US_ASCII));


        byte[] key=sha.digest();

        // int a=sha.getDigestLength();
        //key = sha.digest(key);
        // key = Arrays.copyOf(key,16);

     /*   SecretKeyFactory factory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec=new PBEKeySpec(password.toCharArray(),SALT,32768,256);
        SecretKey tmp=factory.generateSecret(keySpec);*/


        SecretKeySpec sks = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //cipher.init(Cipher.ENCRYPT_MODE, sks);


        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE,sks,ivParameterSpec);

        CipherInputStream cis = new CipherInputStream(fis, cipher);
        int b;
        byte[] d = new byte[8];
        while((b = cis.read(d)) != -1) {
            fos.write(d, 0, b);
        }
        fos.flush();
        fos.close();
        cis.close();
    }
}
