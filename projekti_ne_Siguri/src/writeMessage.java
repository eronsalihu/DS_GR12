import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

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
            String encryptedWord = new String(enc);




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
    public static void write_messageSender(String outFile, String message, String sender) throws Exception {
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
            String encryptedWord = new String(enc);
            System.out.println(enc);

            String[] stringss = sender.split("\\.");
            ArrayList < String > aListNumberss = new ArrayList < String > (Arrays.asList(stringss));
            byte[] tedhenat = Base64.getDecoder().decode(aListNumberss.get(1));
            String data = new String(tedhenat);
            String nenshkrimi = sender.split("\\.")[2];

            String name = data.split("'")[3];

            String dataSkadimit = data.split("'")[7];
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date resultdate = new Date(currentTimeMillis);
            String string = sdf.format(resultdate);
            String name_b64 = Base64.getEncoder().encodeToString(name.getBytes());

            File file1 = new File("keys/" + name + ".xml");

            DocumentBuilderFactory docFactory1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder1 = docFactory1.newDocumentBuilder();
            Document doc1 = docBuilder1.parse(file1);
            doc1.getDocumentElement().normalize();

            String moduliSp = doc1.getElementsByTagName("Modulus").item(0).getTextContent();
            String D = doc1.getElementsByTagName("D").item(0).getTextContent();

            BigInteger modulusp = new BigInteger(Base64.getDecoder().decode(moduliSp));
            BigInteger d = new BigInteger(Base64.getDecoder().decode(D));


            RSAPrivateKeySpec keySpec1 = new RSAPrivateKeySpec(modulusp, d);
            KeyFactory keyFactory1 = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory1.generatePrivate(keySpec1);

            Signature privateSignature = Signature.getInstance("SHA1withRSA");
            privateSignature.initSign(privateKey);
            privateSignature.update((encryptedWord).getBytes(UTF_8));

            byte[] signature = privateSignature.sign();

            String signi = Base64.getEncoder().encodeToString(signature);




            if (logIn.verify(sender)) {


                int compareTO = string.compareTo(dataSkadimit);
                if (compareTO < 0) {
                    boolean has = lidhjameDB.gjejebool(name);
                    if (has == true) {
                        System.out.println(emri + "." + ivi + "." + encryptedKey + "." + encryptedWord + "." + name_b64 + "." + signi);
                    } else
                        System.out.println("Tokeni nuk eshte valid");
                } else
                    System.out.println("Tokeni nuk eshte valid");

            } else {
                System.out.println("Tokeni nuk eshte valid");
            }




        } else {
            System.out.println("Gabim:Qelesi publik '" + outFile + "' nuk ekziston");
        }
    }
}