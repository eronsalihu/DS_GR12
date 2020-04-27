import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;

import javax.naming.NamingEnumeration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class exportKey {

    public static void main(String[] args) throws IOException {
       
    }
    private static Document XMLFileToXMLDocument(File filePath){


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {

            builder = factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(new File(String.valueOf(filePath)));

            return xmlDocument;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    private static void printExportKey(Document xmlDocument){
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
            String xmlString = writer.getBuffer().toString();
            System.out.println(xmlString);


        } catch (TransformerException e) {
            e.printStackTrace();

        }
    }
    private static void writeXmlDocumentToXmlFile(Document xmlDocument, File fileName){
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();

            FileOutputStream outStream = new FileOutputStream(new File(String.valueOf(fileName)));
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));

        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void export_public_private(String type,String fileName){
        try{
            File pubxml=new File("keys/",fileName+".pub.xml");
            File xml = new File("keys/", fileName + ".xml");

            if (type.equals("public")) {
                if(pubxml.exists()) {
                    Document pub = XMLFileToXMLDocument(pubxml);
                    printExportKey(pub);
                } else{
                    System.out.println("Gabim: Celesi public "+fileName+" nuk ekziston.");
                }
            }
            else{
                if (xml.exists()) {

                Document xmlfile = XMLFileToXMLDocument(xml);
                printExportKey(xmlfile);

            } else {
                System.out.println("Gabim: Celesi privat " + fileName + " nuk ekziston.");
            }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void export_public_private(String type,String fileName,String newFile){
        try{
            File pubxml=new File("keys/",fileName+".pub.xml");
            File xml = new File("keys/", fileName + ".xml");

            if ((pubxml.exists())&&type.equals("public")) {

                File pubxmlname = new File(newFile);
                pubxmlname.createNewFile();
                Document pri = XMLFileToXMLDocument(pubxml);
                pubxml.delete();
                writeXmlDocumentToXmlFile(pri, pubxmlname);
                System.out.println("Celesi public u ruajt ne fajllin " + pubxmlname+".");

            }
            if ((xml.exists())&&type.equals("private")) {

                File xmlname = new File(newFile);
                xmlname.createNewFile();
                Document pri = XMLFileToXMLDocument(xml);
                xml.delete();
                writeXmlDocumentToXmlFile(pri, xmlname);

                System.out.println("Celesi privat u ruajt ne fajllin " + xmlname+".");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
