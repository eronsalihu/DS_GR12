import org.w3c.dom.Document;

import javax.crypto.Cipher;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;


public class readMessage{
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String message=scanner.next();
        decrypt(message);
    }
    public static void decrypt(String message) {
        String[] strings = message.split("\\.");
        ArrayList<String> aListNumbers = new ArrayList<String>(Arrays.asList(strings));
        try {
            byte[] emri = Base64.getDecoder().decode(aListNumbers.get(0));
            String decodeemri = new String(emri);
            System.out.println(decodeemri);
            File file = new File("C:\\Users\\Lenovo\\IdeaProjects\\exportKey\\src\\test\\keys\\", decodeemri + ".xml");
            if (file.exists()) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(file);
                doc.getDocumentElement().normalize();

                String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
                String D = doc.getElementsByTagName("D").item(0).getTextContent();

                BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
                BigInteger d = new BigInteger(Base64.getDecoder().decode(D));

                RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, d);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] key=cipher.doFinal();
                String encodeToString = String.valueOf(Base64.getDecoder().decode(key));

                Cipher decryptCipher = Cipher.getInstance("DES");
                decryptCipher.init(Cipher.DECRYPT_MODE,privateKey);
                byte[] utf8 = message.getBytes("UTF8");
                byte[] enc = decryptCipher.doFinal(utf8);
                enc = Base64.getDecoder().decode(enc);
                String encryptedWord = String.valueOf(Base64.getDecoder().decode(enc));

            }
            else{
                System.out.println("Gabim: Celesi privat '" + file + "' nuk ekziston.");
            }
        }catch (Exception e){

        }
    }
}
