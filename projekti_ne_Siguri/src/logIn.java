import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;

public class logIn {
    public static void main(String[] args) throws InvalidKeySpecException, ParserConfigurationException, NoSuchAlgorithmException, SAXException, IOException {
        login("enis");
    }
   public static void login(String emri) throws ParserConfigurationException, IOException, SAXException, NoSuchAlgorithmException, InvalidKeySpecException {
       File file = new File("keys/", emri + ".xml");
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
       System.out.print(privateKey);




    }

//    private static String generateToken(PrivateKey privateKey) {
//
//    }
}
