import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;

public class logIn {

    public static void login(String name) throws Exception {

        System.out.println("Token: " + sign(name));
        boolean a = verify(sign(name));
        if (a)
            System.out.println("Mir");

        File tokens = new File("tokens/" + name + ".txt");
        FileOutputStream fileOutputStream = new FileOutputStream(new File(String.valueOf(tokens)));
        byte[] bytes = sign(name).getBytes();
        fileOutputStream.write(bytes);

    }
    public static String sign(String name) throws Exception {

        File file = new File("keys/" + name + ".xml");

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

        long currentTimeMillis = System.currentTimeMillis() + (20 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date resultdate = new Date(currentTimeMillis);
        String string = sdf.format(resultdate);
        String algoritmi = "{'alg':'RS2048'," +
            "'typ':'JWT'" +
            "}";
        String tedhenat = "{'name':'" + name + "'," +
            "'expiration_date':'" + string + "'}";
        String algoritmi_b64 = Base64.getEncoder().encodeToString(algoritmi.getBytes());
        String tedhenat_b64 = Base64.getEncoder().encodeToString(tedhenat.getBytes());




        Signature privateSignature = Signature.getInstance("SHA1withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update((tedhenat).getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        String signi = Base64.getEncoder().encodeToString(signature);
        String komplet = algoritmi_b64 + "." + tedhenat_b64 + "." + signi;
        return komplet;


    }

    public static boolean verify(String signature) throws Exception {
        String[] stringss = signature.split("\\.");
        ArrayList < String > aListNumberss = new ArrayList < String > (Arrays.asList(stringss));
        byte[] tedhenat = Base64.getDecoder().decode(aListNumberss.get(1));
        String data = new String(tedhenat);
        String nenshkrimi = signature.split("\\.")[2];

        String name = data.split("'")[3];



        File file = new File("keys/" + name + ".pub.xml");

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
        publicSignature.update((data).getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(nenshkrimi);

        return publicSignature.verify(signatureBytes);
    }
    //verifiko me fjalen
    public static boolean verifikoFjala(String e_dhena, String name, String nenshkrimi) throws Exception {

        File file = new File("keys/" + name + ".pub.xml");

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
        publicSignature.update((e_dhena).getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(nenshkrimi);

        return publicSignature.verify(signatureBytes);

    }

    public static void statusi(String signature) throws Exception {
        boolean nenshkrim_valid = verify(signature);
        if (nenshkrim_valid) {
            String[] stringss = signature.split("\\.");
            ArrayList < String > aListNumberss = new ArrayList < String > (Arrays.asList(stringss));
            byte[] tedhenat = Base64.getDecoder().decode(aListNumberss.get(1));
            String data = new String(tedhenat);
            String name = data.split("'")[3];
            String dataSkadimit = data.split("'")[7];
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date resultdate = new Date(currentTimeMillis);
            String string = sdf.format(resultdate);


            int compareTO = string.compareTo(dataSkadimit);
            if (compareTO < 0) {
                lidhjameDB.gjeje(name);
                System.out.println("Valid: Po");
                System.out.println("Skadimi: " + dataSkadimit);

            } else System.out.println("Jo Valid");



        } else System.out.println("Jo Valid");
    }

}