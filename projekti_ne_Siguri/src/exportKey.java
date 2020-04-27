package test;

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

        Scanner scanner=new Scanner(System.in);
        String type=scanner.next();
        String name=scanner.next();

        File pubxml=new File("C:\\Users\\Lenovo\\IdeaProjects\\exportKey\\src\\test\\keys",name+".pub.xml");
        File xml=new File("C:\\Users\\Lenovo\\IdeaProjects\\exportKey\\src\\test\\keys",name+".xml");


        if ((type.equals("public")&&(pubxml.exists()))) {

            String newname=scanner.next();
            File xmlname=new File(newname);
            xmlname.createNewFile();
            Document pub = convertXMLFileToXMLDocument(pubxml);
            pubxml.delete();
            writeXmlDocumentToXmlFile(pub, xmlname);
            // printExportKey(pub);
            System.out.println("Celesi public u ruajt ne fajllin " + xmlname+".");

        }
        else{
            System.out.println("Gabim: Celesi public "+name+" nuk ekziston.");
        }



        if ((type.equals("private"))&&(xml.exists())) {
            String newname=scanner.next();
            File xmlname=new File(newname);
            xmlname.createNewFile();
            Document pri = convertXMLFileToXMLDocument(xml);
            xml.delete();
            writeXmlDocumentToXmlFile(pri, xmlname);
            // printExportKey(pri);
            // System.out.println("Celesi privat u ruajt ne fajllin " + xmlname+".");
        }
        else {
            System.out.println("Gabim: Celesi privat "+name+" nuk ekziton.");
        }

    }
    private static Document convertXMLFileToXMLDocument(File filePath){


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
}