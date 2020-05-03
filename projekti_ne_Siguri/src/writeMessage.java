import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Random;

public class writeMessage {

    public static void write_Message(String outFile, String message) throws Exception {
        File file = new File("keys/" + outFile + ".pub.xml"); //Path to xml file
        if (file.exists()) {
            byte[] bytes = outFile.getBytes("UTF-8");
            String emri = Base64.getEncoder().encodeToString(bytes);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            doc.getDocumentElement().normalize();

            //gjenerimi i 8 bytes per iv
            SecureRandom sr = new SecureRandom(); //create new secure random
            byte[] iv = new byte[8]; //create an array of 8 bytes
            sr.nextBytes(iv);

            String ivi = Base64.getEncoder().encodeToString(iv);

            //gjenerimi i 8 bytes per qelesin key dhe konvertimi ne qeles sekret
            Random keyi = new Random();
            byte[] keys = new byte[8];
            keyi.nextBytes(keys);
            KeySpec myKeySpec = new DESKeySpec(keys);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

            SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


            //Fitimi i qelesit publik nga XML dokumenti
            String moduliS = ((org.w3c.dom.Document) doc).getElementsByTagName("Modulus").item(0).getTextContent();
            String exponentAsString = ((org.w3c.dom.Document) doc).getElementsByTagName("Exponent").item(0).getTextContent();

            BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
            BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentAsString));

            //formimi i RSA publci key nga file ne xml
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            //enkriptimi i rsa key me key
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] encryptedKe = cipher.doFinal(celesi.getEncoded());
            String encryptedKey = Base64.getEncoder().encodeToString(encryptedKe);

            //enkriptimi i mesazhit me key
            Cipher encryptedcipher = Cipher.getInstance("DES");
            encryptedcipher.init(Cipher.ENCRYPT_MODE, celesi);
            byte[] utf8 = message.getBytes("UTF8");
            byte[] enc = encryptedcipher.doFinal(utf8);
            enc = Base64.getEncoder().encode(enc);
            String encryptedWord = Base64.getEncoder().encodeToString(enc);




            System.out.println(emri + "." + ivi + "." + encryptedKey + "." + encryptedWord);
        } else {
            System.out.println("Gabim:Qelesi publik '" + outFile + "' nuk ekziston");
        }
    }
    public static void write_Message(String outFile, String message, String file) {
        try {
            File pubfile = new File("keys/" + outFile + ".pub.xml"); //Path to xml file
            if (pubfile.exists()) {
                byte[] bytes = outFile.getBytes("UTF-8");
                String emri = Base64.getEncoder().encodeToString(bytes);

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(pubfile);
                doc.getDocumentElement().normalize();

                //gjenerimi i 8 bytes per iv
                SecureRandom sr = new SecureRandom(); //create new secure random
                byte[] iv = new byte[8]; //create an array of 8 bytes
                sr.nextBytes(iv);

                String ivi = Base64.getEncoder().encodeToString(iv);

                //gjenerimi i 8 bytes per qelesin key dhe konvertimi ne qeles sekret
                Random keyi = new Random();
                byte[] keys = new byte[8];
                keyi.nextBytes(keys);
                KeySpec myKeySpec = new DESKeySpec(keys);
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

                SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


                //Fitimi i qelesit publik nga XML dokumenti
                String moduliS = ((org.w3c.dom.Document) doc).getElementsByTagName("Modulus").item(0).getTextContent();
                String exponentAsString = ((org.w3c.dom.Document) doc).getElementsByTagName("Exponent").item(0).getTextContent();

                BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
                BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentAsString));

                //formimi i RSA publci key nga file ne xml
                RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey pubKey = keyFactory.generatePublic(keySpec);

                //enkriptimi i rsa key me key
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                byte[] encryptedKe = cipher.doFinal(celesi.getEncoded());
                String encryptedKey = Base64.getEncoder().encodeToString(encryptedKe);

                //enkriptimi i mesazhit me key
                Cipher encryptedcipher = Cipher.getInstance("DES");
                encryptedcipher.init(Cipher.ENCRYPT_MODE, celesi);
                byte[] utf8 = message.getBytes("UTF8");
                byte[] enc = encryptedcipher.doFinal(utf8);
                enc = Base64.getEncoder().encode(enc);
                String encryptedWord = Base64.getEncoder().encodeToString(enc);


                //  System.out.println(emri + "." + ivi + "." + encryptedKey + "." + encryptedWord);

                FileOutputStream outStream = new FileOutputStream(new File(String.valueOf(file)));

                String str = emri + "." + ivi + "." + encryptedKey + "." + encryptedWord;
                byte[] strToByte = str.getBytes();
                outStream.write(strToByte);
                System.out.println("Mesazhi i enkriptuar u ruajt ne fajllin " + file);
            }
        } catch (InvalidKeyException | ParserConfigurationException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}