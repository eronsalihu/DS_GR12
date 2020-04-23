import java.security.*;
import java.math.BigInteger;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class createUser {

    public static void generate_key(String outFile) throws Exception {
        try {

            // creating the object of KeyPairGenerator
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

            // using generateKeyPair() method
            kpg.initialize(2048);

            KeyPair kp = kpg.generateKeyPair();

            PublicKey pub = kp.getPublic();

            PrivateKey pvt = kp.getPrivate();




            xml_private(pvt, outFile);
            xml_public(pub, outFile);



        } catch (NoSuchAlgorithmException e) {

            System.out.println("Exception thrown : " + e);
        }
    }

    public static void xml_private(PrivateKey privateKey, String outFile) {
        try {
            File file = new File("keys/", outFile + ".xml");

            if (!file.exists()) {

                KeyFactory kf = KeyFactory.getInstance("RSA");
                RSAPrivateCrtKeySpec ks = kf.getKeySpec(
                        privateKey, RSAPrivateCrtKeySpec.class);

                //Kthimi ne base64 nga biginteger qysh gjenerohen nga keypair
                BigInteger mod = ks.getModulus();
                String modstr = new String(Base64.getEncoder().encodeToString(mod.toByteArray()));

                BigInteger exp = ks.getPublicExponent();
                String expstr = new String(Base64.getEncoder().encodeToString(exp.toByteArray()));

                BigInteger pi = ks.getPrimeP();
                String pstr = new String(Base64.getEncoder().encodeToString(pi.toByteArray()));

                BigInteger qa = ks.getPrimeQ();
                String qstr = new String(Base64.getEncoder().encodeToString(qa.toByteArray()));

                BigInteger dpa = ks.getPrimeExponentP();
                String dpastr = new String(Base64.getEncoder().encodeToString(dpa.toByteArray()));

                BigInteger dqa = ks.getPrimeExponentQ();
                String dqastr = new String(Base64.getEncoder().encodeToString(dqa.toByteArray()));

                BigInteger inversit = ks.getCrtCoefficient();
                String inversistr = new String(Base64.getEncoder().encodeToString(inversit.toByteArray()));
                BigInteger de = ks.getPrivateExponent();
                String destr = new String(Base64.getEncoder().encodeToString(de.toByteArray()));

                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();


                Document document = documentBuilder.newDocument();
                Element root = document.createElement("RSAKeyValue");
                document.appendChild(root);
                Element moduli = document.createElement("Modulus");
                moduli.appendChild(document.createTextNode(modstr));
                root.appendChild(moduli);
                Element exponenti = document.createElement("Exponent");
                exponenti.appendChild(document.createTextNode(expstr));
                root.appendChild(exponenti);
                Element p = document.createElement("P");
                p.appendChild(document.createTextNode(pstr));
                root.appendChild(p);
                Element q = document.createElement("Q");
                q.appendChild(document.createTextNode(qstr));
                root.appendChild(q);
                Element dp = document.createElement("DP");
                dp.appendChild(document.createTextNode(dpastr));
                root.appendChild(dp);
                Element dq = document.createElement("DQ");
                dq.appendChild(document.createTextNode(dqastr));
                root.appendChild(dq);
                Element inversi = document.createElement("InverseQ");
                inversi.appendChild(document.createTextNode(inversistr));
                root.appendChild(inversi);
                Element d = document.createElement("D");
                d.appendChild(document.createTextNode(destr));
                root.appendChild(d);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(domSource, streamResult);

                System.out.println("Eshte krijuar celesi privat 'keys/" + outFile + ".xml'");
            } else {
                System.out.print("");
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

    public static void xml_public(PublicKey publicKey, String outFile) {


        try {

            File file = new File("keys/", outFile + "pub.xml");

            if (!file.exists()) {
                KeyFactory kf = KeyFactory.getInstance("RSA");
                RSAPublicKeySpec kps = kf.getKeySpec(publicKey, RSAPublicKeySpec.class);

                BigInteger mod = kps.getModulus();
                String modstr = new String(Base64.getEncoder().encodeToString(mod.toByteArray()));

                BigInteger exp = kps.getPublicExponent();
                String expstr = new String(Base64.getEncoder().encodeToString(exp.toByteArray()));



                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

                Document document = documentBuilder.newDocument();

                Element rooti = document.createElement("RSAKeyValue");
                document.appendChild(rooti);
                Element modulu = document.createElement("Modulus");
                modulu.appendChild(document.createTextNode(modstr));
                rooti.appendChild(modulu);
                Element exponentit = document.createElement("Exponent");
                exponentit.appendChild(document.createTextNode(expstr));
                rooti.appendChild(exponentit);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource domSource = new DOMSource(document);

                StreamResult streamResult = new StreamResult(file);
                transformer.transform(domSource, streamResult);
                System.out.println("Eshte krijuar celesi publik 'keys/" + outFile + ".pub.xml'");
            } else {
                System.out.println("Gabim:Celesi " + outFile + " ekziston paraprakisht!");
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }
}