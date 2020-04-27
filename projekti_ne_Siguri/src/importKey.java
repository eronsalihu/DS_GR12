import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class importKey {
   
    public static void import_Key(String outFile, String path) throws ParserConfigurationException, IOException, SAXException, InvalidPathException {
        try {
            if (path.matches("^(http|https|)://.*$")) {
                File freshFile = new File("keys/" + outFile + ".pub.xml");
                if (!freshFile.exists()) {
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).build();
                    HttpResponse < String > response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    // System.out.println(response.body());

                    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

                    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();


                    Document document = documentBuilder.newDocument();
                    Element root = document.createElement("RSAKeyValue");
                    root.appendChild(document.createTextNode(response.body()));
                    document.appendChild(root);

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    DOMSource domSource = new DOMSource(document);
                    StreamResult streamResult = new StreamResult(freshFile);
                    transformer.transform(domSource, streamResult);
                    System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + outFile + ".pub.xml'.");

                } else {
                    System.out.println("Gabim:Celesi publik '" + outFile + "' ekziston paraprakisht!");
                }
            } else {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                File file = new File(String.valueOf(path));
                if (file.getName().contains(".xml")) {
                    if (file.exists()) {
                        Document doc = docBuilder.parse(file);
                        doc.getDocumentElement().normalize();


                        int a = doc.getElementsByTagName("RSAKeyValue").item(0).getChildNodes().getLength();
                        NodeList rsan = doc.getElementsByTagName("RSAKeyValue");
                        NodeList expn = doc.getElementsByTagName("Exponent");
                        NodeList modn = doc.getElementsByTagName("Modulus");
                        NodeList pn = doc.getElementsByTagName("P");
                        NodeList qn = doc.getElementsByTagName("Q");
                        NodeList dpn = doc.getElementsByTagName("DP");
                        NodeList dqn = doc.getElementsByTagName("DQ");
                        NodeList invn = doc.getElementsByTagName("InverseQ");
                        NodeList dn = doc.getElementsByTagName("D");
                        int rsa = rsan.getLength();
                        int mod = modn.getLength();
                        int exp = expn.getLength();
                        int p = pn.getLength();
                        int q = qn.getLength();
                        int dp = dpn.getLength();
                        int dq = dqn.getLength();
                        int inv = invn.getLength();
                        int d = dn.getLength();

                        if (rsa == 1) {

                            if (a == 5 && mod == 1 && exp == 1 && p == 0 && q == 0 && dp == 0 && dq == 0 && inv == 0 && d == 0) {

                                File freshFile = new File("src/keys/", outFile + ".pub.xml");
                                if (freshFile.exists()) {
                                    System.out.println("Celesi '" + outFile + "' ekziston paraprakisht");
                                } else {
                                    file.renameTo(freshFile);
                                    System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + outFile + ".pub.xml'.");
                                }
                            } else if (a == 17 && mod == 1 && exp == 1 && p == 1 && q == 1 && dp == 1 && dq == 1 && inv == 1 && d == 1) {
                                File freshFile = new File("src/keys/" + outFile + ".pub.xml");
                                File privFile = new File("src/keys/" + outFile + ".xml");
                                if (privFile.exists()) {
                                    System.out.println("Celesi '" + outFile + "' ekziston paraprakisht");
                                }

                                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

                                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

                                Document document = documentBuilder.newDocument();
                                Element rooti = document.createElement("RSAKeyValue");
                                document.appendChild(rooti);
                                Element modulu = document.createElement("Modulus");
                                modulu.appendChild(document.createTextNode(doc.getElementsByTagName("Modulus").item(0).getTextContent()));
                                rooti.appendChild(modulu);
                                Element exponentit = document.createElement("Exponent");
                                exponentit.appendChild(document.createTextNode(doc.getElementsByTagName("Exponent").item(0).getTextContent()));
                                rooti.appendChild(exponentit);

                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                Transformer transformer = transformerFactory.newTransformer();
                                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                                DOMSource domSource = new DOMSource(document);

                                StreamResult streamResult = new StreamResult(freshFile);
                                transformer.transform(domSource, streamResult);

                                file.renameTo(privFile);

                                System.out.println("Celesi privat u ruajt ne fajllin 'keys/" + outFile + ".xml'.");
                                System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + outFile + ".pub.xml'.");
                            }



                        }
                    } else if (!file.exists()) {
                        System.out.println("File i dhene ne kete direktorium nuk ekziston");
                    }
                } else {
                    System.out.println("Gabim: Fajlli i dhene nuk eshte celes valid.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
