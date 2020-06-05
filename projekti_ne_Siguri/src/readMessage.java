import org.w3c.dom.Document;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;


public class readMessage {

    public static void decrypt(String message) {

        File filePath= new File("src/"+message+".txt");

        if (filePath.exists()) {
            StringBuilder contentBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    contentBuilder.append(sCurrentLine).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String txtFromFile = contentBuilder.toString();

            String[] stringss = txtFromFile.split("\\.");
            if (stringss.length==4){
            ArrayList<String> aListNumberss = new ArrayList<String>(Arrays.asList(stringss));
            try {

                byte[] emri = Base64.getDecoder().decode(aListNumberss.get(0));
                String ivi = aListNumberss.get(1);
                String encrypti = aListNumberss.get(3);


                byte[] decodedBytesKey = Base64.getDecoder().decode(aListNumberss.get(2));

                String decodeemri = new String(emri);
                System.out.println("Marresi: " + decodeemri);


                File file = new File("keys/", decodeemri + ".xml");
                if (file.exists()) {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(file);
                    doc.getDocumentElement().normalize();

                    String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
                    String D = doc.getElementsByTagName("D").item(0).getTextContent();

                    BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
                    BigInteger d = new BigInteger(Base64.getDecoder().decode(D));

                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, d);
                    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);


                    Cipher cipher = Cipher.getInstance("RSA");
                    cipher.init(Cipher.DECRYPT_MODE, privateKey);
                    byte[] key = cipher.doFinal(decodedBytesKey);

                    KeySpec myKeySpec = new DESKeySpec(key);
                    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
                    SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


                    String a = aListNumberss.get(3);
                    byte[] byteArray = Base64.getDecoder().decode(a.getBytes());


                    Cipher decryptCipher = Cipher.getInstance("DES");
                    decryptCipher.init(Cipher.DECRYPT_MODE, celesi);
                    //   byte[] utf8 = Base64.getDecoder().decode(aListNumbers.get(3));
                    byte[] dec = decryptCipher.doFinal(byteArray);
                    String decryptedWord = new String(dec);
                    System.out.println("Mesazhi:" + decryptedWord);


                } else {
                    System.out.println("Gabim: Celesi privat '" + file + "' nuk ekziston.");
                }

            } catch (Exception e) {

            }}
            else {
                ArrayList<String> aListNumberss = new ArrayList<String>(Arrays.asList(stringss));
                try {

                    byte[] emri = Base64.getDecoder().decode(aListNumberss.get(0));
                    String ivi = aListNumberss.get(1);
                    String encrypti = aListNumberss.get(3);
                    byte [] sender=Base64.getDecoder().decode(aListNumberss.get(4));
                    String senderName=new String(sender);

                    byte[] decodedBytesKey = Base64.getDecoder().decode(aListNumberss.get(2));

                    String decodeemri = new String(emri);
                    System.out.println("Marresi: " + decodeemri);


                    File file = new File("keys/", decodeemri + ".xml");
                    if (file.exists()) {
                        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                        Document doc = docBuilder.parse(file);
                        doc.getDocumentElement().normalize();

                        String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
                        String D = doc.getElementsByTagName("D").item(0).getTextContent();

                        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
                        BigInteger d = new BigInteger(Base64.getDecoder().decode(D));

                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, d);
                        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);


                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE, privateKey);
                        byte[] key = cipher.doFinal(decodedBytesKey);

                        KeySpec myKeySpec = new DESKeySpec(key);
                        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
                        SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


                        String a = aListNumberss.get(3);
                        byte[] byteArray = Base64.getDecoder().decode(a.getBytes());


                        Cipher decryptCipher = Cipher.getInstance("DES");
                        decryptCipher.init(Cipher.DECRYPT_MODE, celesi);
                        //   byte[] utf8 = Base64.getDecoder().decode(aListNumbers.get(3));
                        byte[] dec = decryptCipher.doFinal(byteArray);
                        String decryptedWord = new String(dec);
                        System.out.println("Mesazhi:" + decryptedWord);
                        System.out.println("Derguesi: "+senderName);

                    } else {
                        System.out.println("Gabim: Celesi privat '" + file + "' nuk ekziston.");
                    }

                } catch (Exception e) {

                }
            }
        }
        else {
            String[] strings = message.split("\\.");
            if (strings.length==4){
            ArrayList<String> aListNumbers = new ArrayList<String>(Arrays.asList(strings));
            try {

                byte[] emri = Base64.getDecoder().decode(aListNumbers.get(0));
                String ivi = aListNumbers.get(1);
                String encrypti = aListNumbers.get(3);


                byte[] decodedBytesKey = Base64.getDecoder().decode(aListNumbers.get(2));

                String decodeemri = new String(emri);
                System.out.println("Marresi: " + decodeemri);


                File file = new File("keys/", decodeemri + ".xml");
                if (file.exists()) {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(file);
                    doc.getDocumentElement().normalize();

                    String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
                    String D = doc.getElementsByTagName("D").item(0).getTextContent();

                    BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
                    BigInteger d = new BigInteger(Base64.getDecoder().decode(D));

                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, d);
                    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);


                    Cipher cipher = Cipher.getInstance("RSA");
                    cipher.init(Cipher.DECRYPT_MODE, privateKey);
                    byte[] key = cipher.doFinal(decodedBytesKey);

                    KeySpec myKeySpec = new DESKeySpec(key);
                    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
                    SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


                    String a = aListNumbers.get(3);
                    byte[] byteArray = Base64.getDecoder().decode(a.getBytes());


                    Cipher decryptCipher = Cipher.getInstance("DES");
                    decryptCipher.init(Cipher.DECRYPT_MODE, celesi);
                    //   byte[] utf8 = Base64.getDecoder().decode(aListNumbers.get(3));
                    byte[] dec = decryptCipher.doFinal(byteArray);
                    String decryptedWord = new String(dec);
                    System.out.println("Mesazhi:" + decryptedWord);


                } else {
                    System.out.println("Gabim: Celesi privat '" + file + "' nuk ekziston.");
                }

            } catch (Exception e) {

            }}
            else{
                ArrayList<String> aListNumbers = new ArrayList<String>(Arrays.asList(strings));
                try {

                    byte[] emri = Base64.getDecoder().decode(aListNumbers.get(0));
                    String ivi = aListNumbers.get(1);
                    String encrypti = aListNumbers.get(3);
                    byte [] sender=Base64.getDecoder().decode(aListNumbers.get(4));

                    String senderName=new String(sender);

                    byte[] decodedBytesKey = Base64.getDecoder().decode(aListNumbers.get(2));



                    String decodeemri = new String(emri);
                    System.out.println("Marresi: " + decodeemri);


                    File file = new File("keys/", decodeemri + ".xml");
                    if (file.exists()) {
                        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                        Document doc = docBuilder.parse(file);
                        doc.getDocumentElement().normalize();

                        String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
                        String D = doc.getElementsByTagName("D").item(0).getTextContent();

                        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
                        BigInteger d = new BigInteger(Base64.getDecoder().decode(D));

                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, d);
                        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);


                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE, privateKey);
                        byte[] key = cipher.doFinal(decodedBytesKey);

                        KeySpec myKeySpec = new DESKeySpec(key);
                        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
                        SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


                        String a = aListNumbers.get(3);
                        byte[] byteArray = Base64.getDecoder().decode(a.getBytes());


                        Cipher decryptCipher = Cipher.getInstance("DES");
                        decryptCipher.init(Cipher.DECRYPT_MODE, celesi);
                        //   byte[] utf8 = Base64.getDecoder().decode(aListNumbers.get(3));
                        byte[] dec = decryptCipher.doFinal(byteArray);
                        String decryptedWord = new String(dec);
                        System.out.println("Mesazhi:" + decryptedWord);
                        System.out.println("Derguesi: "+senderName);
                        String nenshkrimi=strings[5];
                        File file2=new File("keys/",senderName+".pub.xml");
                        if (file2.exists())
                        {
                          boolean verifikimi=logIn.verifikoFjala(encrypti,senderName,nenshkrimi);
                           if (verifikimi==true)
                            System.out.println("Nenshkrimi: valid");
                            else System.out.println("Nenshkrimi: jovalid");
                        }
                        else System.out.println("Mungon celesi publik '"+senderName+"'");

                    } else {
                        System.out.println("Gabim: Celesi privat '" + file + "' nuk ekziston.");
                    }

                } catch (Exception e) {

                }
            }
        }
    }
}
