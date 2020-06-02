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
    public static void login(String name) throws Exception {

        System.out.println("Token: " + sign(name));
        verify(sign(name), name);

        File tokens=new File("/tokens/"+name+".txt");
        FileOutputStream fileOutputStream=new FileOutputStream(new File(String.valueOf(tokens)));
        byte [] bytes=sign(name).getBytes();
        fileOutputStream.write(bytes);

        /*long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date resultdate = new Date(currentTimeMillis);
        //System.out.println(sdf.format(resultdate));

        long minutesPassed = System.currentTimeMillis() - ( 20 * 60 * 1000);
        if (currentTimeMillis<minutesPassed){
            //System.out.println("Token is invalid!");
        }
        else {
            //System.out.println("Token is valid!");
        }*/

    }



    public static String sign(String name) throws Exception {

        File file=new File("/keys/"+name+".xml");

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        doc.getDocumentElement().normalize();

        String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponentAsString = doc.getElementsByTagName("D").item(0).getTextContent();

        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
        BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentAsString));


        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Signature privateSignature = Signature.getInstance("SHA1withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(name.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);

    }

    public static boolean verify(String signature, String name) throws Exception {

        File file=new File("/keys/"+name+".pub.xml");

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        doc.getDocumentElement().normalize();

        String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponentAsString = doc.getElementsByTagName("Exponent").item(0).getTextContent();

        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
        BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentAsString));

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Signature publicSignature = Signature.getInstance("SHA1withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(name.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);

    }
      
}
